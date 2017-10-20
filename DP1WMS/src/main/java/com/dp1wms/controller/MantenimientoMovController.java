package com.dp1wms.controller;

import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.Producto;
import com.dp1wms.view.FxmlView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MantenimientoMovController implements FxmlController{
    @Autowired
    private RepositoryMantMov repositoryMantMov;

    private final StageManager stageManager;

    @Autowired @Lazy
     public  MantenimientoMovController(StageManager stageManager){
                this.stageManager = stageManager;
            }


    @FXML
    public void ingresoLote(ActionEvent event){
        System.out.println("Ingreso de Lote");
        if(repositoryMantMov != null){
            System.out.println("not null repository");
            List<Producto> listaProductos = repositoryMantMov.obtenerProductos();
        }else{
            System.out.println("null repository");
        }

        Parent root = null;
        FXMLLoader loader;
        try {

            loader =new FXMLLoader(getClass().getResource("/fxml/CrearLote.fxml"));
            root = (Parent) loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Window existingWindow = ((Node) event.getSource()).getScene().getWindow();



        // create a new stage:
        Stage stage = new Stage();
        // make it modal:
        stage.initModality(Modality.APPLICATION_MODAL);
        // make its owner the existing window:
       // stage.initOwner(existingWindow);

        stage.setScene(scene);
        stage.show();
     }

    public void movimientoProducto(ActionEvent event){
        System.out.println("Movimiento de un Producto");
        /*if(repositoryMantMov != null){
            System.out.println("not null repository");
            List<Producto> listaProductos = repositoryMantMov.obtenerProductos();
            for(Producto producto : listaProductos){
                System.out.println("IdProducto: " + producto.getIdProducto());
                System.out.println("Nombre: "+producto.getNombreProducto());
                System.out.println("idCategoria: " + producto.getIdCategoria());
                System.out.println("Categoria: "+producto.getCategoria());
            }
        }else{
            System.out.println("null repository");
        }

        Parent root = null;
        FXMLLoader loader;
        try {

            loader =new FXMLLoader(getClass().getResource("/fxml/IngresoProducto.fxml"));
            root = (Parent) loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Window existingWindow = ((Node) event.getSource()).getScene().getWindow();



        // create a new stage:
        Stage stage = new Stage();
        // make it modal:
        stage.initModality(Modality.APPLICATION_MODAL);
        // make its owner the existing window:
        stage.initOwner(existingWindow);

        stage.setScene(scene);
        stage.show();

       /* Parent crearLotePage = null;
        try {
            crearLotePage = FXMLLoader.load(getClass().getResource("/fxml/IngresoProductoController.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene crearLoteScene = new Scene(crearLotePage);
        Stage crearLoteStage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        crearLoteStage.hide();
        crearLoteStage.setScene(crearLoteScene);
        crearLoteStage.show();*/
        this.stageManager.mostarModal(FxmlView.INGRESO_PRODUCTO);
    }

    @Override
    public void initialize() {

    }
}
