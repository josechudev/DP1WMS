package com.dp1wms.controller.Factura;

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
public class MantenimientoFacturaController implements FxmlController {

    private final StageManager stageManager;

    @FXML private TableView<ComprobantePago> e_table;
    @FXML private TableColumn<ComprobantePago, Long> e_id;
    @FXML private TableColumn<ComprobantePago, String> e_tipoComprobante;
    @FXML private TableColumn<ComprobantePago, String> e_cliente;
    @FXML private TableColumn<ComprobantePago, String> e_fechaCreacion;
    @FXML private TableColumn<ComprobantePago, String> e_fechaMod;
    @FXML private TableColumn<ComprobantePago, Float> e_subtotal;
    @FXML private TableColumn<ComprobantePago, Float> e_flete;
    @FXML private TableColumn<ComprobantePago, Float> e_igv;
    @FXML private TableColumn<ComprobantePago, Float> e_total;
    @FXML private TableColumn<ComprobantePago, String> e_estadoComprobante;

    @Autowired
    private RepositoryComprobantePago repositoryComprobantePago;

    @Autowired @Lazy
    public MantenimientoFacturaController(StageManager stageManager){
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(){

        e_id.setCellValueFactory(new PropertyValueFactory<ComprobantePago, Long>("v_id"));
        e_tipoComprobante.setCellValueFactory(new PropertyValueFactory<ComprobantePago, String>("v_tipoComprobante"));
        e_cliente.setCellValueFactory(new PropertyValueFactory<ComprobantePago, String>("v_cliente"));
        e_fechaCreacion.setCellValueFactory(new PropertyValueFactory<ComprobantePago, String>("v_fechaCreacion"));
        e_fechaMod.setCellValueFactory(new PropertyValueFactory<ComprobantePago, String>("v_fechaModificacion"));
        e_subtotal.setCellValueFactory(new PropertyValueFactory<ComprobantePago, Float>("v_subtotal"));
        e_flete.setCellValueFactory(new PropertyValueFactory<ComprobantePago, Float>("v_flete"));
        e_igv.setCellValueFactory(new PropertyValueFactory<ComprobantePago, Float>("v_igv"));
        e_total.setCellValueFactory(new PropertyValueFactory<ComprobantePago, Float>("v_total"));
        e_estadoComprobante.setCellValueFactory(new PropertyValueFactory<ComprobantePago, String>("v_mostrarActivo"));

        this._llenarGrilla();
    }

    public void _llenarGrilla(){

        e_table.getItems().clear();

        List<ComprobantePago> auxListaComprobantesPago = repositoryComprobantePago.selectAllComprobantes();

        this.e_table.getItems().addAll(auxListaComprobantesPago);
    }

    public void btnClickCrear(){
        if( (repositoryComprobantePago.selectAllTipoComprobantePago() == null)
                || (repositoryComprobantePago.selectAllTipoComprobantePago().size() == 0) ){
            mostrarErrorDialog("No hay ningun tipo de comprobante de pago",
                    "Ningun tipo de comprobante de pago registrado. Registrar al menos uno para continuar");
            return;
        }


    }

    public void btnClickModificar(){
        if( (repositoryComprobantePago.selectAllTipoComprobantePago() == null)
                || (repositoryComprobantePago.selectAllTipoComprobantePago().size() == 0) ){
            mostrarErrorDialog("No hay ningun tipo de comprobante de pago",
                    "Ningun tipo de comprobante de pago registrado. Registrar al menos uno para continuar");
            return;
        }


    }

    public void btnClickCancelar(){

    }

    public void btnClickRenovar(){

    }

    public void mostrarErrorDialog(String auxTitulo, String auxTexto){
        this.stageManager.mostrarErrorDialog(auxTitulo, null,
                auxTexto);
    }

    public void mostrarInfoDialog(String auxTitulo, String auxTexto){
        this.stageManager.mostrarInfoDialog(auxTitulo, null,
                auxTexto);
    }
}
