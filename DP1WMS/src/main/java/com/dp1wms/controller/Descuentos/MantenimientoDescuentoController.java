package com.dp1wms.controller.Descuentos;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryCondicion;
import com.dp1wms.model.Condicion;
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
    private RepositoryCondicion repositoryCondicion;

    @FXML
    private TableView<Condicion> tableViewDescuentos = new TableView<Condicion>();

    List<Condicion> listaCondicions;

    @FXML
    private TableColumn<Condicion,Integer> c_indice;
    @FXML
    private TableColumn<Condicion,String> c_tipoDescuento;
    @FXML
    private TableColumn<Condicion,String> c_productoGen;
    @FXML
    private TableColumn<Condicion,String> c_categoriaGen;
    @FXML
    private TableColumn<Condicion,Integer> c_cantidadGen;
    @FXML
    private TableColumn<Condicion,String> c_ProdDesc;
    @FXML
    private TableColumn<Condicion,String> c_categoriaDesc;
    @FXML
    private TableColumn<Condicion,Integer> c_cantidadDesc;
    @FXML
    private TableColumn<Condicion,Double> c_porcentajeDesc;
    @FXML
    private TableColumn<Condicion,Timestamp> c_fechaInicio;
    @FXML
    private TableColumn<Condicion,Timestamp> c_fechaFin;


    private final StageManager stageManager;

    private boolean esCreacion;

    @Autowired
    @Lazy
    public MantenimientoDescuentoController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    public void agregarDescuento(ActionEvent event) {
        System.out.println("Agregar Condicion");
        this.esCreacion = true;
        this.stageManager.mostrarModal(MainView.DATOS_DESCUENTO);
    }

    public void modificarDescuento(ActionEvent event){
        System.out.println("Modificar Condicion");
        this.esCreacion = false;
        this.stageManager.mostrarModal(MainView.DATOS_DESCUENTO);
    }

    public Condicion getDescuento(){
        Condicion condicion = tableViewDescuentos.getSelectionModel().getSelectedItem();
        return condicion;
    }

    public void eliminarDescuento(ActionEvent event){

        System.out.println("Eliminar Condicion");
        Condicion condicion = tableViewDescuentos.getSelectionModel().getSelectedItem();
        repositoryCondicion.eliminarDescuento(condicion.getIdDescuento());
        this.actualizarTabla();
    }

    public void actualizarTabla(){
        this.listaCondicions = this.repositoryCondicion.obtenerDescuentos();
        this.limpiarTabla();
        this.llenarTabla(this.listaCondicions);
    }


    public Boolean esCreacion(){
        return this.esCreacion;
    }

    private void limpiarTabla(){
        tableViewDescuentos.getItems().clear();
        c_indice.setCellValueFactory(new PropertyValueFactory<Condicion, Integer>("indiceTabla"));
        c_tipoDescuento.setCellValueFactory(new PropertyValueFactory<Condicion, String>("TipoDescuento"));
        c_productoGen.setCellValueFactory(new PropertyValueFactory<Condicion, String>("nombreProductoGenerador"));
        c_categoriaGen.setCellValueFactory(new PropertyValueFactory<Condicion, String>("categoriaGenerador"));
        c_cantidadGen.setCellValueFactory(new PropertyValueFactory<Condicion, Integer>("cantProdGen"));
        c_ProdDesc.setCellValueFactory(new PropertyValueFactory<Condicion, String>("nombreProductoDescuento"));
        c_categoriaDesc.setCellValueFactory(new PropertyValueFactory<Condicion, String>("categoriaDescuento"));
        c_cantidadDesc.setCellValueFactory(new PropertyValueFactory<Condicion, Integer>("cantProdDesc"));
        c_porcentajeDesc.setCellValueFactory(new PropertyValueFactory<Condicion, Double>("valorDescuento"));
        c_fechaInicio.setCellValueFactory(new PropertyValueFactory<Condicion, Timestamp>("fechaInicio"));
        c_fechaFin.setCellValueFactory(new PropertyValueFactory<Condicion, Timestamp>("fechaFin"));
        tableViewDescuentos.setEditable(true);
    }


    private void llenarTabla(List<Condicion> listaDesc){
        Integer indice = 1;
        for(Condicion condicion :listaDesc){
            condicion.setIndiceTabla(indice);
            indice++;
            this.tableViewDescuentos.getItems().add(condicion);
        }
    }


    @Override
    public void initialize() {
        this.listaCondicions = repositoryCondicion.obtenerDescuentos();
        c_indice.setCellValueFactory(new PropertyValueFactory<Condicion, Integer>("indiceTabla"));
        c_tipoDescuento.setCellValueFactory(new PropertyValueFactory<Condicion, String>("TipoDescuento"));
        c_productoGen.setCellValueFactory(new PropertyValueFactory<Condicion, String>("nombreProductoGenerador"));
        c_categoriaGen.setCellValueFactory(new PropertyValueFactory<Condicion, String>("categoriaGenerador"));
        c_cantidadGen.setCellValueFactory(new PropertyValueFactory<Condicion, Integer>("cantProdGen"));
        c_ProdDesc.setCellValueFactory(new PropertyValueFactory<Condicion, String>("nombreProductoDescuento"));
        c_categoriaDesc.setCellValueFactory(new PropertyValueFactory<Condicion, String>("categoriaDescuento"));
        c_cantidadDesc.setCellValueFactory(new PropertyValueFactory<Condicion, Integer>("cantProdDesc"));
        c_porcentajeDesc.setCellValueFactory(new PropertyValueFactory<Condicion, Double>("valorDescuento"));
        c_fechaInicio.setCellValueFactory(new PropertyValueFactory<Condicion, Timestamp>("fechaInicio"));
        c_fechaFin.setCellValueFactory(new PropertyValueFactory<Condicion, Timestamp>("fechaFin"));
        tableViewDescuentos.setEditable(true);


        this.llenarTabla(this.listaCondicions);
        System.out.println("Cantidad Descuentos -> "+ listaCondicions.size());
    }
}
