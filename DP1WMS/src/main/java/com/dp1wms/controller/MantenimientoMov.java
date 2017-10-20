package com.dp1wms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MantenimientoMov {

    public void ingresoLote(ActionEvent event){
        System.out.println("Ingreso de Lote");

        Parent root = null;
        FXMLLoader loader;
        try {

            loader =new FXMLLoader(getClass().getResource("/fxml/CrearLote.fxml"));
            root = (Parent) loader.load();

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

    public void movimientoProducto(ActionEvent event){
        System.out.println("Movimiento de un Producto");

        Parent root = null;
        FXMLLoader loader;
        try {

            loader =new FXMLLoader(getClass().getResource("/fxml/IngresoProducto.fxml"));
            root = (Parent) loader.load();

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

       /* Parent crearLotePage = null;
        try {
            crearLotePage = FXMLLoader.load(getClass().getResource("/fxml/IngresoProducto.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene crearLoteScene = new Scene(crearLotePage);
        Stage crearLoteStage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        crearLoteStage.hide();
        crearLoteStage.setScene(crearLoteScene);
        crearLoteStage.show();*/
    }
}
