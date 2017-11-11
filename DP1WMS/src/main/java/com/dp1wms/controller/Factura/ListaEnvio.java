package com.dp1wms.controller.Factura;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryEnvio;
import com.dp1wms.model.Envio;
import javafx.fxml.FXML;
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

    private MantenimientoFacturaController v_parentController;

    public ListaEnvio(){}

    @Override
    public void initialize(){
        Envio a = new Envio();
        c_indice.setCellValueFactory(new PropertyValueFactory<Envio, Long>("idEnvio"));
        c_cliente.setCellValueFactory(new PropertyValueFactory<Envio, String>("razonSocial"));
        c_fechaenvio.setCellValueFactory(new PropertyValueFactory<Envio,Timestamp>("fechaEnvio"));
        c_destino.setCellValueFactory(new PropertyValueFactory<Envio, String>("destino"));

    }

    public void _llenarGrila(){
        tablaEnviosPendientes.getItems().clear();
        List<Envio> auxListaEnvio = v_parentController.selectAllEnviosSinComprobantePago();
        this.tablaEnviosPendientes.getItems().addAll(auxListaEnvio);
    }

    public void _setControllerParent(MantenimientoFacturaController v_parentController){
        this.v_parentController = v_parentController;
    }

    public void cancelarListaEnvios(){
        System.out.println("Cancelar");
        Stage stage = (Stage) tablaEnviosPendientes.getScene().getWindow();
        stage.close();
    }
}
