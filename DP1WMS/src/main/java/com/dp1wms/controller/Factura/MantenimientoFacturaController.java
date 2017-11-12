package com.dp1wms.controller.Factura;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryComprobantePago;
import com.dp1wms.dao.RepositoryEnvio;
import com.dp1wms.model.ComprobantePago;
import com.dp1wms.model.Envio;
import com.dp1wms.model.TipoComprobantePago;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MantenimientoFacturaController implements FxmlController {

    private final StageManager stageManager;

    private com.dp1wms.model.Usuario usuario;

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
    @Autowired
    private RepositoryEnvio repositoryEnvio;


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

    public void btnClickCrear(ActionEvent event){
        if( (repositoryComprobantePago.selectAllTipoComprobantePago() == null)
                || (repositoryComprobantePago.selectAllTipoComprobantePago().size() == 0) ){
            mostrarErrorDialog("No hay ningun tipo de comprobante de pago",
                    "Ningun tipo de comprobante de pago registrado. Registrar al menos uno para continuar");
            return;
        }

        Parent root = null;
        FXMLLoader loader;
        //
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/FacturaFxml/ListaEnvios.fxml"));
            root = (Parent) loader.load();
            ListaEnvio controller = loader.getController();
            //0 es crear
            controller._setControllerParent(this);
            controller._llenarGrila();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();

    }

    public void btnClickDetalle(ActionEvent event){
        /*
        if( (repositoryComprobantePago.selectAllTipoComprobantePago() == null)
                || (repositoryComprobantePago.selectAllTipoComprobantePago().size() == 0) ){
            mostrarErrorDialog("No hay ningun tipo de comprobante de pago",
                    "Ningun tipo de comprobante de pago registrado. Registrar al menos uno para continuar");
            return;
        }*/
        if(e_table.getSelectionModel().getSelectedItem() == null){
            mostrarErrorDialog("Error Comprobante de Pago",
                    "No se selecciono ningun comprobante de pago.");
            return;
        }

        Parent root = null;
        FXMLLoader loader;
        //
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/FacturaFxml/DetalleComprobantePago.fxml"));
            root = (Parent) loader.load();
            DetalleComprobantePagoController controller = loader.getController();
            controller._setData(e_table.getSelectionModel().getSelectedItem(), repositoryComprobantePago.getDetalleComprobantePago( e_table.getSelectionModel().getSelectedItem().getV_id() ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();

    }

    public void btnClickCancelar(){
        if(e_table.getSelectionModel().getSelectedItem() == null){
            mostrarErrorDialog("Error Comprobante de Pago",
                    "No se selecciono ningun comprobante de pago.");
            return;
        }

        if(!e_table.getSelectionModel().getSelectedItem().isV_activo()){
            mostrarErrorDialog("Error Comprobante de Pago",
                    "El comprobante de pago ya ha sido cancelado.");
            return;
        }

        repositoryComprobantePago.cambiarActivo(false, e_table.getSelectionModel().getSelectedItem().getV_id(), this.usuario);
        this._llenarGrilla();

        mostrarInfoDialog("Deshabilitar Comprobante de Pago",
                "Se deshabilito el comprobante de pago con exito.");

    }

    public void btnClickRenovar(){
        if(e_table.getSelectionModel().getSelectedItem() == null){
            mostrarErrorDialog("Error Comprobante de Pago",
                    "No se selecciono ningun comprobante de pago.");
            return;
        }

        if(e_table.getSelectionModel().getSelectedItem().isV_activo()){
            mostrarErrorDialog("Error Comprobante de Pago",
                    "El comprobante de pago se encuentra activo.");
            return;
        }

        repositoryComprobantePago.cambiarActivo(true, e_table.getSelectionModel().getSelectedItem().getV_id(), this.usuario);
        this._llenarGrilla();

        mostrarInfoDialog("Habilitar Comprobante de Pago",
                "Se habilito el comprobante de pago con exito.");

    }

    public void mostrarErrorDialog(String auxTitulo, String auxTexto){
        this.stageManager.mostrarErrorDialog(auxTitulo, null,
                auxTexto);
    }

    public void mostrarInfoDialog(String auxTitulo, String auxTexto){
        this.stageManager.mostrarInfoDialog(auxTitulo, null,
                auxTexto);
    }

    public List<Envio> selectAllEnviosSinComprobantePago(){
        return repositoryEnvio.obtenerListaEnvioRepoio();
    }

    public List<TipoComprobantePago> selectAllTipoComprobantePago(){ return repositoryComprobantePago.selectAllTipoComprobantePago(); }

    public void crearComprobantePago(Envio auxEnvio, String auxNombreTipoComprobante){
        TipoComprobantePago auxTipoComprobantePago = repositoryComprobantePago.getIdTipoComprobantePago(auxNombreTipoComprobante);
        repositoryComprobantePago.crearComprobantePago(auxEnvio, auxTipoComprobantePago, this.usuario);
    }

    public boolean existeFacturaActiva(Long auxIdEnvio){
        return repositoryComprobantePago.existeFacturaActiva(auxIdEnvio);
    }

    public void setUsuario(com.dp1wms.model.Usuario usuario){
        this.usuario = usuario;
    }
}
