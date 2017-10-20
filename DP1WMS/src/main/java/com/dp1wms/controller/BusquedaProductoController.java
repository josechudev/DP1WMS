package com.dp1wms.controller;

import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.Producto;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class BusquedaProductoController implements Initializable {

    @FXML
    private AnchorPane buscarProductoAnchorPane;

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

    private IngresoProductoController ingresoProductoControllerController;

    private CrearLote crearLoteController;

    private String controllerActual = null;

    @Autowired
    private RepositoryMantMov repositoryMantMov;

    private final StageManager stageManager;

    @Autowired @Lazy
    public BusquedaProductoController(StageManager stageManager, IngresoProductoController ingresoProductoController){
        this.stageManager = stageManager;
        this.ingresoProductoControllerController = ingresoProductoController;
    }


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

    public void setMovimientoProductoController(IngresoProductoController controller){
        this.ingresoProductoControllerController = controller;
        this.controllerActual = "IngresoProductoController";
    }

    public void setCrearLoteController(CrearLote controller){
        this.crearLoteController = controller;
        this.controllerActual = "CrearLote";
    }

    public void escogerProducto(ActionEvent event){
        Producto producto = tableViewProductos.getSelectionModel().getSelectedItem();
        System.out.println(producto.getNombreProducto());
        // Integer idProducto = obtenerIdProducto();

        ingresoProductoControllerController.actualizarDataProducto(producto.getNombreProducto(),1);
        buscarProductoAnchorPane.getScene().getWindow().hide();
    }

    public void cancelarBusqueda(ActionEvent event){
        buscarProductoAnchorPane.getScene().getWindow().hide();
    }

    private void limpiarTabla(){
        tableViewProductos.getItems().clear();
        c_nombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
        c_categoria.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("idCategoria"));
        tableViewProductos.setEditable(true);
    }

    private  List<Producto >buscarProducto(String nombreProducto){
        List<Producto> lista  = new ArrayList<Producto>();
        if(nombreProducto.equalsIgnoreCase(""))
            return this.listaProductos;

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
        if(repositoryMantMov == null){
            System.out.println("Repository null");
        }else{
            System.out.println("repository not null");
        }
        return listaProductos;
        //return repositoryMantMov.obtenerProductos();
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
