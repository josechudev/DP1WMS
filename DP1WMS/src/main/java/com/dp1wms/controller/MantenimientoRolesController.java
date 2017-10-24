package com.dp1wms.controller;

import com.dp1wms.dao.impl.RespositoryMantTipoEmpleadoImpl;
import com.dp1wms.model.RolxSeccion;
import com.dp1wms.model.Seccion;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MantenimientoRolesController implements Initializable {


    @FXML private ListView<String> lista_roles;
    @FXML private Button boton_nuevo;
    @FXML private Button boton_editar;
    @FXML private CheckBox usuarios;
    @FXML private CheckBox productos;
    @FXML private CheckBox almacenes;
    @FXML private CheckBox ventas;


    private final StageManager stageManager;

    @Autowired
    private RespositoryMantTipoEmpleadoImpl repo_tipo_empleado;

    @Autowired @Lazy
    public MantenimientoRolesController(StageManager stageManager){
        this.stageManager = stageManager;
    }


    public List <String> getTiposEmpleado(){
        List<TipoEmpleado> tipos_empleado = repo_tipo_empleado.selectAllTipoEmpleado();
        List<String> nombres = new ArrayList<String>();
        for (TipoEmpleado t :tipos_empleado) {
            nombres.add(t.getDescripcion());
        }
        return nombres;
    }

    private List<String> getCheckedPermisos(){
        List<String> permisos = new ArrayList<String>();
        if (usuarios.isSelected()){
            permisos.add(usuarios.getText());
        }
        if(almacenes.isSelected()){
            permisos.add(almacenes.getText());
        }
        if(productos.isSelected()){
            permisos.add(productos.getText());
        }
        if(ventas.isSelected()){
            permisos.add(ventas.getText());
        }
        //System.out.println(permisos);
        return permisos;
    }

    @FXML
    private void click_boton_nuevo(ActionEvent event) {
        this.stageManager.mostrarModal(MainView.NUEVO_TIPOEMPLEADO);
    }

    @FXML
    private void click_boton_editar(ActionEvent event){
        if (lista_roles.getSelectionModel().getSelectedItem() == null){
            System.out.println("No hay nada seleccionado");
        }
        else{
            lista_roles.setDisable(true);
            usuarios.setDisable(false);
            productos.setDisable(false);
            almacenes.setDisable(false);
            ventas.setDisable(false);
        }
    }



    @FXML
    private void click_boton_guardar(ActionEvent event){

        lista_roles.setDisable(false);
        usuarios.setDisable(true);
        productos.setDisable(true);
        almacenes.setDisable(true);
        ventas.setDisable(true);

        repo_tipo_empleado.actualizarPermisos(lista_roles.getSelectionModel().getSelectedItem(),this.getCheckedPermisos());
    }

    private void fill_checkbox(String nombre_rol){
        List<String> permisos = repo_tipo_empleado.getPermisosTipoEmpleado(nombre_rol);

        usuarios.setSelected(false);
        ventas.setSelected(false);
        almacenes.setSelected(false);
        productos.setSelected(false);

        if (permisos.contains("Mantenimiento de Usuarios")){
            usuarios.setSelected(true);
        }
        if(permisos.contains("Generar Ventas")){
            ventas.setSelected(true);
        }
        if(permisos.contains("Mantenimiento de Almacenes")){
            almacenes.setSelected(true);
        }
        if(permisos.contains("Mantenimiento de Productos")){
            productos.setSelected(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> nombres_roles = FXCollections.observableArrayList(getTiposEmpleado());

//        repo_tipo_empleado.insertarPermisos("Mantenimiento de Usuarios");
//        repo_tipo_empleado.insertarPermisos("Mantenimiento de Productos");
//        repo_tipo_empleado.insertarPermisos("Mantenimiento de Almacenes");
//        repo_tipo_empleado.insertarPermisos("Generar Ventas");
//
//        System.out.println(this.getPermisos());
//        List<String> permisos = new ArrayList<String>();
//        permisos.add("Mantenimiento de Usuarios");
//        permisos.add("Mantenimiento de Productos");
//        permisos.add("Mantenimiento de Almacenes");
//        permisos.add("Generar Ventas");
//        repo_tipo_empleado.asignarTodosPermisos("Master", permisos);

        usuarios.setDisable(true);
        productos.setDisable(true);
        almacenes.setDisable(true);
        ventas.setDisable(true);


        //listener para que cada vez que se seleccione un nuevo item en la lista se actualizen los check ins
        lista_roles.setItems(nombres_roles);
        lista_roles.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void
                    changed(ObservableValue<? extends String> ov,
                                        String old_val, String new_val) {

                        fill_checkbox(new_val);

                    }
                });

        List<TipoEmpleado> empleados = repo_tipo_empleado.selectAllTipoEmpleado();
        System.out.println("TIPOS DE EMPLEADO");
        for (TipoEmpleado empleado: empleados) {
            System.out.print(empleado.getIdtipoempleado());
            System.out.print("    ");
            System.out.print(empleado.getDescripcion());
            System.out.println();
        }

        List<RolxSeccion> rolxSeccions= repo_tipo_empleado.selectAllRolesxSeccion();
        System.out.println("ROLES X SECCION");
        for (RolxSeccion r: rolxSeccions ) {
            System.out.print(r.getIdTipoEmpleado());
            System.out.print("   ");
            System.out.print(r.getIdSeccion());
            System.out.println();
        }

        List<Seccion> secciones = repo_tipo_empleado.selectAllPermisos();
        System.out.println("SECCIONES");
        for (Seccion s: secciones){
            System.out.print(s.getIdSeccion());
            System.out.print("   ");
            System.out.print(s.getDescripcion());
            System.out.println();
        }
    }
}
