package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.controller.MantCliente.ClienteInfoController;
import com.dp1wms.dao.RepositoryCondicion;
import com.dp1wms.dao.RepositoryMantPedido;
import com.dp1wms.model.*;
import com.dp1wms.util.DateParser;
import com.dp1wms.util.DescuentoAlgoritmo;
import com.dp1wms.view.ClientesView;
import com.dp1wms.view.StageManager;
import com.dp1wms.view.VentasView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class VentaPedido implements FxmlController {

    @FXML private Label nombreLabel;
    @FXML private Label telefonoLabel;
    @FXML private Label emailLabel;
    @FXML private Label rucLabel;
    @FXML private Label direccionLabel;

    @FXML private TableView<DetallePedido> pedidoTable;
    @FXML private TableColumn<DetallePedido, String> codigoTC;
    @FXML private TableColumn<DetallePedido, String> productoTC;
    @FXML private TableColumn<DetallePedido, Float> precioTC;
    @FXML private TableColumn<DetallePedido, Integer> cantidadTC;
    @FXML private TableColumn<DetallePedido, Float> descuentoTC;
    @FXML private TableColumn<DetallePedido, Float> subtotalTC;

    @FXML private TableView<Envio> enviosTable;
    @FXML private TableColumn<Envio, String> destinoTC;
    @FXML private TableColumn<Envio, String> fechaEnvioTC;
    @FXML private TableColumn<Envio, Float> costoFleteTC;


    @FXML private TextArea observacionTA;
    @FXML private Label subtotalLabel;
    @FXML private Label totalFleteLabel;
    @FXML private Label totalLabel;

    private Cliente cliente;
    private Pedido pedido;
    private ArrayList<Envio> envios;

    private StageManager stageManager;
    private MainController mainController;
    private ClienteInfoController clienteInfoController;
    private VentaBuscarCliente ventaBuscarCliente;
    private VentaBuscarProducto ventaBuscarProducto;
    private VentaBuscarProforma ventaBuscarProforma;
    private VentaInformacionEnvio ventaInformacionEnvio;

    @Autowired private RepositoryCondicion repositoryCondicion;
    @Autowired private RepositoryMantPedido repositoryMantPedido;

    @Override
    public void initialize(){
        this.cliente = null;
        this.pedido = new Pedido();
        this.envios = new ArrayList<>();
        this.limpiarTablaPedido();
        this.limpiarTablaEnvios();
    }

    @FXML
    private void mostrarBusquedaCliente(){
        this.stageManager.mostrarModal(VentasView.BUSCAR_CLIENTE);

        Cliente c = this.ventaBuscarCliente.getCliente();
        if(c!=null){
            this.cliente = new Cliente(c);
            this.completarCamposClientes();
        }
    }

    @FXML
    private void mostrarRegistrarCliente(){
        this.clienteInfoController.setCliente(null);

        this.stageManager.mostrarModal(ClientesView.INFO);

        Cliente c = this.clienteInfoController.getCliente();
        if(c != null){
            this.cliente = new Cliente(c);
            this.completarCamposClientes();
        }
    }

    @FXML
    private void mostrarBusquedaProducto(){
        this.stageManager.mostrarModal(VentasView.BUSCAR_PROD);
        Producto p = this.ventaBuscarProducto.getProducto();
        int cantidad = this.ventaBuscarProducto.getCantidad();
        if(p != null && cantidad > 0){
            this.agregarProducto(p, cantidad);
        }

    }

    private void agregarProducto(Producto producto, int cantidad){
        this.pedido.agregarProducto(producto, cantidad);
        this.llenarTablaPedido();
    }

    @FXML
    private void modificarCantidadDetalle(){
        DetallePedido dp = this.pedidoTable.getSelectionModel().getSelectedItem();
        if (dp == null){
            this.stageManager.mostrarErrorDialog("Error", null, "No ha seleccionado ningún item de la proforma");
        } else {
            Producto p = dp.getProducto();
            TextInputDialog dialog = new TextInputDialog(String.valueOf(dp.getCantidad()));
            dialog.setTitle("Confirmación");
            dialog.setHeaderText("Producto: " + p.getNombreProducto());
            dialog.setContentText("Ingrese la cantidad:");
            ButtonType ok = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().setAll(ok, cancelar);
            Optional<String> result = dialog.showAndWait();
            int cantidad = 0;
            if(result.isPresent()){
                try{
                    cantidad = Integer.parseInt(result.get());
                } catch (NumberFormatException e){
                    cantidad = 0;
                }
            }
            if(cantidad > 0 ){
                dp.setCantidad(cantidad);
            } else {//show error
                this.stageManager.mostrarErrorDialog("Error cantidad de producto",null,
                        "Debes ingresar un valor válido");
            }
            this.llenarTablaPedido();
        }
    }


    private void completarCamposClientes(){
        this.nombreLabel.setText(cliente.getRazonSocial());
        this.rucLabel.setText(cliente.getNumDoc());
        this.telefonoLabel.setText(cliente.getTelefono());
        this.emailLabel.setText(cliente.getEmail());
        this.direccionLabel.setText(cliente.getDireccion());
    }

    @FXML
    private void eliminarProducto(){
        DetallePedido dp = this.pedidoTable.getSelectionModel().getSelectedItem();
        if(dp == null){
            this.stageManager.mostrarErrorDialog("Error", null,
                    "No ha seleccionado ningún item del pedido");
        } else {
            this.pedido.eliminarDetalle(dp);
            this.llenarTablaPedido();
        }
    }

    @FXML
    private void eliminarTodoProducto(){
        this.pedido.setDetalles(new ArrayList<>());
        this.llenarTablaPedido();
    }

    private void limpiarTablaPedido(){
        this.pedidoTable.getItems().clear();
        this.codigoTC.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getCodigoProducto());
        });
        this.productoTC.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getProducto().getNombreProducto());
        });
        this.precioTC.setCellValueFactory(value -> {
            return new SimpleObjectProperty<Float>(value.getValue().getPrecioUnitario());
        });
        this.cantidadTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Integer>(value.getValue().getCantidad());
        });
        this.descuentoTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Float>(value.getValue().getDescuento());
        });
        this.subtotalTC.setCellValueFactory(value -> {
            return new SimpleObjectProperty<Float>(value.getValue().getSubtotal());
        });
    }

    private void llenarTablaPedido(){
        this.limpiarTablaPedido();
        List<Condicion> condiciones = this.repositoryCondicion.obtenerCondicionesActivos();
        DescuentoAlgoritmo.aplicarDescuento(condiciones, this.pedido);
        this.pedido.calcularsubTotal();
        this.pedido.calcularTotal();
        this.pedidoTable.getItems().addAll(this.pedido.getDetalles());

        this.subtotalLabel.setText(String.valueOf(this.pedido.getSubtotal()));
        this.costoFleteTC.setText(String.valueOf(this.pedido.getCostoflete()));
        this.totalLabel.setText(String.valueOf(this.pedido.getTotal()));
    }

    @FXML private void cerrarVentana(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    @FXML private void registrarPedido(ActionEvent event){
        this.pedido.setObservaciones(this.observacionTA.getText());

        if(this.pedido.getCantidadDetalle() == 0){
            this.stageManager.mostrarErrorDialog("Error pedido", null,
                    "Debe registrar al menos un producto");
            return;
        }
        if(this.cliente == null){
            this.stageManager.mostrarErrorDialog("Error pedido", null,
                    "Debe seleccionar un cliente");
            return;
        } else {
            this.pedido.setIdCliente(this.cliente.getIdCliente());
            this.pedido.setCliente(this.cliente);
        }
        boolean res = false;
        try{
            res = this.repositoryMantPedido.registrarPedido(this.pedido);
        } catch(Exception e){
            res = false;
        }
        if(!res){
            this.stageManager.mostrarErrorDialog("Error registrar pedido", null,
                    "Hubo un error al intentar registrar el pedido. " +
                            "Inténtelo otra vez.");
        } else {
            this.stageManager.mostrarInfoDialog("Pedido", null,
                    "Se registró satisfactoriamente. Continue seleccionado los envios.");
            this.cerrarVentana(event);
        }
    }

    @FXML
    private void buscarProforma(){
        this.stageManager.mostrarModal(VentasView.BUSCAR_PROFORMA);
        Proforma proforma = this.ventaBuscarProforma.getProforma();
        if(proforma != null){
            convetirProformaAPedido(proforma);
            this.llenarTablaPedido();
        }
    }

    private void convetirProformaAPedido(Proforma proforma){
        this.pedido = new Pedido();
        for(DetalleProforma dp: proforma.getDetallesProforma()){
            pedido.agregarProducto(dp.getProducto(), dp.getCantidad());
        }
        this.cliente = proforma.getCliente();
        this.completarCamposClientes();
    }

    private void limpiarTablaEnvios(){
        this.enviosTable.getItems().clear();
        this.destinoTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getDestino());
        });
        this.fechaEnvioTC.setCellValueFactory(value->{
            Timestamp time = value.getValue().getFechaEnvio();
            return new SimpleStringProperty(DateParser.timestampToString(time));
        });
        this.costoFleteTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Float>(value.getValue().getCostoFlete());
        });
    }
    private void llenarTablaEnvios(){
        this.limpiarTablaPedido();
        this.enviosTable.getItems().addAll(this.envios);
    }

    @FXML
    private void agregarEnvio(){
        this.ventaInformacionEnvio.setEnvio(null);
        this.stageManager.mostrarModal(VentasView.INFO_ENVIO);
    }

    @FXML
    private void modificarEnvio(){
        Envio envio = this.enviosTable.getSelectionModel().getSelectedItem();
        if(envio == null){
            this.stageManager.mostrarErrorDialog("Error Pedido", null,
                    "Debe seleccionar un pedido");
        } else {
            this.ventaInformacionEnvio.setEnvio(new Envio(envio));
            this.stageManager.mostrarModal(VentasView.INFO_ENVIO);
            Envio envioAux = this.ventaInformacionEnvio.getEnvio();
            if(envioAux != null){
                envio.copiar(envioAux);
                this.llenarTablaEnvios();
            }
        }
    }

    @FXML
    private void verificarEnvios(){

    }

    @FXML
    private void eliminarEnvio(){

    }

    @FXML
    private void eliminarTodoEnvios(){

    }

    @Autowired @Lazy
    public VentaPedido(StageManager stageManager, MainController mainController,
                       ClienteInfoController clienteInfoController,
                       VentaBuscarCliente ventaBuscarCliente,
                       VentaBuscarProducto ventaBuscarProducto,
                       VentaBuscarProforma ventaBuscarProforma,
                       VentaInformacionEnvio ventaInformacionEnvio){
        this.stageManager = stageManager;
        this.mainController = mainController;
        this.clienteInfoController = clienteInfoController;
        this.ventaBuscarCliente = ventaBuscarCliente;
        this.ventaBuscarProducto = ventaBuscarProducto;
        this.ventaBuscarProforma = ventaBuscarProforma;
        this.ventaInformacionEnvio = ventaInformacionEnvio;
    }

    public Pedido getPedido(){
        return  this.pedido;
    }

    public ArrayList<Envio> getEnvios(){
        return this.envios;
    }
}
