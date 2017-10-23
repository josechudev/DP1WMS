package com.dp1wms.controller;

import com.dp1wms.dao.RepositorySeguridad;
import com.dp1wms.model.Usuario;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class LoginController implements FxmlController{

    @FXML
    private TextField userField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label statusLabel;

    @Autowired
    private RepositorySeguridad repositorySeguridad;

    private final StageManager stageManager;
    private final MainController mainController;

    @Autowired @Lazy
    public LoginController(StageManager stageManager, MainController mainController){
        this.stageManager = stageManager;
        this.mainController = mainController;
    }

    @Override
    public void initialize(){
    }
    /**
     *  Revisar las credenciales de acceso (nombre de usuario y contraseña)
     *
     */
    @FXML
    public void onClickIngresarBtn(){

        String username = getUsername();
        String password = getPassword();
        Usuario usuario = new Usuario();
        usuario.setNombreusuario(username);
        usuario.setPassword(password);
        if ((usuario = repositorySeguridad.validarCredenciales(usuario)) != null){
            this.clearOutStatusLabel();
            this.mainController.setUsuario(usuario);
            this.stageManager.cambiarScene(MainView.MAIN);
        } else {
            this.borrarCredenciales();
            statusLabel.setText("Nombre de usuario o contraseña inválidos");
        }
    }

    @FXML
    private void onKeyPressedIngresar(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            this.onClickIngresarBtn();
        }
    }

    private void borrarCredenciales() {
        userField.clear();
        passwordField.clear();
    }

    private void clearOutStatusLabel() {
        statusLabel.setText("");
    }

    public String getUsername() {
        return userField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }
}
