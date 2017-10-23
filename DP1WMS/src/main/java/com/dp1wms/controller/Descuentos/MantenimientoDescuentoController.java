package com.dp1wms.controller.Descuentos;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryDescuento;
import com.dp1wms.model.Descuento;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javafx.scene.control.TableView;

import java.sql.Timestamp;
import java.util.List;

@Component
public class MantenimientoDescuentoController implements FxmlController {
    @Autowired
    private RepositoryDescuento repositoryDescuento;

    @FXML
    private TableView<Descuento> tableViewDescuentos = new TableView<Descuento>();

    List<Descuento> listaDescuentos;

    @FXML
    private TableColumn<Descuento,Integer> c_indice;
    @FXML
    private TableColumn<Descuento,String> c_tipoDescuento;
    @FXML
    private TableColumn<Descuento,String> c_productoGen;
    @FXML
    private TableColumn<Descuento,String> c_categoriaGen;
    @FXML
    private TableColumn<Descuento,Integer> c_cantidadGen;
    @FXML
    private TableColumn<Descuento,String> c_ProdDesc;
    @FXML
    private TableColumn<Descuento,String> c_categoriaDesc;
    @FXML
    private TableColumn<Descuento,Integer> c_cantidadDesc;
    @FXML
    private TableColumn<Descuento,Double> c_porcentajeDesc;
    @FXML
    private TableColumn<Descuento,Timestamp> c_fechaInicio;
    @FXML
    private TableColumn<Descuento,Timestamp> c_fechaFin;


    private final StageManager stageManager;

    private boolean esCreacion;

    @Autowired
    @Lazy
    public MantenimientoDescuentoController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    public void agregarDescuento(ActionEvent event) {
        System.out.println("Agregar Descuento");
        this.esCreacion = true;
        this.stageManager.mostrarModal(MainView.DATOS_DESCUENTO);
    }

    public void modificarDescuento(ActionEvent event){
        System.out.println("Modificar Descuento");
        this.esCreacion = false;
        this.stageManager.mostrarModal(MainView.DATOS_DESCUENTO);
    }

    public Descuento getDescuento(){
        Descuento descuento = tableViewDescuentos.getSelectionModel().getSelectedItem();
        return descuento;
    }

    public void eliminarDescuento(ActionEvent event){

        System.out.println("Eliminar Descuento");
        Descuento descuento = tableViewDescuentos.getSelectionModel().getSelectedItem();
        repositoryDescuento.eliminarDescuento(descuento.getIdDescuento());
        this.actualizarTabla();
    }

    public void actualizarTabla(){
        this.listaDescuentos = this.repositoryDescuento.obtenerDescuentos();
        this.limpiarTabla();
        this.llenarTabla(this.listaDescuentos);
    }


    public Boolean esCreacion(){
        return this.esCreacion;
    }

    private void limpiarTabla(){
        tableViewDescuentos.getItems().clear();
        c_indice.setCellValueFactory(new PropertyValueFactory<Descuento, Integer>("indiceTabla"));
        c_tipoDescuento.setCellValueFactory(new PropertyValueFactory<Descuento, String>("TipoDescuento"));
        c_productoGen.setCellValueFactory(new PropertyValueFactory<Descuento, String>("nombreProductoGenerador"));
        c_categoriaGen.setCellValueFactory(new PropertyValueFactory<Descuento, String>("categoriaGenerador"));
        c_cantidadGen.setCellValueFactory(new PropertyValueFactory<Descuento, Integer>("cantProdGen"));
        c_ProdDesc.setCellValueFactory(new PropertyValueFactory<Descuento, String>("nombreProductoDescuento"));
        c_categoriaDesc.setCellValueFactory(new PropertyValueFactory<Descuento, String>("categoriaDescuento"));
        c_cantidadDesc.setCellValueFactory(new PropertyValueFactory<Descuento, Integer>("cantProdDesc"));
        c_porcentajeDesc.setCellValueFactory(new PropertyValueFactory<Descuento, Double>("valorDescuento"));
        c_fechaInicio.setCellValueFactory(new PropertyValueFactory<Descuento, Timestamp>("fechaInicio"));
        c_fechaFin.setCellValueFactory(new PropertyValueFactory<Descuento, Timestamp>("fechaFin"));
        tableViewDescuentos.setEditable(true);
    }


    private void llenarTabla(List<Descuento> listaDesc){
        Integer indice = 1;
        for(Descuento descuento:listaDesc){
            descuento.setIndiceTabla(indice);
            indice++;
            this.tableViewDescuentos.getItems().add(descuento);
        }
    }


    @Override
    public void initialize() {
        this.listaDescuentos = repositoryDescuento.obtenerDescuentos();
        c_indice.setCellValueFactory(new PropertyValueFactory<Descuento, Integer>("indiceTabla"));
        c_tipoDescuento.setCellValueFactory(new PropertyValueFactory<Descuento, String>("TipoDescuento"));
        c_productoGen.setCellValueFactory(new PropertyValueFactory<Descuento, String>("nombreProductoGenerador"));
        c_categoriaGen.setCellValueFactory(new PropertyValueFactory<Descuento, String>("categoriaGenerador"));
        c_cantidadGen.setCellValueFactory(new PropertyValueFactory<Descuento, Integer>("cantProdGen"));
        c_ProdDesc.setCellValueFactory(new PropertyValueFactory<Descuento, String>("nombreProductoDescuento"));
        c_categoriaDesc.setCellValueFactory(new PropertyValueFactory<Descuento, String>("categoriaDescuento"));
        c_cantidadDesc.setCellValueFactory(new PropertyValueFactory<Descuento, Integer>("cantProdDesc"));
        c_porcentajeDesc.setCellValueFactory(new PropertyValueFactory<Descuento, Double>("valorDescuento"));
        c_fechaInicio.setCellValueFactory(new PropertyValueFactory<Descuento, Timestamp>("fechaInicio"));
        c_fechaFin.setCellValueFactory(new PropertyValueFactory<Descuento, Timestamp>("fechaFin"));
        tableViewDescuentos.setEditable(true);


        this.llenarTabla(this.listaDescuentos);
        System.out.println("Cantidad Descuentos -> "+listaDescuentos.size());
    }
}
