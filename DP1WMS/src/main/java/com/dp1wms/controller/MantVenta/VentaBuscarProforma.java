package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryProforma;
import com.dp1wms.model.DetalleProforma;
import com.dp1wms.model.Proforma;
import com.dp1wms.util.ClienteCampo;
import com.dp1wms.util.DateParser;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class VentaBuscarProforma implements FxmlController {


    @FXML private ComboBox<ClienteCampo> campoClienteCB;
    @FXML private TextField busquedaField;
    @FXML private TextField codigoProformaField;
    @FXML private DatePicker fechaDesdeDP;
    @FXML private DatePicker fechaHastaDP;

    @FXML private TableView<Proforma> proformasTable;
    @FXML private TableColumn<Proforma, Integer> codigoCol;
    @FXML private TableColumn<Proforma, String> clienteRazonSocialCol;
    @FXML private TableColumn<Proforma, String> clienteRUCCol;
    @FXML private TableColumn<Proforma, String> fechaCol;
    @FXML private TableColumn<Proforma, Float> totalCol;

    @Autowired
    private RepositoryProforma repositoryProforma;

    private Proforma proforma;

    private StageManager stageManager;

    @FXML
    private void buscarProformas(){
        LocalDate desdeLD = this.fechaDesdeDP.getValue();
        LocalDate hastaLD = this.fechaHastaDP.getValue();

        //mas validacion
        if(desdeLD == null || hastaLD == null){
            this.stageManager.mostrarErrorDialog("Error Buscar Proforma", null,
                    "Debe seleccionar un rango de fecha adecuado");
        } else if (desdeLD.isAfter(hastaLD)){
            this.stageManager.mostrarErrorDialog("Error Buscar Proforma", null,
                    "Debe seleccionar un rango de fecha adecuado");
        } else {
            String campoCliente = this.campoClienteCB.getValue().campo;
            String datoCliente = this.busquedaField.getText();
            String codigoProforma = this.codigoProformaField.getText();
            List<Proforma> proformas = this.repositoryProforma.buscarProformas(campoCliente, datoCliente, codigoProforma,
                    DateParser.localdateToTimestamp(desdeLD),
                    DateParser.localdateToTimestamp(hastaLD));
            if(proformas == null){
                this.stageManager.mostrarErrorDialog("Error Buscar Proforma", null,
                        "Hubo un error al buscar. Inténtelo otra vez");
            } else {
                this.llenarTabla(proformas);
            }
        }
    }


    @FXML
    private void seleccionarProforma(ActionEvent event){
        Proforma p = this.proformasTable.getSelectionModel().getSelectedItem();
        if(p == null){
            this.stageManager.mostrarErrorDialog("Error Busqueda de Proforma", null,
                    "Debe seleccionar una proforma.");
        } else {
            //Obtener los detalles
            ArrayList<DetalleProforma> detalles = this.repositoryProforma.obtenerDetallesDeProforma(p.getIdProforma());
            if(detalles == null){
                this.stageManager.mostrarErrorDialog("Error Busqueda de Proforma", null,
                        "Hubo un error al seleccionar la proforma. Inténtelo de nuevo.");
            } else {
                this.proforma = p;
                this.proforma.setDetallesProforma(detalles);
                this.stageManager.cerrarVentana(event);
            }
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    private void limpiarTabla(){
        this.proformasTable.getItems().clear();
        this.codigoCol.setCellValueFactory(value->{
            return new SimpleObjectProperty<Integer>(value.getValue().getIdProforma());
        });
        this.clienteRazonSocialCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getCliente().getRazonSocial());
        });
        this.clienteRUCCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getCliente().getNumDoc());
        });
        this.fechaCol.setCellValueFactory(value->{
            String fecha = value.getValue().getFechaCreacion();
            return new SimpleStringProperty(fecha);
        });
        this.totalCol.setCellValueFactory(value->{
            return new SimpleObjectProperty<Float>(value.getValue().getTotal());
        });
    }

    private void llenarTabla(List<Proforma> proformas){
        this.limpiarTabla();
        this.proformasTable.getItems().addAll(proformas);
    }

    @Override
    public void initialize() {
        this.initCampoCB();
        this.proforma = null;
    }

    private void initCampoCB(){
        ArrayList<ClienteCampo> campos = new ArrayList<>();
        campos.add(new ClienteCampo("DNI / RUC", "numdoc"));
        campos.add(new ClienteCampo("Nombre / Razon Social", "razonsocial"));
        campos.add(new ClienteCampo("Telefono", "telefono"));
        campos.add(new ClienteCampo("Email", "email"));
        this.campoClienteCB.getItems().addAll(campos);
        this.campoClienteCB.getSelectionModel().select(0);
        Callback<ListView<ClienteCampo>, ListCell<ClienteCampo>> factory = lv -> new ListCell<ClienteCampo>(){
            @Override
            protected void updateItem(ClienteCampo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.descripcion);
            }
        };
        this.campoClienteCB.setCellFactory(factory);
        this.campoClienteCB.setButtonCell(factory.call(null));
        this.campoClienteCB.getSelectionModel().select(0);

        this.fechaDesdeDP.setConverter(DateParser.getConverter());
        this.fechaDesdeDP.setValue(DateParser.currentDateLocalDate());

        this.fechaHastaDP.setConverter(DateParser.getConverter());
        this.fechaHastaDP.setValue(DateParser.currentDateLocalDate());
    }

    @Autowired @Lazy
    public VentaBuscarProforma(StageManager stageManager){
        this.stageManager = stageManager;
    }

    public Proforma getProforma() {
        return proforma;
    }
}
