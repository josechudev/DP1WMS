package com.dp1wms.controller;

import com.dp1wms.dao.RepositoryMantUsuario;
import com.dp1wms.model.UsuarioModel.Usuario;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class UsuarioCtrl implements FxmlController{

    private final StageManager stageManager;

    @FXML private TableView<Usuario> e_table;
    @FXML private TableColumn<Usuario, Integer> e_id;
    @FXML private TableColumn<Usuario, String> e_nombre;
    @FXML private TableColumn<Usuario, String> e_password;

    //private ListaUsuario v_listaUsuario;

    @Autowired
    private RepositoryMantUsuario repositoryMantUsuario;

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
        //
        Usuario auxUsuario = new Usuario();
        //auxUsuario.setV_id(v_listaUsuario._getNewId());
        auxUsuario.setV_id(repositoryMantUsuario.newIdUsuario());
        auxUsuario.setV_nombre(null);
        auxUsuario.setV_password(null);
        //
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/UsuarioFxml/DatosUsuario.fxml"));
            root = (Parent) loader.load();
            UsuarioDatosController controller = loader.getController();
            //0 es crear
            controller._setData(auxUsuario,0);
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
        repositoryMantUsuario.deleteUsuario(auxUsuario);
        //v_listaUsuario._eliminarUsuario(auxUsuario.getV_id());

        this._llenarGrilla();
    }

    @Override
    public void initialize() {
        e_id.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("v_id"));
        e_nombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("v_nombre"));
        e_password.setCellValueFactory(new PropertyValueFactory<Usuario, String>("v_password"));

        //No se usa
        //v_listaUsuario = new ListaUsuario();
        this._llenarGrilla();
    }

    private void _llenarGrilla(){

        e_table.getItems().clear();

        List<Usuario> auxListaUsuarios = repositoryMantUsuario.selectAllUsuario();
        for(int i = 0; i < auxListaUsuarios.size(); i++){
            e_table.getItems().add(new Usuario( auxListaUsuarios.get(i).getV_id(), auxListaUsuarios.get(i).getV_nombre(), auxListaUsuarios.get(i).getV_password() ) );
        }
    }

    public void crearUsuarioDB(Usuario auxUsuario){
        repositoryMantUsuario.createUsuario(auxUsuario);
    }

    public void modificarUsuarioDB(Usuario auxUsuario){
        repositoryMantUsuario.updateUsuario(auxUsuario);
    }

}
