package com.dp1wms.controller;

import com.dp1wms.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BusquedaProducto implements Initializable {

    @FXML
    private TextField txb_nombre;

    @FXML
    private TableView<Producto> tableViewProductos = new TableView<Producto>();

    @FXML
    private ComboBox cb_categoria;

    @FXML
    private TableColumn<Producto,String> c_nombre;
    @FXML
    private TableColumn<Producto,Integer> c_categoria;

    private List<Producto> listaProductos;

    public void buscarProducto(ActionEvent event){
        System.out.println("Buscar Producto");

        String nombreProducto = txb_nombre.getText();
        //String idCategoria = cb_categoria.getValue().toString();
        List<Producto> listaProductos = buscarProducto(nombreProducto);
        limpiarTabla();
        for(Producto producto:listaProductos){
            tableViewProductos.getItems().add(producto);
        }
    }

    public Producto escogerProducto(ActionEvent event){
        Producto producto = tableViewProductos.getSelectionModel().getSelectedItem();
        System.out.println(producto.getNombreProducto());
        return producto;
    }

    private void limpiarTabla(){
        tableViewProductos.getItems().clear();
        c_nombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
        c_categoria.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("idCategoria"));
        tableViewProductos.setEditable(true);
    }

    private  List<Producto >buscarProducto(String nombreProducto){
        List<Producto> lista  = new ArrayList<Producto>();
        for(Producto producto : listaProductos){
            if(producto.getNombreProducto().equalsIgnoreCase(nombreProducto))
                lista.add(producto);
        }
        return lista;
    }

    private List<Producto> obtenerProductos(){
        List<Producto> listaProductos = new ArrayList<Producto>();
        Producto producto = new Producto();
        producto.setNombreProducto("Martillo");
        producto.setIdCategoria(1);
        listaProductos.add(producto);
        Producto producto2 = new Producto();
        producto2.setNombreProducto("Cemento");
        producto2.setIdCategoria(2);
        listaProductos.add(producto2);
        return listaProductos;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.listaProductos = obtenerProductos();
        c_nombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
        c_categoria.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("idCategoria"));
        tableViewProductos.setEditable(true);

        for(Producto producto:listaProductos){
            tableViewProductos.getItems().add(producto);
        }
    }
/*

    public ObservableList<Person>  getPeople()
    {
        ObservableList<Person> people = FXCollections.observableArrayList();
        people.add(new Person("Frank","Sinatra",LocalDate.of(1915, Month.DECEMBER, 12), new Image("FrankSinatra.jpg")));
        people.add(new Person("Rebecca","Fergusson",LocalDate.of(1986, Month.JULY, 21)));
        people.add(new Person("Mr.","T",LocalDate.of(1952, Month.MAY, 21)));

        return people;
    }*/
}
