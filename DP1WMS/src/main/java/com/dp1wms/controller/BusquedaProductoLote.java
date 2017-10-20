package com.dp1wms.controller;

import com.dp1wms.model.Lote;
import com.dp1wms.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BusquedaProductoLote  implements Initializable {
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

    private List<Lote> listaLotes;

    private CrearLote crearLoteController;

    public void buscarProducto(ActionEvent event){
        System.out.println("Buscar Producto");

        String nombreProducto = txb_nombre.getText();
        //String idCategoria = cb_categoria.getValue().toString();
        List<Lote> listaLotes = buscarProducto(nombreProducto);
        limpiarTabla();
        for(Lote lote : listaLotes){
            tableViewProductos.getItems().add(lote);
        }
    }
    public void setCrearLoteController(CrearLote controller){
        this.crearLoteController = controller;
    }

    public void escogerProducto(ActionEvent event){
        Lote lote = tableViewProductos.getSelectionModel().getSelectedItem();
        System.out.println(lote.getNombreProducto());
        // Integer idProducto = obtenerIdProducto();

        crearLoteController.actualizarDataProducto(lote.getNombreProducto(),1,2);

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
        tableViewProductos.setEditable(true);
    }

    private  List<Lote >buscarProducto(String nombreProducto){
        List<Lote> lista  = new ArrayList<Lote>();

        if(nombreProducto.equalsIgnoreCase(""))
            return this.listaLotes;

        for(Lote lote : listaLotes){
            if(lote.getNombreProducto().equalsIgnoreCase(nombreProducto))
                lista.add(lote);
        }
        return lista;
    }

    private List<Lote> obtenerProductos(){
        List<Lote> listaLotes = new ArrayList<Lote>();

        Lote lote = new Lote();
        lote.setNombreProducto("Martillo");
        lote.setFechaEntrada("22/11/1963");
        lote.setStockParcial(50);
        listaLotes.add(lote);

        Lote lote2 = new Lote();
        lote2.setNombreProducto("Cemento");
        lote2.setFechaEntrada("24/11/1964");
        lote2.setStockParcial(24);
        listaLotes.add(lote2);

        Lote lote3 = new Lote();
        lote3.setNombreProducto("Martillo");
        lote3.setFechaEntrada("22/11/2017");
        lote3.setStockParcial(37);
        listaLotes.add(lote3);
        return listaLotes;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.listaLotes = obtenerProductos();
        c_nombre.setCellValueFactory(new PropertyValueFactory<Lote, String>("nombreProducto"));
        c_cantidadDisponible.setCellValueFactory(new PropertyValueFactory<Lote, Integer>("stockParcial"));
        c_fechaEntrada.setCellValueFactory(new PropertyValueFactory<Lote, String>("fechaEntrada"));
        tableViewProductos.setEditable(true);

        for(Lote lote : listaLotes){
            tableViewProductos.getItems().add(lote);
        }
    }
}
