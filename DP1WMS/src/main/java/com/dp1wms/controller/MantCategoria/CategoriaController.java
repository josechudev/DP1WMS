package com.dp1wms.controller.CategoriaController;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.ICategoriaProducto.RepositoryMantCategoria;
import com.dp1wms.model.CategoriaProducto;
import com.dp1wms.view.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

@Component
public class CategoriaController implements FxmlController {

    private final StageManager stageManager;

    @FXML private TableView <CategoriaProducto> e_table;
    @FXML private TableColumn <CategoriaProducto,Integer> e_id;
    @FXML private TableColumn <CategoriaProducto,String> e_descripcion;

    @Autowired
    private RepositoryMantCategoria repositryMantCategoria;
    @Autowired @Lazy
    public  CategoriaController (StageManager stageManager){
        this.stageManager = stageManager;
    }

    public void btnClickCrearCategoria(ActionEvent event){
        System.out.printf( "Crear categoria");
        Parent root = null;
        FXMLLoader loader;
        CategoriaProducto categoriaProducto = new CategoriaProducto();
        try{
            loader = new FXMLLoader(getClass().getResource("/fxml/CategoriaFxml/DtosCategoria.xml"));
            root = (Parent) loader.load();
            CategoriaDatosController controller = loader.getController();
            controller._setData(categoriaProducto,0);
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
    }




    public void btnClickModificarCategoria(ActionEvent actionEvent) {
        System.out.println("Modificar Categoria");

        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;

        //this.stageManager.mostarModal(FxmlView.DATOS_USUARIO);
        CategoriaProducto categoriaProducto = e_table.getSelectionModel().getSelectedItem();
        Parent root = null;
        FXMLLoader loader;
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/UsuarioFxml/DatosUsuario.fxml"));
            root = (Parent) loader.load();
            CategoriaDatosController controller = loader.getController();
            //1 es modificar
            controller._setData(categoriaProducto,1);
            controller.setV_parentController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Window existingWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    public void btnClickEliminarCategoria(ActionEvent actionEvent) {
        System.out.println("Eliminar Usuario");

        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;

        CategoriaProducto categoriaProducto = e_table.getSelectionModel().getSelectedItem();

        repositryMantCategoria.deleteCategoria(categoriaProducto);

        this._llenarGrilla();
    }

    private void _llenarGrilla() {
        e_table.getItems().clear();
        List<CategoriaProducto> categoriaProductoList = repositryMantCategoria.selectAllCategoria();
        for (CategoriaProducto cat: categoriaProductoList
             ) {
            e_table.getItems().add(new CategoriaProducto(cat.getIdCategoria(),cat.getDescripcion()));
        }
    }

    @Override
    public void initialize() {

    }
}
