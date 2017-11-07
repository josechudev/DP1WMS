package com.dp1wms.controller.Guia;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryEnvio;
import com.dp1wms.model.Envio;
import com.dp1wms.model.Guia;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class ListaEnviosRealizadosController implements FxmlController {

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

    @Autowired
    private RepositoryEnvio repositoryEnvio;

    private  ListaGuiasController listaGuiasController;

    List<Envio> listaEnvios;

    Long idEmpleadoAuditado = null;

    private final StageManager stageManager;

    @Autowired
    @Lazy
    public ListaEnviosRealizadosController(StageManager stageManager, ListaGuiasController listaGuiasController) {
        this.stageManager = stageManager;
        this.listaGuiasController = listaGuiasController;
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

    public void crearGuiaRemision(ActionEvent event){

        if(tablaEnviosPendientes.getSelectionModel().getSelectedItem() != null){
            this.stageManager.mostrarModal(MainView.CREAR_GUIA);
        }else{
            this.stageManager.mostrarErrorDialog("Error Creacion Guia de Remision", null,
                    "Debe seleccionar un envio para crear la guia");
            return;
        }

    }

    public Envio obtenerEnvio(){
        return tablaEnviosPendientes.getSelectionModel().getSelectedItem();
    }

    public Long obtenerIdEmpleadoAuditado(){
        return this.idEmpleadoAuditado;
    }

    public void actualizarTabla(){
        this.limpiarTabla();
        this.listaEnvios = this.obtenerEnviosRealizadosSinGuias(this.listaGuiasController.obtenerListaGuias());
        this.llenarTabla(this.listaEnvios);
    }

    public void actualizarListaGuia(){
        this.listaGuiasController.actualizarListaGuias();
        this.actualizarTabla();
    }

    public void llenarTabla(List<Envio> lista){
        Integer indice = 1;
        for(Envio envio : lista){
            envio.setIndiceTabla(indice);
            indice++;
            this.tablaEnviosPendientes.getItems().add(envio);
        }
    }

    private List<Envio> obtenerEnviosRealizadosSinGuias(List<Guia> listaGuias){
        List<Envio> listaEnviosAux = this.repositoryEnvio.obtenerEnviosRealizados(true);
        List<Envio> listaEnviosRealizados = new ArrayList<Envio>();
        Boolean añadir = true;
        for(Envio envio:listaEnviosAux){
            añadir = true;
            for(Guia guia : listaGuias){
                if(envio.getIdEnvio() == guia.getIdEnvio()){
                    añadir = false;
                }
            }
            if(añadir){
                listaEnviosRealizados.add(envio);
            }
        }
        return listaEnviosRealizados;
    }

    @Override
    public void initialize() {
        this.listaEnvios = this.obtenerEnviosRealizadosSinGuias(this.listaGuiasController.obtenerListaGuias());
        System.out.println("Tamñao de lista envios recibida de repository->" + this.listaEnvios.size());
        this.limpiarTabla();
        this.llenarTabla(this.listaEnvios);

        this.idEmpleadoAuditado = this.listaGuiasController.obtenerIdEmpleadoAuditado();
    }
}
