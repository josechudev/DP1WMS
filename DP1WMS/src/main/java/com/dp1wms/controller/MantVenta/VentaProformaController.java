package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryCondicion;
import com.dp1wms.dao.RepositoryProforma;
import com.dp1wms.model.*;
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
public class VentaProformaController implements FxmlController{

    @FXML private Label codigoLabel;
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
    public VentaProformaController(StageManager stageManager, MainController mainController){
        this.stageManager = stageManager;
        this.mainController = mainController;
    }

    @FXML
    private void mostrarBusquedaCliente(){
        this.stageManager.mostrarModal(VentasView.BUSCAR_CLIENTE);
    }

    @FXML
    private void mostrarRegistrarCliente(){
        this.stageManager.mostrarModal(VentasView.REGISTRAR_CLIENTE);
    }

    @FXML
    private void mostrarBusquedaProducto(){
        this.stageManager.mostrarModal(VentasView.VENTA_BUSCAR_PROD);
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        this.codigoLabel.setText(String.valueOf(cliente.getIdCliente()));
        this.nombreLabel.setText(cliente.getRazonSocial());
        this.rucLabel.setText(cliente.getNumDoc());
        this.telefonoLabel.setText(cliente.getTelefono());
        this.emailLabel.setText(cliente.getEmail());
        this.direccionLabel.setText(cliente.getDireccion());
    }

