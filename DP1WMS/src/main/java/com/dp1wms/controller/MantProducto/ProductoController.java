package com.dp1wms.controller.MantProducto;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

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
    @FXML private TableView<Producto> tableViewProductos;
    @FXML private TableColumn<Producto,Integer>c_indice;
    @FXML private TableColumn<Producto,String>c_prodNombre;
    @FXML private  TableColumn<Producto,Float>c_prodPeso;
    @FXML private  TableColumn<Producto,Float>c_prodFechaV;
    @FXML private  TableColumn<Producto,Float>c_prodDescripcion;
    @FXML private  TableColumn<Producto,Float>c_prodPrecio;
    @FXML private  TableColumn<Producto,Float>c_prodStock;
    @FXML private  TableColumn<Producto,Float>c_prodCat;
    @FXML private  TableColumn<Producto,Float>c_prodCod;
    @FXML private  TableColumn<Producto,Float>c_prodFechaC;
    @FXML private  TableColumn<Producto,Float>c_prodAct;


    @Override
    public void initialize() {

    }
}
