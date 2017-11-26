package com.dp1wms.controller.DevolucionPedido;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryDevoluciones;
import com.dp1wms.model.ComprobantePago;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListaPedidoDevolucionController implements FxmlController {


    @FXML
    private TableView<ComprobantePago> tablaPedidoDevolucion = new TableView<ComprobantePago>();
    @FXML
    private TableColumn<ComprobantePago,Integer> c_indice;
    @FXML
    private TableColumn<ComprobantePago,String> c_tipocomprobante;
    @FXML
    private TableColumn<ComprobantePago,String> c_fechaemision;
    @FXML
    private TableColumn<ComprobantePago,String> c_cliente;
    @FXML
    private TableColumn<ComprobantePago,String> c_total;

    private List<ComprobantePago> listaComprobantesPedidoDev;

    private ListaNotaCreditoController listaNotaCreditoController;

    private final StageManager stageManager;

    private Long idEmpleadoAuditado;

    @Autowired
    private RepositoryDevoluciones repositoryDevoluciones;

    @Autowired
    @Lazy
    public ListaPedidoDevolucionController(StageManager stageManager, ListaNotaCreditoController listaNotaCreditoController) {
        this.stageManager = stageManager;
        this.listaNotaCreditoController = listaNotaCreditoController;
    }

    public void cancelarOperacion(ActionEvent event){
        this.stageManager.cerrarVentana(event);
        this.listaNotaCreditoController.actualizarLista();
    }

    public void crearNotaCredito(ActionEvent event){
        if (tablaPedidoDevolucion.getSelectionModel().getSelectedItem() != null) {
            this.stageManager.mostrarModal(MainView.CREAR_NOTACREDITO);

        }else{
            this.stageManager.mostrarErrorDialog("Error Creacion Nota de Credito", null,
                    "Debe seleccionar un pedido de devolucion para crear la nota de credito");
            return;
        }
    }

    public ComprobantePago obtenerPedidoEscogido(){
        return tablaPedidoDevolucion.getSelectionModel().getSelectedItem();
    }

    public void actualizarLista(){
        this.listaNotaCreditoController.actualizarLista();
        List<ComprobantePago> listaAux = this.repositoryDevoluciones.obtenerFacturas(false);
        this.listaComprobantesPedidoDev = new ArrayList<ComprobantePago>();
        for(ComprobantePago comprobantePago : listaAux){
            if(!this.listaNotaCreditoController.existeNotaCredito(comprobantePago.getV_id())){
                this.listaComprobantesPedidoDev.add(comprobantePago);
            }
        }
        this.limpiarTabla();
        this.llenarTabla(listaComprobantesPedidoDev);
    }

    public Long obtenerIdEmpleadoAuditado(){
        return this.idEmpleadoAuditado;
    }

    public void limpiarTabla(){
        tablaPedidoDevolucion.getItems().clear();
        c_indice.setCellValueFactory(new PropertyValueFactory<ComprobantePago,Integer>("indiceTabla"));
        c_tipocomprobante.setCellValueFactory(new PropertyValueFactory<ComprobantePago,String>("v_tipoComprobante"));
        c_cliente.setCellValueFactory(new PropertyValueFactory<ComprobantePago,String>("v_cliente"));
        c_fechaemision.setCellValueFactory(new PropertyValueFactory<ComprobantePago,String>("v_fechaCreacion"));
        //c_total.setCellValueFactory(new PropertyValueFactory<ComprobantePago,Float>("v_total"));
        c_total.setCellValueFactory(value->{
            Float total = value.getValue().getV_total();
            BigDecimal b_total = new BigDecimal(total);
            return new SimpleStringProperty(""+b_total.setScale(3,BigDecimal.ROUND_HALF_UP));
        });
        tablaPedidoDevolucion.setEditable(true);
    }

    public void llenarTabla(List<ComprobantePago> lista){
        Integer indice = 1;
        for(ComprobantePago comprobantePago : lista){
            comprobantePago.setIndiceTabla(indice);
            indice++;
            this.tablaPedidoDevolucion.getItems().add(comprobantePago);
        }
    }

    @Override
    public void initialize() {
        this.idEmpleadoAuditado = this.listaNotaCreditoController.obtenerIdEmpleadoAuditado();
        this.actualizarLista();
    }
}
