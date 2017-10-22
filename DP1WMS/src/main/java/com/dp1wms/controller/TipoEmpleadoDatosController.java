package com.dp1wms.controller;

import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.model.UsuarioModel.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class TipoEmpleadoDatosController implements FxmlController{

    //private final StageManager stageManager;

    @FXML private TextField e_id;
    @FXML private TextField e_descripcion;

    @FXML private Label e_labelId;

    @FXML private Button e_buton_datos;

    //private ListaUsuario v_listaUsuario;
    private int v_accion;
    private Usuario v_usuario;

    public static MantenimientoTipoEmpleadoController v_parentController;

    /*
    @Autowired @Lazy
    public UsuarioDatosController(StageManager stageManager){
        this.stageManager = stageManager;
    }
    */

    //Control de la ventana DatosUsuario
    public void btnClickAceptar_datos(ActionEvent event){
        System.out.println("Aceptar");
        TipoEmpleado auxTipoEmpleado = new TipoEmpleado();
        auxTipoEmpleado.setIdtipoempleado(Long.parseLong(e_id.getText()));
        auxTipoEmpleado.setDescripcion(e_descripcion.getText());
        if(this.v_accion == 0){
            v_parentController.crearTipoEmpleadoDB(auxTipoEmpleado);
        }
        else{
            v_parentController.modificarTipoEmpleadoDB(auxTipoEmpleado);
        }

        System.out.println("Accion realizada");

        v_parentController.inicializarGrilla();

        Stage stage = (Stage) e_buton_datos.getScene().getWindow();
        stage.close();
    }

    public void btnClickCancelar_datos(ActionEvent event){
        System.out.println("Cancelar");
        Stage stage = (Stage) e_descripcion.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize() {
    }

    public void setV_parentController(MantenimientoTipoEmpleadoController v_parentController){
        this.v_parentController = v_parentController;
    }

    public void _setData(TipoEmpleado auxTipoUsuario, int v_accion){

        this._setTipoEmpleado(auxTipoUsuario);
        this.v_accion = v_accion;

        if(this.v_accion == 0){
            e_buton_datos.setText("Crear Tipo Empleado");
            e_labelId.setVisible(false);
            e_id.setVisible(false);
        }
        else{
            e_buton_datos.setText("Modificar Tipo Empleado");
            e_labelId.setVisible(true);
            e_id.setVisible(true);
        }
    }

    public void _setTipoEmpleado(TipoEmpleado auxTipoEmpleado){
        e_id.setText(Long.toString(auxTipoEmpleado.getIdtipoempleado()));
        e_descripcion.setText(auxTipoEmpleado.getDescripcion());
    }

}
