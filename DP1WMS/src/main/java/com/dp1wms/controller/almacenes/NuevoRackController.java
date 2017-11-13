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
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.dp1wms.util.RackUtil.RackOrientacion;
import org.springframework.stereotype.Component;

@Component
public class NuevoRackController implements FxmlController {

    @FXML TextField tfCodigo;
    @FXML TextField tfPosIni;
    @FXML TextField tfAltura;
    @FXML TextField tfCajon;
    @FXML TextField tfPosFinX;
    @FXML TextField tfPosFinY;

    @FXML CheckBox chAgregarSerie;

    @FXML TextField tfSeparacion;
    @FXML RadioButton rbHorizontal;
    @FXML RadioButton rbVertical;
    @FXML Label lbSeparacion;
    @FXML Label lbDireccionSerie;
    @FXML Label lbPosFinSerie;
    @FXML Label lbSerieX;
    @FXML Label lbSerieY;
    @FXML RadioButton rbHorizontalSerie;
    @FXML RadioButton rbVerticalSerie;
    @FXML TextField tfPosFinSerieX;
    @FXML TextField tfPosFinSerieY;

    private ToggleGroup grpOrientacion = new ToggleGroup();
    private ToggleGroup grpOrientacionSerie = new ToggleGroup();

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
        tfPosIni.setDisable(true);
        rbHorizontal.setToggleGroup(grpOrientacion);
        rbVertical.setToggleGroup(grpOrientacion);
        rbHorizontalSerie.setToggleGroup(grpOrientacionSerie);
        rbVerticalSerie.setToggleGroup(grpOrientacionSerie);
        agregarEnSerie = false;
    }

    @FXML
    private void checkOrientacion(ActionEvent event){
        boolean orientacionHorizontal = rbHorizontal.isSelected();
        if (orientacionHorizontal){
            tfPosFinX.setDisable(false);
            tfPosFinX.setText("");
            tfPosFinY.setText(String.valueOf((int)posicion.getY()));
            tfPosFinY.setDisable(true);
        } else {
            tfPosFinX.setDisable(true);
            tfPosFinX.setText(String.valueOf((int) posicion.getX()));
            tfPosFinY.setDisable(false);
            tfPosFinY.setText("");
        }
    }

    @FXML
    private void checkAgregarEnSerie(ActionEvent event){
        if (!validarRack()){
            chAgregarSerie.setSelected(false);
            return;
        }
        agregarEnSerie = chAgregarSerie.isSelected();
        lbSeparacion.setDisable(!agregarEnSerie);
        tfSeparacion.setDisable(!agregarEnSerie);
        lbDireccionSerie.setDisable(!agregarEnSerie);
        rbHorizontalSerie.setDisable(!agregarEnSerie);
        rbVerticalSerie.setDisable(!agregarEnSerie);
        lbPosFinSerie.setDisable(!agregarEnSerie);
        lbSerieX.setDisable(!agregarEnSerie);
        lbSerieY.setDisable(!agregarEnSerie);

        rbHorizontalSerie.setSelected(false);
        rbVerticalSerie.setSelected(false);
        tfPosFinSerieX.setText("");
        tfPosFinSerieX.setDisable(true);
        tfPosFinSerieY.setText("");
        tfPosFinSerieY.setDisable(true);
    }

    @FXML
    private void checkDireccionSerie(ActionEvent event){
        boolean direccionHorizontal = rbHorizontalSerie.isSelected();
        if (direccionHorizontal){
            tfPosFinSerieX.setText("");
            tfPosFinSerieX.setDisable(false);
            tfPosFinSerieY.setText(tfPosFinY.getText());
            tfPosFinSerieY.setDisable(true);
        } else {
            tfPosFinSerieX.setText(tfPosFinX.getText());
            tfPosFinSerieX.setDisable(true);
            tfPosFinSerieY.setText("");
            tfPosFinSerieY.setDisable(false);
        }
    }

    @FXML
    private void btnClickGuardar(ActionEvent event){
        if (!validarRack()){
            return;
        }

        String codigo;
        int altura, longitudCajon;
        Point2D posicionFinal;
        RackOrientacion rackOrientacion;

        codigo = tfCodigo.getText();
        altura = Integer.parseInt(tfAltura.getText());
        longitudCajon = Integer.parseInt(tfCajon.getText());
        posicionFinal = new Point2D(Double.valueOf(tfPosFinX.getText()), Double.valueOf(tfPosFinY.getText()));

        Rack nuevoRack = new Rack();
        nuevoRack.setCodigo(codigo);
        nuevoRack.setPosicionInicial(posicion);
        nuevoRack.setAltura(altura);
        nuevoRack.setLongitudCajon(longitudCajon);
        nuevoRack.setPosicionFinal(posicionFinal);

        vistaRacksController.nuevoRack(nuevoRack);
        stageManager.cerrarVentana(event);
    }

    private boolean validarRack(){
        if (tfCodigo.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha especificado el codigo del rack", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        if (tfAltura.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha especificado la altura del rack", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        else {
            try {
                int altura = Integer.parseInt(tfAltura.getText());
            } catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La altura debe ser un numero entero", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        }
        if (tfCajon.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha especificado la longitud del cajon del rack", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        else {
            try {
                int cajon = Integer.parseInt(tfCajon.getText());
            } catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La longitud del cajon debe ser un numero entero", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        }
        if (tfPosFinX.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha especificado la posicion final en X del rack", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        else {
            try {
                int x = Integer.parseInt(tfPosFinX.getText());
            } catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La posicion final X debe ser un numero entero", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        }
        if (tfPosFinY.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha especificado la posicion final en Y del rack", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        else {
            try {
                int y = Integer.parseInt(tfPosFinY.getText());
            } catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La posicion final Y debe ser un numero entero", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        }
        if (rbHorizontal.isSelected()){
            int x1 = (int) posicion.getX();
            int x2 = Integer.parseInt(tfPosFinX.getText());
            int cajon = Integer.parseInt(tfCajon.getText());
            if ((x2-x1+1) % cajon != 0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La posicion final X no puede contener la longitud de cajon indicada", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        } else {
            int y1 = (int) posicion.getY();
            int y2 = Integer.parseInt(tfPosFinY.getText());
            int cajon = Integer.parseInt(tfCajon.getText());
            if ((y2-y1+1) % cajon != 0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La posicion final Y no puede contener la longitud de cajon indicada", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }

    private boolean validarSerie(){
        if (tfSeparacion.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha especificado la separación de la serie", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        else {
            try {
                int separacion = Integer.parseInt(tfSeparacion.getText());
            } catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La separación debe ser un número entero", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        }
        if (tfPosFinSerieX.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha especificado la posicion final X de la serie", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        else {
            try {
                int x = Integer.parseInt(tfPosFinSerieX.getText());
            } catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La posicion final X de la serie debe ser un número entero", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        }
        if (tfPosFinSerieY.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha especificado la posicion final Y de la serie", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        else {
            try {
                int x = Integer.parseInt(tfPosFinSerieY.getText());
            } catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La posicion final Y de la serie debe ser un número entero", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        }
        if (rbHorizontalSerie.isSelected()){
            int x2 = Integer.parseInt(tfPosFinSerieX.getText());
            int x1 = (int) posicion.getX();
            int separacion = Integer.parseInt(tfSeparacion.getText());
            if ((x2-x1) % (separacion + 1) != 0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La posicion final X no corresponde a la separación entre racks propuesta", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        } else {
            int y2 = Integer.parseInt(tfPosFinSerieY.getText());
            int y1 = (int) posicion.getY();
            int separacion = Integer.parseInt(tfSeparacion.getText());
            if ((y2-y1) % (separacion + 1) != 0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "La posicion final Y no corresponde a la separación entre racks propuesta", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }
}
