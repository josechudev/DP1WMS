package com.dp1wms.controller;

import com.dp1wms.dao.RepositoryMantEmpleado;
import com.dp1wms.dao.RepositoryMantTipoEmpleado;
import com.dp1wms.dao.RepositoryMantUsuario;
import com.dp1wms.model.Empleado;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.model.UsuarioModel.Usuario;
import com.dp1wms.model.UsuarioModel.UsuarioXEmpleado;
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

    @FXML private TableView<UsuarioXEmpleado> e_table;
    @FXML private TableColumn<UsuarioXEmpleado, Integer> e_id;
    @FXML private TableColumn<UsuarioXEmpleado, String> e_user;
    @FXML private TableColumn<UsuarioXEmpleado, String> e_numDoc;
    @FXML private TableColumn<UsuarioXEmpleado, String> e_nombre;
    @FXML private TableColumn<UsuarioXEmpleado, String> e_apellido;
    @FXML private TableColumn<UsuarioXEmpleado, String> e_descripcion;

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
        System.out.println("Agrear Usuario");

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
            controller.setV_parentController(this);
            controller._setData(auxUsuario,auxEmpleado,auxTipoEmpleado,0);

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

        UsuarioXEmpleado auxUsuarioXEmpleado = e_table.getSelectionModel().getSelectedItem();
        Usuario auxUsuario = repositoryMantUsuario.findUsuariobyId(auxUsuarioXEmpleado.getV_id_user());
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
        System.out.println("Eliminar Usuario");

        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;

        UsuarioXEmpleado auxUsuarioXEmpleado = e_table.getSelectionModel().getSelectedItem();
        Usuario auxUsuario = repositoryMantUsuario.findUsuariobyId(auxUsuarioXEmpleado.getV_id_user());
        repositoryMantEmpleado.deleteEmpleado(auxUsuario, repositoryMantEmpleado.obtenerEmpleadoPorIdUsuario(auxUsuario.getV_id()) );
        repositoryMantUsuario.deleteUsuario(auxUsuario);

        this._llenarGrilla();
    }

    @Override
    public void initialize() {
        e_id.setCellValueFactory(new PropertyValueFactory<UsuarioXEmpleado, Integer>("v_id_user"));
        e_user.setCellValueFactory(new PropertyValueFactory<UsuarioXEmpleado, String>("v_user"));
        e_numDoc.setCellValueFactory(new PropertyValueFactory<UsuarioXEmpleado, String>("v_numDoc"));
        e_nombre.setCellValueFactory(new PropertyValueFactory<UsuarioXEmpleado, String>("v_nombre"));
        e_apellido.setCellValueFactory(new PropertyValueFactory<UsuarioXEmpleado, String>("v_apellido"));
        e_descripcion.setCellValueFactory(new PropertyValueFactory<UsuarioXEmpleado, String>("v_descripcion"));

        this._llenarGrilla();
    }

    private void _llenarGrilla(){

        e_table.getItems().clear();
        List<UsuarioXEmpleado> auxListaUsuarioXEmpleado = repositoryMantEmpleado.obtenerUsuarioXEmpleadoPorIdUsuario();
        for(int i = 0; i < auxListaUsuarioXEmpleado.size(); i++){
            e_table.getItems().add(new UsuarioXEmpleado( auxListaUsuarioXEmpleado.get(i).getV_id_user(),
                    auxListaUsuarioXEmpleado.get(i).getV_user(), auxListaUsuarioXEmpleado.get(i).getV_numDoc(),
                    auxListaUsuarioXEmpleado.get(i).getV_nombre(), auxListaUsuarioXEmpleado.get(i).getV_apellido(),
                    auxListaUsuarioXEmpleado.get(i).getV_descripcion() ));
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
    public TipoEmpleado obtenerTipoEmpleadoPorDescripcion(String auxDescripcion){
        return repositoryMantTipoEmpleado.obtenerTipoEmpleadoPorDescripcion(auxDescripcion);
    }

    public List<TipoEmpleado> llenarGrillaTipoEmpleado(){
        return repositoryMantTipoEmpleado.selectAllTipoEmpleado();
    }

}
