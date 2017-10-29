package com.dp1wms.controller.almacenes;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantArea;
import com.dp1wms.dao.impl.RepositoryMantAlmacenImpl;
import com.dp1wms.model.Almacen;
import com.dp1wms.model.Area;
import com.dp1wms.view.StageManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlmacenController implements FxmlController{

    @FXML private TextField tfNombre;
    @FXML private TextField tfLargo;
    @FXML private TextField tfAncho;
    @FXML private TextField tfDireccion;
    @FXML private Label lbSuperficie;
    @FXML private AnchorPane apAlmacen;

    @Autowired
    RepositoryMantAlmacenImpl repositoryMantAlmacen;

    @Autowired
    RepositoryMantArea repositoryMantArea;

    private StageManager stageManager;
    private MantenimientoAlmacenesController mantenimientoAlmacenesController;
    private Almacen almacen;

    @Autowired @Lazy
    public AlmacenController(StageManager stageManager, MantenimientoAlmacenesController mantenimientoAlmacenesController){
        this.stageManager = stageManager;
        this.mantenimientoAlmacenesController = mantenimientoAlmacenesController;
    }

    @Override
    public void initialize() {
        almacen = mantenimientoAlmacenesController.getAlmacenSeleccionado();
        tfNombre.setText(almacen.getNombre());
        tfDireccion.setText(almacen.getDireccion());
        tfLargo.setText(String.valueOf(almacen.getLargo()));
        tfAncho.setText(String.valueOf(almacen.getAncho()));
        lbSuperficie.setText(String.valueOf(almacen.getLargo()*almacen.getAncho()) + " m2");
        apAlmacen.setPrefWidth(almacen.getLargo()*10);
        apAlmacen.setPrefHeight(almacen.getAncho()*10);
        dibujarAreas();
    }

    public void eliminarAlmacen(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, String.format("¿Esta seguro que desea elminar el almacen %s", almacen.getNombre()), ButtonType.CANCEL, ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK){
            repositoryMantAlmacen.eliminarAlmacen(almacen);
            alert.close();
            stageManager.cerrarVentana(event);
            mantenimientoAlmacenesController.refrescarAlmacenes();
        } else {
            alert.close();
        }
    }

    public void guardarCambios(ActionEvent event){
        // Falta hacer comprobacion del input
        almacen.setNombre(tfNombre.getText());
        almacen.setDireccion(tfDireccion.getText());
        almacen.setLargo(Integer.parseInt(tfLargo.getText()));
        almacen.setAncho(Integer.parseInt(tfAncho.getText()));
        repositoryMantAlmacen.editarAlmacen(almacen);
        stageManager.cerrarVentana(event);
        mantenimientoAlmacenesController.refrescarAlmacenes();
    }

    public void cancelarCambios(ActionEvent event){
        stageManager.cerrarVentana(event);
    }

    private void dibujarAreas(){
        ObservableList apItems = apAlmacen.getChildren();
        List<Area> areas = repositoryMantArea.getAreasByIdAlmacen(almacen.getIdAlmacen());

        Point2D pIni, pFin;
        double x1, y1, x2, y2;
        double aLargo, aAncho;

        for (Area area: areas){
            pIni = area.getPosicionInicial();
            pFin = area.getPosicionFinal();

            x1 = pIni.getX();
            y1 = pIni.getY();
            x2 = pFin.getX();
            y2 = pFin.getY();

            aLargo = y2-y1;
            aAncho = x2-x1;

            Rectangle rectArea = new Rectangle();
            rectArea.setWidth(aLargo);
            rectArea.setHeight(aAncho);
            rectArea.setFill(Color.web("#8AAED0", 0.5));

            apItems.add(rectArea);

            AnchorPane.setLeftAnchor(rectArea, y1*10);
            AnchorPane.setTopAnchor(rectArea, x1*10);
        }
    }
}
