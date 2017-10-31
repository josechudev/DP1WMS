package com.dp1wms.controller.MantProducto;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

;import javax.xml.soap.Text;
import java.text.ParseException;


@Component
public class ProductoDatosController  implements FxmlController {
    private  int v_accion;
    private Producto producto;
    public static ProductoController v_parentController;
    @FXML private TextField txt_prodId;
    @FXML private TextField txt_prodPeso;
    @FXML private TextField txt_prodCategoria;
    @FXML private TextField txt_prodNombre;
    @FXML private TextField txt_prodFechaV;
    @FXML private TextField txt_prodCod;
    @FXML private TextField txt_prodPrecio;
    @FXML private TextField txt_prodFechaC;
    @FXML private TextField txt_prodStock;
    @FXML private ComboBox cb_prodActivo;
    @FXML private Button btn_aceptar, btn_cancelar;
    @FXML private TextField txt_prodDescripcion;
    @FXML private TextField txt_prodPrecioC;
    @FXML private TextField txt_prodUnidades;
    @Override
    public void initialize() {
        cb_prodActivo.getItems().removeAll(cb_prodActivo.getItems());
        cb_prodActivo.getItems().addAll("Activo","Inactivo");

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
            txt_prodCategoria.setText(Integer.toString(producto.getIdCategoria()));
            txt_prodNombre.setText(producto.getNombreProducto());
            txt_prodFechaV.setText(producto.getFechaVencimiento());
            txt_prodCod.setText(producto.getCodigo());
            txt_prodPrecio.setText(Float.toString(producto.getPrecio()));
            txt_prodFechaC.setText(producto.getFechaCreacion());
            txt_prodStock.setText(Integer.toString(producto.getStock()));
            cb_prodActivo.getSelectionModel().select(producto.isActivo());
            txt_prodPrecioC.setText(Float.toString(producto.getPrecioCompra()));
            txt_prodUnidades.setText(producto.getUnidades());
            btn_aceptar.setText("Modificar Producto");
        }
    }


    public void btnClickAceptar(ActionEvent actionEvent) throws ParseException {
        System.out.println("Aceptar");
        Producto producto = new Producto();
        producto.setIdProducto(Integer.parseInt(v_accion==0?"0":txt_prodId.getText()));
        producto.setPeso(Float.parseFloat(txt_prodPeso.getText()));
        producto.setIdCategoria(Integer.parseInt(txt_prodCategoria.getText()));
        producto.setDescripcion(txt_prodDescripcion.getText());
        producto.setNombreProducto(txt_prodNombre.getText());
        producto.setFechaVencimiento(txt_prodFechaV.getText());
        producto.setCodigo(txt_prodCod.getText());
        producto.setPrecio(Float.parseFloat(txt_prodPrecio.getText()));
        producto.setFechaCreacion(txt_prodFechaC.getText());
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

    public void setV_parentController(ProductoController productoController) {
        this.v_parentController = productoController;
    }
}
