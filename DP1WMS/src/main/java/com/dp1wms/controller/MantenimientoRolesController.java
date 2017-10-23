package com.dp1wms.controller;

import com.dp1wms.dao.impl.RespositoryMantTipoEmpleadoImpl;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MantenimientoRolesController implements Initializable {


    @FXML private ListView<String> lista_roles;
    @FXML private Button boton_nuevo;


    private final StageManager stageManager;
    public RespositoryMantTipoEmpleadoImpl repo_tipo_empleado;

    @Autowired @Lazy
    public MantenimientoRolesController(StageManager stageManager){
        this.stageManager = stageManager;
    }


    public void llenarTiposEmpleado(){
        List<TipoEmpleado> tipos_empleado = repo_tipo_empleado.selectAllTipoEmpleado();
        for (TipoEmpleado empleado: tipos_empleado) {
            System.out.println(empleado.getDescripcion());
        }

    }

    @FXML
    private void click_boton_nuevo(ActionEvent event) {
        System.out.println("Nuevo Rol");
        this.llenarTiposEmpleado();
        this.stageManager.mostrarModal(MainView.NUEVO_TIPOEMPLEADO);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
