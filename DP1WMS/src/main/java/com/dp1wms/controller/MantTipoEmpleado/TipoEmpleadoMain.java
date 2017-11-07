package com.dp1wms.controller.MantTipoEmpleado;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantTipoEmpleado;
import com.dp1wms.dao.impl.RespositoryMantTipoEmpleadoImpl;

import com.dp1wms.model.Seccion;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;

import com.dp1wms.view.TipoEmpleadoView;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class TipoEmpleadoMain implements FxmlController {

    @FXML private TextField buscarTF;

    @FXML private TableView<TipoEmpleado> tipoEmpleadoTable;
    @FXML private TableColumn<TipoEmpleado, String> descripcionTC;
    @FXML private TableColumn<TipoEmpleado, String> estadoTC;

    private List<TipoEmpleado> tiposEmpleado;

    private StageManager stageManager;
    private TipoEmpleadoNuevo tipoEmpleadoNuevo;

    @Autowired
    private RepositoryMantTipoEmpleado repositoryMantTipoEmpleado;


    @FXML
    private void buscarTipoEmpleado(){
        String tipoEmpleado = this.buscarTF.getText();
        this.tiposEmpleado = this.repositoryMantTipoEmpleado.buscarTipoEmpleado(tipoEmpleado);
        this.llenarTableTipoEmpleado();
    }

    @FXML
    private void crearNuevoTipoEmpleado(ActionEvent event) {
        this.tipoEmpleadoNuevo.setTipoEmpleado(null);
        this.stageManager.mostrarModal(TipoEmpleadoView.INFO);
        TipoEmpleado te = this.tipoEmpleadoNuevo.getTipoEmpleado();
        if(te != null){
            this.tiposEmpleado.add(te);
            this.tipoEmpleadoTable.getItems().add(te);
        }
    }

    @FXML
    private void editarTipoEmpleado(ActionEvent event){
        TipoEmpleado te = this.tipoEmpleadoTable.getSelectionModel().getSelectedItem();
        if(te == null){
            this.stageManager.mostrarErrorDialog("Error Mantenimiento Roles", null,
                    "Debe seleccionar un rol que desea editar");
        } else {
            this.tipoEmpleadoNuevo.setTipoEmpleado(te);
            this.stageManager.mostrarModal(TipoEmpleadoView.INFO);
            TipoEmpleado teAux = this.tipoEmpleadoNuevo.getTipoEmpleado();
            te.setActivo(teAux.getActivo());
            te.setDescripcion(teAux.getDescripcion());
            this.llenarTableTipoEmpleado();
        }
    }

    private void llenarTableTipoEmpleado(){
        this.tipoEmpleadoTable.getItems().clear();
        this.descripcionTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getDescripcion());
        });
        this.estadoTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getActivo()? "Habilitado" : "Deshabilitado");
        });
        if(this.tiposEmpleado != null){
            this.tipoEmpleadoTable.getItems().addAll(this.tiposEmpleado);
        }
    }


    @Override
    public void initialize() {
        this.buscarTF.setText("");
        this.buscarTipoEmpleado();
    }

    @Autowired @Lazy
    public TipoEmpleadoMain(StageManager stageManager,
                            TipoEmpleadoNuevo tipoEmpleadoNuevo){
        this.stageManager = stageManager;
        this.tipoEmpleadoNuevo = tipoEmpleadoNuevo;
    }
}
