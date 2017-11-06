package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryEnvio;
import com.dp1wms.dao.RepositoryMantPedido;
import com.dp1wms.model.*;
import com.dp1wms.util.DateParser;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class VentaInformacionPedido implements FxmlController {

    @FXML private Label nombreLabel;
    @FXML private Label rucLabel;
    @FXML private Label telefonoLabel;
    @FXML private Label direccionLabel;
    @FXML private Label emailLabel;

    @FXML private Label estadoPedidoLabel;
    @FXML private TableView<DetallePedido> pedidoTable;
    @FXML private TableColumn<DetallePedido, String> codigoTC;
    @FXML private TableColumn<DetallePedido, String> productoTC;
    @FXML private TableColumn<DetallePedido, Float> precioTC;
    @FXML private TableColumn<DetallePedido, Integer> cantidadTC;
    @FXML private TableColumn<DetallePedido, Float> descuentoTC;
    @FXML private TableColumn<DetallePedido, Float> subtotalTC;

    @FXML private TableView<Envio> enviosTable;
    @FXML private TableColumn<Envio, String> destinoTC;
    @FXML private TableColumn<Envio, String> fechaEnvioTC;
    @FXML private TableColumn<Envio, Float> costoFleteTC;
    @FXML private TableColumn<Envio, String> estadoEnvioTC;

    @FXML private TextArea observacionTA;
    @FXML private Label subtotalLabel;
    @FXML private Label totalFleteLabel;
    @FXML private Label totalLabel;

    @FXML private Button rechazarPedidoBtn;
    @FXML private Button cancelarPedidoBtn;

    private Pedido pedido;

    @Autowired
    private RepositoryMantPedido repositoryMantPedido;
    @Autowired
    private RepositoryEnvio repositoryEnvio;

    private StageManager stageManager;


    @FXML
    private void rechazarPedido(ActionEvent event){
        //segun bd, rechazar es 3
        boolean res = this.repositoryMantPedido.actualizarEstadoPedido(this.pedido.getIdPedido(), 3);
        if(res){
            //suc
            this.stageManager.mostrarInfoDialog("Información de Pedido", null,
                    "Usted acaba de rechazar el pedido con codigo " + this.pedido.getIdPedido());
            EstadoPedido ep = new EstadoPedido();
            ep.setIdEstadoPedido(3);
            ep.setDescripcion("Rechazado");
            this.pedido.setEstadoPedido(ep);
            this.estadoPedidoLabel.setText("Estado: (Rechazado)");
            this.initButtons();
        } else {
            this.stageManager.mostrarErrorDialog("Error Información de Pedido", null,
                    "No se pudo actualizar el estado del pedido. Inténtelo de nuevo.");
        }
    }

    @FXML
    private void cancelarPedido(ActionEvent event){
        //segun bd, cancelar es 4
        boolean res = this.repositoryMantPedido.actualizarEstadoPedido(this.pedido.getIdPedido(), 4);
        if(res){
            //suc
            this.stageManager.mostrarInfoDialog("Información de Pedido", null,
                    "Usted acaba de cancelar el pedido con codigo " + this.pedido.getIdPedido());
            EstadoPedido ep = new EstadoPedido();
            ep.setIdEstadoPedido(4);
            ep.setDescripcion("Cancelado por cliente");
            this.pedido.setEstadoPedido(ep);
            this.estadoPedidoLabel.setText("Estado: (Cancelado por cliente)");
            this.initButtons();
        } else {
            this.stageManager.mostrarErrorDialog("Error Información de Pedido", null,
                    "No se pudo actualizar el estado del pedido. Inténtelo de nuevo.");
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    private void initDatosCliente(){
        Cliente c = this.pedido.getCliente();
        this.nombreLabel.setText(c.getRazonSocial());
        this.rucLabel.setText(c.getNumDoc());
        this.telefonoLabel.setText(c.getTelefono());
        this.direccionLabel.setText(c.getDireccion());
        this.emailLabel.setText(c.getEmail());
    }
    private void initDetallesPedido(){
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
        this.cantidadTC.setCellValueFactory(value -> {
            return new SimpleObjectProperty<Integer>(value.getValue().getCantidad());
        });
        this.descuentoTC.setCellValueFactory(value -> {
            return new SimpleObjectProperty<Float>(value.getValue().getDescuento());
        });
        this.subtotalTC.setCellValueFactory(value -> {
            return new SimpleObjectProperty<Float>(value.getValue().getSubtotal());
        });

        List<DetallePedido> detallesPedido = this.repositoryMantPedido.obtenerDetallesPedido(this.pedido.getIdPedido());
        if(detallesPedido == null){
            this.stageManager.mostrarErrorDialog("Error Informacion Pedido", null,
                    "Hubo un error al cargar los detalles del pedido. Vuelva a cargar la ventana.");
        } else {
            this.pedidoTable.getItems().addAll(detallesPedido);
        }
    }
    private void initTablaEnvios(){
        this.enviosTable.getItems().clear();
        this.destinoTC.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getDestino());
        });
        this.fechaEnvioTC.setCellValueFactory(value -> {
            Timestamp time = value.getValue().getFechaEnvio();
            return new SimpleStringProperty(DateParser.timestampToString(time));
        });
        this.costoFleteTC.setCellValueFactory(value -> {
            return new SimpleObjectProperty<Float>(value.getValue().getCostoFlete());
        });
        this.estadoEnvioTC.setCellValueFactory(value->{
            String estado  = value.getValue().getRealizado() ? "Realizado" : "En espera";
            return new SimpleStringProperty(estado);
        });

        List<Envio> envios = this.repositoryEnvio.obtenerEnvios(this.pedido.getIdPedido());
        if(envios == null){
            this.stageManager.mostrarErrorDialog("Error Informacion Pedido", null,
                    "No se pudo cargar los envios. Vuelva a cargar la ventana.");
        } else {
            this.enviosTable.getItems().addAll(envios);
        }
    }
    private void initButtons(){
        //buttons
        if(this.pedido.getEstadoPedido().getIdEstadoPedido() == 1){//en espera
            this.rechazarPedidoBtn.setDisable(false);
            this.cancelarPedidoBtn.setDisable(false);
        } else {
            this.rechazarPedidoBtn.setDisable(true);
            this.cancelarPedidoBtn.setDisable(true);
        }
    }
    @Override
    public void initialize() {
        this.initDatosCliente();
        this.initDetallesPedido();
        this.initTablaEnvios();

        //resto de labels
        this.observacionTA.setText(this.pedido.getObservaciones());
        this.subtotalLabel.setText(String.valueOf(this.pedido.getSubtotal()));
        this.totalFleteLabel.setText(String.valueOf(this.pedido.getCostoflete()));
        this.totalLabel.setText(String.valueOf(this.pedido.getTotal()));

        //estado del pedido
        this.estadoPedidoLabel.setText("Estado: (" +
                this.pedido.getEstadoPedido().getDescripcion() + ")");

        this.initButtons();
    }

    @Autowired @Lazy
    public VentaInformacionPedido(StageManager stageManager){
        this.stageManager = stageManager;
    }

    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }
}
