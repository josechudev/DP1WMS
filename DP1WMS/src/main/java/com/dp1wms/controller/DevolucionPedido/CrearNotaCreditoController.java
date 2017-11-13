package com.dp1wms.controller.DevolucionPedido;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryDevoluciones;
import com.dp1wms.model.ComprobantePago;
import com.dp1wms.model.DetalleFactura;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CrearNotaCreditoController implements FxmlController {

    @FXML
    private TableView<DetalleFactura> tablaDetalleComprobante = new TableView<DetalleFactura>();
    @FXML
    private TableColumn<DetalleFactura,Integer> c_indice;
    @FXML
    private TableColumn<DetalleFactura,String> c_codigoproducto;
    @FXML
    private TableColumn<DetalleFactura,String> c_nombreproducto;
    @FXML
    private TableColumn<DetalleFactura,Integer> c_cantidad;
    @FXML
    private TableColumn<DetalleFactura,Double> c_precio;
    @FXML
    private TableColumn<DetalleFactura,String> c_subtotal;
    @FXML
    private TextField txb_nombreCliente;
    @FXML
    private TextField txb_numComprobante;
    @FXML
    private TextField txb_direccion;
    @FXML
    private TextField txb_subtotal;
    @FXML
    private TextField txb_igv;
    @FXML
    private TextField txb_flete;
    @FXML
    private TextField txb_total;
    @FXML
    private TextField txb_numDocCliente;
    @FXML
    private TextField txb_totaligv;

    private ListaPedidoDevolucionController listaPedidoDevolucionController;

    private ComprobantePago facturaEscogida;

    private final StageManager stageManager;

    private Long idEmpleadoAuditado;

    @Autowired
    private RepositoryDevoluciones repositoryDevoluciones;

    @Autowired
    @Lazy
    public CrearNotaCreditoController(StageManager stageManager, ListaPedidoDevolucionController listaPedidoDevolucionController) {
        this.stageManager = stageManager;
        this.listaPedidoDevolucionController = listaPedidoDevolucionController;
    }


    public void registrarNotaCredito(ActionEvent event){
        this.repositoryDevoluciones.registrarNotaCredito(this.idEmpleadoAuditado,facturaEscogida.getV_id(),facturaEscogida.getV_idCliente());
        this.listaPedidoDevolucionController.actualizarLista();
        this.stageManager.cerrarVentana(event);
    }

    public void cancelarOperacion(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    public void limpiarTabla(){
        tablaDetalleComprobante.getItems().clear();
        c_indice.setCellValueFactory(new PropertyValueFactory<DetalleFactura,Integer>("indiceTabla"));
        c_codigoproducto.setCellValueFactory(new PropertyValueFactory<DetalleFactura,String>("codigoProducto"));
        c_nombreproducto.setCellValueFactory(new PropertyValueFactory<DetalleFactura,String>("nombreProducto"));
        c_cantidad.setCellValueFactory(new PropertyValueFactory<DetalleFactura,Integer>("cantidad"));
        c_precio.setCellValueFactory(new PropertyValueFactory<DetalleFactura,Double>("precioUnitario"));
        //c_subtotal.setCellValueFactory(new PropertyValueFactory<DetalleFactura,Double>("subtotal"));
        c_subtotal.setCellValueFactory(value->{
            Double subtotal = value.getValue().getSubtotal();
            BigDecimal b_total = new BigDecimal(subtotal);
            return new SimpleStringProperty(""+b_total.setScale(3,BigDecimal.ROUND_HALF_UP));
        });
        tablaDetalleComprobante.setEditable(true);
    }

    public void llenarTabla(List<DetalleFactura> lista){
        Integer indice = 1;
        for(DetalleFactura detalleComprobantePago : lista){
            detalleComprobantePago.setIndiceTabla(indice);
            indice++;
            this.tablaDetalleComprobante.getItems().add(detalleComprobantePago);
        }
    }


    @Override
    public void initialize() {
        this.idEmpleadoAuditado = this.listaPedidoDevolucionController.obtenerIdEmpleadoAuditado();
        this.facturaEscogida = this.listaPedidoDevolucionController.obtenerPedidoEscogido();

        this.txb_nombreCliente.setDisable(true);
        this.txb_numComprobante.setDisable(true);
        this.txb_direccion.setDisable(true);
        this.txb_subtotal.setDisable(true);
        this.txb_igv.setDisable(true);
        this.txb_flete.setDisable(true);
        this.txb_total.setDisable(true);
        this.txb_numDocCliente.setDisable(true);
        this.txb_totaligv.setDisable(true);

        this.txb_nombreCliente.setText(facturaEscogida.getV_cliente());
        this.txb_numComprobante.setText("" + facturaEscogida.getV_id());
        this.txb_direccion.setText(facturaEscogida.getDireccionCliente());
        this.txb_numDocCliente.setText(facturaEscogida.getNumeroDocumentoCliente());

        Float subtotal = facturaEscogida.getV_subtotal();
        BigDecimal b_subtotal = new BigDecimal(subtotal);
        this.txb_subtotal.setText(""+b_subtotal.setScale(2,BigDecimal.ROUND_HALF_UP));

        Float igv = facturaEscogida.getV_igv();
        BigDecimal b_igv = new BigDecimal(igv);
        this.txb_igv.setText(""+b_igv.setScale(2,BigDecimal.ROUND_HALF_UP));

        Float total = facturaEscogida.getV_total();
        BigDecimal b_total = new BigDecimal(total);
        this.txb_total.setText(""+b_total.setScale(2,BigDecimal.ROUND_HALF_UP));


        //BigDecimal flete = b_total.subtract(b_igv.add(b_subtotal));
        BigDecimal flete = new BigDecimal(facturaEscogida.getV_flete());
        this.txb_flete.setText(""+flete.setScale(2,BigDecimal.ROUND_HALF_UP));

        BigDecimal totalConIgv = b_total.add(b_igv);
        this.txb_totaligv.setText(""+totalConIgv.setScale(2,BigDecimal.ROUND_HALF_UP));

        this.limpiarTabla();
        this.llenarTabla(facturaEscogida.getListaDetalleComprobante());
    }
}
