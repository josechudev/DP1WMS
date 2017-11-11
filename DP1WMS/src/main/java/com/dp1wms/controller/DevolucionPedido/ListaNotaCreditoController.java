package com.dp1wms.controller.DevolucionPedido;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
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
import java.util.List;

@Component
public class ListaNotaCreditoController implements FxmlController {

    @FXML
    private TableView<ComprobantePago> tablaNotaCredito = new TableView<ComprobantePago>();
    @FXML
    private TableColumn<ComprobantePago,Integer> c_indice;
    @FXML
    private TableColumn<ComprobantePago,String> c_nroNotaCredito;
    @FXML
    private TableColumn<ComprobantePago,String> c_cliente;
    @FXML
    private TableColumn<ComprobantePago,String> c_total;

    private MainController mainController;

    private final StageManager stageManager;

    private Long idEmpleadoAuditado;

    @Autowired
    private RepositoryDevoluciones repositoryDevoluciones;

    private List<ComprobantePago> listaNotaCredito;

    @Autowired
    @Lazy
    public ListaNotaCreditoController(StageManager stageManager, MainController mainController) {
        this.stageManager = stageManager;
        this.mainController = mainController;
    }

    public void cancelarOperacion(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    public Long obtenerIdEmpleadoAuditado(){
        return this.idEmpleadoAuditado;
    }

    public void nuevaNotaCredito(ActionEvent event){
        this.stageManager.mostrarModal(MainView.LISTA_PEDIDOS_DEVOLUCION);
    }

    public Boolean existeNotaCredito(Long idComprobante){
        for(ComprobantePago comprobantePago:this.listaNotaCredito){
            if(comprobantePago.getV_id() == idComprobante){
                return true;
            }
        }
        return false;
    }

    public void actualizarLista(){
        this.listaNotaCredito = this.repositoryDevoluciones.obtenerFacturasNotasCredito();
        this.limpiarTabla();
        this.llenarTabla(listaNotaCredito);
    }

    public void limpiarTabla(){
        tablaNotaCredito.getItems().clear();
        c_indice.setCellValueFactory(new PropertyValueFactory<ComprobantePago,Integer>("indiceTabla"));
        c_nroNotaCredito.setCellValueFactory(value->{
            return new SimpleStringProperty("00"+value.getValue().getIdNotaCredito());
        });
        c_cliente.setCellValueFactory(new PropertyValueFactory<ComprobantePago,String>("v_cliente"));
        c_total.setCellValueFactory(value->{
            Float total = value.getValue().getV_total();
            BigDecimal b_total = new BigDecimal(total);
            return new SimpleStringProperty(""+b_total.setScale(3,BigDecimal.ROUND_HALF_UP));
        });
        tablaNotaCredito.setEditable(true);
    }

    public void llenarTabla(List<ComprobantePago> lista){
        Integer indice = 1;
        for(ComprobantePago comprobantePago : lista){
            comprobantePago.setIndiceTabla(indice);
            indice++;
            this.tablaNotaCredito.getItems().add(comprobantePago);
        }
    }

    @Override
    public void initialize() {
        this.idEmpleadoAuditado = this.mainController.getEmpleado().getIdempleado();
        this.actualizarLista();
    }
}
