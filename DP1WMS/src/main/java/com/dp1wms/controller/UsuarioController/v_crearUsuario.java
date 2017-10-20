package com.dp1wms.controller.UsuarioController;

import com.dp1wms.model.UsuarioModel.ListaUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;


import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.awt.*;
import java.io.IOException;

public class v_crearUsuario {

    @FXML private TextField e_id, e_nombre, e_password;

    private int v_id;

    @FXML
    public void initialize() {
        ListaUsuario auxListaUsuario = new ListaUsuario();
        v_id = auxListaUsuario._getNewId();
        e_id.setText(Integer.toString(v_id));
        //e_id.setValue(Integer.toString(v_id));
    }

    public void _crearUsuario(ActionEvent event){
        ListaUsuario auxListaUsuario = new ListaUsuario();
        auxListaUsuario._agregarUsuario(v_id, e_nombre.getText(), e_password.getText() );

        Parent crearVentana = null;
        try {
            crearVentana = FXMLLoader.load(getClass().getResource("/fxml/UsuarioFxml/v_opcionesUsuario.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene crearScene = new Scene(crearVentana);
        Stage crearStage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        crearStage.hide();
        crearStage.setScene(crearScene);
        crearStage.show();
    }

    public void _regrearMantenimiento(ActionEvent event){
        System.out.println("Regresar a la ventana seleccionar mantenimiento");

        Parent crearVentana = null;
        try {
            crearVentana = FXMLLoader.load(getClass().getResource("/fxml/UsuarioFxml/v_opcionesUsuario.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene crearScene = new Scene(crearVentana);
        Stage crearStage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        crearStage.hide();
        crearStage.setScene(crearScene);
        crearStage.show();

    }
}