    public void agregarProducto(Producto producto, int cantidad){
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
                        "Debes ingreasr un valor válido");
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
        List<Condicion> condicions = this.repositoryCondicion.obtenerCondicionesActivos();
        this.aplicarDescuentos(condicions, this.proforma);
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
            this.stageManager.mostrarInfonDialog("Proforma", null, "Se registró satisfactoriamente");
            this.cerrarVentana(event);
        }
    }

    private void aplicarDescuentos(List<Condicion> condicions, Proforma proforma){

        for(DetalleProforma dp: proforma.getDetallesProforma()){
            dp.setDescuento(0);
        }

        //Eliminar condicions no válidos
        ArrayList<Condicion> descValidos = this.descuentosValidos(condicions, proforma);

        //aplicar prioridades a los condicions
        this.orderDescuentos(descValidos);

        //aplicar condicions por porcentaje primero
        this.aplicarDescuentosPorPorcentaje(descValidos, proforma);

        //aplicar condicions restantes
        for(Condicion desc: descValidos){
            if(desc.getPrioridad() < 7){
                for(DetalleProforma dp: proforma.getDetallesProforma()){
                    if(dp.getDescuento() <= 0){//aplica
                        Producto p = dp.getProducto();
                        if(desc.getIdProductoDescuento() == p.getIdProducto() ||
                           desc.getIdCategoriaProdDesc() == p.getIdCategoria()){
                            dp.setDescuento((float) (dp.getCantidad() * p.getPrecio() * desc.getValorDescuento()));
                        }
                    }
                }
            }
        }
    }

    private ArrayList<Condicion> descuentosValidos(List<Condicion> condicions, Proforma proforma){
        ArrayList<Condicion> descValidos = new ArrayList<>();
        for(Condicion desc: condicions){
            boolean valido = false;
            switch(desc.getDescripcion()){
                case Condicion.DESC_P:{//por porcentaje
                    if(desc.getIdProductoDescuento() > 0){ //por producto
                        DetalleProforma detalleProf = null;
                        for(DetalleProforma dp: proforma.getDetallesProforma()){
                            if(dp.getProducto().getIdProducto() == desc.getIdProductoDescuento()){
                                detalleProf = dp;
                                break;
                            }
                        }
                        valido = (detalleProf != null);                    }
                    else if(desc.getIdCategoriaProdDesc() > 0){
                        ArrayList<DetalleProforma> variosDP = new ArrayList<>();
                        for(DetalleProforma dp: proforma.getDetallesProforma()){
                            if(dp.getProducto().getIdCategoria() == desc.getIdCategoriaProdDesc()){
                                variosDP.add(dp);
                            }
                        }
                        valido = (variosDP.size() > 0);
                    }
                    break;
                }
                default:{//por cantidad
                    int cantidadGenerador = 0;
                    if(desc.getIdProductoGenerador() > 0){//por producto
                        cantidadGenerador = obtenerCantidadPorIdProd(proforma, desc.getIdProductoGenerador());
                    } else {//por categoria
                        cantidadGenerador = obtenerCantidadPorIdCat(proforma, desc.getIdCategoriaProdGen());
                    }
                    int cantidadTarget = 0;
                    if(desc.getIdProductoDescuento() > 0){
                        cantidadTarget = obtenerCantidadPorIdProd(proforma, desc.getIdProductoDescuento());
                    } else {
                        cantidadTarget = obtenerCantidadPorIdCat(proforma, desc.getIdCategoriaProdGen());
                    }
                    valido = (cantidadGenerador >= desc.getCantProdGen() && cantidadTarget >= desc.getCantProdDesc());
                    break;
                }
            }
            if(valido){
                descValidos.add(desc);
            }
        }
        return descValidos;
    }

    private int obtenerCantidadPorIdProd(Proforma proforma, int idProducto){
        int cantidad = 0;
        for(DetalleProforma dp: proforma.getDetallesProforma()){
            if(dp.getProducto().getIdProducto() == idProducto){
                cantidad = dp.getCantidad();
                break;
            }
        }
        return cantidad;
    }

    private int obtenerCantidadPorIdCat(Proforma proforma, int idCategoria){
        int cantidad = 0;
        for(DetalleProforma dp: proforma.getDetallesProforma()){
            if(dp.getProducto().getIdCategoria() == idCategoria){
                cantidad += dp.getCantidad();
            }
        }
        return cantidad;
    }

    private void orderDescuentos(ArrayList<Condicion> condicions){
        //asignar prioridades
        for(Condicion desc: condicions){
            switch (desc.getDescripcion()){
                case Condicion.DESC_P:{//por porcentaje
                    desc.setPrioridad(7);
                    break;
                }
                case Condicion.DESC_C:{//por cantidad (3x2)
                    if(desc.getIdCategoriaProdGen() > 0){ //categoria de producto
                        desc.setPrioridad(6);
                    } else { //producto
                        desc.setPrioridad(5);
                    }
                    break;
                }
                case Condicion.DESC_B:{//bonificacion por especie
                    if(desc.getIdCategoriaProdGen() > 0 && desc.getIdCategoriaProdGen() > 0){
                        desc.setPrioridad(4);
                    } else if (desc.getIdCategoriaProdGen() > 0 && desc.getIdProductoDescuento() > 0){
                        desc.setPrioridad(3);
                    } else if (desc.getIdProductoGenerador() > 0 && desc.getIdProductoDescuento() > 0){
                        desc.setPrioridad(2);
                    } else {
                        desc.setPrioridad(1);
                    }
                    break;
                }
            }
        }
        //ordenar
        Collections.sort(condicions, new Comparator<Condicion>() {
            @Override
            public int compare(Condicion o1, Condicion o2) {
                return o2.getPrioridad() - o1.getPrioridad();
            }
        });
    }

    private void aplicarDescuentosPorPorcentaje(ArrayList<Condicion> condicions, Proforma proforma){
        for(DetalleProforma dp: proforma.getDetallesProforma()){
            ArrayList<Float> complementoDesc = new ArrayList<>();
            Producto p = dp.getProducto();
            for(Condicion desc: condicions){
                if(desc.getPrioridad() < 7){
                    break;
                }
                if(desc.getIdProductoGenerador() == p.getIdProducto() || desc.getIdCategoriaProdGen() == p.getIdProducto()){
                    complementoDesc.add(new Float(1.0 - desc.getValorDescuento()));
                }
            }
            if(complementoDesc.size() > 0){
                float comDescTotal = 1;
                for(Float cd: complementoDesc){
                    comDescTotal *= cd.floatValue();
                }
                float descTotal = 1 - comDescTotal;
                dp.setDescuento(dp.getCantidad() * p.getPrecio() * descTotal);
            }
        }
    }
}
