package com.dp1wms.controller.almacenes;

import com.dp1wms.view.StageManager;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class NuevoAlmacenController {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfLargo;
    @FXML
    private TextField tfAncho;
    @FXML
    private Label lbSuperficie;
    @FXML

    private MantenimientoAlmacenesController mantenimientoAlmacenesController;
    private StageManager stageManager;

    @Autowired @Lazy
    public NuevoAlmacenController(MantenimientoAlmacenesController mantenimientoAlmacenesController, StageManager stageManager){
        this.mantenimientoAlmacenesController = mantenimientoAlmacenesController;
        this.stageManager = stageManager;
    }

    @FXML
    private void btnClickCrearAlmacen(ActionEvent event){
        //falta hacer validaciones
        String nombre = tfNombre.getText();
        String direccion = tfDireccion.getText();
        int largo = Integer.parseInt(tfLargo.getText());
        int ancho = Integer.parseInt(tfAncho.getText());

        int rows = this.mantenimientoAlmacenesController.anadirAlmacen(nombre, direccion, largo, ancho);

        Alert alert;

        if (rows == 1){
            alert = new Alert(AlertType.NONE, "El almacen fue creado", ButtonType.OK);
            alert.setTitle("WMS");
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK){
                stageManager.cerrarVentana(event);
                mantenimientoAlmacenesController.refrescarAlmacenes();
            }

        } else {
            alert = new Alert(AlertType.ERROR, "No se pudo crear el almacen", ButtonType.CLOSE);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }

    private boolean _validarCampos(){
        return false;
    }

    @FXML
    private void calcularSuperficie(KeyEvent event){
        String txtLargo = tfLargo.getText();
        String txtAncho = tfAncho.getText();
        if (!txtLargo.equals("") && !txtAncho.equals("")) try {
            int largo = Integer.parseInt(txtLargo);
            int ancho = Integer.parseInt(txtAncho);
            lbSuperficie.setText(String.valueOf(largo * ancho) + " m2");
        } catch (NumberFormatException e) {
            return;
        }
    }
}
