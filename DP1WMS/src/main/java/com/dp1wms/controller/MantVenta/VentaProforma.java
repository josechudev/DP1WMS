package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.controller.MantCliente.ClienteInfoController;
import com.dp1wms.dao.RepositoryCondicion;
import com.dp1wms.dao.RepositoryProforma;
import com.dp1wms.model.*;
import com.dp1wms.util.DescuentoAlgoritmo;
import com.dp1wms.view.ClientesView;
import com.dp1wms.view.StageManager;
import com.dp1wms.view.VentasView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class VentaProforma implements FxmlController{

    @FXML private Label nombreLabel;
    @FXML private Label telefonoLabel;
    @FXML private Label emailLabel;
    @FXML private Label rucLabel;
    @FXML private Label direccionLabel;

    @FXML private TableView<DetalleProforma> proformaTable;
    @FXML private TableColumn<DetalleProforma, String> codigoTC;
    @FXML private TableColumn<DetalleProforma, String> productoTC;
    @FXML private TableColumn<DetalleProforma, Float> precioTC;
    @FXML private TableColumn<DetalleProforma, Integer> cantidadTC;
    @FXML private TableColumn<DetalleProforma, Float> descuentoTC;
    @FXML private TableColumn<DetalleProforma, Float> subtotalTC;

    @FXML private TextArea observacionTA;
    @FXML private Label totalLabel;


    private Cliente cliente;
    private Proforma proforma;

    private StageManager stageManager;
    private MainController mainController;
    private ClienteInfoController clienteInfoController;
    private VentaBuscarCliente ventaBuscarCliente;
    private VentaBuscarProducto ventaBuscarProducto;

    @Autowired private RepositoryProforma repositoryProforma;
    @Autowired private RepositoryCondicion repositoryCondicion;

    @Override
    public void initialize(){
        this.cliente = null;
        this.proforma = new Proforma();
        this.proforma.setIdEmpleado(this.mainController.getEmpleado().getIdempleado());
        this.limpiarTablaProforma();
    }

    @Autowired @Lazy
    public VentaProforma(StageManager stageManager, MainController mainController,
                         ClienteInfoController clienteInfoController,
                         VentaBuscarCliente ventaBuscarCliente,
                         VentaBuscarProducto ventaBuscarProducto){
        this.stageManager = stageManager;
        this.mainController = mainController;
        this.clienteInfoController = clienteInfoController;
        this.ventaBuscarCliente = ventaBuscarCliente;
        this.ventaBuscarProducto = ventaBuscarProducto;
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

    private void completarCamposClientes(){
        this.nombreLabel.setText(cliente.getRazonSocial());
        this.rucLabel.setText(cliente.getNumDoc());
        this.telefonoLabel.setText(cliente.getTelefono());
        this.emailLabel.setText(cliente.getEmail());
        this.direccionLabel.setText(cliente.getDireccion());
    }

    private void agregarProducto(Producto producto, int cantidad){
        this.proforma.agregarProducto(producto, cantidad);
        this.llenarTablaProforma();
    }

    @FXML
    private void modificarCantidadDetalle(){
        DetalleProforma dp = this.proformaTable.getSelectionModel().getSelectedItem();
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
            this.llenarTablaProforma();
        }
    }

    @FXML
    private void eliminarDetalle(){
        DetalleProforma de = this.proformaTable.getSelectionModel().getSelectedItem();
        if(de == null){
            this.stageManager.mostrarErrorDialog("Error", null, "No ha seleccionado ningún item de la proforma");
        } else {
            this.proforma.eliminarDetalleProforma(de);
            this.llenarTablaProforma();
        }
    }

    @FXML
    private void limpiarTablaProforma(){
        this.proformaTable.getItems().clear();
        this.codigoTC.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getProducto().getCodigo());
        });
        this.productoTC.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getProducto().getNombreProducto());
        });
        this.precioTC.setCellValueFactory(value -> {
            return new SimpleObjectProperty<Float>(value.getValue().getProducto().getPrecio());
        });
        this.cantidadTC.setCellValueFactory(new PropertyValueFactory<DetalleProforma, Integer>("cantidad"));
        this.descuentoTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Float>(value.getValue().getDescuento());
        });
        this.subtotalTC.setCellValueFactory(value -> {
            return new SimpleObjectProperty<Float>(value.getValue().getSubtotal());
        });
    }

    private void llenarTablaProforma(){
        this.limpiarTablaProforma();
        List<Condicion> condiciones = this.repositoryCondicion.obtenerCondicionesActivos();
        DescuentoAlgoritmo.aplicarDescuento(condiciones, this.proforma);
        this.proforma.calcularTotal();
        this.proformaTable.getItems().addAll(this.proforma.getDetallesProforma());
        this.totalLabel.setText(String.valueOf(this.proforma.getTotal()));
    }


    @FXML private void cerrarVentana(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    @FXML private void registrarProforma(ActionEvent event) {
        this.proforma.setObservaciones(this.observacionTA.getText());

        if(this.proforma.getDetallesProforma().size() == 0){
            this.stageManager.mostrarErrorDialog("Error proforma", null,
                    "Debe registrar al menos un producto");
            return;
        }
        if(this.cliente == null){
            this.stageManager.mostrarErrorDialog("Error proforma", null,
                    "Debe seleccionar un cliente");
            return;
        } else {
            this.proforma.setIdCliente(this.cliente.getIdCliente());
        }

        boolean res = false;
        try {
            res = this.repositoryProforma.registrarProforma(this.proforma);
        } catch (Exception e ){
            res = false;
        }
        if(!res){
            this.stageManager.mostrarErrorDialog("Error registrar proforma", null,
                    "Hubo un error al intentar registrar la proforma. " +
                            "Inténtelo otra vez.");
        } else {
            this.stageManager.mostrarInfoDialog("Proforma", null, "Se registró satisfactoriamente");
            this.cerrarVentana(event);
        }
    }
}
