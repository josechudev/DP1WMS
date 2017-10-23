package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.model.*;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import com.dp1wms.view.VentasView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class VentaProformaController implements FxmlController{

    @FXML private Label codigoLabel;
    @FXML private Label nombreLabel;
    @FXML private Label telefonoLabel;
    @FXML private Label emailLabel;
    @FXML private Label rucLabel;
    @FXML private Label direccionLabel;

    @FXML private TableView<DetalleProforma> proformaTable;
    @FXML private TableColumn<DetalleProforma, String> profCodigoTC;
    @FXML private TableColumn<DetalleProforma, String> profProductoTC;
    @FXML private TableColumn<DetalleProforma, Float> profPrecioTC;
    @FXML private TableColumn<DetalleProforma, Integer> profCantidadTC;
    @FXML private TableColumn<DetalleProforma, Integer> profNoAsignadoTC;
    @FXML private TableColumn<DetalleProforma, Float> profSubtotalTC;
    @FXML private TableColumn<DetalleProforma, Float> profDsctoTC;

    @FXML private TableView enviosTable;


    private Cliente cliente;
    private Proforma proforma;
    private ArrayList<Envio> envios;

    private final StageManager stageManager;
    private final MainController mainController;

    @Override
    public void initialize(){
        this.cliente = null;
        this.proforma = new Proforma();
        this.envios = new ArrayList<Envio>();

        this.limpiarProforma();
    }

    @Autowired @Lazy
    public VentaProformaController(StageManager stageManager, MainController mainController){
        this.stageManager = stageManager;
        this.mainController = mainController;
    }

    @FXML
    private void mostrarBusquedaCliente(){
        this.stageManager.mostrarModal(MainView.BUSCAR_CLIENTE);
    }

    @FXML
    private void mostrarRegistrarCliente(){
        this.stageManager.mostrarModal(MainView.REGISTRAR_CLIENTE);
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

    public void agregarProductoAProforma(Producto producto, int cantidad){
        DetalleProforma dp = this.proforma.agregarProducto(producto, cantidad);
        if(dp == null){
            this.limpiarProforma();
            this.proformaTable.getItems().addAll(this.proforma.getDetallesProforma());
        } else {
            this.proformaTable.getItems().add(dp);
        }
    }

    @FXML
    private void eliminarDetalleProforma(){
        DetalleProforma dp = this.proformaTable.getSelectionModel().getSelectedItem();
        int index = this.proformaTable.getSelectionModel().getSelectedIndex();
        if(dp == null){
            this.stageManager.mostrarErrorDialog("Error", null, "No ha seleccionado ningún item de la proforma");
        } else {
            this.proforma.eliminarDetalleProforma(dp);
            this.proformaTable.getItems().remove(index);
        }
    }

    private void limpiarProforma(){
        this.proformaTable.getItems().clear();
        this.profCodigoTC.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getProducto().getCodigo());
        });
        this.profProductoTC.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getProducto().getNombreProducto());
        });
        this.profPrecioTC.setCellValueFactory(value -> {
            return new SimpleObjectProperty<Float>(value.getValue().getProducto().getPrecio());
        });
        this.profCantidadTC.setCellValueFactory(new PropertyValueFactory<DetalleProforma, Integer>("cantidad"));
        this.profNoAsignadoTC.setCellValueFactory(new PropertyValueFactory<DetalleProforma, Integer>("cantidadSinAsignar"));
        this.profSubtotalTC.setCellValueFactory(new PropertyValueFactory<DetalleProforma, Float>("subTotal"));
        this.profDsctoTC.setCellValueFactory(new PropertyValueFactory<DetalleProforma, Float>("descuento"));
    }
}
