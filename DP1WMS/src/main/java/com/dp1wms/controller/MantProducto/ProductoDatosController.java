package com.dp1wms.controller.MantProducto;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.CategoriaProducto;
import com.dp1wms.model.Producto;
import com.dp1wms.util.DateParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

;import javax.xml.soap.Text;
import java.text.ParseException;
import java.util.List;


@Component
public class ProductoDatosController  implements FxmlController {
    private  int v_accion;
    private Producto producto;
    public static ProductoController v_parentController;
    @FXML private TextField txt_prodId;
    @FXML private TextField txt_prodPeso;
    //@FXML private TextField txt_prodCategoria;
    @FXML private TextField txt_prodNombre;
    //@FXML private TextField txt_prodFechaV;
    @FXML private TextField txt_prodCod;
    @FXML private TextField txt_prodPrecio;
    //@FXML private TextField txt_prodFechaC;
    @FXML private TextField txt_prodStock;
    @FXML private ComboBox cb_prodActivo;
    @FXML private Button btn_aceptar, btn_cancelar;
    @FXML private TextField txt_prodDescripcion;
    @FXML private TextField txt_prodPrecioC;
    @FXML private TextField txt_prodUnidades;
    @FXML private ComboBox<CategoriaProducto> cb_categoria;
    @FXML private DatePicker dp_fechavencimiento;
    @FXML private DatePicker dp_fechacreacion;

    private List<CategoriaProducto> listaCategorias;


    @Override
    public void initialize() {
        cb_prodActivo.getItems().removeAll(cb_prodActivo.getItems());
        cb_prodActivo.getItems().addAll("Activo","Inactivo");
        this.configurarComboBoxCajon();
    }

    private void configurarComboBoxCajon(){

        Callback<ListView<CategoriaProducto>, ListCell<CategoriaProducto>> factory = lv -> new ListCell<CategoriaProducto>() {

            @Override
            protected void updateItem(CategoriaProducto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : ""+item.getDescripcion());
            }

        };

        cb_categoria.setCellFactory(factory);
        cb_categoria.setButtonCell(factory.call(null));
    }

    public void _setData(Producto producto, int v_accion) {
        this.v_accion = v_accion;
        if(this.v_accion==0){
            btn_aceptar.setText("Crear Producto");
        }
        else{

            txt_prodId.setText(Integer.toString(producto.getIdProducto()));
            txt_prodDescripcion.setText(producto.getDescripcion());
            txt_prodPeso.setText(Float.toString(producto.getPeso()));
            for(CategoriaProducto categoria:this.listaCategorias) {
                if(categoria.getIdCategoria() == producto.getIdCategoria()){
                    cb_categoria.setValue(categoria);
                }
            }
            //txt_prodCategoria.setText(Integer.toString(producto.getIdCategoria()));
            txt_prodNombre.setText(producto.getNombreProducto());
            //txt_prodFechaV.setText(producto.getFechaVencimiento());
            txt_prodCod.setText(producto.getCodigo());
            txt_prodPrecio.setText(Float.toString(producto.getPrecio()));
            //txt_prodFechaC.setText(producto.getFechaCreacion());
            txt_prodStock.setText(Integer.toString(producto.getStock()));
            cb_prodActivo.getSelectionModel().select(producto.isActivo());
            txt_prodPrecioC.setText(Float.toString(producto.getPrecioCompra()));
            txt_prodUnidades.setText(producto.getUnidades());
            btn_aceptar.setText("Modificar Producto");
        }
    }


    public void btnClickAceptar(ActionEvent actionEvent) throws ParseException {
        System.out.println("Aceptar");
        if (cb_categoria.getValue() == null) {
            //this.stageManager.mostrarErrorDialog("Error al escoger producto", null,
            //        "Debe seleccionar un producto");
            System.out.println("No selecciono categoria");
            return;
        }

        Producto producto = new Producto();
        producto.setIdProducto(Integer.parseInt(v_accion==0?"0":txt_prodId.getText()));
        producto.setPeso(Float.parseFloat(txt_prodPeso.getText()));
        //producto.setIdCategoria(Integer.parseInt(txt_prodCategoria.getText()));
        producto.setIdCategoria(cb_categoria.getValue().getIdCategoria());
        producto.setDescripcion(txt_prodDescripcion.getText());
        producto.setNombreProducto(txt_prodNombre.getText());
        producto.setFechaVencimiento(DateParser.timestampToString(DateParser.localdateToTimestamp(dp_fechavencimiento.getValue())));
       // producto.setFechaVencimiento(txt_prodFechaV.getText());
        producto.setCodigo(txt_prodCod.getText());
        producto.setPrecio(Float.parseFloat(txt_prodPrecio.getText()));
        //producto.setFechaCreacion(txt_prodFechaC.getText());
        producto.setFechaCreacion(DateParser.timestampToString(DateParser.localdateToTimestamp(dp_fechacreacion.getValue())));
        producto.setStock(v_accion==0?0:Integer.parseInt(txt_prodStock.getText()));
        producto.setUnidades(txt_prodUnidades.getText());
        producto.setPrecioCompra(Float.parseFloat(txt_prodPrecioC.getText()));
        if(cb_prodActivo.getValue().equals("Activo"))
            System.out.println(cb_prodActivo.getValue());
        producto.setActivo(cb_prodActivo.getValue().equals("Activo")?true:false);
        if(this.v_accion ==0){
            v_parentController.crearProductoBD(producto);
        }else {
            v_parentController.modificarProductoBD(producto);
        }
        System.out.println("Accion realizada");
        v_parentController.initialize();
        Stage stage = (Stage) btn_aceptar.getScene().getWindow();
        stage.close();
    }
    public void btnClickCancelar(ActionEvent actionEvent) {
        System.out.println("Cancelar");
        Stage stage = (Stage) btn_cancelar.getScene().getWindow();
        stage.close();
    }

    public void llenarComboCategoria(){
        this.listaCategorias = v_parentController.obtenerListaCategorias();

        for(CategoriaProducto categoria:this.listaCategorias) {
            cb_categoria.getItems().add(categoria);
        }
    }

    public void setV_parentController(ProductoController productoController) {
        this.v_parentController = productoController;
    }
}
