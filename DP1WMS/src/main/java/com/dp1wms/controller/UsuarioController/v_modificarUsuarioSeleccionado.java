package com.dp1wms.controller.UsuarioController;

import com.dp1wms.model.UsuarioModel.ListaUsuario;
import com.dp1wms.model.UsuarioModel.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class v_modificarUsuarioSeleccionado {

    @FXML private TextField e_id, e_nombre, e_password;

    private int v_id;
    private String v_nombre;
    private String v_password;

    @FXML
    public void initialize() {
        /*
        e_id.setText(Integer.toString(v_id));
        e_nombre.setText(v_nombre);
        e_password.setText(v_password);
        */
        e_id.setEditable(false);
        //e_id.setValue(Integer.toString(v_id));
    }

    public void _setData(Usuario auxUsuario){
        this.v_id = auxUsuario.getV_id();
        this.v_nombre = auxUsuario.getV_nombre();
        this.v_password = auxUsuario.getV_password();

        e_id.setText(Integer.toString(v_id));
        e_nombre.setText(v_nombre);
        e_password.setText(v_password);
    }

    public void _crearUsuario(ActionEvent event){
        ListaUsuario auxListaUsuario = new ListaUsuario();
        auxListaUsuario._eliminarUsuario(v_id);
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
}
