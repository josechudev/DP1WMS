package com.dp1wms.controller.almacenes;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.racks.VistaRacksController;
import com.dp1wms.model.Rack;
import com.dp1wms.util.PosicionFormatter;
import com.dp1wms.util.RackUtil;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.dp1wms.util.RackUtil.RackOrientacion;


public class NuevoRackController implements FxmlController {

    @FXML TextField tfPosIni;
    @FXML TextField tfAltura;
    @FXML TextField tfCajon;
    @FXML TextField tfPosFin;

    @FXML CheckBox chAgregarSerie;

    @FXML TextField tfSeparacion;
    @FXML RadioButton rbHorizontal;
    @FXML RadioButton rbVertical;
    @FXML TextField tfPosFinSerie;
    @FXML Label lbSeparacion;
    @FXML Label lbDireccionSerie;
    @FXML Label lbPosFinSerie;

    private StageManager stageManager;
    private VistaRacksController vistaRacksController;

    private Point2D posicion;
    private boolean agregarEnSerie;

    @Autowired @Lazy
    public NuevoRackController(StageManager stageManager, VistaRacksController vistaRacksController){
        this.stageManager = stageManager;
        this.vistaRacksController = vistaRacksController;
    }

    @Override
    public void initialize() {
        posicion = vistaRacksController.getPosicionActualGrid();
        tfPosIni.setText(PosicionFormatter.pointToXYPair(posicion));
        agregarEnSerie = false;
    }

    @FXML
    private void checkAgregarEnSerie(ActionEvent event){
        agregarEnSerie = chAgregarSerie.isSelected();
        lbSeparacion.setDisable(!agregarEnSerie);
        tfSeparacion.setDisable(!agregarEnSerie);
        lbDireccionSerie.setDisable(!agregarEnSerie);
        rbHorizontal.setDisable(!agregarEnSerie);
        rbVertical.setDisable(!agregarEnSerie);
        lbPosFinSerie.setDisable(!agregarEnSerie);
        tfPosFinSerie.setDisable(!agregarEnSerie);
    }

    @FXML
    private void btnClickGuardar(ActionEvent event){
        int altura, longitudCajon;
        Point2D posicionFinal;
        RackOrientacion rackOrientacion;

        altura = Integer.parseInt(tfAltura.getText());
        longitudCajon = Integer.parseInt(tfCajon.getText());
        posicionFinal = PosicionFormatter.stringToPoint(tfPosFin.getText());

        Rack nuevoRack = new Rack();
        nuevoRack.setPosicionInicial(posicion);
        nuevoRack.setLongitudCajon(longitudCajon);
        nuevoRack.setPosicionFinal(posicionFinal);

        if (rbHorizontal.isSelected() && !rbVertical.isSelected()){
            rackOrientacion = RackOrientacion.HORIZONTAL;
        } else if (!rbHorizontal.isSelected() && rbVertical.isSelected()){
            rackOrientacion = RackOrientacion.VERTICAL;
        } else {
            rackOrientacion = null;
        }

        verificarOrientacionYPuntoFinal(nuevoRack, rackOrientacion);

    }

    private void verificarOrientacionYPuntoFinal(Rack rack, RackOrientacion orientacion){
        if (orientacion == RackOrientacion.HORIZONTAL){
            rack.getPosicionInicial();
        } else {

        }
    }
}
