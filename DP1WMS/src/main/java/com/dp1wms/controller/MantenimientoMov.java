package com.dp1wms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MantenimientoMov {

    public void ingresoLote(ActionEvent event){
        System.out.println("Ingreso de Lote");

        Parent crearLotePage = null;
        try {
            crearLotePage = FXMLLoader.load(getClass().getResource("/fxml/CrearLote.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene crearLoteScene = new Scene(crearLotePage);
        Stage crearLoteStage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        crearLoteStage.hide();
        crearLoteStage.setScene(crearLoteScene);
        crearLoteStage.show();
    }

    public void ingresarProducto(ActionEvent event){
        System.out.println("Ingreso de Lote");

        Parent crearLotePage = null;
        try {
            crearLotePage = FXMLLoader.load(getClass().getResource("/fxml/IngresoProducto.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene crearLoteScene = new Scene(crearLotePage);
        Stage crearLoteStage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        crearLoteStage.hide();
        crearLoteStage.setScene(crearLoteScene);
        crearLoteStage.show();
    }
}
