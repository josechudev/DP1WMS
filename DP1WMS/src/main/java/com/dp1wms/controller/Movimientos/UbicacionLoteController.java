package com.dp1wms.controller.Movimientos;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.Lote;
import com.dp1wms.model.Ubicacion;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import sun.applet.Main;

import java.util.List;

@Component
public class UbicacionLoteController implements FxmlController {

    @FXML
    private TableView<Ubicacion> tablaUbicaciones = new TableView<Ubicacion>();

    @FXML
    private TableColumn<Ubicacion,String> c_almacen;
    @FXML
    private TableColumn<Ubicacion,String> c_area;
    @FXML
    private TableColumn<Ubicacion,String> c_rack;
    @FXML
    private TableColumn<Ubicacion,Integer> c_indice;
    @FXML
    private TableColumn<Ubicacion,String> c_cajon;
    @FXML
    private TableColumn<Ubicacion,Integer> c_cantidad;

    private  ListaLotesController listaLotesController;

    private List<Ubicacion> listaUbicaciones;

    private Lote loteEscogido;

    private Ubicacion ubicacionEscogida;

    private Boolean esAgregar;

    private int maxCantidadRestante = 0; // cantidad que se puede ubicar que no este ubicada en ningun lado

    @Autowired
    private RepositoryMantMov repositoryMantMov;

    private final StageManager stageManager;


    @Autowired
    @Lazy
    public UbicacionLoteController(StageManager stageManager, ListaLotesController listaLotesController){
        this.stageManager = stageManager;
        this.listaLotesController = listaLotesController;
    }

    private int calcularMaxCantidad(){
        int cantidadUbicada = 0;
        for(Ubicacion ubicacion: this.listaUbicaciones){
            cantidadUbicada += ubicacion.getCantidad();
        }
        int cantidadStockLote = this.loteEscogido.getStockParcial();
        return (cantidadStockLote - cantidadUbicada);
    }

    public int obtenerMaxCantidad(){
        return this.maxCantidadRestante;
    }

    public Boolean esAgregar(){
        return this.esAgregar;
    }

    public void agregarUbicacion(ActionEvent event){
        this.esAgregar = true;
        this.maxCantidadRestante = calcularMaxCantidad();
        if(maxCantidadRestante>0){
            this.stageManager.mostrarModal(MainView.ESCOGER_UBICACION_LOTE);
        }else{
            this.stageManager.mostrarErrorDialog("Error Lotes", null,
                    "Todo el stock de lote ya ha sido asignado. Intente Modificar la cantidad de las ubicaciones existentes");
            return;
        }

    }

    public Lote obtenerLoteEscogido(){
        return this.loteEscogido;
    }

    public void modificarUbicacion(ActionEvent event){
        Ubicacion ubicacion = tablaUbicaciones.getSelectionModel().getSelectedItem();
        if( ubicacion == null){
            this.stageManager.mostrarErrorDialog("Error Lotes", null,
                    "Debe seleccionar un registro para modificar su ubicacion");
            return;
        }else{
            this.esAgregar = false;
            this.ubicacionEscogida = ubicacion;
            this.maxCantidadRestante = ubicacion.getCantidad() + (this.calcularMaxCantidad());
            this.stageManager.mostrarModal(MainView.ESCOGER_UBICACION_LOTE);
        }
    }

    public Ubicacion obtenerUbicacionEscogida(){
        return this.ubicacionEscogida;
    }

    public Boolean cajonExiste(int idCajon) {
        for (Ubicacion ubicacion : this.listaUbicaciones) {
            if(this.ubicacionEscogida.getIdCajon() == idCajon){
                return false;
            }

            if (ubicacion.getIdCajon() == idCajon) {
                return true;
            }
        }
        return false;
    }

    public void cancelarOperacion(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    private void llenarTabla(List<Ubicacion> listaBusqueda){
        Integer indice = 1;
        for(Ubicacion ubicacion : listaBusqueda){
            ubicacion.setIndiceTabla(indice);
            indice++;
            tablaUbicaciones.getItems().add(ubicacion);
        }
    }

    private void limpiarTabla(){
        tablaUbicaciones.getItems().clear();
        c_almacen.setCellValueFactory(new PropertyValueFactory<Ubicacion, String>("nombreAlmacen"));
        c_area.setCellValueFactory(new PropertyValueFactory<Ubicacion, String>("codigoArea"));
        c_rack.setCellValueFactory(new PropertyValueFactory<Ubicacion, String>("codigoRack"));
        c_indice.setCellValueFactory(new PropertyValueFactory<Ubicacion, Integer>("indiceTabla"));
        c_cajon.setCellValueFactory(value->{
            return new SimpleStringProperty("X: "+value.getValue().getCajonPosicionX() + ", Nivel: "+value.getValue().getCajonPosicionY());
        });
        c_cantidad.setCellValueFactory(new PropertyValueFactory<Ubicacion, Integer>("cantidad"));
        tablaUbicaciones.setEditable(true);
    }

    public void refrescarTabla(){
        this.listaUbicaciones = this.repositoryMantMov.obtenerUbicaciones(this.loteEscogido.getIdLote(),this.loteEscogido.getIdProducto());
        this.limpiarTabla();
        this.llenarTabla(this.listaUbicaciones);
    }

    @Override
    public void initialize() {
        this.loteEscogido = listaLotesController.obtenerLoteEscogido();
        this.refrescarTabla();
    }
}
