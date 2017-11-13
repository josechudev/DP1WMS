package com.dp1wms.controller.racks;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.almacenes.AlmacenController;
import com.dp1wms.dao.impl.RepositoryCajonImpl;
import com.dp1wms.dao.impl.RepositoryMantRackImpl;
import com.dp1wms.model.Almacen;
import com.dp1wms.model.Area;
import com.dp1wms.model.Rack;
import com.dp1wms.util.AreaUtil;
import com.dp1wms.view.AlmacenView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VistaRacksAreaController implements FxmlController {

    @FXML private Label lbTitulo;
    @FXML private ScrollPane spRacksView;
    @FXML private TextField tfPosX;
    @FXML private TextField tfPosY;
    @FXML private Button btnInsertarRack;
    @FXML private Button btnEliminarRack;

    @Autowired
    RepositoryMantRackImpl repositoryMantRack;
    @Autowired
    RepositoryCajonImpl repositoryCajon;

    private StageManager stageManager;
    private AlmacenController almacenController;
    private GridRacksAreaController gridRacksController;

    private List<Area> areas;
    private List<Rack> racks;

    private ArrayList<Rack> racksCreados;
    private ArrayList<Rack> racksEliminados;

    private Area areaSeleccionada;
    private Almacen almacen;

    private Point2D posicionActual;
    private int offsetAreaX;
    private int offsetAreaY;

    @Autowired @Lazy
    public VistaRacksAreaController(AlmacenController almacenController, StageManager stageManager){
        this.almacenController = almacenController;
        this.stageManager = stageManager;
    }

    @Override
    public void initialize() {
        areaSeleccionada = almacenController.getAreaSeleccionada();
        offsetAreaX = (int) areaSeleccionada.getPosicionInicial().getX();
        offsetAreaY = (int) areaSeleccionada.getPosicionInicial().getY();
        lbTitulo.setText("Vista de Racks - Area " + areaSeleccionada.getCodigo());
        gridRacksController = new GridRacksAreaController(AreaUtil.getLargo(areaSeleccionada), AreaUtil.getAncho(areaSeleccionada), this);
        racksCreados = new ArrayList<>();
        racksEliminados = new ArrayList<>();
        obtenerRacks();
        dibujarRacks();
        btnInsertarRack.setDisable(true);
        btnEliminarRack.setDisable(true);
    }

    private void obtenerRacks(){
        racks = repositoryMantRack.getRacksByAreaId(areaSeleccionada.getIdArea());
    }

    private void dibujarRacks(){
        spRacksView.setContent(gridRacksController);
        for (Rack rack: racks){
            gridRacksController.anadirRack(rack);
        }
    }

    void actualizarPosicion(int x, int y){
        tfPosX.setText(String.valueOf(x + offsetAreaX));
        tfPosY.setText(String.valueOf(y + offsetAreaY));
        posicionActual = new Point2D(x + offsetAreaX, y + offsetAreaY);
    }

    void rackSeleccionado(boolean seleccionado){
        btnInsertarRack.setDisable(seleccionado);
        btnEliminarRack.setDisable(!seleccionado);
    }

    @FXML
    private void btnClickInsertarRack(ActionEvent event){
        stageManager.mostrarModal(AlmacenView.NUEVO_RACK);
    }

    public void nuevoRack(Rack rack){
        racksCreados.add(rack);
        gridRacksController.anadirRack(rack);
    }

    public void nuevaSerie(ArrayList<Rack> racks){
        for (Rack r: racks){
            nuevoRack(r);
        }
    }

    @FXML
    private void btnClickEliminarRack(ActionEvent event){
        Rack rackSeleccionado = gridRacksController.getRackSeleccionado();
        if (racksCreados.contains(rackSeleccionado)){
            racksCreados.remove(rackSeleccionado);
        } else {
            racksEliminados.add(rackSeleccionado);
        }
        gridRacksController.eliminarRack(rackSeleccionado);
    }

    @FXML
    private void btnClickGuardarCambios(ActionEvent event){
        if (racksEliminados.size() == 1){
            repositoryMantRack.eliminar(racksEliminados.get(0));
            repositoryCajon.eliminar(racksEliminados.get(0).getIdRack());
        } else if (racksEliminados.size() > 1) {
            repositoryMantRack.eliminar(racksEliminados);
            repositoryCajon.eliminar(racksEliminados);
        }
        if (racksCreados.size() == 1){
            repositoryMantRack.crear(racksCreados.get(0));
            repositoryCajon.crear(racksCreados.get(0));
        } else if (racksCreados.size() > 1){
            repositoryMantRack.crear(racksCreados);
            repositoryCajon.crear(racksCreados);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Se guardaron los cambios", ButtonType.OK);
        alert.showAndWait();
        stageManager.cerrarVentana(event);
    }

    public Point2D getPosicionActualGrid(){
        return this.posicionActual;
    }

    public Area getAreaSeleccionada(){
        return this.areaSeleccionada;
    }

    public int getOffsetAreaX() {
        return offsetAreaX;
    }

    public int getOffsetAreaY() {
        return offsetAreaY;
    }
}
