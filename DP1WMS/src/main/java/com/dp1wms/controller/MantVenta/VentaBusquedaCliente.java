package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantCliente;
import com.dp1wms.model.Cliente;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import java.util.List;

@Component
public class VentaBusquedaCliente implements FxmlController{

    @FXML private ComboBox campoClienteCB;
    @FXML private TextField busquedaField;
    @FXML private TableView<Cliente> clienteTable;
    @FXML private TableColumn<Cliente, String> clienteRucCol;
    @FXML private TableColumn<Cliente, String> clienteRazonSocialCol;
    @FXML private TableColumn<Cliente, String> clienteTelefonoCol;
    @FXML private TableColumn<Cliente, String> clienteEmailCol;
    @FXML private TableColumn<Cliente, String> estadoCol;


    private final StageManager stageManager;

    private Cliente cliente;

    @Autowired
    private RepositoryMantCliente repositoryMantCliente;

    @Override
    public void initialize(){
        this.campoClienteCB.getItems().addAll("RUC", "Razon Social", "Telefono", "Email");
        this.campoClienteCB.getSelectionModel().select(0);
    }

    @Autowired @Lazy
    public VentaBusquedaCliente(StageManager stageManager){
        this.stageManager = stageManager;
    }

    @FXML
    private void buscarCliente(){
        String campo = (String) this.campoClienteCB.getValue();
        String dato = this.busquedaField.getText();

        switch (campo){
            case "RUC": {
                campo = "numdoc";
                break;
            }
            case "Razon Social": {
                campo = "razonsocial";
                break;
            }
            case "Telefono": {
                campo = "telefono";
                break;
            }
            case "Email": {
                campo = "email";
                break;
            }
        }
        List<Cliente> clientes = this.repositoryMantCliente.buscarCliente(campo, dato);
        if(clientes == null){
            //revisar consola
            System.exit(1);
        }
        this.limpiarTabla();
        this.clienteTable.getItems().addAll(clientes);
    }

    @FXML
    private void seleccionarCliente(ActionEvent event){
        Cliente cliente = this.clienteTable.getSelectionModel().getSelectedItem();
        if(cliente == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debes seleccionar un cliente");
            alert.showAndWait();
        } else {
            this.setCliente(cliente);
            this.cerrarVentana(event);
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    private void limpiarTabla(){
        this.clienteTable.getItems().clear();
        this.clienteRucCol.setCellValueFactory(new PropertyValueFactory<Cliente, String>("numDoc"));
        this.clienteEmailCol.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
        this.clienteRazonSocialCol.setCellValueFactory(new PropertyValueFactory<Cliente, String>("razonSocial"));
        this.clienteTelefonoCol.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
        this.estadoCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().isActivo()?"Hablitado":"Deshabilitado");
        });
        this.clienteTable.setEditable(false);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
