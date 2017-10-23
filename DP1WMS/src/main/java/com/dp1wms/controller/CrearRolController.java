package com.dp1wms.controller;


import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.aspectj.org.eclipse.jdt.internal.core.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CrearRolController implements Initializable{

    private final StageManager stageManager;


    @FXML private Button boton_guardar;
    @FXML private Button boton_cancelar;
    @FXML private CheckBox check_usuarios;
    @FXML private CheckBox check_productos;
    @FXML private CheckBox check_almacenes;
    @FXML private CheckBox check_ventas;
    @FXML private TextField text_nombre_rol;

    @Autowired @Lazy
    public CrearRolController(StageManager stageManager){
        this.stageManager = stageManager;
    }


    @FXML
    private void click_boton_guardar(ActionEvent event) {
        System.out.println(text_nombre_rol.getText());

        List<String> permisos = new ArrayList<String>();
        if (check_almacenes.isSelected()){
            permisos.add(check_almacenes.getText());
        }
        if (check_productos.isSelected()){
            permisos.add(check_productos.getText());
        }
        if (check_ventas.isSelected()){
            permisos.add(check_ventas.getText());
        }
        if (check_usuarios.isSelected()){
            permisos.add(check_usuarios.getText());
        }
        System.out.println(permisos);
    }

    @FXML
    private void click_boton_cancelar(ActionEvent event) {
        System.out.println("Boton Cancelar");
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Creación de Roles");
    }
}
