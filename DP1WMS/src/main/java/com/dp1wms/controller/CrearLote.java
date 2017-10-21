package com.dp1wms.controller;

import com.dp1wms.model.Empleado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CrearLote implements Initializable{
    @FXML
    private TextField txb_nombreProducto;

    @FXML
    private AnchorPane crearLoteAnchorPane;

    private Integer idProducto=null;

    private Integer idLote=null;

    public void buscarProducto(ActionEvent event) {
        System.out.println("Buscar Producto");


        Parent root = null;
        FXMLLoader loader;
        try {

            // root =(Parent) FXMLLoader.load(getClass().getResource("/fxml/BusquedaProducto.fxml"));
            loader =new FXMLLoader(getClass().getResource("/fxml/BusquedaProductoLote.fxml"));
            root = (Parent) loader.load();
            //root = (Parent) loader.load();

            BusquedaProductoLote busquedaControlador=loader.getController();
            busquedaControlador.setCrearLoteController(this);
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

    public void actualizarDataProducto(String nombreProducto,Integer idProducto,Integer idLote){
        this.txb_nombreProducto.setText(nombreProducto);
        this.idProducto = idProducto;
        this.idLote = idLote;
    }

    public void registrarLote(ActionEvent event){
        System.out.println("Registrar Lote");
        crearLoteAnchorPane.getScene().getWindow().hide();
    }

    public void cancelarRegistrarLote(ActionEvent event){
        System.out.println("Se cancelo el registro del Lote");
        crearLoteAnchorPane.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txb_nombreProducto.setDisable(true);
    }


}
