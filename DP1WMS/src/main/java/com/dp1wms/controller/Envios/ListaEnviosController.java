package com.dp1wms.controller.Envios;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryEnvio;
import com.dp1wms.dao.impl.RepositoryMantMovImpl;
import com.dp1wms.model.DetalleEnvio;
import com.dp1wms.model.DetalleMovimiento;
import com.dp1wms.model.Envio;
import com.dp1wms.model.Movimiento;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ListaEnviosController implements FxmlController {


    @FXML
    private TableView<Envio> tablaEnviosPendientes = new TableView<Envio>();
    @FXML
    private TableColumn<Envio,Integer> c_indice;
    @FXML
    private TableColumn<Envio,String> c_cliente;
    @FXML
    private TableColumn<Envio,Timestamp> c_fechaenvio;
    @FXML
    private TableColumn<Envio,String> c_destino;

    private MainController mainController;

    List<Envio> listaEnvios;

    Long idEmpleadoAuditado = null;

    @Autowired
    private RepositoryEnvio repositoryEnvio;

    @Autowired
    private RepositoryMantMovImpl repositoryMantMovImpl;

    private final StageManager stageManager;

    @Autowired
    @Lazy
    public ListaEnviosController(StageManager stageManager,MainController mainController) {
        this.stageManager = stageManager;
        this.mainController = mainController;
    }

    public void limpiarTabla(){
        tablaEnviosPendientes.getItems().clear();
        c_indice.setCellValueFactory(new PropertyValueFactory<Envio,Integer>("indiceTabla"));
        c_cliente.setCellValueFactory(new PropertyValueFactory<Envio,String>("razonSocial"));
        c_fechaenvio.setCellValueFactory(new PropertyValueFactory<Envio,Timestamp>("fechaEnvio"));
        c_destino.setCellValueFactory(new PropertyValueFactory<Envio,String>("destino"));
        tablaEnviosPendientes.setEditable(true);
    }

    public void cancelarListaEnvios(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    public void retirarProductosEnvio(ActionEvent event){
        this.stageManager.mostrarModal(MainView.RETIRO_ENVIO);

    }

    public Envio obtenerEnvio(){
        return tablaEnviosPendientes.getSelectionModel().getSelectedItem();
    }


    public void actualizarTabla(){
        this.limpiarTabla();
        this.listaEnvios = this.repositoryEnvio.obtenerEnviosRealizados(false);
        this.llenarTabla(this.listaEnvios);
    }

    public void llenarTabla(List<Envio> lista){
        Integer indice = 1;
        for(Envio envio : lista){
            envio.setIndiceTabla(indice);
            indice++;
            this.tablaEnviosPendientes.getItems().add(envio);
        }
    }

    @Override
    public void initialize() {
        this.listaEnvios = this.repositoryEnvio.obtenerEnviosRealizados(false);
        System.out.println("Tamñao de lista envios recibida de repository->" + this.listaEnvios.size());
        //this.limpiarTabla();
        c_indice.setCellValueFactory(new PropertyValueFactory<Envio,Integer>("indiceTabla"));
        c_cliente.setCellValueFactory(new PropertyValueFactory<Envio,String>("razonSocial"));
        c_fechaenvio.setCellValueFactory(new PropertyValueFactory<Envio,Timestamp>("fechaEnvio"));
        c_destino.setCellValueFactory(new PropertyValueFactory<Envio,String>("destino"));
        tablaEnviosPendientes.setEditable(true);
        this.llenarTabla(this.listaEnvios );

        this.idEmpleadoAuditado = this.mainController.getEmpleado().getIdempleado();
    }
}
