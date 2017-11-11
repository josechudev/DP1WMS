package com.dp1wms.controller.DevolucionPedido;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryDevoluciones;
import com.dp1wms.model.ComprobantePago;
import com.dp1wms.model.DetalleComprobantePago;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DetalleDevolucionController implements FxmlController {

    @FXML
    private TextField txb_tipocomprobante;
    @FXML
    private TextField txb_cliente;
    @FXML
    private TextField txb_total;@FXML
    private TableView<DetalleComprobantePago> tablaDetalleComprobante = new TableView<DetalleComprobantePago>();
    @FXML
    private TableColumn<DetalleComprobantePago,Integer> c_indice;
    @FXML
    private TableColumn<DetalleComprobantePago,String> c_codigoproducto;
    @FXML
    private TableColumn<DetalleComprobantePago,String> c_nombreproducto;
    @FXML
    private TableColumn<DetalleComprobantePago,Integer> c_cantidad;

    private ComprobantePago facturaEscogida;

    private Long idEmpleadoAuditado;

    private ListaFacturasController listaFacturasController;

    private final StageManager stageManager;

    @Autowired
    private RepositoryDevoluciones repositoryDevoluciones;

    @Autowired
    @Lazy
    public DetalleDevolucionController(StageManager stageManager, ListaFacturasController listaFacturasController) {
        this.stageManager = stageManager;
        this.listaFacturasController = listaFacturasController;
    }

    public void aceptarPedidoDevolucion(ActionEvent event){
        this.repositoryDevoluciones.registrarPedidoDevolucion(this.idEmpleadoAuditado,facturaEscogida.getV_id(),facturaEscogida.getV_idEnvio());
        this.listaFacturasController.actualizarLista();
        this.stageManager.cerrarVentana(event);
    }

    public void cancelarOperacion(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    public void limpiarTabla(){
        tablaDetalleComprobante.getItems().clear();
        c_indice.setCellValueFactory(new PropertyValueFactory<DetalleComprobantePago,Integer>("indiceTabla"));
        c_codigoproducto.setCellValueFactory(new PropertyValueFactory<DetalleComprobantePago,String>("codigoProducto"));
        c_nombreproducto.setCellValueFactory(new PropertyValueFactory<DetalleComprobantePago,String>("nombreProducto"));
        c_cantidad.setCellValueFactory(new PropertyValueFactory<DetalleComprobantePago,Integer>("cantidad"));
        tablaDetalleComprobante.setEditable(true);
    }

    public void llenarTabla(List<DetalleComprobantePago> lista){
        Integer indice = 1;
        for(DetalleComprobantePago detalleComprobantePago : lista){
            detalleComprobantePago.setIndiceTabla(indice);
            indice++;
            this.tablaDetalleComprobante.getItems().add(detalleComprobantePago);
        }
    }

    @Override
    public void initialize() {
        this.txb_cliente.setDisable(true);
        this.txb_tipocomprobante.setDisable(true);
        this.txb_total.setDisable(true);

        this.idEmpleadoAuditado = this.listaFacturasController.obtenerIdEmpleadoAuditado();
        this.facturaEscogida = this.listaFacturasController.obtenerFacturaEscogida();
        this.txb_cliente.setText(facturaEscogida.getV_cliente());
        this.txb_tipocomprobante.setText(facturaEscogida.getV_tipoComprobante());
        this.txb_total.setText(""+facturaEscogida.getV_total());

        this.limpiarTabla();
        this.llenarTabla(facturaEscogida.getListaDetalleComprobante());
    }
}
