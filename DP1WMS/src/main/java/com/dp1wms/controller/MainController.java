package com.dp1wms.controller;

import com.dp1wms.model.Empleado;
import com.dp1wms.model.Usuario;
import com.dp1wms.view.ClientesView;
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

    private final UsuarioCtrl usuarioCtrl;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RepositoryMantEmpleado repositoryMantEmpleado;

    @Autowired @Lazy
    public MainController(StageManager stageManager, UsuarioCtrl usuarioCtrl){
        this.stageManager = stageManager;
        this.usuarioCtrl = usuarioCtrl;
    }

    @Override
    public void initialize(){
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
/*
    @FXML
    private void cargarMantenimientoMovimientos(ActionEvent event) {
        System.out.println("cargarMantenimientoMovimientos");
        this.stageManager.mostrarModal(MainView.MANTENIMIENTO_MOVIMIENTO);
    }
*/
    @FXML
    private void cargarMantenimientoUsuario(ActionEvent event) {
        System.out.println("cargarMantenimientoUsuario");
        this.usuarioCtrl.setUsuario(usuario);
        this.stageManager.mostrarModal(MainView.MANTENIMIENTO_USUARIO);
    }

    @FXML
    private void cargarMantenimientoCliente(){
        this.stageManager.mostrarModal(ClientesView.MAIN);
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
    private void cargarMantenimientoAlmacenes(ActionEvent event) {
        System.out.println("cargarMantenimientoAlmacenes");
        this.stageManager.mostrarModal(MainView.MANTENIMIENTO_ALMACEN);
    }

    @FXML
    private void cargarCrearLote(ActionEvent event) {
        System.out.println("cargarCrearLote");
        this.stageManager.mostrarModal(MainView.CREAR_LOTE);
    }

    @FXML
    private void cargarIngresoProducto(ActionEvent event) {
        System.out.println("cargarIngreso/SalidaProducto");
        this.stageManager.mostrarModal(MainView.INGRESO_PRODUCTO);
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

    @FXML
    private void cargarGenerarPedido(){
        this.stageManager.mostrarModal(VentasView.GEN_PEDIDO);
    }

    @FXML
    private void cargarListaEnviosPendientes(){
        this.stageManager.mostrarModal(MainView.LISTAR_ENVIOS);
    }

    @FXML
    private void cargarListaGuias(){
        this.stageManager.mostrarModal(MainView.LISTAR_GUIA);
    }
    @FXML
    private void cargarKardex(){
        this.stageManager.mostrarModal(MainView.CARGAR_KARDEX);
    }

    @FXML
    private void cargarDevolucionFactura(){
        System.out.println("cargarDevolucionPedido");
        this.stageManager.mostrarModal(MainView.DEVOLVER_PEDIDO);
    }

    @FXML
    private void cargarConsultarPedido(){
        this.stageManager.mostrarModal(VentasView.CONSULTAR_PEDIDO);
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public Empleado getEmpleado(){
        return this.empleado;
    }
}
