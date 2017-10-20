package com.dp1wms.controller;

import com.dp1wms.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
@Component
public class IngresoProducto implements Initializable {

    @FXML
    private TextField txb_nombreProd;
    @FXML
    private TextField txb_cantidad;
    @FXML
    private DatePicker dp_fecha;
    @FXML
    private ComboBox cb_motivo;
    @FXML
    private TextArea txa_observaciones;
    @FXML
    private Button btn_registrar;

    private Integer idProducto=null;

    public void buscarProducto(ActionEvent event){
        System.out.println("Buscar Producto");


        Parent root = null;
        FXMLLoader loader;
        try {

           // root =(Parent) FXMLLoader.load(getClass().getResource("/fxml/BusquedaProducto.fxml"));
            loader =new FXMLLoader(getClass().getResource("/fxml/BusquedaProducto.fxml"));
            root = (Parent) loader.load();
            //root = (Parent) loader.load();

            BusquedaProducto busquedaControlador=loader.getController();
            busquedaControlador.setMovimientoProductoController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Window existingWindow = ((Node) event.getSource()).getScene().getWindow();



        // create a new stage:
        Stage stage = new Stage();
        // make it modal:
        stage.initModality(Modality.APPLICATION_MODAL);
        // make its owner the existing window:
        stage.initOwner(existingWindow);

        stage.setScene(scene);
        stage.show();


    }

    public void actualizarDataProducto(String nombreProducto,int idProducto){
        this.txb_nombreProd.setText(nombreProducto);

    }

    public void registrarIngresoProducto(ActionEvent event){
        String motivoMovimiento = cb_motivo.getValue().toString();
        if(motivoMovimiento != null){
            //obtener id motivo
            int idMotivoMovimiento;
            if((this.idProducto != null)){
                //añadirMovimiento(this.idProducto,idMotivoMovimiento,this.txb_cantidad,this.dp_fecha.getValue().toString(),this.txa_observaciones.getText());
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txb_nombreProd.setDisable(true);
    }
}
