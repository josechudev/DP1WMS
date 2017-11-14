package com.dp1wms.controller.auditoria;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.impl.RepositoryAuditoriaImpl;
import com.dp1wms.model.auditoria.Evento;
import com.dp1wms.util.DateParser;
import com.dp1wms.view.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReporteAuditoriaController implements FxmlController {

    @FXML private DatePicker dpFechaIni;
    @FXML private DatePicker dpFechaFin;
    @FXML private ComboBox cbTipoAccion;
    @FXML private TextField tfNombreEmpleado;

    @FXML private TableView tbvEventos;
    @FXML private TableColumn<Evento, String> colFecha;
    @FXML private TableColumn<Evento, String> colAccion;
    @FXML private TableColumn<Evento, String> colElemento;
    @FXML private TableColumn<Evento, String> colEmpleado;

    private List<Evento> eventosAuditoria;

    private StageManager stageManager;

    @Autowired
    private RepositoryAuditoriaImpl repositoryAuditoria;

    @Autowired @Lazy
    public ReporteAuditoriaController(StageManager stageManager){
        this.stageManager = stageManager;
    }

    @Override
    public void initialize() {
        colFecha.setCellValueFactory(new PropertyValueFactory<Evento, String>("fechaAccion"));
        colAccion.setCellValueFactory(new PropertyValueFactory<Evento, String>("accion"));
        colElemento.setCellValueFactory(new PropertyValueFactory<Evento, String>("tabla"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Evento, String>("nombreEmpleado"));
        llenarTabla();
    }

    private void llenarTabla(){
        eventosAuditoria = repositoryAuditoria.getAll();
        tbvEventos.getItems().clear();
        for(Evento evento: eventosAuditoria){
            tbvEventos.getItems().add(evento);
        }
    }
}
