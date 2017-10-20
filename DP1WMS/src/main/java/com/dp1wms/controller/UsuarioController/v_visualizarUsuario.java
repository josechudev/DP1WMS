package com.dp1wms.controller.UsuarioController;

import com.dp1wms.model.UsuarioModel.ListaUsuario;
import com.dp1wms.model.UsuarioModel.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class v_visualizarUsuario {

    private ListaUsuario v_listaUsuario;

    @FXML private TableView<Usuario> e_table;
    @FXML private TableColumn<Usuario, Integer> e_id;
    @FXML private TableColumn<Usuario, String> e_nombre;
    @FXML private TableColumn<Usuario, String> e_password;

    @FXML
    public void initialize() {

        e_id.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("v_id"));
        e_nombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("v_nombre"));
        e_password.setCellValueFactory(new PropertyValueFactory<Usuario, String>("v_password"));

        v_listaUsuario = new ListaUsuario();
        _llenarGrilla();
    }

    private void _llenarGrilla(){
        for(int i = 0; i < v_listaUsuario.getV_cantUsuario(); i++){
            e_table.getItems().add(new Usuario( v_listaUsuario._getUsuario(i).getV_id(), v_listaUsuario._getUsuario(i).getV_nombre(), v_listaUsuario._getUsuario(i).getV_password() ) );
        }
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
