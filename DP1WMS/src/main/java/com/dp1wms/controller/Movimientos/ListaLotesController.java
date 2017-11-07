package com.dp1wms.controller.Movimientos;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.Lote;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListaLotesController implements FxmlController {

    @FXML
    private TextField txb_nombre;

    @FXML
    private TableView<Lote> tablaLotes = new TableView<Lote>();

    @FXML
    private TableColumn<Lote,String> c_nombre;
    @FXML
    private TableColumn<Lote,String> c_fechaEntrada;
    @FXML
    private TableColumn<Lote,Integer> c_cantidadDisponible;
    @FXML
    private TableColumn<Lote,Integer> c_indice;
    @FXML
    private TableColumn<Lote,String> c_codigo;


    private List<Lote> listaLotes;

    private Lote loteEscogido = null;

    @Autowired
    private RepositoryMantMov repositoryMantMov;

    private  MainController mainController;

    private final StageManager stageManager;


    @Autowired
    @Lazy
    public ListaLotesController(StageManager stageManager, MainController mainController){
        this.stageManager = stageManager;
        this.mainController = mainController;
    }
    public void verUbicacionLote(ActionEvent event){
        Lote lote = tablaLotes.getSelectionModel().getSelectedItem();
        if( lote == null){
            this.stageManager.mostrarErrorDialog("Error Lotes", null,
                    "Debe seleccionar un lote para ver o modificar su ubicacion");
            return;
        }else{
            this.loteEscogido = lote;
            this.stageManager.mostrarModal(MainView.UBICACION_LOTE);
        }

    }

    public void cancelarOperacion(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    public Lote obtenerLoteEscogido(){
        return this.loteEscogido;
    }

    private void llenarTabla(List<Lote> listaLoteBusqueda){
        Integer indice = 1;
        for(Lote lote : listaLoteBusqueda){
            lote.setIndiceTableView(indice);
            indice++;
            tablaLotes.getItems().add(lote);
        }
    }

    private void limpiarTabla(){
        tablaLotes.getItems().clear();
        c_nombre.setCellValueFactory(new PropertyValueFactory<Lote, String>("nombreProducto"));
        c_cantidadDisponible.setCellValueFactory(new PropertyValueFactory<Lote, Integer>("stockParcial"));
        c_fechaEntrada.setCellValueFactory(new PropertyValueFactory<Lote, String>("fechaEntrada"));
        /*this.c_fechaEntrada.setCellValueFactory(value->{
            Timestamp fecha= DateParser.stringToTimestamp(value.getValue().getFechaEntrada());
            String fechaEntrada = DateParser.timestampToString(fecha);
            return new SimpleStringProperty(fechaEntrada);
        });*/
        c_indice.setCellValueFactory(new PropertyValueFactory<Lote, Integer>("indiceTableView"));
        c_codigo.setCellValueFactory(new PropertyValueFactory<Lote, String>("codigoProducto"));
        tablaLotes.setEditable(true);
    }

    private List<Lote> obtenerLotes(){
        return repositoryMantMov.obtenerLotes();
    }

    @Override
    public void initialize() {
        this.listaLotes = obtenerLotes();
        this.limpiarTabla();
        llenarTabla(this.listaLotes);
    }
}
