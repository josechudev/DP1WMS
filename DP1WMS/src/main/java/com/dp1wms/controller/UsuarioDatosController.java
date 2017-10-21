package com.dp1wms.controller;

import com.dp1wms.model.UsuarioModel.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDatosController implements FxmlController{

    //private final StageManager stageManager;

    @FXML private TextField e_id_datos;
    @FXML private TextField e_nombre_datos;
    @FXML private TextField e_password_datos;
    @FXML private Button e_buton_datos;

    //private ListaUsuario v_listaUsuario;
    private int v_accion;
    private Usuario v_usuario;

    public static UsuarioCtrl v_parentController;

    /*
    @Autowired @Lazy
    public UsuarioDatosController(StageManager stageManager){
        this.stageManager = stageManager;
    }
    */

    //Control de la ventana DatosUsuario
    public void btnClickAceptar_datos(ActionEvent event){
        System.out.println("Aceptar");
        Usuario auxUsuario = new Usuario();
        auxUsuario.setV_id( Integer.parseInt(e_id_datos.getText()));
        auxUsuario.setV_nombre( e_nombre_datos.getText());
        auxUsuario.setV_password( e_password_datos.getText());
        if(this.v_accion == 0){
            v_parentController.crearUsuarioDB(auxUsuario);
        }
        else{
            v_parentController.modificarUsuarioDB(auxUsuario);
            //v_listaUsuario._eliminarUsuario(Integer.parseInt(e_id_datos.getText()));
            //v_listaUsuario._agregarUsuario(Integer.parseInt(e_id_datos.getText()), e_nombre_datos.getText(), e_password_datos.getText());
        }

        System.out.println("Accion realizada");
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UsuarioFxml/MantenimientoUsuario.fxml"));
        UsuarioCtrl controller = loader.getController();
        controller.initialize();
        */

        v_parentController.initialize();

        Stage stage = (Stage) e_buton_datos.getScene().getWindow();
        stage.close();
    }

    public void btnClickCancelar_datos(ActionEvent event){
        System.out.println("Cancelar");
        Stage stage = (Stage) e_buton_datos.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize() {
    }

    public void setV_parentController(UsuarioCtrl v_parentController){
        this.v_parentController = v_parentController;
    }

    public void _setData(Usuario auxUsuario, int v_accion){

        e_id_datos.setText(Integer.toString(auxUsuario.getV_id()));
        e_nombre_datos.setText(auxUsuario.getV_nombre());
        e_password_datos.setText(auxUsuario.getV_password());

        this.v_accion = v_accion;

        if(this.v_accion == 0){
            e_buton_datos.setText("Crear Usuario");
        }
        else{
            e_buton_datos.setText("Modificar Usuario");
        }

        //v_listaUsuario = new ListaUsuario();
    }



}
