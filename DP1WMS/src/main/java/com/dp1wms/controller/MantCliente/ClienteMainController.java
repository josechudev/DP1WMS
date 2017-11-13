package com.dp1wms.controller.MantCliente;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryCargaMasiva;
import com.dp1wms.dao.RepositoryMantCliente;
import com.dp1wms.model.Cliente;
import com.dp1wms.util.ClienteCampo;
import com.dp1wms.view.ClientesView;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ClienteMainController implements FxmlController{


    @FXML private ComboBox<ClienteCampo> campoCB;
    @FXML private TextField busquedaTF;

    @FXML private TableView<Cliente> clienteTable;
    @FXML private TableColumn<Cliente, String> rucCol;
    @FXML private TableColumn<Cliente, String> razonSocialCol;
    @FXML private TableColumn<Cliente, String> dirCol;
    @FXML private TableColumn<Cliente, String> telfCol;
    @FXML private TableColumn<Cliente, String> emailCol;
    @FXML private TableColumn<Cliente, String> estadoCol;

    private List<Cliente> clientes;

    @Autowired
    private RepositoryMantCliente repositoryMantCliente;
    @Autowired
    private RepositoryCargaMasiva repositoryCargaMasiva;

    private StageManager stageManager;
    private MainController mainController;
    private ClienteInfoController clienteInfoController;


    public void cargaMasiva(ActionEvent event){
        this.repositoryCargaMasiva.storeProcedure_cargarClientes();
        this.llenarClienteTable();
    }

    @FXML
    private void cargarCSV(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*CSV Files", "*.csv"));

        File archivoCSV = fc.showOpenDialog(null);

        if(archivoCSV != null){
            try{
                ArrayList<Cliente> clientes = new ArrayList<>();
                BufferedReader br = new BufferedReader((new FileReader(archivoCSV)));
                String line = null;
                while((line = br.readLine()) != null){
                    String[] datos = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    Cliente c = new Cliente();
                    c.setNumDoc(datos[0]);
                    if(datos[1].contains("\"")){
                        c.setRazonSocial(datos[1].substring(1,datos[1].length()-1));
                    } else {
                        c.setRazonSocial(datos[1]);
                    }
                    c.setTelefono(datos[2]);
                    c.setDireccion(datos[3]);
                    c.setEmail(datos[4]);
                    clientes.add(c);
                }
                if(clientes.size() > 0){
                    this.repositoryMantCliente.registrarVariosClientes(clientes);

                    this.stageManager.mostrarInfoDialog("Mantenimiento de Clientes", null,
                                "Se cargaron los clientes en la base de datos");
                    this.clientes = clientes;
                    this.llenarClienteTable();
                }
            } catch(Exception e){
                e.printStackTrace();
                this.stageManager.mostrarErrorDialog("Mantenimiento de Clientes", null,
                        "No se pudo cargar los clientes.");
            }

        }
    }

    @FXML
    private void buscarClientes(){
        String campo = this.campoCB.getValue().campo;
        String dato = this.busquedaTF.getText();
        this.clientes = this.repositoryMantCliente.buscarCliente(campo, dato);
        if(clientes != null){
            this.llenarClienteTable();
        }
    }

    @FXML
    private void registrarCliente(){
        this.clienteInfoController.setCliente(null);
        this.stageManager.mostrarModal(ClientesView.INFO);
        Cliente c = this.clienteInfoController.getCliente();
        if(c != null){
            if(this.clientes ==  null){
                this.clientes = new ArrayList<>();
            }
            this.clientes.add(c);
            this.clienteTable.getItems().add(c);
        }
    }

    @FXML
    private void modificarCliente(){
        Cliente c = this.clienteTable.getSelectionModel().getSelectedItem();
        if(c == null){
            this.stageManager.mostrarErrorDialog("Error Mantenimiento Cliente", null,
                    "Debe seleccionar un cliente");
        } else {
            this.clienteInfoController.setCliente(new Cliente(c));
            this.stageManager.mostrarModal(ClientesView.INFO);
            Cliente cMod = this.clienteInfoController.getCliente();
            if(cMod != null){
                c.copyFrom(cMod);
                this.llenarClienteTable();
            }
        }
    }

    @Override
    public void initialize(){
        this.initCampoCB();
        this.limpiarClienteTable();
    }
    private void initCampoCB(){
        ArrayList<ClienteCampo> campos = new ArrayList<>();
        campos.add(new ClienteCampo("RUC / DNI", "numdoc"));
        campos.add(new ClienteCampo("Razon Social", "razonsocial"));
        campos.add(new ClienteCampo("Telefono", "telefono"));
        campos.add(new ClienteCampo("Email", "email"));
        this.campoCB.getItems().addAll(campos);
        this.campoCB.getSelectionModel().select(0);
        Callback<ListView<ClienteCampo>, ListCell<ClienteCampo>> factory = lv -> new ListCell<ClienteCampo>(){
            @Override
            protected void updateItem(ClienteCampo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.descripcion);
            }
        };
        this.campoCB.setCellFactory(factory);
        this.campoCB.setButtonCell(factory.call(null));
    }

    private void limpiarClienteTable(){
        this.clienteTable.getItems().clear();
        this.rucCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getNumDoc());
        });
        this.razonSocialCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getRazonSocial());
        });
        this.dirCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getDireccion());
        });
        this.telfCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getTelefono());
        });
        this.emailCol.setCellValueFactory(value->{
            return  new SimpleStringProperty(value.getValue().getEmail());
        });
        this.estadoCol.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().isActivo()?"Habilitado":"Deshabilitado");
        });
    }

    private void llenarClienteTable(){
        this.limpiarClienteTable();
        this.clienteTable.getItems().addAll(this.clientes);
    }

    @Autowired @Lazy
    public ClienteMainController(StageManager stageManager,
                                 MainController mainController,
                                 ClienteInfoController clienteInfoController){
        this.stageManager = stageManager;
        this.mainController = mainController;
        this.clienteInfoController = clienteInfoController;
    }


}
