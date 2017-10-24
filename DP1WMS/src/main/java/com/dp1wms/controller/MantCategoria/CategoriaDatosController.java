package com.dp1wms.controller.MantCategoria;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.CategoriaProducto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class CategoriaDatosController implements FxmlController{
   
    @FXML private TextField e_idCategoria;
    @FXML private TextField e_descripcionCategoria;
    @FXML private Button btn_aceptar,btn_cancelar;
    private int v_accion;
    private  CategoriaProducto categoriaProducto;
    
    public static CategoriaController v_parentController;
    @Override
    public void initialize() {

    }

    public void _setData(CategoriaProducto categoriaProducto, int v_accion) {
        e_idCategoria.setText("0");
        e_descripcionCategoria.setText("0");
        this.v_accion=v_accion;

        if(this.v_accion==0){
            btn_aceptar.setText("Crear Categoria");
        }
        else{
            e_descripcionCategoria.setText(categoriaProducto.getDescripcion());
            e_idCategoria.setText(Integer.toString(categoriaProducto.getIdCategoria()));
            btn_aceptar.setText("Modificar Categoria");
        }
    }

    public void setV_parentController(CategoriaController v_parentController) {
        this.v_parentController = v_parentController;
    }

    public void btnClickAceptar(ActionEvent actionEvent) {
        System.out.println("Aceptar");
        CategoriaProducto categoriaProducto = new CategoriaProducto();
        categoriaProducto.setIdCategoria(Integer.parseInt(e_idCategoria.getText()));
        categoriaProducto.setDescripcion(e_descripcionCategoria.getText());
        if(this.v_accion ==0){
            v_parentController.crearCategoriaDB(categoriaProducto);
        }else {
            v_parentController.modificarCategoriaBD(categoriaProducto);
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
}
