package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryProforma;
import com.dp1wms.model.Producto;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class VentaBusquedaProductoController implements FxmlController{

    //error en no seleccionar producto
    private static final String ERR_PROD_TITLE = "Error";
    private static final String ERR_PROD_HEADER = null;
    private static final String ERR_PROD_CONTENT = "Debe seleccionar un producto";

    //error en cantidad de producto seleccionado
    private static final String ERR_CANT_TITLE = "Error";
    private static final String ERR_CANT_HEADER = null;
    private static final String ERR_CANT_CONTENT= "Ingrese un valor válido";

    @FXML private ComboBox campoCB;
    @FXML private TextField busquedaField;
    @FXML private TableView<Producto> productoTable;
    @FXML private TableColumn<Producto, Integer> codigoTC;
    @FXML private TableColumn<Producto, String> nombreTC;
    @FXML private TableColumn<Producto, Float> precioTC;
    @FXML private TableColumn<Producto, String> categoriaTC;

    @Autowired
    private RepositoryProforma repositoryProforma;

    private StageManager stageManager;
    private VentaProformaController ventaProformaController;

    @FXML
    private void buscarProducto(){
        this.limpiarTabla();
        String campo = null;
        switch(this.campoCB.getSelectionModel().getSelectedIndex()){
            case 0:{
                campo = "codigo";
                break;
            }
            case 1:{
                campo = "nombreproducto";
                break;
            }
            case 2:{
                campo = null;
            }
        }
        String dato = this.busquedaField.getText();
        List<Producto> productos = this.repositoryProforma.buscarProductosParaVenta(campo, dato);
        if(productos == null){
            System.exit(1);
        }
        this.productoTable.getItems().addAll(productos);
    }

    @FXML
    private void seleccionarProducto(ActionEvent event){
        Producto p = this.productoTable.getSelectionModel().getSelectedItem();
        if(p == null){
            this.stageManager.mostrarErrorDialog(ERR_PROD_TITLE, ERR_PROD_HEADER, ERR_PROD_CONTENT);
        } else {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Confirmación");
            dialog.setHeaderText("Producto: " + p.getNombreProducto());
            dialog.setContentText("Ingrese la cantidad:");
            ButtonType ok = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().setAll(ok, cancelar);
            Optional<String> result = dialog.showAndWait();
            int cantidad = 0;
            if(result.isPresent()){
                try{
                    cantidad = Integer.parseInt(result.get());
                } catch (NumberFormatException e){
                    cantidad = 0;
                }
            }
            if(cantidad > 0){
                this.ventaProformaController.agregarProducto(p, cantidad);
                this.cerrarVentana(event);
            } else {//show error
                this.stageManager.mostrarErrorDialog(ERR_CANT_TITLE, ERR_CANT_HEADER, ERR_CANT_CONTENT);
            }
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event){
        this.limpiarTabla();
        this.stageManager.cerrarVentana(event);
    }

    private void limpiarTabla(){
        this.productoTable.getItems().clear();
        this.codigoTC.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("codigo"));
        this.nombreTC.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
        this.precioTC.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precio"));
        this.categoriaTC.setCellValueFactory(new PropertyValueFactory<Producto, String>("Categoria"));
        this.productoTable.setEditable(false);
    }

    @Override
    public void initialize(){
        this.productoTable.getItems().clear();
        this.campoCB.getItems().addAll("Codigo", "Nombre", "Categoria");
        this.campoCB.getSelectionModel().select(0);
    }

    @Autowired @Lazy
    public VentaBusquedaProductoController(StageManager stageManager, VentaProformaController ventaProformaController){
        this.stageManager = stageManager;
        this.ventaProformaController = ventaProformaController;
    }

}
