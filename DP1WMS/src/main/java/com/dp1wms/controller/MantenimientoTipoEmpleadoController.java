package com.dp1wms.controller;

import com.dp1wms.dao.RepositoryMantTipoEmpleado;
import com.dp1wms.dao.RepositoryMantUsuario;
import com.dp1wms.dao.impl.RespositoryMantTipoEmpleadoImpl;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.model.UsuarioModel.Usuario;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
public class MantenimientoTipoEmpleadoController implements FxmlController{

    @FXML private TableView<TipoEmpleado> e_table;
    @FXML private TableColumn<TipoEmpleado, Long> e_id;
    @FXML private TableColumn<TipoEmpleado, String> e_descripcion;

    public static UsuarioDatosController v_parentController;

    private final StageManager stageManager;

    @Autowired @Lazy
    public MantenimientoTipoEmpleadoController(StageManager stageManager){
        this.stageManager = stageManager;
    }

    public void btnClickCrear(ActionEvent event){
        System.out.println("Agrear Tipo Empleado");
        Parent root = null;
        FXMLLoader loader;
        TipoEmpleado auxTipoEmpleado = new TipoEmpleado();
        auxTipoEmpleado.setDescripcion("");
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/UsuarioFxml/DatosTipoEmpleado.fxml"));
            root = (Parent) loader.load();
            TipoEmpleadoDatosController controller = loader.getController();
            //0 es crear
            controller._setData(auxTipoEmpleado,0);
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

    public void btnClickModificar(ActionEvent event){
        System.out.println("Modificar Tipo Usuario");
        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;
        TipoEmpleado auxTipoUsuario = e_table.getSelectionModel().getSelectedItem();
        Parent root = null;
        FXMLLoader loader;
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/UsuarioFxml/DatosTipoEmpleado.fxml"));
            root = (Parent) loader.load();
            TipoEmpleadoDatosController controller = loader.getController();
            //1 es modificar
            controller._setData(auxTipoUsuario, 1);
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

    public void btnClickEliminar(ActionEvent event){
        System.out.println("Eliminar Tipo Empleado");
        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;
        TipoEmpleado auxTipoEmpleado = e_table.getSelectionModel().getSelectedItem();
        this.v_parentController.eliminarTipoEmpleadoDB(auxTipoEmpleado);
        this.inicializarGrilla();
    }

    public void btnClickSeleccionar(ActionEvent event) {
        System.out.println("Seleccionar Tipo Empleado");
        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;
        TipoEmpleado auxTipoEmpleado = e_table.getSelectionModel().getSelectedItem();
        this.v_parentController._setTipoEmpleado(auxTipoEmpleado);
        Stage stage = (Stage) e_table.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize() {
        System.out.println("Implementar tipo Empleado");

    }

    public void inicializarGrilla() {
        e_id.setCellValueFactory(new PropertyValueFactory<TipoEmpleado, Long>("idtipoempleado"));
        e_descripcion.setCellValueFactory(new PropertyValueFactory<TipoEmpleado, String>("descripcion"));
        e_table.getItems().clear();
        List<TipoEmpleado> auxListaTipoEmpleado = v_parentController.llenarGrillaTipoEmpleado();
        for(int i = 0; i < auxListaTipoEmpleado.size(); i++){
            e_table.getItems().add(new TipoEmpleado( auxListaTipoEmpleado.get(i).getIdtipoempleado(), auxListaTipoEmpleado.get(i).getDescripcion() ) );
        }
    }

    public void setV_parentController(UsuarioDatosController v_parentController){
        this.v_parentController = v_parentController;
    }

    public void crearTipoEmpleadoDB(TipoEmpleado auxTipoEmpelado){
        this.v_parentController.crearTipoEmpleadoDB(auxTipoEmpelado);
    }

    public void modificarTipoEmpleadoDB(TipoEmpleado auxTipoEmpelado){
        this.v_parentController.modificarTipoEmpleadoDB(auxTipoEmpelado);
    }

}
