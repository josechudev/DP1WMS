package com.dp1wms.controller;

import com.dp1wms.view.FxmlView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
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

import java.io.IOException;

@Component
public class MantenimientoMovController implements FxmlController{

    private final StageManager stageManager;

    @Autowired @Lazy
    public  MantenimientoMovController(StageManager stageManager){
        this.stageManager = stageManager;
    }

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
        //stage.initOwner(existingWindow);
        stage.setScene(scene);
        stage.show();

     }

    public void movimientoProducto(ActionEvent event){
        System.out.println("Movimiento de un Producto");
        this.stageManager.mostrarModal(FxmlView.INGRESO_PRODUCTO);
    }

    @Override
    public void initialize() {

    }
}
