package com.dp1wms.controller.racks;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.almacenes.AlmacenController;
import com.dp1wms.controller.superficies.SuperficieGridController;
import com.dp1wms.model.Almacen;
import com.dp1wms.model.Area;
import com.dp1wms.view.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VistaRacksController implements FxmlController {

    @FXML private ComboBox cbArea;
    @FXML private ScrollPane spRacksView;

    private StageManager stageManager;
    private AlmacenController almacenController;

    private List<Area> areas;
    private Area areaSeleccionada;
    private Almacen almacen;

    @Autowired @Lazy
    public VistaRacksController(AlmacenController almacenController, StageManager stageManager){
        this.almacenController = almacenController;
        this.stageManager = stageManager;
    }

    @Override
    public void initialize() {
        almacen = almacenController.getAlmacen();
        areas = almacenController.getAreas();
        areaSeleccionada = almacenController.getAreaSeleccionada();
        if (areaSeleccionada != null){
            SuperficieGridController superficieGridController = new SuperficieGridController(areaSeleccionada.)
        } e

    }
}
