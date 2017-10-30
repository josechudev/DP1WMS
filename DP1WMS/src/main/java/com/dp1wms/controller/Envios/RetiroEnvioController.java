package com.dp1wms.controller.Envios;


import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryEnvio;
import com.dp1wms.dao.impl.RepositoryMantMovImpl;
import com.dp1wms.model.*;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class RetiroEnvioController implements FxmlController {

    Long idEmpleadoAuditado = null;

    @FXML
    private TextField txb_Observaciones;
    @FXML
    private TableView<DetalleEnvio> tablaDetalleEnvio = new TableView<DetalleEnvio>();
    @FXML
    private TableColumn<DetalleEnvio,Integer> c_indice;
    @FXML
    private TableColumn<DetalleEnvio,String> c_codigoProducto;
    @FXML
    private TableColumn<DetalleEnvio,String> c_nombreProducto;
    @FXML
    private TableColumn<DetalleEnvio,Integer> c_cantidad;
    @FXML
    private TableColumn<DetalleEnvio,String> c_loteAsignado;

    @Autowired
    private RepositoryEnvio repositoryEnvio;

    @Autowired
    private RepositoryMantMovImpl repositoryMantMovImpl;

    private final StageManager stageManager;

    private ListaEnviosController listaEnviosController;

    private List<DetalleEnvio> listaDetalleEnvio;

    private Envio envio;

    private DetalleEnvio detalleEnvioSeleccionado;

    @Autowired
    @Lazy
    public RetiroEnvioController(StageManager stageManager,ListaEnviosController listaEnviosController) {
        this.stageManager = stageManager;
        this.listaEnviosController = listaEnviosController;
    }

    public void asignarLoteProducto(ActionEvent event){
        //this.detalleEnvioSeleccionado = tablaDetalleEnvio.getSelectionModel().getSelectedItem();
        this.stageManager.mostrarModal(MainView.SELECCONAR_LOTE);
    }

    public DetalleEnvio obtenerDetalleMovimientoEscogido(){
        this.detalleEnvioSeleccionado = tablaDetalleEnvio.getSelectionModel().getSelectedItem();
        return detalleEnvioSeleccionado;
    }

    public void actualizarDataLote(Lote lote){
        System.out.println("Detalle Envio Seleccionado "+detalleEnvioSeleccionado);
       // detalleEnvioSeleccionado.setLoteAsociado("Si");
       // detalleEnvioSeleccionado.setIdLote(lote.getIdLote());
        List<DetalleEnvio> nuevaLista = new ArrayList<DetalleEnvio>();
        for(DetalleEnvio detalleEnvio: listaDetalleEnvio){

            if(detalleEnvio.getIdProducto() == lote.getIdProducto()){
                detalleEnvio.setIdLote(lote.getIdLote());
                detalleEnvio.setLoteAsociado("Si");
            }
            nuevaLista.add(detalleEnvio);
        }
        this.listaDetalleEnvio.clear();
        this.listaDetalleEnvio = nuevaLista;
        this.limpiarTabla();
        this.llenarTabla(nuevaLista);
    }

    public void aceptarRetiroEnvio(ActionEvent event){
        Movimiento movimiento = new Movimiento();

        java.util.Date today = new java.util.Date();
        Timestamp fechahoraactual =new java.sql.Timestamp(today.getTime());
        movimiento.setFechaMovimiento(fechahoraactual);
        movimiento.setIdEmpleadoAuditado(this.idEmpleadoAuditado);
        movimiento.setObservaciones(this.txb_Observaciones.getText());

        List<DetalleEnvio> listaDetalleEnvio = this.listaDetalleEnvio;
        List<DetalleMovimiento> listaDetalleMovimiento = new ArrayList<DetalleMovimiento>();
        int totalProductos = 0;
        for(DetalleEnvio detalleEnvio : listaDetalleEnvio){
            totalProductos++;
            DetalleMovimiento detalleMovimiento = new DetalleMovimiento();
            detalleMovimiento.setIdLote(detalleEnvio.getIdLote());
            detalleMovimiento.setCantidad(detalleEnvio.getCantidad());
            detalleMovimiento.setIdProducto(detalleEnvio.getIdProducto());
            listaDetalleMovimiento.add(detalleMovimiento);
        }
        System.out.println("Tamaño lista movimiento->"+listaDetalleMovimiento.size());
        movimiento.setTotalProductos(totalProductos);
        movimiento.setListaDetalleMovimiento(listaDetalleMovimiento);
        int resultado = this.repositoryMantMovImpl.registrarMovimiento(movimiento);
        if(resultado > 0){
            this.repositoryEnvio.actualizarEstadoEnvio(this.envio.getIdEnvio());
        }
        this.listaEnviosController.actualizarTabla();
        this.stageManager.cerrarVentana(event);
    }

    public void cancelarRetiroEnvio(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    public void limpiarTabla(){
        this.tablaDetalleEnvio.getItems().clear();
        c_indice.setCellValueFactory(new PropertyValueFactory<DetalleEnvio,Integer>("indiceTabla"));
        c_codigoProducto.setCellValueFactory(new PropertyValueFactory<DetalleEnvio,String>("codigoProducto"));
        c_nombreProducto.setCellValueFactory(new PropertyValueFactory<DetalleEnvio,String>("nombreProducto"));
        c_cantidad.setCellValueFactory(new PropertyValueFactory<DetalleEnvio,Integer>("cantidad"));
        c_loteAsignado.setCellValueFactory(new PropertyValueFactory<DetalleEnvio,String>("loteAsociado"));
        tablaDetalleEnvio.setEditable(true);
    }

    public void llenarTabla(List<DetalleEnvio> lista){
        Integer indice = 1;
        for(DetalleEnvio detalleEnvio : lista){
            detalleEnvio.setIndiceTabla(indice);
            //detalleEnvio.setLoteAsociado("No");
            indice++;
            this.tablaDetalleEnvio.getItems().add(detalleEnvio);
        }
    }

    @Override
    public void initialize() {
        c_indice.setCellValueFactory(new PropertyValueFactory<DetalleEnvio,Integer>("indiceTabla"));
        c_codigoProducto.setCellValueFactory(new PropertyValueFactory<DetalleEnvio,String>("codigoProducto"));
        c_nombreProducto.setCellValueFactory(new PropertyValueFactory<DetalleEnvio,String>("nombreProducto"));
        c_cantidad.setCellValueFactory(new PropertyValueFactory<DetalleEnvio,Integer>("cantidad"));
        c_loteAsignado.setCellValueFactory(new PropertyValueFactory<DetalleEnvio,String>("loteAsociado"));
        tablaDetalleEnvio.setEditable(true);

        this.envio = listaEnviosController.obtenerEnvio();
        this.listaDetalleEnvio = envio.getDetalleEnvio();

        for(DetalleEnvio detalleEnvio : listaDetalleEnvio){
            detalleEnvio.setLoteAsociado("No");
        }
        this.llenarTabla(this.listaDetalleEnvio);
    }
}
