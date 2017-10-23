package com.dp1wms.controller.Descuentos;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.CategoriaProducto;
import com.dp1wms.model.Producto;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
public class BusquedaProductoDescController implements FxmlController {

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
    private TableColumn<Producto,String> c_categoria;
    @FXML
    private TableColumn<Producto,Integer> c_indice;
    @FXML
    private TableColumn<Producto,Integer> c_stock;

    private List<Producto> listaProductos;

    private List<CategoriaProducto> listaCategorias;

    private DatosDescuentoController datosDescuentoController = null;

    @Autowired
    private RepositoryMantMov repositoryMantMov;

    private final StageManager stageManager;

    @Autowired @Lazy
     public BusquedaProductoDescController(StageManager stageManager, DatosDescuentoController datosDescuentoController){
                this.stageManager = stageManager;
                this.datosDescuentoController = datosDescuentoController;
        }
    public void buscarProducto(ActionEvent event){
        System.out.println("Buscar Producto");

        //String idCategoria = cb_categoria.getValue().toString();
        List<Producto> listaProductos = buscarProductoTabla();
        limpiarTabla();
        Integer indice = 1;
        for(Producto producto:listaProductos){
            producto.setIndiceTableView(indice);
            indice++;
            tableViewProductos.getItems().add(producto);
        }
    }

    public void escogerProducto(ActionEvent event){
        Producto producto = tableViewProductos.getSelectionModel().getSelectedItem();
        System.out.println("Nombre Producto: " + producto.getNombreProducto()+" IdProducto: "+producto.getIdProducto());

        Boolean productoGenerador = datosDescuentoController.busquedaParaGenerador();

        datosDescuentoController.actualizarProducto(producto,productoGenerador);

        buscarProductoAnchorPane.getScene().getWindow().hide();
    }

    public void cancelarBusqueda(ActionEvent event){
        buscarProductoAnchorPane.getScene().getWindow().hide();
    }

    private void limpiarTabla(){
        tableViewProductos.getItems().clear();
        c_nombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
        c_categoria.setCellValueFactory(new PropertyValueFactory<Producto, String>("categoria"));
        c_indice.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("indiceTableView"));
        c_stock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("stock"));
        tableViewProductos.setEditable(true);
    }

    private  List<Producto >buscarProductoTabla(){
        List<Producto> lista  = new ArrayList<Producto>();
        String categoriaBusqueda;

        if(cb_categoria.getValue() != null){
            categoriaBusqueda = cb_categoria.getValue().toString();
        }else{
            categoriaBusqueda = "";
        }

        String nombreBusqueda = txb_nombre.getText().toLowerCase();

        if(!categoriaBusqueda.equalsIgnoreCase("")){
            if(!nombreBusqueda.equalsIgnoreCase("")){
                for(Producto producto : listaProductos){
                    if(producto.getNombreProducto().toLowerCase().contains(nombreBusqueda) && producto.getCategoria().equalsIgnoreCase(categoriaBusqueda))
                        lista.add(producto);
                }
            }else{
                for(Producto producto : listaProductos){
                    if(producto.getCategoria().equalsIgnoreCase(categoriaBusqueda))
                        lista.add(producto);
                }
            }
        }else if(!nombreBusqueda.equalsIgnoreCase("")){
            for(Producto producto : listaProductos){
                if(producto.getNombreProducto().toLowerCase().contains(nombreBusqueda))
                    lista.add(producto);
            }
        }else{
            return this.listaProductos;
        }
        return lista;
    }

    private List<Producto> obtenerProductos(){
        return repositoryMantMov.obtenerProductos();
    }

    private void llenarTabla(List<Producto> listaProd){
        Integer indice = 1;
        for(Producto producto:listaProductos){
            producto.setIndiceTableView(indice);
            indice++;
            tableViewProductos.getItems().add(producto);
        }
    }


    @Override
    public void initialize() {
        this.listaProductos = obtenerProductos();
        c_nombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
        c_categoria.setCellValueFactory(new PropertyValueFactory<Producto, String>("categoria"));
        c_indice.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("indiceTableView"));
        c_stock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("stock"));
        tableViewProductos.setEditable(true);

        this.llenarTabla(this.listaProductos);
        this.listaCategorias = repositoryMantMov.obtenerCategoriasProducto();
        cb_categoria.getItems().add("");
        for(CategoriaProducto categoria:this.listaCategorias) {
            cb_categoria.getItems().add(categoria.getDescripcion());
        }
    }
}
