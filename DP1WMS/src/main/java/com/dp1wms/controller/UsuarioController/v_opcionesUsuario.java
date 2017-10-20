package com.dp1wms.controller.UsuarioController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class v_opcionesUsuario {

    public void _crearUsuario(ActionEvent event){
        System.out.println("Crear usuario");

        Parent crearVentana = null;
        try {
            crearVentana = FXMLLoader.load(getClass().getResource("/fxml/UsuarioFxml/v_crearUsuario.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene crearScene = new Scene(crearVentana);
        Stage crearStage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        crearStage.hide();
        crearStage.setScene(crearScene);
        crearStage.show();
    }

    public void _modificarUsuario(ActionEvent event){
        System.out.println("Modificar usuario");

        Parent crearVentana = null;
        try {
            crearVentana = FXMLLoader.load(getClass().getResource("/fxml/UsuarioFxml/v_modificarUsuario.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene crearScene = new Scene(crearVentana);
        Stage crearStage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        crearStage.hide();
        crearStage.setScene(crearScene);
        crearStage.show();
    }

    public void _eliminarUsuario(ActionEvent event){
        System.out.println("Eliminar usuario");

        Parent crearVentana = null;
        try {
            crearVentana = FXMLLoader.load(getClass().getResource("/fxml/UsuarioFxml/v_eliminarUsuario.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene crearScene = new Scene(crearVentana);
        Stage crearStage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        crearStage.hide();
        crearStage.setScene(crearScene);
        crearStage.show();
    }

    public void _visualizarUsuario(ActionEvent event){
        System.out.println("Visualizar usuario");

        Parent crearVentana = null;
        try {
            crearVentana = FXMLLoader.load(getClass().getResource("/fxml/UsuarioFxml/v_visualizarUsuario.fxml"));
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
            crearVentana = FXMLLoader.load(getClass().getResource("/fxml/GeneralFxml/v_mantenimientos.fxml"));
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
