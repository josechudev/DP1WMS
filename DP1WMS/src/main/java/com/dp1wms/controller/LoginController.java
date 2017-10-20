package com.dp1wms.controller;

import com.dp1wms.model.Usuario;
import com.dp1wms.view.FxmlView;
import com.dp1wms.view.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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


    private final StageManager stageManager;

    /**
     * Necesitan ser inicializadas
     */
    private UsuarioCtrl usuarioCtrl;

    @Autowired @Lazy
    public LoginController(StageManager stageManager){
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(){
        this.usuarioCtrl = new UsuarioCtrl();
    }
    /**
     *  Revisar las credenciales de acceso (nombre de usuario y contraseña)
     *
     */
    @FXML
    public void onClickIngresarBtn(){

        String username = getUsername();
        String password = getPassword();
        if (usuarioCtrl.verificarCredenciales(username, password)){
            this.stageManager.cambiarScene(FxmlView.MAIN);
        } else {
            this.borrarCredenciales();
            statusLabel.setText("Nombre de usuario o contraseña inválidos");
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
