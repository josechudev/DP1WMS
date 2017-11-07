package com.dp1wms.controller.MantTipoEmpleado;


import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantTipoEmpleado;
import com.dp1wms.model.Seccion;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @Autowired
    private RepositoryMantTipoEmpleado repositoryMantTipoEmpleado;

    @FXML
    private void registrarTipoEmpleado(ActionEvent event){

    }

    @FXML void guardarCambios(ActionEvent event){

    }

    @FXML void cancelarCambios(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    private void initSecciones(){
        this.secciones = this.repositoryMantTipoEmpleado.obtenerTodasLasSecciones();
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
