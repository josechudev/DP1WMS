package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantCliente;
import com.dp1wms.model.Cliente;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class RegistrarClienteController implements FxmlController{

    @FXML private TextField rucField;
    @FXML private TextField nombreField;
    @FXML private TextField telefonoField;
    @FXML private TextField direccionField;
    @FXML private TextField emailField;

    @Autowired
    private RepositoryMantCliente repositoryMantCliente;

    private final StageManager stageManager;
    private final ProformaController proformaController;

    @Override
    public void initialize(){
        this.limpiarCampos();
    }

    @Autowired @Lazy
    public RegistrarClienteController(StageManager stageManager, ProformaController proformaController){
        this.stageManager = stageManager;
        this.proformaController = proformaController;
    }

    @FXML
    private void registrarCliente(ActionEvent event){
        Cliente c = new Cliente();
        c.setNumDoc(rucField.getText());
        c.setRazonSocial(nombreField.getText());
        c.setTelefono(telefonoField.getText());
        c.setDireccion(direccionField.getText());
        c.setEmail(emailField.getText());
        if(c.getNumDoc().isEmpty() || c.getRazonSocial().isEmpty() || c.getTelefono().isEmpty() ||
                c.getDireccion().isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Completar los campos vacios que estan marcos con (*)");
            alert.showAndWait();
        } else {
            c = this.repositoryMantCliente.registrarCliente(c);
            this.proformaController.setCliente(c);
            this.cerrarVentana(event);
        }
    }

    @FXML
    private void limpiarCampos(){
        rucField.clear();
        nombreField.clear();
        telefonoField.clear();
        direccionField.clear();
        emailField.clear();
    }

    @FXML
    private void cerrarVentana(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
