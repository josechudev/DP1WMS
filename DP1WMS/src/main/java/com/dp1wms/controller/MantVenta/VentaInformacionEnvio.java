package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.*;
import com.dp1wms.util.DateParser;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class VentaInformacionEnvio implements FxmlController{


    private Envio envio;

    @FXML private TextField destinoTF;
    @FXML private DatePicker fechaEnvioDP;

    @FXML private TableView<DetallePedido> prodDisponiblesTable;
    @FXML private TableColumn<DetallePedido, String> codigoDispTC;
    @FXML private TableColumn<DetallePedido, String> nombreDispTC;
    @FXML private TableColumn<DetallePedido, Integer> disponibleTC;

    @FXML private TableView<DetalleEnvio> envioTable;
    @FXML private TableColumn<DetalleEnvio, String>  codigoEnvioTC;
    @FXML private TableColumn<DetalleEnvio, String>  nombreEnvioTC;
    @FXML private TableColumn<DetalleEnvio, Integer>  asignadoEnvioTC;

    @FXML private Label costoFleteLabel;

    @FXML private Button guardarBtn;
    @FXML private Button agregarBtn;

    private ArrayList<DetallePedido> productosDisponibles;
    private ArrayList<DetalleEnvio> detalleEnvios;


    private StageManager stageManager;
    private VentaPedido ventaPedido;


    @FXML
    private void agregarTodosProductos(){

    }

    @FXML
    private void agregarUnProducto(){

    }

    @FXML
    private void removeUnProducto(){

    }

    @FXML
    private void removerTodosProductos(){

    }

    @FXML
    private void agregarEnvio(ActionEvent event){
    }

    @FXML
    private void guardarEnvio(ActionEvent event){

    }

    @FXML
    private void cerrarVentana(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    @Override
    public void initialize(){
        this.detalleEnvios = new ArrayList<>();
        this.limpiarTablaDisponibles();
        this.limpiarTablaAsignados();

        //buttons
        this.agregarBtn.managedProperty().bind(agregarBtn.visibleProperty());
        this.guardarBtn.managedProperty().bind(agregarBtn.visibleProperty());

        if(this.envio == null){ //nuevo envio
            //buttons
            this.agregarBtn.setDisable(false);
            this.agregarBtn.setVisible(true);

            this.guardarBtn.setDisable(true);
            this.guardarBtn.setVisible(false);
        } else {
            //buttons
            this.agregarBtn.setDisable(true);
            this.agregarBtn.setVisible(false);

            this.guardarBtn.setDisable(false);
            this.guardarBtn.setVisible(true);

            //init campos
            this.destinoTF.setText(this.envio.getDestino());
            this.fechaEnvioDP.setValue(DateParser.timestampToLocaldate(this.envio.getFechaEnvio()));

            //init tabla envio
            for(DetalleEnvio de: this.envio.getDetalleEnvio()){
                this.detalleEnvios.add(new DetalleEnvio(de));
            }
            this.envioTable.getItems().addAll(this.detalleEnvios);

            //init costo flete
            this.costoFleteLabel.setText(String.valueOf(this.envio.getCostoFlete()));
        }

        this.initProductosDisponibles();
    }

    private void initProductosDisponibles(){
        this.productosDisponibles = new ArrayList<DetallePedido>();

        Pedido pedido = this.ventaPedido.getPedido();

        ArrayList<Envio> envios = this.ventaPedido.getEnvios();

        //crear nuevos detalles proforma
        for(DetallePedido dp: pedido.getDetalles()){
            DetallePedido dpNew = new DetallePedido();
            int cantidad = dp.getCantidad();
            Producto producto = dp.getProducto();

            for(Envio e: envios){
                //buscar el producto
                for(DetalleEnvio de: e.getDetalleEnvio()){
                    if(de.getProducto().getIdProducto() == producto.getIdProducto()){
                        cantidad -= de.getCantidad();
                        break;
                    }
                }
            }

            dpNew.setProducto(dp.getProducto());
            dpNew.setCantidad(cantidad);
            this.productosDisponibles.add(dpNew);
        }

        //remove detalles con cantidad 0
        this.productosDisponibles.removeIf(dp -> dp.getCantidad() <= 0);
        this.prodDisponiblesTable.getItems().addAll(this.productosDisponibles);
    }

    private void limpiarTablaDisponibles(){
        this.prodDisponiblesTable.getItems().clear();
        this.codigoDispTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getProducto().getCodigo());
        });
        this.nombreDispTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getProducto().getNombreProducto());
        });
        this.disponibleTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Integer>(value.getValue().getCantidad());
        });
    }

    private void limpiarTablaAsignados(){
        this.envioTable.getItems().clear();
        this.codigoEnvioTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getProducto().getCodigo());
        });
        this.nombreEnvioTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getProducto().getNombreProducto());
        });
        this.asignadoEnvioTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Integer>(value.getValue().getCantidad());
        });
    }

    @Autowired @Lazy
    public VentaInformacionEnvio(StageManager stageManager, VentaPedido ventaPedido){
        this.stageManager = stageManager;
        this.ventaPedido = ventaPedido;
    }

    public Envio getEnvio(){
        return  this.envio;
    }

    public void setEnvio(Envio envio){
        this.envio = envio;
    }
}
