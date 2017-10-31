package com.dp1wms.controller.MantProducto;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MantCategoria.CategoriaDatosController;
import com.dp1wms.dao.IProducto.RepositoryMantProducto;
import com.dp1wms.model.CategoriaProducto;
import com.dp1wms.model.Producto;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.SplittableRandom;

@Component
public class ProductoController  implements FxmlController {
    /*
   column_name    |          data_type
------------------+-----------------------------
 idproducto       | integer
 nombreproducto   | character varying
 peso             | real
 fechavencimiento | timestamp without time zone
 descripcion      | character varying
 precio           | real
 stock            | integer
 idcategoria      | integer
 codigo           | character varying
 fechacreacion    | timestamp without time zone
 activo           | boolean

     */
    private final StageManager stageManager;
    @FXML private TableView<Producto> tableViewProductos;
    @FXML private TableColumn<Producto,Integer>c_indice;
    @FXML private TableColumn<Producto,String>c_prodNombre;
    @FXML private TableColumn<Producto,Float>c_prodPeso;
    @FXML private TableColumn<Producto,String>c_prodFechaV;
    @FXML private TableColumn<Producto,String>c_prodDescripcion;
    @FXML private TableColumn<Producto,Float>c_prodPrecio;
    @FXML private TableColumn<Producto,Integer>c_prodStock;
    @FXML private TableColumn<Producto,String>c_prodCat;
    @FXML private TableColumn<Producto,String>c_prodCod;
    @FXML private TableColumn<Producto,String>c_prodFechaC;
    @FXML private TableColumn<Producto, String> c_prodAct;
    @FXML private TableColumn<Producto,Float>c_prodPrecioC;
    @FXML private TableColumn<Producto,String>c_prodUnidades;
    @Autowired
    private RepositoryMantProducto repositoryMantProducto;
    @Autowired
    @Lazy
    public ProductoController (StageManager stageManager){
        this.stageManager = stageManager;
    }

    public void btnClickAgregar(ActionEvent event){
        System.out.println("Crear producto");
        Parent root = null;
        FXMLLoader loader;
        Producto producto = new Producto();
        try{
            loader = new FXMLLoader(getClass().getResource("/fxml/Productos/DatosProducto.fxml"));
            root = (Parent) loader.load();
            ProductoDatosController controller = loader.getController();
            controller._setData(producto,0);
            controller.setV_parentController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        this._llenarGrilla();
    }
    public void btnClickModificar(ActionEvent event){
        System.out.println("Modificar producto");

        if(tableViewProductos.getSelectionModel().getSelectedItem() == null)
            return;

        //this.stageManager.mostarModal(FxmlView.DATOS_USUARIO);
        Producto producto = tableViewProductos.getSelectionModel().getSelectedItem();
        Parent root = null;
        FXMLLoader loader;
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/Productos/DatosProducto.fxml"));
            root = (Parent) loader.load();
            ProductoDatosController controller = loader.getController();
            //1 es modificar
            controller._setData(producto,1);
            controller.setV_parentController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        this._llenarGrilla();
    }
    public void btnClickEliminar(ActionEvent actionEvent){
        System.out.println("Eliminar Usuario");

        if(tableViewProductos.getSelectionModel().getSelectedItem() == null)
            return;

        Producto producto = tableViewProductos.getSelectionModel().getSelectedItem();

        eliminarProductoBD(producto);

        this._llenarGrilla();
    }

    private void _llenarGrilla() {
        tableViewProductos.getItems().clear();
        List<Producto> productoList = repositoryMantProducto.selectAllProducto();

        for(int i = 0; i < productoList.size(); i++){
            tableViewProductos.getItems().add(new Producto( productoList.get(i)));
        }
    }

    @Override
    public void initialize() {
        c_indice.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("IdProducto"));
        c_prodAct.setCellValueFactory(new PropertyValueFactory<Producto,String>("Activo"));
        c_prodCat.setCellValueFactory(new PropertyValueFactory<Producto,String>("Categoria"));
        c_prodCod.setCellValueFactory(new PropertyValueFactory<Producto,String>("Codigo"));
        c_prodDescripcion.setCellValueFactory(new PropertyValueFactory<Producto,String>("Descripcion"));
        c_prodFechaC.setCellValueFactory(new PropertyValueFactory<Producto,String>("FechaCreacion"));
        c_prodFechaV.setCellValueFactory(new PropertyValueFactory<Producto,String>("FechaVencimiento"));
        c_prodNombre.setCellValueFactory(new PropertyValueFactory<Producto,String>("NombreProducto"));
        c_prodPeso.setCellValueFactory(new PropertyValueFactory<Producto,Float>("Peso"));
        c_prodPrecio.setCellValueFactory(new PropertyValueFactory<Producto,Float>("Precio"));
        c_prodStock.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("Stock"));
        c_prodUnidades.setCellValueFactory(new PropertyValueFactory<Producto,String>("Unidades"));
        c_prodPrecioC.setCellValueFactory(new PropertyValueFactory<Producto,Float>("PrecioCompra"));

        this._llenarGrilla();
    }

    public void crearProductoBD(Producto producto) throws ParseException {
        repositoryMantProducto.createProducto(producto);
    }
    public void  modificarProductoBD(Producto producto){
        repositoryMantProducto.updateProducto(producto);
    }
    public void eliminarProductoBD(Producto producto){
        repositoryMantProducto.deleteProducto(producto);
    }
}
