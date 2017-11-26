package com.dp1wms.controller.auditoria;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.auditoria.Evento;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.dp1wms.util.DataSimple;
import com.dp1wms.util.DataComparable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DetalleEventoController implements FxmlController {

    @FXML private TableView tbvData;
    @FXML private Label lbFecha;
    @FXML private Label lbHora;
    @FXML private Label lbEmpleado;
    @FXML private Label lbAccion;

    private Evento eventoAuditoria;

    private StageManager stageManager;
    private ReporteAuditoriaController reporteAuditoriaController;

    @Autowired @Lazy
    public DetalleEventoController(StageManager stageManager, ReporteAuditoriaController reporteAuditoriaController){
        this.stageManager = stageManager;
        this.reporteAuditoriaController = reporteAuditoriaController;
    }


    @Override
    public void initialize() {
        eventoAuditoria = reporteAuditoriaController.getEventoSeleccionado();

        lbFecha.setText(eventoAuditoria.getFechaAccion());
        lbHora.setText(eventoAuditoria.getHoraAccion());
        lbEmpleado.setText(eventoAuditoria.getNombreEmpleado());
        lbAccion.setText(eventoAuditoria.getAccion() + " " + eventoAuditoria.getTabla());

        tbvData.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        String tipoAccion = eventoAuditoria.getAccion();
        TableColumn col1, col2;
        switch (tipoAccion){
            case "Insertar":
                col1 = new TableColumn<>("Datos insertados");
                col1.setCellValueFactory(new PropertyValueFactory<DataSimple, String>("data"));
                tbvData.getColumns().add(col1);
                tbvData.setItems(getListDataSimple(eventoAuditoria.getDataNueva()));
                break;
            case "Actualizar":
                col1 = new TableColumn<>("Datos nuevos");
                col1.setCellValueFactory(new PropertyValueFactory<DataSimple, String>("data1"));
                col2 = new TableColumn<>("Datos originales");
                col2.setCellValueFactory(new PropertyValueFactory<DataSimple, String>("data2"));
                tbvData.getColumns().addAll(col1, col2);
                tbvData.setItems(getListDataComparable(eventoAuditoria.getDataOriginal(), eventoAuditoria.getDataNueva()));
                break;
            case "Eliminar":
                col1 = new TableColumn<>("Datos eliminados");
                col1.setCellValueFactory(new PropertyValueFactory<DataSimple, String>("data"));
                tbvData.getColumns().add(col1);
                tbvData.setItems(getListDataSimple(eventoAuditoria.getDataOriginal()));
                break;
        }
    }

    public ObservableList<DataSimple> getListDataSimple(String data){
        String subStr = data.substring(1, data.length()-1);
        List<String> listStr = Arrays.asList(subStr.split(","));
        List<DataSimple> listData = new ArrayList<>();
        for(String s: listStr){
            DataSimple d = new DataSimple(s);
            listData.add(d);
        }
        return FXCollections.observableArrayList(listData);
    }

    public ObservableList<DataComparable> getListDataComparable(String data1, String data2){
        String subStr1 = data1.substring(1, data1.length()-1);
        List<String> listStr1 = Arrays.asList(subStr1.split(","));
        String subStr2 = data2.substring(1, data2.length()-1);
        List<String> listStr2 = Arrays.asList(subStr2.split(","));

        List<DataComparable> listData = new ArrayList<>();

        for (int i=0, j = 0; i< listStr1.size() && j < listStr2.size(); i++, j++){
            DataComparable d = new DataComparable(listStr1.get(i), listStr2.get(j));
            listData.add(d);
        }
        return FXCollections.observableArrayList(listData);
    }

}
