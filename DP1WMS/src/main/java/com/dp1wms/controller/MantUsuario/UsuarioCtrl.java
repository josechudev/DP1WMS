package com.dp1wms.controller.MantUsuario;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantEmpleado;
import com.dp1wms.dao.RepositoryMantTipoEmpleado;
import com.dp1wms.dao.RepositoryMantUsuario;
import com.dp1wms.model.Empleado;
import com.dp1wms.model.TipoEmpleado;
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
public class UsuarioCtrl implements FxmlController {

    private final StageManager stageManager;

    @FXML private TableView<Usuario> e_table;
    @FXML private TableColumn<Usuario, Integer> e_id;
    @FXML private TableColumn<Usuario, String> e_nombre;
    @FXML private TableColumn<Usuario, String> e_password;

    //private ListaUsuario v_listaUsuario;

    @Autowired
    private RepositoryMantUsuario repositoryMantUsuario;
    @Autowired
    private RepositoryMantEmpleado repositoryMantEmpleado;
    @Autowired
    private RepositoryMantTipoEmpleado repositoryMantTipoEmpleado;

    @Autowired @Lazy
    public  UsuarioCtrl(StageManager stageManager){
        this.stageManager = stageManager;
    }

    //Los botones del mantenimiento de usuarios
    public void btnClickCrearUsuario(ActionEvent event){
        System.out.println("Agrear MantUsuario");

        Parent root = null;
        FXMLLoader loader;
        Usuario auxUsuario = new Usuario();
        Empleado auxEmpleado = new Empleado();
        TipoEmpleado auxTipoEmpleado = new TipoEmpleado();
        //
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/UsuarioFxml/DatosUsuario.fxml"));
            root = (Parent) loader.load();
            UsuarioDatosController controller = loader.getController();
            //0 es crear
            controller._setData(auxUsuario,auxEmpleado,auxTipoEmpleado,0);
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
        System.out.println("Modificar MantUsuario");

        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;

        //this.stageManager.mostarModal(FxmlView.DATOS_USUARIO);
        Usuario auxUsuario = e_table.getSelectionModel().getSelectedItem();
        Empleado auxEmpleado = repositoryMantEmpleado.obtenerEmpleadoPorIdUsuario( auxUsuario.getV_id() );
        TipoEmpleado auxTipoEmpleado = repositoryMantTipoEmpleado.obtenerTipoEmpleadoPorIdTipo(auxEmpleado.getIdtipoempleado());
        Parent root = null;
        FXMLLoader loader;
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/UsuarioFxml/DatosUsuario.fxml"));
            root = (Parent) loader.load();
            UsuarioDatosController controller = loader.getController();
            //1 es modificar
            controller._setData(auxUsuario, auxEmpleado, auxTipoEmpleado,1);
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
        System.out.println("Eliminar MantUsuario");

        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;

        Usuario auxUsuario = e_table.getSelectionModel().getSelectedItem();
        repositoryMantEmpleado.deleteEmpleado(auxUsuario, repositoryMantEmpleado.obtenerEmpleadoPorIdUsuario(auxUsuario.getV_id()) );
        repositoryMantUsuario.deleteUsuario(auxUsuario);

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

    public void crearEmpleadoDB(Usuario auxUsuario, Empleado auxEmpleado, TipoEmpleado auxTipoEmpelado){
        repositoryMantEmpleado.createEmpleado(auxUsuario, auxEmpleado, auxTipoEmpelado);
    }
    public void modificarEmpleadoDB(Usuario auxUsuario, Empleado auxEmpleado, TipoEmpleado auxTipoEmpelado){
        repositoryMantEmpleado.updateEmpleado(auxUsuario, auxEmpleado, auxTipoEmpelado);
    }
    public void eliminarEmpleadoDB(Usuario auxUsuario, Empleado auxEmpleado){
        repositoryMantEmpleado.deleteEmpleado(auxUsuario, auxEmpleado);
    }

    public void crearTipoEmpleadoDB(TipoEmpleado auxTipoEmpelado){
        repositoryMantTipoEmpleado.createTipoEmpleado(auxTipoEmpelado);
    }
    public void modificarTipoEmpleadoDB(TipoEmpleado auxTipoEmpelado){
        repositoryMantTipoEmpleado.updateTipoEmpleado(auxTipoEmpelado);
    }
    public void eliminarTipoEmpleadoDB(TipoEmpleado auxTipoEmpelado){
        repositoryMantTipoEmpleado.deleteTipoEmpleado(auxTipoEmpelado);
    }

    public List<TipoEmpleado> llenarGrillaTipoEmpleado(){
        return repositoryMantTipoEmpleado.selectAllTipoEmpleado();
    }
}
