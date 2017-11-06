package com.dp1wms.controller.usuarioController;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.Empleado;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.model.UsuarioModel.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioDatosController implements FxmlController {

    //private final StageManager stageManager
    private static final int DNIsize = 8;

    @FXML private TextField e_id_datos, e_nombre_datos,e_password_datos;
    @FXML private TextField e_id_empleado, e_numDoc_empleado, e_nombre_empleado, e_apellido_empleado, e_email_empleado;
    @FXML private TextField e_id_tipoEmpleado, e_descripcion_tipoEmpleado;
    @FXML private Button e_buton_datos;

    @FXML private ComboBox e_tipoEmpleado;

    @FXML private CheckBox e_modPassword;

    private boolean v_crear;
    private Usuario v_usuario;

    private boolean v_ModificarPassword;

    public static UsuarioCtrl v_parentController;

    public void btnClickAceptar_datos(ActionEvent event){
        System.out.println("Aceptar");
        Usuario auxUsuario = new Usuario();
        auxUsuario.setV_id( Integer.parseInt(e_id_datos.getText()));
        auxUsuario.setV_nombre( e_nombre_datos.getText());
        auxUsuario.setV_password( e_password_datos.getText());
        Empleado auxEmpleado = new Empleado();
        auxEmpleado.setIdempleado(Long.parseLong(e_id_empleado.getText()));
        auxEmpleado.setIdusuario(Integer.parseInt(e_id_datos.getText()));
        auxEmpleado.setNumDoc(e_numDoc_empleado.getText());
        auxEmpleado.setNombre(e_nombre_empleado.getText());
        auxEmpleado.setApellidos(e_apellido_empleado.getText());
        auxEmpleado.setEmail(e_email_empleado.getText());

        TipoEmpleado auxTipoEmpleado = new TipoEmpleado();
        auxTipoEmpleado.setIdtipoempleado(Long.parseLong(e_id_tipoEmpleado.getText()));
        auxTipoEmpleado.setDescripcion(e_descripcion_tipoEmpleado.getText());

        if( auxEmpleado.getNumDoc().length() != DNIsize ){
            v_parentController.mostrarErrorDialog("Error usuario",
                    "El dni brindado no es valido.");
            return;
        }

        if(this.v_crear){
            if( v_parentController.existeUsuarioDB(auxUsuario.getV_nombre()) ){
                v_parentController.mostrarErrorDialog("Error usuario",
                        "El nombre de usuario " + auxUsuario.getV_nombre() + " ya se encuentra en uso.");
                return;
            }
            v_parentController.crearUsuarioDB(auxUsuario);
            v_parentController.crearEmpleadoDB(auxUsuario, auxEmpleado, auxTipoEmpleado);

            v_parentController.mostrarInfoDialog("Creacion Usuario",
                    "Se creo el usuario " + auxUsuario.getV_nombre() + " con exito.");
        }
        else{
            if(!v_parentController.coincideUsuarioIdDB(auxUsuario.getV_nombre(), auxUsuario.getV_id()))
                if( v_parentController.existeUsuarioDB(auxUsuario.getV_nombre()) ){
                    v_parentController.mostrarErrorDialog("Error usuario",
                            "El nombre de usuario " + auxUsuario.getV_nombre() + " ya se encuentra en uso.");
                    return;
                }
            v_parentController.modificarUsuarioDB(auxUsuario, v_ModificarPassword);
            v_parentController.modificarEmpleadoDB(auxUsuario, auxEmpleado, auxTipoEmpleado);

            v_parentController.mostrarInfoDialog("Modificar Usuario",
                    "Se modifico el usuario " + auxUsuario.getV_nombre() + " con exito.");
        }
        System.out.println("Accion realizada");

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
        e_numDoc_empleado.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(DNIsize));
    }

    public void setV_parentController(UsuarioCtrl v_parentController){
        this.v_parentController = v_parentController;
    }

    public void _setData(Usuario auxUsuario, Empleado auxEmpleado, TipoEmpleado auxTipoEmpleado, boolean v_crear){

        e_id_datos.setText("0");
        e_id_empleado.setText("0");
        e_id_tipoEmpleado.setText("0");
        this.v_crear = v_crear;
        v_ModificarPassword = false;
        List<TipoEmpleado> iniciarTipoEmpleado = v_parentController.llenarGrillaTipoEmpleado();
        for(int i = 0; i < iniciarTipoEmpleado.size(); i++) {
            e_tipoEmpleado.getItems().add(iniciarTipoEmpleado.get(i).getDescripcion());
        }
        if(this.v_crear){
            e_modPassword.setVisible(false);
            e_tipoEmpleado.getSelectionModel().select(iniciarTipoEmpleado.get(0).getDescripcion());
            _setTipoEmpleado(iniciarTipoEmpleado.get(0));
            e_buton_datos.setText("Crear Usuario");
        }
        else{
            e_modPassword.setVisible(true);
            e_modPassword.setSelected(false);
            e_id_datos.setText(Integer.toString(auxUsuario.getV_id()));
            e_nombre_datos.setText(auxUsuario.getV_nombre());
            e_password_datos.setText("");

            e_id_empleado.setText(Long.toString(auxEmpleado.getIdempleado()));
            e_numDoc_empleado.setText(auxEmpleado.getNumDoc());
            e_nombre_empleado.setText(auxEmpleado.getNombre());
            e_apellido_empleado.setText(auxEmpleado.getApellidos());
            e_email_empleado.setText(auxEmpleado.getEmail());

            e_tipoEmpleado.getSelectionModel().select(auxTipoEmpleado.getDescripcion());
            e_id_tipoEmpleado.setText(Long.toString(auxTipoEmpleado.getIdtipoempleado()));
            e_descripcion_tipoEmpleado.setText(auxTipoEmpleado.getDescripcion());
            e_buton_datos.setText("Modificar Usuario");
        }


    }

    public void btnClickSeleccionarTipoEmpleado(ActionEvent event) {
    }

    public void ComboBoxSelectTipoUsuario(ActionEvent event){
        TipoEmpleado AuxTipoEmpleado = v_parentController.obtenerTipoEmpleadoPorDescripcion( e_tipoEmpleado.getValue().toString() );
        _setTipoEmpleado(AuxTipoEmpleado);
    }

    public void checkBoxAction(ActionEvent event){
        if(e_modPassword.isSelected())
            v_ModificarPassword = true;
        else
            v_ModificarPassword = false;
    }

    public void _setTipoEmpleado(TipoEmpleado auxTipoEmpleado){
        e_id_tipoEmpleado.setText(Long.toString(auxTipoEmpleado.getIdtipoempleado()));
        e_descripcion_tipoEmpleado.setText(auxTipoEmpleado.getDescripcion());
    }

    public List<TipoEmpleado> llenarGrillaTipoEmpleado(){
        return v_parentController.llenarGrillaTipoEmpleado();
    }




    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if(e.getCharacter().matches("[0-9.]")){
                    if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                        e.consume();
                    }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                        e.consume();
                    }
                }else{
                    e.consume();
                }
            }
        };
    }
}
