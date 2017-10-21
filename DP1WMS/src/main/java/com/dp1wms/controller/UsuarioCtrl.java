package com.dp1wms.controller;

import com.dp1wms.controller.UsuarioDatosController;
import com.dp1wms.model.UsuarioModel.ListaUsuario;
import com.dp1wms.model.UsuarioModel.Usuario;
import com.dp1wms.view.FxmlView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UsuarioCtrl implements FxmlController{

    private final StageManager stageManager;

    @FXML private TableView<Usuario> e_table;
    @FXML private TableColumn<Usuario, Integer> e_id;
    @FXML private TableColumn<Usuario, String> e_nombre;
    @FXML private TableColumn<Usuario, String> e_password;

    private ListaUsuario v_listaUsuario;

    @Autowired @Lazy
    public  UsuarioCtrl(StageManager stageManager){
        this.stageManager = stageManager;
    }

    //Los botones del mantenimiento de usuarios
    public void btnClickCrearUsuario(ActionEvent event){
        System.out.println("Agrear Usuario");
        //this.stageManager.mostarModal(FxmlView.DATOS_USUARIO);

        Parent root = null;
        FXMLLoader loader;
        Usuario auxUsuario = new Usuario();
        auxUsuario.setV_id(v_listaUsuario._getNewId());
        auxUsuario.setV_nombre(null);
        auxUsuario.setV_password(null);
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/UsuarioFxml/DatosUsuario.fxml"));
            root = (Parent) loader.load();
            UsuarioDatosController controller = loader.getController();
            //0 es crear
            controller._setData(auxUsuario,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();

    }

    public void btnClickModificarUsuario(ActionEvent event){
        System.out.println("Modificar Usuario");

        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;

        //this.stageManager.mostarModal(FxmlView.DATOS_USUARIO);
        Usuario auxUsuario = e_table.getSelectionModel().getSelectedItem();
        Parent root = null;
        FXMLLoader loader;
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/UsuarioFxml/DatosUsuario.fxml"));
            root = (Parent) loader.load();
            UsuarioDatosController controller = loader.getController();
            //1 es modificar
            controller._setData(auxUsuario, 1);
            controller.setV_parentController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    public void btnClickEliminarUsuario(ActionEvent event){
        System.out.println("Eliminar Usuario");

        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;

        Usuario auxUsuario = e_table.getSelectionModel().getSelectedItem();
        v_listaUsuario._eliminarUsuario(auxUsuario.getV_id());

        this._llenarGrilla();
    }

    @Override
    public void initialize() {
        e_id.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("v_id"));
        e_nombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("v_nombre"));
        e_password.setCellValueFactory(new PropertyValueFactory<Usuario, String>("v_password"));

        v_listaUsuario = new ListaUsuario();
        this._llenarGrilla();
    }

    private void _llenarGrilla(){

        e_table.getItems().clear();

        for(int i = 0; i < v_listaUsuario.getV_cantUsuario(); i++){
            e_table.getItems().add(new Usuario( v_listaUsuario._getUsuario(i).getV_id(), v_listaUsuario._getUsuario(i).getV_nombre(), v_listaUsuario._getUsuario(i).getV_password() ) );
        }
    }

}
