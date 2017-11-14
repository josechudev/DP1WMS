package com.dp1wms.controller.MantTipoEmpleado;


import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantTipoEmpleado;
import com.dp1wms.model.Seccion;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoEmpleadoNuevo implements FxmlController{

    @FXML private TextField descripcionTF;
    @FXML private CheckBox estadoCB;

    @FXML private Button registrarBtn;
    @FXML private Button guardarBtn;

    @FXML private VBox seccionsVBox;

    private List<Seccion> secciones;
    private TipoEmpleado tipoEmpleado;

    private final StageManager stageManager;


    private RepositoryMantTipoEmpleado repositoryMantTipoEmpleado;

    @FXML
    private void registrarTipoEmpleado(ActionEvent event){
        String descripcion = this.descripcionTF.getText();
        for(int i = 0; i < this.seccionsVBox.getChildren().size(); i++){
            CheckBox checkBox = (CheckBox) this.seccionsVBox.getChildren().get(i);
            Seccion seccion = this.secciones.get(i);
            seccion.setSeleccionado(checkBox.isSelected());
        }
        if(descripcion.isEmpty()){
            this.stageManager.mostrarErrorDialog("Error Rol", null,
                    "Debe ingresar un nombre al rol");
            return;
        }
        TipoEmpleado te = new TipoEmpleado();
        te.setDescripcion(descripcion);
        try{
            te = this.repositoryMantTipoEmpleado.crearTipoEmpleado(te, this.secciones);
            this.tipoEmpleado = te;
            this.stageManager.mostrarInfoDialog("Rol", null,
                    "Se registró un nuevo rol");
            this.stageManager.cerrarVentana(event);
        } catch (Exception e){
            this.stageManager.mostrarErrorDialog("Error Rol", null,
                    "Hubo un error al registrar el nuevo rol. Inténtelo otra vez.");
        }
    }

    @FXML void guardarCambios(ActionEvent event){
        String descripcion = this.descripcionTF.getText();
        for(int i = 0; i < this.seccionsVBox.getChildren().size(); i++){
            CheckBox checkBox = (CheckBox) this.seccionsVBox.getChildren().get(i);
            Seccion seccion = this.secciones.get(i);
            seccion.setSeleccionado(checkBox.isSelected());
        }
        if(descripcion.isEmpty()){
            this.stageManager.mostrarErrorDialog("Error Rol", null,
                    "Debe ingresar un nombre al rol");
            return;
        }
        TipoEmpleado te = new TipoEmpleado();
        te.setIdtipoempleado(this.tipoEmpleado.getIdtipoempleado());
        te.setDescripcion(descripcion);
        te.setActivo(this.estadoCB.isSelected());
        int numEmpleados = this.repositoryMantTipoEmpleado.obtenerNumEmpleadosDeTipoEmp(te.getIdtipoempleado());
        if(numEmpleados < 0){
            this.stageManager.mostrarErrorDialog("Error Rol", null,
                    "Hubo un error inesperado, inténtelo de nuevo.");
            return;
        } else if (numEmpleados > 0 && te.getActivo() == false) {
            this.stageManager.mostrarErrorDialog("Error Rol", null,
                    "No puede deshabilitar un rol que se encuentra asociado a " +
                            "almenos un empleado.");
            return;
        }
        try{
            this.repositoryMantTipoEmpleado.actualizarPermisos(te, this.secciones);
            this.tipoEmpleado = te;
            this.stageManager.mostrarInfoDialog("Rol", null,
                    "Se actualizaron los datos del rol con éxito.");
            this.stageManager.cerrarVentana(event);
        } catch (Exception e){
            this.stageManager.mostrarErrorDialog("Error Rol", null,
                    "Hubo un error al actualizar los datos del rol. Inténtelo otra vez");
        }
    }

    @FXML void cancelarCambios(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    private void initSecciones(){
        if(this.secciones == null){
            this.stageManager.mostrarErrorDialog("Error Mantenimiento Roles", null,
                    "Hubo un error al cargar los permisos. Vuelva a cargar la ventana.");
        }
        this.llenarSeccion();
    }

    private void llenarSeccion(){
        this.seccionsVBox.getChildren().clear();
        if(this.secciones != null){
            for(Seccion seccion: this.secciones){
                CheckBox cb = this.crearCheckBoxSeccion(seccion);
                this.seccionsVBox.getChildren().add(cb);
            }
        }
    }

    private CheckBox crearCheckBoxSeccion(Seccion seccion){
        CheckBox cb = new CheckBox();
        cb.setText(seccion.getDescripcion());
        cb.setSelected(seccion.isSeleccionado());
        return cb;
    }

    @Override
    public void initialize() {
        this.registrarBtn.managedProperty().bind(this.registrarBtn.visibleProperty());
        this.guardarBtn.managedProperty().bind(this.guardarBtn.visibleProperty());
        this.registrarBtn.setVisible(this.tipoEmpleado == null);
        this.registrarBtn.setDisable(!(this.tipoEmpleado == null));
        this.guardarBtn.setVisible(!(this.tipoEmpleado == null));
        this.guardarBtn.setDisable(this.tipoEmpleado == null);

        this.estadoCB.setDisable(this.tipoEmpleado == null);
        if(this.tipoEmpleado == null){
            this.descripcionTF.setText("");
            this.estadoCB.setSelected(true);
            this.secciones = this.repositoryMantTipoEmpleado.obtenerTodasLasSecciones();
        } else {
            this.descripcionTF.setText(this.tipoEmpleado.getDescripcion());
            this.estadoCB.setSelected(this.tipoEmpleado.getActivo());
            this.secciones = this.repositoryMantTipoEmpleado.obtenerSeccionesDeTipoEmpleado(
                    this.tipoEmpleado.getIdtipoempleado()
            );
        }
        this.initSecciones();
    }

    @Autowired @Lazy
    public TipoEmpleadoNuevo(StageManager stageManager){
        this.stageManager = stageManager;
    }


    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}
