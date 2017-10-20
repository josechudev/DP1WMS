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
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;

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
    

    @FXML
    public void fooFunct(ActionEvent event){
        System.err.println("Foo function");
    }

    @FXML
    private void cargarMantenimientoMovimientos(ActionEvent event) {
        System.out.println("cargarMantenimientoMovimientos");
        this.stageManager.mostarModal(FxmlView.MANTENIMIENTO_MOVVIMIENTO);
    }
}
