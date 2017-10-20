package com.dp1wms.controller;

import com.dp1wms.view.FxmlView;
import com.dp1wms.view.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.*;
import javafx.event.ActionEvent;
import java.io.IOException;

@Component
public class MainController implements FxmlController {

    private final StageManager stageManager;

    @Autowired @Lazy
    public MainController(StageManager stageManager){
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(){}

   /* public void cargarMantenimientoMovimientos(ActionEvent event){
        this.stageManager.cambiarScene(FxmlView.MANTMOV);
    }*/

    @FXML
    private void cargarMantenimientoMovimientos(ActionEvent event) {
        System.out.println("cargarMantenimientoMovimientos");
        /*Parent root = null;
        FXMLLoader loader;
        try {

            // root =(Parent) FXMLLoader.load(getClass().getResource("/fxml/BusquedaProductoController.fxml"));
            loader =new FXMLLoader(getClass().getResource("/fxml/MantenimientoMov.fxml"));
            root = (Parent) loader.load();
            //root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        //Window existingWindow = ((Node) event.getSource()).getScene().getWindow(); // linea importnate fake 1



        // create a new stage:
        Stage stage = new Stage();
        // make it modal:
        stage.initModality(Modality.APPLICATION_MODAL);
        // make its owner the existing window:
        //stage.initOwner(existingWindow); // linea importante fake 2

        stage.setScene(scene);
        stage.show();*/
        this.stageManager.mostarModal(FxmlView.MANTENIMIENTO_MOVIMIENTO);
    }
}
