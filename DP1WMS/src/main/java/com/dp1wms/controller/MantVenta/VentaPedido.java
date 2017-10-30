package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.controller.MantCliente.ClienteInfoController;
import com.dp1wms.dao.RepositoryCondicion;
import com.dp1wms.model.*;
import com.dp1wms.util.Descuento;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VentaPedido implements FxmlController {

    @FXML private Label nombreLabel;
    @FXML private Label telefonoLabel;
    @FXML private Label emailLabel;
    @FXML private Label rucLabel;
    @FXML private Label direccionLabel;

    @FXML private TableView<DetallePedido> pedidoTable;
    @FXML private TableColumn<DetallePedido, String> codigoTC;
    @FXML private TableColumn<DetallePedido, String> productoTC;
    @FXML private TableColumn<DetallePedido, Float> precioTC;
    @FXML private TableColumn<DetallePedido, Integer> cantidadTC;
    @FXML private TableColumn<DetallePedido, Float> descuentoTC;
    @FXML private TableColumn<DetallePedido, Float> subtotalTC;

    @FXML private TextArea observacionTA;
    @FXML private Label totalLabel;

    private Cliente cliente;
    private Pedido pedido;

    private StageManager stageManager;
    private MainController mainController;
    private ClienteInfoController clienteInfoController;
    private VentaBusquedaCliente ventaBusquedaCliente;

    @Autowired private RepositoryCondicion repositoryCondicion;

    @Override
    public void initialize(){
        this.cliente = null;
        this.pedido = new Pedido();
        this.pedido.setIdEmpleadoAuditado((int)this.mainController.getEmpleado().getIdempleado());
        this.limpiarTablaPedido();
    }

    @Autowired
    @Lazy
    public VentaPedido(StageManager stageManager, MainController mainController,
                       ClienteInfoController clienteInfoController,
                       VentaBusquedaCliente ventaBusquedaCliente){
        this.stageManager = stageManager;
        this.mainController = mainController;
        this.clienteInfoController = clienteInfoController;
        this.ventaBusquedaCliente = ventaBusquedaCliente;
    }

    private void limpiarTablaPedido(){
        this.pedidoTable.getItems().clear();
        this.codigoTC.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getCodigoProducto());
        });
        this.productoTC.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getProducto().getNombreProducto());
        });
        this.precioTC.setCellValueFactory(value -> {
            return new SimpleObjectProperty<Float>(value.getValue().getPrecioUnitario());
        });
        this.cantidadTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Integer>(value.getValue().getCantidad());
        });
        this.descuentoTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Float>(value.getValue().getDescuento());
        });
        this.subtotalTC.setCellValueFactory(value -> {
            return new SimpleObjectProperty<Float>(value.getValue().getSubtotal());
        });
    }

    private void llenarTablaPedido(){
        this.limpiarTablaPedido();
        List<Condicion> condiciones = this.repositoryCondicion.obtenerCondicionesActivos();
        Descuento.aplicarDescuento(condiciones, this.pedido);
        this.pedido.calcularTotal();

        this.pedidoTable.getItems().addAll(this.pedido.getDetalles());
        this.totalLabel.setText(String.valueOf(this.pedido.getTotal()));
    }
}
