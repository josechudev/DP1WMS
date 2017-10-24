package com.dp1wms.controller.MantCategoria;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.ICategoriaProducto.RepositoryMantCategoria;
import com.dp1wms.model.CategoriaProducto;
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
import java.util.List;

@Component
public class CategoriaController implements FxmlController {

    private final StageManager stageManager;

    @FXML private TableView <CategoriaProducto> e_table;
    @FXML private TableColumn <CategoriaProducto,Integer> e_id;
    @FXML private TableColumn <CategoriaProducto,String> e_descripcion;

    @Autowired
    private RepositoryMantCategoria repositoryMantCategoria;
    @Autowired @Lazy
    public CategoriaController (StageManager stageManager){
        this.stageManager = stageManager;
    }

    public void btnClickCrearCategoria(ActionEvent event){
        System.out.printf( "Crear Categoria");
        Parent root = null;
        FXMLLoader loader;
        CategoriaProducto categoriaProducto = new CategoriaProducto();
        try{
            loader = new FXMLLoader(getClass().getResource("/fxml/Categorias/DatosCategoria.fxml"));
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




    @FXML public void btnClickModificarCategoria(ActionEvent actionEvent) {
        System.out.println("Modificar Categoria");

        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;

        //this.stageManager.mostarModal(FxmlView.DATOS_USUARIO);
        CategoriaProducto categoriaProducto = e_table.getSelectionModel().getSelectedItem();
        Parent root = null;
        FXMLLoader loader;
        try {
            loader =new FXMLLoader(getClass().getResource("/fxml/Categorias/DatosCategoria.fxml"));
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
        this._llenarGrilla();
    }

    @FXML public void btnClickEliminarCategoria(ActionEvent actionEvent) {
        System.out.println("Eliminar MantUsuario");

        if(e_table.getSelectionModel().getSelectedItem() == null)
            return;

        CategoriaProducto categoriaProducto = e_table.getSelectionModel().getSelectedItem();

        repositoryMantCategoria.deleteCategoria(categoriaProducto);

        this._llenarGrilla();
    }

    private void _llenarGrilla() {
        e_table.getItems().clear();
        List<CategoriaProducto> categoriaProductoList = repositoryMantCategoria.selectAllCategoria();

        for(int i = 0; i < categoriaProductoList.size(); i++){
            e_table.getItems().add(new CategoriaProducto( categoriaProductoList.get(i).getIdCategoria(),categoriaProductoList.get(i).getDescripcion()));
        }
    }

    @Override
    public void initialize() {
        e_id.setCellValueFactory(new PropertyValueFactory<CategoriaProducto,Integer>("IdCategoria"));
        e_descripcion.setCellValueFactory(new PropertyValueFactory<CategoriaProducto,String>("Descripcion"));
        this._llenarGrilla();
    }

    public void crearCategoriaDB(CategoriaProducto categoriaProducto){
        repositoryMantCategoria.createCategoria(categoriaProducto);
    }
    public void  modificarCategoriaBD(CategoriaProducto categoriaProducto){
        repositoryMantCategoria.updateCategoria(categoriaProducto);
    }

}
