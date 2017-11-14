package com.dp1wms.controller;

import com.dp1wms.controller.Factura.MantenimientoFacturaController;
import com.dp1wms.controller.UsuarioController.UsuarioCtrl;
import com.dp1wms.dao.RepositoryMantTipoEmpleado;
import com.dp1wms.model.Empleado;
import com.dp1wms.model.Seccion;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.model.Usuario;
import com.dp1wms.view.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import com.dp1wms.dao.RepositoryMantEmpleado;

import java.util.HashMap;
import java.util.List;

@Component
public class MainController implements FxmlController {

    private Usuario usuario;
    private Empleado empleado;

    //menu items
    //Administracion
    @FXML private MenuItem rolesMI; @FXML private MenuItem usuariosMI;
    @FXML private MenuItem clientesMI; @FXML private MenuItem categoriasMI;
    @FXML private MenuItem productosMI; @FXML private MenuItem ubicacionesMI;
    //Almacenes
    @FXML private MenuItem almacenesCMMI; @FXML private MenuItem almacenesTransMI;
    @FXML private MenuItem almacenesISProdMI; @FXML private MenuItem almacenLoteMI;
    @FXML private MenuItem almacenDespachoMI;
    //Ventas
    @FXML private MenuItem ventaCondComMI; @FXML private MenuItem ventaProformaMI;
    @FXML private MenuItem ventaConsPedMI; @FXML private MenuItem ventaGenPedMI;
    @FXML private MenuItem ventaGuiaMI; @FXML private MenuItem ventaCompPagoMI;
    @FXML private MenuItem ventaPedDevMI; @FXML private MenuItem ventaNotaCreditoMI;
    //Reporte
    @FXML private MenuItem reportKardexMI; @FXML private MenuItem reporteAlmacenMI;


    @FXML
    private Label nombreEmpleadoLabel;
    @FXML
    private Label tipoEmpleadoLabel;

    private final StageManager stageManager;

    private final UsuarioCtrl usuarioCtrl;
    private final MantenimientoFacturaController mantenimientoFacturaController;

    @Autowired
    private RepositoryMantTipoEmpleado repositoryMantTipoEmpleado;

    @Autowired
    private RepositoryMantEmpleado repositoryMantEmpleado;

    @Autowired @Lazy
    public MainController(StageManager stageManager, UsuarioCtrl usuarioCtrl, MantenimientoFacturaController mantenimientoFacturaController){
        this.stageManager = stageManager;
        this.usuarioCtrl = usuarioCtrl;
        this.mantenimientoFacturaController = mantenimientoFacturaController;
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
            this.initMenus();
        }
    }

    private void initMenus(){
        //hash menu items
        HashMap<Integer, MenuItem> menus = new HashMap<>();
        //administracion
        menus.put(1, this.rolesMI); menus.put(2, this.usuariosMI);
        menus.put(3, this.clientesMI); menus.put(4, this.categoriasMI);
        menus.put(5, this.productosMI); menus.put(6, this.ubicacionesMI);
        //Almacenes
        menus.put(7, this.almacenesCMMI); menus.put(8, this.almacenesTransMI);
        menus.put(9, this.almacenesISProdMI); menus.put(10, this.almacenLoteMI);
        menus.put(11, this.almacenDespachoMI);
        //Ventas
        menus.put(12, this.ventaCondComMI); menus.put(13, this.ventaProformaMI);
        menus.put(14, this.ventaConsPedMI); menus.put(15, this.ventaGenPedMI);
        menus.put(16, this.ventaGuiaMI); menus.put(17, this.ventaCompPagoMI);
        menus.put(18, this.ventaPedDevMI); menus.put(19, this.ventaNotaCreditoMI);
        //Reporte
        menus.put(20, this.reportKardexMI); menus.put(21, this.reporteAlmacenMI);

        TipoEmpleado te = this.empleado.getTipoEmpleado();
        //cargar secciones
        List<Seccion> secciones = this.repositoryMantTipoEmpleado.obtenerSeccionesDeTipoEmpleado(te.getIdtipoempleado());

        if(secciones != null){
            for(Seccion seccion: secciones){
                MenuItem mi = menus.get(seccion.getIdSeccion());
                if(mi != null){
                    mi.setDisable(!seccion.isSeleccionado());
                }
            }
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
        this.stageManager.mostrarModal(TipoEmpleadoView.MAIN);
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
    private void cargarReporteAlmacen(){
        this.stageManager.mostrarModal(MainView.REPORTE_ALMACEN);
    }

    @FXML
    private void cargarDevolucionFactura(){
        System.out.println("cargarDevolucionPedido");
        this.stageManager.mostrarModal(FacturaView.MANTENIMIENTO_FACTURA);
    }

    @FXML
    private void cargarConsultarPedido(){
        this.stageManager.mostrarModal(VentasView.CONSULTAR_PEDIDO);
    }

    @FXML
    private void cargarMantenimientoComprobantePago(){
        System.out.println("cargarDevolucionPedido");
        this.mantenimientoFacturaController.setUsuario(usuario);
        this.stageManager.mostrarModal(FacturaView.MANTENIMIENTO_FACTURA);
    }

    @FXML
    private void cargarPedidoDevolucion(){
        System.out.println("cargarPedidoDevolucion");
        this.stageManager.mostrarModal(MainView.LISTA_FACTURAS_DEVOLUCION);
    }

    @FXML
    private void cargarListaLotes(){
        this.stageManager.mostrarModal(MainView.LISTA_LOTES);
    }

    @FXML
    private void cargarListaNotaCredito(){
        this.stageManager.mostrarModal(MainView.LISTA_NOTA_CREDITO);
    }
    @FXML
    private void cargarUbicaciones(){
        this.stageManager.mostrarModal(MainView.UBICACIONES);
    }

    @FXML
    private void cargarGenerarRuta(){
        this.stageManager.mostrarModal(AlmacenView.TABU);
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public Empleado getEmpleado(){
        return this.empleado;
    }

    @FXML
    private void menuAuditoriaClick(ActionEvent event){
        this.stageManager.mostrarModal(MainView.REPORTE_AUDITORIA);
    }
}
