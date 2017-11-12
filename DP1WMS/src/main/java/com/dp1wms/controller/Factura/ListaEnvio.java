package com.dp1wms.controller.Factura;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryEnvio;
import com.dp1wms.model.Envio;
import com.dp1wms.model.TipoComprobantePago;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

public class ListaEnvio implements FxmlController {

    @FXML private TableView<Envio> tablaEnviosPendientes;
    @FXML private TableColumn<Envio, Long> c_indice;
    @FXML private TableColumn<Envio, String> c_cliente;
    @FXML private TableColumn<Envio,Timestamp> c_fechaenvio;
    @FXML private TableColumn<Envio, String> c_destino;

    @FXML private ComboBox c_tipoComprobante;

    private MantenimientoFacturaController v_parentController;

    public ListaEnvio(){}

    @Override
    public void initialize(){
        Envio a = new Envio();
        c_indice.setCellValueFactory(new PropertyValueFactory<Envio, Long>("idEnvio"));
        c_cliente.setCellValueFactory(new PropertyValueFactory<Envio, String>("razonSocial"));
        c_fechaenvio.setCellValueFactory(new PropertyValueFactory<Envio,Timestamp>("fechaEnvio"));
        c_destino.setCellValueFactory(new PropertyValueFactory<Envio, String>("destino"));

        //this.v_parentController.selectAllTipoComprobantePago();
    }

    public void _llenarGrila(){
        tablaEnviosPendientes.getItems().clear();
        List<Envio> auxListaEnvio = v_parentController.selectAllEnviosSinComprobantePago();

        for(int i = 0; i < auxListaEnvio.size(); i++){
            if( !v_parentController.existeFacturaActiva( auxListaEnvio.get(i).getIdEnvio() ) ) {
                if( auxListaEnvio.get(i).getRealizado() ) this.tablaEnviosPendientes.getItems().add(auxListaEnvio.get(i));
            }
        }
        //this.tablaEnviosPendientes.getItems().addAll(auxListaEnvio);

    }

    public void _setControllerParent(MantenimientoFacturaController v_parentController){
        this.v_parentController = v_parentController;
        List <TipoComprobantePago> auxListaTipoComprobante = this.v_parentController.selectAllTipoComprobantePago();
        c_tipoComprobante.getItems().clear();
        for(int i = 0; i < auxListaTipoComprobante.size(); i++)
            c_tipoComprobante.getItems().add(auxListaTipoComprobante.get(i).getV_descripcion());
        c_tipoComprobante.getSelectionModel().select(auxListaTipoComprobante.get(0).getV_descripcion());
    }

    public void cancelarListaEnvios(ActionEvent event){
        System.out.println("Cancelar");
        Stage stage = (Stage) tablaEnviosPendientes.getScene().getWindow();
        stage.close();
    }

    public void crearComprobantePago(ActionEvent event){
        System.out.println("CrearComprobante");
        if(tablaEnviosPendientes.getSelectionModel().getSelectedItem() == null){
            this.v_parentController.mostrarErrorDialog("Error Comprobante de Pago",
                    "No se selecciono ningun envio.");
            return;
        }

        this.v_parentController.crearComprobantePago(tablaEnviosPendientes.getSelectionModel().getSelectedItem(),
                                                c_tipoComprobante.getSelectionModel().getSelectedItem().toString() );

        this.v_parentController._llenarGrilla();

        this.v_parentController.mostrarInfoDialog("Crear Comprobante de Pago",
                "Se creo el comprobante de pago con exito.");

        Stage stage = (Stage) tablaEnviosPendientes.getScene().getWindow();
        stage.close();
    }

}
