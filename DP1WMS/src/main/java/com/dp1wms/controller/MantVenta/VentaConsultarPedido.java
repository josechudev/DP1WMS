package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantPedido;
import com.dp1wms.model.Pedido;
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
public class VentaConsultarPedido implements FxmlController {

    @FXML private ComboBox<ClienteCampo> campoClienteCB;
    @FXML private TextField busquedaField;
    @FXML private TextField codigoPedidoTF;
    @FXML private DatePicker fechaDesdeDP;
    @FXML private DatePicker fechaHastaDP;

    @FXML private TableView<Pedido> pedidosTable;
    @FXML private TableColumn<Pedido, Integer> codigoCol;
    @FXML private TableColumn<Pedido, String> clienteRUCCol;
    @FXML private TableColumn<Pedido, String> clienteRazonSocialCol;
    @FXML private TableColumn<Pedido, String> fechaCol;
    @FXML private TableColumn<Pedido, Integer> numEnviosCol;
    @FXML private TableColumn<Pedido, Float> totalCol;
    @FXML private TableColumn<Pedido, String> estadoCol;

    @Autowired
    RepositoryMantPedido repositoryMantPedido;

    private StageManager stageManager;


    @FXML
    private void buscarPedidos(){
        LocalDate desdeLD = this.fechaDesdeDP.getValue();
        LocalDate hastaLD = this.fechaHastaDP.getValue();

        //mas validacion
        if(desdeLD == null || hastaLD == null || desdeLD.isAfter(hastaLD)) {
            this.stageManager.mostrarErrorDialog("Error Buscar Proforma", null,
                    "Debe seleccionar un rango de fecha adecuado");
        } else {
            String campoCliente = this.campoClienteCB.getValue().campo;
            String datoCliente = this.busquedaField.getText();
            String codigoPedido = this.codigoPedidoTF.getText();
            List<Pedido> pedidos = this.repositoryMantPedido.buscarPedidos(campoCliente,
                    datoCliente, codigoPedido,
                    DateParser.localdateToTimestamp(desdeLD),
                    DateParser.localdateToTimestamp(hastaLD));
            if(pedidos == null){
                this.stageManager.mostrarErrorDialog("Error Buscar Proforma", null,
                        "Hubo un error al buscar. Inténtelo otra vez");
            } else {
                this.llenartablaPedidos(pedidos);
            }
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event){
        this.stageManager.cerrarVentana(event);
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
    private void limpiarTablaPedidos(){
        this.pedidosTable.getItems().clear();
        this.codigoCol.setCellValueFactory(value->{
            return new SimpleObjectProperty<Integer>(value.getValue().getIdPedido());
        });
        this.clienteRUCCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getCliente().getNumDoc());
        });
        this.clienteRazonSocialCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getCliente().getRazonSocial());
        });
        this.fechaCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getFechaCreacion());
        });
        this.numEnviosCol.setCellValueFactory(value->{
            return new SimpleObjectProperty<Integer>(value.getValue().getNumEnvios());
        });
        this.totalCol.setCellValueFactory(value->{
            return new SimpleObjectProperty<Float>(value.getValue().getTotal());
        });
        this.estadoCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getEstadoPedido().getDescripcion());
        });
    }
    private void llenartablaPedidos(List<Pedido> pedidos){
        this.limpiarTablaPedidos();
        if(pedidos != null){
            this.pedidosTable.getItems().addAll(pedidos);
        }
    }

    @Override
    public void initialize() {
        this.initCampoCB();
        this.limpiarTablaPedidos();
    }

    @Autowired @Lazy
    public VentaConsultarPedido(StageManager stageManager){
        this.stageManager = stageManager;
    }
}