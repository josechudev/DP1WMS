package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.model.Cliente;
import com.dp1wms.model.Envio;
import com.dp1wms.model.Proforma;
import com.dp1wms.view.FxmlView;
import com.dp1wms.view.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProformaController implements FxmlController{

    @FXML private Label codigoLabel;
    @FXML private Label nombreLabel;
    @FXML private Label telefonoLabel;
    @FXML private Label emailLabel;
    @FXML private Label rucLabel;
    @FXML private Label direccionLabel;
    @FXML private TableView proformaTable;
    @FXML private TableView enviosTable;

    private Cliente cliente;
    private Proforma proforma;
    private ArrayList<Envio> envios;

    private final StageManager stageManager;
    private final MainController mainController;

    @Override
    public void initialize(){
        this.cliente = null;
        this.proforma = new Proforma();
        this.envios = new ArrayList<Envio>();
    }

    @Autowired @Lazy
    public ProformaController(StageManager stageManager, MainController mainController){
        this.stageManager = stageManager;
        this.mainController = mainController;
    }

    @FXML
    private void mostrarBusquedaCliente(){
        this.stageManager.mostrarModal(FxmlView.BUSCAR_CLIENTE);
    }

    @FXML
    private void mostrarRegistrarCliente(){
        this.stageManager.mostrarModal(FxmlView.REGISTRAR_CLIENTE);
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        this.codigoLabel.setText(String.valueOf(cliente.getIdCliente()));
        this.nombreLabel.setText(cliente.getRazonSocial());
        this.rucLabel.setText(cliente.getNumDoc());
        this.telefonoLabel.setText(cliente.getTelefono());
        this.emailLabel.setText(cliente.getEmail());
        this.direccionLabel.setText(cliente.getDireccion());
    }

}
