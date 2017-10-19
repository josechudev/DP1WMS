package com.dp1wms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IngresoProducto {

    public void buscarProducto(ActionEvent event){
        System.out.println("Buscar Producto");

        Parent buscarProductoPage = null;
        try {
            buscarProductoPage = FXMLLoader.load(getClass().getResource("/fxml/BusquedaProducto.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene buscarProductoScene = new Scene(buscarProductoPage);
        Stage buscarProductoStage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        buscarProductoStage.hide();
        buscarProductoStage.setScene(buscarProductoScene);
        buscarProductoStage.showAndWait();
    }
}
