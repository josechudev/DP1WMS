package com.dp1wms.controller.DevolucionPedido;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryComprobantePago;
import com.dp1wms.model.ComprobantePago;
import com.dp1wms.view.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DevolucionPedidoController implements FxmlController {

    private final StageManager stageManager;

    @FXML private TableView<ComprobantePago> e_table;
    @FXML private TableColumn<ComprobantePago, Long> e_id;
    @FXML private TableColumn<ComprobantePago, String> e_tipoComprobante;
    @FXML private TableColumn<ComprobantePago, String> e_cliente;
    //@FXML private TableColumn<ComprobantePago, String> e_empleado;
    //@FXML private TableColumn<ComprobantePago, Float> e_subtotal;
    @FXML private TableColumn<ComprobantePago, Float> e_igv;
    @FXML private TableColumn<ComprobantePago, Float> e_total;
    @FXML private TableColumn<ComprobantePago, String> e_estadoComprobante;

    @Autowired
    private RepositoryComprobantePago repositoryComprobantePago;

    @Autowired @Lazy
    public  DevolucionPedidoController(StageManager stageManager){
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(){
        e_id.setCellValueFactory(new PropertyValueFactory<ComprobantePago, Long>("v_id"));
        e_tipoComprobante.setCellValueFactory(new PropertyValueFactory<ComprobantePago, String>("v_tipoComprobante"));
        e_cliente.setCellValueFactory(new PropertyValueFactory<ComprobantePago, String>("v_cliente"));
        //e_empleado.setCellValueFactory(new PropertyValueFactory<ComprobantePago, String>("v_empleado"));
        //e_subtotal.setCellValueFactory(new PropertyValueFactory<ComprobantePago, Float>("v_subtotal"));
        e_igv.setCellValueFactory(new PropertyValueFactory<ComprobantePago, Float>("v_igv"));
        e_total.setCellValueFactory(new PropertyValueFactory<ComprobantePago, Float>("v_total"));
        e_estadoComprobante.setCellValueFactory(new PropertyValueFactory<ComprobantePago, String>("v_estadoComprobante"));

        this._llenarGrilla();
    }

    public void _llenarGrilla(){

        e_table.getItems().clear();

        List<ComprobantePago> auxListaComprobantesPago = repositoryComprobantePago.selectAllComprobantes();
        try {
            for (int i = 0; i < auxListaComprobantesPago.size(); i++) {
                e_table.getItems().add(new ComprobantePago(auxListaComprobantesPago.get(i).getV_id(),
                        auxListaComprobantesPago.get(i).getV_tipoComprobante(),
                        auxListaComprobantesPago.get(i).getV_cliente(),
                        //auxListaComprobantesPago.get(i).getV_empleado(),
                        "",
                        //auxListaComprobantesPago.get(i).getV_subtotal(),
                        0,
                        auxListaComprobantesPago.get(i).getV_igv(),
                        auxListaComprobantesPago.get(i).getV_total(),
                        auxListaComprobantesPago.get(i).getV_estadoComprobante() ));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
