package com.dp1wms.controller.racks;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.almacenes.AlmacenController;
import com.dp1wms.controller.superficies.SuperficieGridController;
import com.dp1wms.dao.impl.RepositoryMantRackImpl;
import com.dp1wms.model.Almacen;
import com.dp1wms.model.Area;
import com.dp1wms.model.Rack;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VistaRacksController implements FxmlController {

    @FXML private ComboBox cbArea;
    @FXML private ScrollPane spRacksView;
    @FXML private TextField tfPosX;
    @FXML private TextField tfPosY;
    @FXML private Button btnInsertarRack;
    @FXML private Button btnEliminarRack;

    @Autowired
    RepositoryMantRackImpl repositoryMantRack;

    private StageManager stageManager;
    private AlmacenController almacenController;
    private GridRacksController gridRacksController;

    private List<Area> areas;
    private List<Rack> racks;

    private ArrayList<Rack> racksCreados;
    private ArrayList<Rack> racksEliminados;

    private Area areaSeleccionada;
    private Almacen almacen;

    private Point2D posicionActual;

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
        gridRacksController = new GridRacksController(almacen.getLargo(), almacen.getAncho(), this);
        racksCreados = new ArrayList<>();
        racksEliminados = new ArrayList<>();
        obtenerRacks();
        dibujarRacks();
        btnInsertarRack.setDisable(true);
        btnEliminarRack.setDisable(true);
    }

    private void obtenerRacks(){
        racks = repositoryMantRack.getRacksByAlmacenId(almacen.getIdAlmacen());
    }

    private void dibujarRacks(){
        spRacksView.setContent(gridRacksController);
        for (Rack rack: racks){
            gridRacksController.anadirRack(rack);
        }
    }

    void actualizarPosicion(int x, int y){
        tfPosX.setText(String.valueOf(x));
        tfPosY.setText(String.valueOf(y));
        posicionActual = new Point2D(x, y);
    }

    void rackSeleccionado(boolean seleccionado){
        btnInsertarRack.setDisable(seleccionado);
        btnEliminarRack.setDisable(!seleccionado);
    }

    @FXML
    private void btnClickInsertarRack(ActionEvent event){

    }

    @FXML
    private void btnClickEliminarRack(ActionEvent event){
        Rack rackSeleccionado = gridRacksController.getRackSeleccionado();
        racksEliminados.add(rackSeleccionado);
        gridRacksController.eliminarRack(rackSeleccionado);
    }

    @FXML
    private void btnClickGuardarCambios(ActionEvent event){

    }

    public Point2D getPosicionActualGrid(){
        return this.posicionActual;
    }
}
