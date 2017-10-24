package com.dp1wms.controller.MantProducto;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

;import javax.xml.soap.Text;


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
    @FXML private TextField txt_prodActivo;
    @FXML private Button btn_aceptar, btn_cancelar;
    @Override
    public void initialize() {

    }

    public void _setData(Producto producto, int v_accion) {
        this.v_accion = v_accion;
        if(this.v_accion==0){
            btn_aceptar.setText("Crear Producto");
        }
        else{

            txt_prodId.setText(Integer.toString(producto.getIdProducto()));
            txt_prodPeso.setText(Float.toString(producto.getPeso()));
            txt_prodCategoria.setText(producto.getCategoria());
            txt_prodNombre.setText(producto.getNombreProducto());
            txt_prodFechaV.setText(producto.getFechaVencimiento());
            txt_prodCod.setText(producto.getCodigo());
            txt_prodPrecio.setText(Float.toString(producto.getPrecio()));
            txt_prodFechaC.setText(producto.getFechaCreacion());
            txt_prodStock.setText(Integer.toString(producto.getStock()));
            txt_prodActivo.setText(Boolean.toString(producto.isActivo()));
            btn_aceptar.setText("Modificar Producto");
        }
    }


    public void btnClickAceptar(ActionEvent actionEvent) {
        System.out.println("Aceptar");
        Producto producto = new Producto();
        producto.setIdProducto(Integer.parseInt(txt_prodId.getText()));
        producto.setPeso(Float.parseFloat(txt_prodPeso.getText()));
        producto.setCategoria(txt_prodCategoria.getText());
        producto.setNombreProducto(txt_prodNombre.getText());
        producto.setFechaVencimiento(txt_prodFechaV.getText());
        producto.setCodigo(txt_prodCod.getText());
        producto.setPrecio(Float.parseFloat(txt_prodPrecio.getText()));
        producto.setFechaCreacion(txt_prodFechaC.getText());
        producto.setStock(Integer.parseInt(txt_prodStock.getText()));
        producto.setActivo(Boolean.parseBoolean(txt_prodStock.getText()));
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
        this.v_parentController = v_parentController;
    }
}
