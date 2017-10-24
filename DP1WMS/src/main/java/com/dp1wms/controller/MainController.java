package com.dp1wms.controller;

import com.dp1wms.model.Empleado;
import com.dp1wms.model.Usuario;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import com.dp1wms.view.VentasView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import com.dp1wms.dao.RepositoryMantEmpleado;

@Component
public class MainController implements FxmlController {

    private Usuario usuario;
    private Empleado empleado;


    @FXML
    private Label nombreEmpleadoLabel;
    @FXML
    private Label tipoEmpleadoLabel;

    private final StageManager stageManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RepositoryMantEmpleado repositoryMantEmpleado;

    @Autowired @Lazy
    public MainController(StageManager stageManager){
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(){
        /**
         * Cargar información del empleado
         */
        long idusuario = this.usuario.getIdusuario();
        this.empleado = repositoryMantEmpleado.obtenerEmpleadoPorIdUsuario(idusuario);
        if(this.empleado == null){
            //Algún error - revisar consola
            System.exit(0);
        } else {
            //Cargar subcontrollador
            this.nombreEmpleadoLabel.setText("Bienvenido, " + this.empleado.getNombre());
            this.tipoEmpleadoLabel.setText(this.empleado.getTipoEmpleado().getDescripcion());
        }
    }

    @FXML
    private void cargarMantenimientoMovimientos(ActionEvent event) {
        System.out.println("cargarMantenimientoMovimientos");
        this.stageManager.mostrarModal(MainView.MANTENIMIENTO_MOVIMIENTO);
    }

    @FXML
    private void cargarMantenimientoUsuario(ActionEvent event) {
        System.out.println("cargarMantenimientoUsuario");
        this.stageManager.mostrarModal(MainView.MANTENIMIENTO_USUARIO);
    }

    @FXML
    private void cargarMantenimientoRol(ActionEvent event) {
        System.out.println("cargarMantenimientoTipoEmpleado");
        this.stageManager.mostrarModal(MainView.MANTENIMIENTO_TIPOEMPLEADO);
    }


    @FXML
    private void cargarMantenimientoCategoria(ActionEvent event) {
        System.out.println("cargarMantenimientoCategoria");
        this.stageManager.mostrarModal(MainView.MANTENIMIENTO_CATEGORIA);
    }

    @FXML
    private void cargarMantenimientoProducto(ActionEvent event) {
        System.out.println("cargarMantenimientoProducto");
        this.stageManager.mostrarModal(MainView.MANTENIMIENTO_PRODUCTO);
    }


    @FXML
    private void cerrarSesion(){
        this.usuario = null;
        this.stageManager.cambiarScene(MainView.LOGIN);
    }

    @FXML
    private void cargarMantenimientoDescuentos(){
        this.stageManager.mostrarModal(MainView.MANTENIMIENTO_DESCUENTO);
    }


    @FXML
    private void cargarGenerarProforma(){
        this.stageManager.mostrarModal(VentasView.GEN_PROFORMA);
    }



    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public Empleado getEmpleado(){
        return this.empleado;
    }
}
