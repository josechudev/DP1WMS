package com.dp1wms.controller.Envios;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.IngresoProductoController;
import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.DetalleEnvio;
import com.dp1wms.model.DetalleMovimiento;
import com.dp1wms.model.Lote;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeleccionarLoteController implements FxmlController {
    @FXML
    private AnchorPane buscarProductoLoteAnchorPane;

    @FXML
    private TextField txb_nombre;

    @FXML
    private TableView<Lote> tableViewProductos = new TableView<Lote>();

    @FXML
    private TableColumn<Lote,String> c_nombre;
    @FXML
    private TableColumn<Lote,String> c_fechaEntrada;
    @FXML
    private TableColumn<Lote,Integer> c_cantidadDisponible;
    @FXML
    private TableColumn<Lote,Integer> c_indice;

    private List<Lote> listaLotes = new ArrayList<Lote>();

    private RetiroEnvioController retiroEnvioController;

    private DetalleEnvio detalleEnvioEscogido;

    @Autowired
    private RepositoryMantMov repositoryMantMov;

    private final StageManager stageManager;

    @Autowired @Lazy
    public SeleccionarLoteController(StageManager stageManager, RetiroEnvioController retiroEnvioController){
        this.stageManager = stageManager;
        this.retiroEnvioController = retiroEnvioController;
    }

    public void buscarProducto(ActionEvent event){
        System.out.println("Buscar Producto");

        String nombreProducto = txb_nombre.getText();
        //String idCategoria = cb_categoria.getValue().toString();
        List<Lote> listaLotes = buscarProducto(nombreProducto);
        limpiarTabla();
        llenarTabla(listaLotes);
    }

    private void llenarTabla(List<Lote> listaLoteBusqueda){
        Integer indice = 1;
        for(Lote lote : listaLoteBusqueda){
            if(lote.getIdProducto() == this.detalleEnvioEscogido.getIdProducto()){
                lote.setIndiceTableView(indice);
                indice++;
                tableViewProductos.getItems().add(lote);
            }
        }
    }

    public void escogerProducto(ActionEvent event){
        Lote lote = tableViewProductos.getSelectionModel().getSelectedItem();
        if(lote==null){
            this.stageManager.mostrarErrorDialog("Error Seleccion de Lote", null,
                    "Debe seleccionar un lote");
            return;
        }

        if(lote.getStockParcial() < this.detalleEnvioEscogido.getCantidad()){
            this.stageManager.mostrarErrorDialog("Error Seleccion de Lote", null,
                    "No dispone de la cantidad necesaria para el despacho con ese lote");
            return;
        }

        System.out.println("Nombre Producto: " + lote.getNombreProducto()+" IdLote: "+lote.getIdLote()+" IdProducto: "+lote.getIdProducto());

        retiroEnvioController.actualizarDataLote(lote);

        buscarProductoLoteAnchorPane.getScene().getWindow().hide();
    }

    public void cancelarBusqueda(ActionEvent event){
        buscarProductoLoteAnchorPane.getScene().getWindow().hide();
    }

    private void limpiarTabla(){
        tableViewProductos.getItems().clear();
        c_nombre.setCellValueFactory(new PropertyValueFactory<Lote, String>("nombreProducto"));
        c_cantidadDisponible.setCellValueFactory(new PropertyValueFactory<Lote, Integer>("stockParcial"));
        c_fechaEntrada.setCellValueFactory(new PropertyValueFactory<Lote, String>("fechaEntrada"));
        c_indice.setCellValueFactory(new PropertyValueFactory<Lote, Integer>("indiceTableView"));
        tableViewProductos.setEditable(true);
    }

    private  List<Lote >buscarProducto(String nombreProducto){
        List<Lote> lista  = new ArrayList<Lote>();
        nombreProducto =   nombreProducto.toLowerCase();
        if(nombreProducto.equalsIgnoreCase(""))
            return this.listaLotes;

        for(Lote lote : this.listaLotes){
            String nombreProductoLote = lote.getNombreProducto().toLowerCase();
            if(nombreProductoLote.contains(nombreProducto))
                lista.add(lote);
        }
        return lista;
    }

    private List<Lote> obtenerProductos(){
        return repositoryMantMov.obtenerLotes();
    }


    private void obtenerListaProductosLotes(){
        List<Lote> listaLotesAux = obtenerProductos();
        for(Lote lote : listaLotesAux){
            if(lote.getIdProducto() == this.detalleEnvioEscogido.getIdProducto()){
                this.listaLotes.add(lote);
            }
        }
    }

    @Override
    public void initialize() {
        this.listaLotes = new ArrayList<Lote>();
        this.detalleEnvioEscogido = retiroEnvioController.obtenerDetalleMovimientoEscogido();
        this.obtenerListaProductosLotes();

       /* c_nombre.setCellValueFactory(new PropertyValueFactory<Lote, String>("nombreProducto"));
        c_cantidadDisponible.setCellValueFactory(new PropertyValueFactory<Lote, Integer>("stockParcial"));
        c_fechaEntrada.setCellValueFactory(new PropertyValueFactory<Lote, String>("fechaEntrada"));
        c_indice.setCellValueFactory(new PropertyValueFactory<Lote, Integer>("indiceTableView"));
        tableViewProductos.setEditable(true);*/
       this.limpiarTabla();

        llenarTabla(this.listaLotes);
    }
}
