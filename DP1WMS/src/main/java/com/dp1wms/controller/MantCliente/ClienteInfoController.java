package com.dp1wms.controller.MantCliente;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantCliente;
import com.dp1wms.model.Cliente;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ClienteInfoController implements FxmlController{

    @FXML private ComboBox tipoDocumentoCB;
    @FXML private TextField rucField;
    @FXML private TextField nombreField;
    @FXML private TextField telefonoField;
    @FXML private TextField direccionField;
    @FXML private TextField emailField;
    @FXML private CheckBox estadoCB;

    @FXML private Button registrarBtn;
    @FXML private Button guardarBtn;
    @FXML private Button limpiarBtn;

    @Autowired
    private RepositoryMantCliente repositoryMantCliente;

    private StageManager stageManager;

    private Cliente cliente;

    @FXML
    private void registrarCliente(ActionEvent event){
        Cliente c = new Cliente();
        c.setNumDoc(rucField.getText());
        c.setRazonSocial(nombreField.getText());
        c.setTelefono(telefonoField.getText());
        c.setDireccion(direccionField.getText());
        c.setEmail(emailField.getText());
        c.setActivo(estadoCB.isSelected());
        if(c.getNumDoc().isEmpty() || c.getRazonSocial().isEmpty() || c.getTelefono().isEmpty() ||
                c.getDireccion().isEmpty() ){
            this.stageManager.mostrarErrorDialog("Eror cliente", null,
                    "Completar los campos vacios que estan marcos con (*)");
        } else {
            String numDoc = c.getNumDoc();
            if(this.tipoDocumentoCB.getSelectionModel().getSelectedIndex() == 0){
                if (!validarRUC(numDoc)){
                    this.stageManager.mostrarErrorDialog("Error cliente", null,
                            "El RUC ingresado no es válido.");
                    return;
                }
            } else {
                if (!validarDNI(numDoc)){
                    this.stageManager.mostrarErrorDialog("Error cliente", null,
                            "El DNI ingresado no es válido");
                    return;
                }
            }

            this.cliente = this.repositoryMantCliente.registrarCliente(c);
            if(this.cliente == null){
                this.stageManager.mostrarErrorDialog("Eror cliente", null,
                        "Hubo un error al registrar el cliente. Inténtelo de nuevo.");
            } else {
                this.stageManager.mostrarInfoDialog("Cliente", null,
                        "Se registró un nuevo cliente");
                this.cerrarVentana(event);
            }
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
    private void guardarCliente(ActionEvent event){
        String numDoc = rucField.getText();
        String  nombre = nombreField.getText();
        String telefono = telefonoField.getText();
        String direccion = direccionField.getText();
        String email = emailField.getText();
        Boolean activo = estadoCB.isSelected();

        if(cliente.getNumDoc().isEmpty() || cliente.getRazonSocial().isEmpty() ||
                cliente.getTelefono().isEmpty() ||
                cliente.getDireccion().isEmpty() ){
            this.stageManager.mostrarErrorDialog("Eror cliente", null,
                    "Completar los campos vacios que estan marcos con (*)");
        } else {

            if(this.tipoDocumentoCB.getSelectionModel().getSelectedIndex() == 0){
                if (!validarRUC(numDoc)){
                    this.stageManager.mostrarErrorDialog("Error cliente", null,
                            "El RUC ingresado no es válido.");
                    return;
                }
            } else {
                if (!validarDNI(numDoc)){
                    this.stageManager.mostrarErrorDialog("Error cliente", null,
                            "El DNI ingresado no es válido");
                    return;
                }
            }

            cliente.setNumDoc(numDoc);
            cliente.setRazonSocial(nombre);
            cliente.setTelefono(telefono);
            cliente.setDireccion(direccion);
            cliente.setEmail(email);
            cliente.setActivo(activo);

            boolean exito = this.repositoryMantCliente.modificarCliente(cliente);
            if(!exito){
                this.stageManager.mostrarErrorDialog("Eror cliente", null,
                        "Hubo un error al actualizar los datos del cliente. Inténtelo de nuevo.");
                this.cliente = null;
            } else {
                this.stageManager.mostrarInfoDialog("Cliente", null,
                        "Se actualizó los datos del cliente");
            }
            this.cerrarVentana(event);
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    @Override
    public void initialize() {
        registrarBtn.managedProperty().bind(registrarBtn.visibleProperty());
        guardarBtn.managedProperty().bind(guardarBtn.visibleProperty());
        limpiarBtn.managedProperty().bind(limpiarBtn.visibleProperty());

        tipoDocumentoCB.getItems().clear();
        tipoDocumentoCB.getItems().add("RUC");
        tipoDocumentoCB.getItems().add("DNI");

        if(this.cliente == null){
            this.registrarBtn.setDisable(false);
            this.registrarBtn.setVisible(true);

            this.guardarBtn.setDisable(true);
            this.guardarBtn.setVisible(false);

            this.limpiarBtn.setDisable(false);
            this.limpiarBtn.setVisible(true);

            this.estadoCB.setDisable(true);

            this.tipoDocumentoCB.getSelectionModel().select(0);
        } else {
            this.registrarBtn.setDisable(true);
            this.registrarBtn.setVisible(false);

            this.guardarBtn.setDisable(false);
            this.guardarBtn.setVisible(true);

            this.limpiarBtn.setDisable(true);
            this.limpiarBtn.setVisible(false);

            this.estadoCB.setDisable(false);
            this.initCampos();
        }
    }

    private void initCampos(){

        if(this.validarDNI(this.cliente.getNumDoc())){
            this.tipoDocumentoCB.getSelectionModel().select(1);
        } else {
            this.tipoDocumentoCB.getSelectionModel().select(0);
        }

        this.rucField.setText(this.cliente.getNumDoc());
        this.nombreField.setText(this.cliente.getRazonSocial());
        this.telefonoField.setText(this.cliente.getTelefono());
        this.direccionField.setText(this.cliente.getDireccion());
        this.emailField.setText(this.cliente.getEmail());
        this.estadoCB.setSelected(this.cliente.isActivo());
    }

    @Autowired @Lazy
    public ClienteInfoController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    private boolean validarRUC(String ruc){
        Pattern p = Pattern.compile("[0-9]{11}");
        return p.matcher(ruc).matches();
    }

    private boolean validarDNI(String dni){
        Pattern p = Pattern.compile("[0-9]{8}");
        return p.matcher(dni).matches();
    }


}
