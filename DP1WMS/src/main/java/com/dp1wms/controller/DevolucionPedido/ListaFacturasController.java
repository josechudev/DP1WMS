package com.dp1wms.controller.DevolucionPedido;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryDevoluciones;
import com.dp1wms.model.ComprobantePago;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListaFacturasController implements FxmlController {

    @FXML
    private TableView<ComprobantePago> tablaFacturas = new TableView<ComprobantePago>();
    @FXML
    private TableColumn<ComprobantePago,Integer> c_indice;
    @FXML
    private TableColumn<ComprobantePago,String> c_tipocomprobante;
    @FXML
    private TableColumn<ComprobantePago,String> c_fechaemision;
    @FXML
    private TableColumn<ComprobantePago,String> c_cliente;
    @FXML
    private TableColumn<ComprobantePago,Float> c_total;

    private MainController mainController;

    private final StageManager stageManager;

    private List<ComprobantePago> listaComprobantes;

    private ComprobantePago facturaEscogida;

    private Long idEmpleadoAuditado;


    @Autowired
    private RepositoryDevoluciones repositoryDevoluciones;

    @Autowired
    @Lazy
    public ListaFacturasController(StageManager stageManager, MainController mainController) {
        this.stageManager = stageManager;
        this.mainController = mainController;
    }

    public void cancelarOperacion(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    public void generarPedidoDevolucion(ActionEvent event) {
        if (tablaFacturas.getSelectionModel().getSelectedItem() != null) {
            this.stageManager.mostrarModal(MainView.DETALLE_DEVOLUCION);

        }else{
            this.stageManager.mostrarErrorDialog("Error Creacion Pedido de Devolucion", null,
                    "Debe seleccionar una factura para crear el pedido de devolucion");
            return;
        }
    }

    public ComprobantePago obtenerFacturaEscogida(){
        return tablaFacturas.getSelectionModel().getSelectedItem();
    }

    public void actualizarLista(){
        this.listaComprobantes = this.repositoryDevoluciones.obtenerFacturas(true);
        this.limpiarTabla();
        this.llenarTabla(listaComprobantes);
    }

    public Long obtenerIdEmpleadoAuditado(){
        return this.idEmpleadoAuditado;
    }


    public void limpiarTabla(){
        tablaFacturas.getItems().clear();
        c_indice.setCellValueFactory(new PropertyValueFactory<ComprobantePago,Integer>("indiceTabla"));
        c_tipocomprobante.setCellValueFactory(new PropertyValueFactory<ComprobantePago,String>("v_tipoComprobante"));
        c_cliente.setCellValueFactory(new PropertyValueFactory<ComprobantePago,String>("v_cliente"));
        c_fechaemision.setCellValueFactory(new PropertyValueFactory<ComprobantePago,String>("v_fechaCreacion"));
        c_total.setCellValueFactory(new PropertyValueFactory<ComprobantePago,Float>("v_total"));
        tablaFacturas.setEditable(true);
    }

    public void llenarTabla(List<ComprobantePago> lista){
        Integer indice = 1;
        for(ComprobantePago comprobantePago : lista){
            comprobantePago.setIndiceTabla(indice);
            indice++;
            this.tablaFacturas.getItems().add(comprobantePago);
        }
    }

    @Override
    public void initialize() {
        this.idEmpleadoAuditado = this.mainController.getEmpleado().getIdempleado();
        this.actualizarLista();
        /*this.listaComprobantes = this.repositoryDevoluciones.obtenerFacturas(true);
        this.limpiarTabla();
        this.llenarTabla(listaComprobantes);*/
    }
}
