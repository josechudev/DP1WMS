package com.dp1wms.controller.almacenes;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.Almacen;
import com.dp1wms.view.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AlmacenController implements FxmlController{

    @FXML private TextField tfNombre;
    @FXML private TextField tfLargo;
    @FXML private TextField tfAncho;
    @FXML private Label lbSuperficie;

    private StageManager stageManager;
    private AlmacenItemController almacenItemController;
    private Almacen almacen;

    @Autowired @Lazy
    public AlmacenController(StageManager stageManager, AlmacenItemController almacenItemController){
        this.stageManager = stageManager;
        this.almacenItemController = almacenItemController;
    }

    @Override
    public void initialize() {
        almacen = almacenItemController.getAlmacen();
        tfNombre.setText(almacen.getNombre());
        tfLargo.setText(String.valueOf(almacen.getLargo()));
        tfAncho.setText(String.valueOf(almacen.getAncho()));
        lbSuperficie.setText(String.valueOf(almacen.getLargo()*almacen.getAncho()) + " m2");
    }
}
