package com.dp1wms.controller.Descuentos;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryCargaMasiva;
import com.dp1wms.dao.RepositoryCondicion;
import com.dp1wms.model.Condicion;
import com.dp1wms.util.DateParser;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
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
    private AnchorPane mantDescuentoAnchorPane;
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
    private TableColumn<Condicion,String> c_porcentajeDesc;
    @FXML
    private TableColumn<Condicion,String> c_fechaInicio;
    @FXML
    private TableColumn<Condicion,String> c_fechaFin;
    @FXML
    private TableColumn<Condicion,String> c_costoFlete;
    @FXML
    private TableColumn<Condicion,String> c_habilitado;
    @FXML
    private TableColumn<Condicion,String> c_cantidadFlete;
    @FXML
    private Button btn_modificar;

    private MainController mainController;

    Long idEmpleadoAuditado = null;

    private final StageManager stageManager;

    private boolean esCreacion;
    @Autowired
    private RepositoryCargaMasiva repositoryCargaMasiva;

    @Autowired
    @Lazy
    public MantenimientoDescuentoController(StageManager stageManager,MainController mainController) {
        this.stageManager = stageManager;
        this.mainController = mainController;
    }

    public void cargaMasiva(ActionEvent event){
        this.repositoryCargaMasiva.storeProcedure_cargarCondicionesComerciales();
        this.actualizarTabla();
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
        condicion.setIdEmpleadoAuditado(this.idEmpleadoAuditado);
        return condicion;
    }

    public Long getIdEmpleadoAuditado(){
        return this.idEmpleadoAuditado;
    }

    public void eliminarDescuento(ActionEvent event){

        System.out.println("Eliminar Condicion");
        Condicion condicion = tableViewDescuentos.getSelectionModel().getSelectedItem();
        repositoryCondicion.eliminarDescuento(condicion.getIdCondicion(),this.idEmpleadoAuditado);
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
        c_tipoDescuento.setCellValueFactory(new PropertyValueFactory<Condicion, String>("tipoCondicion"));
        c_productoGen.setCellValueFactory(new PropertyValueFactory<Condicion, String>("nombreProductoGenerador"));
        c_categoriaGen.setCellValueFactory(new PropertyValueFactory<Condicion, String>("categoriaGenerador"));
        c_cantidadGen.setCellValueFactory(new PropertyValueFactory<Condicion, Integer>("cantProdGen"));
        c_ProdDesc.setCellValueFactory(new PropertyValueFactory<Condicion, String>("nombreProductoDescuento"));
        c_categoriaDesc.setCellValueFactory(new PropertyValueFactory<Condicion, String>("categoriaDescuento"));
        c_cantidadDesc.setCellValueFactory(new PropertyValueFactory<Condicion, Integer>("cantProdDesc"));
        //c_costoFlete.setCellValueFactory(new PropertyValueFactory<Condicion, Float>("factorFlete"));
        c_costoFlete.setCellValueFactory(value->{
            if(value.getValue().getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FP) ||value.getValue().getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FD)){
                return new SimpleStringProperty("" + value.getValue().getValorDescuento());
            }else{

                return new SimpleStringProperty("");
            }

        });
        c_cantidadFlete.setCellValueFactory(value->{
            if(value.getValue().getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FP) ||value.getValue().getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FD)){
                return new SimpleStringProperty("" + value.getValue().getFactorFlete());
            }else{
                return new SimpleStringProperty("");
            }

        });

        //c_porcentajeDesc.setCellValueFactory(new PropertyValueFactory<Condicion, Double>("valorDescuento"));
        //c_fechaInicio.setCellValueFactory(new PropertyValueFactory<Condicion, Timestamp>("fechaInicio"));

        this.c_fechaInicio.setCellValueFactory(value->{
            String fechaInicio = DateParser.timestampToString(value.getValue().getFechaInicio());
            return new SimpleStringProperty(fechaInicio);
        });
        this.c_fechaFin.setCellValueFactory(value->{
            String fechaFin= DateParser.timestampToString(value.getValue().getFechaFin());
            return new SimpleStringProperty(fechaFin);
        });
        //c_fechaFin.setCellValueFactory(new PropertyValueFactory<Condicion, Timestamp>("fechaFin"));
        this.c_porcentajeDesc.setCellValueFactory(value->{
            if(value.getValue().getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FP) ||value.getValue().getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FD)){
                return new SimpleStringProperty("");
            }else{
                return new SimpleStringProperty("" + (value.getValue().getValorDescuento() *100));
            }

        });

        this.c_habilitado.setCellValueFactory(value->{
            String habilitado="";
            if(value.getValue().getActivo()){
                habilitado="Si";
            }else{
                habilitado="No";
            }
            return new SimpleStringProperty(habilitado);
        });
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
        /*c_indice.setCellValueFactory(new PropertyValueFactory<Condicion, Integer>("indiceTabla"));
        c_tipoDescuento.setCellValueFactory(new PropertyValueFactory<Condicion, String>("tipoCondicion"));
        c_productoGen.setCellValueFactory(new PropertyValueFactory<Condicion, String>("nombreProductoGenerador"));
        c_categoriaGen.setCellValueFactory(new PropertyValueFactory<Condicion, String>("categoriaGenerador"));
        c_cantidadGen.setCellValueFactory(new PropertyValueFactory<Condicion, Integer>("cantProdGen"));
        c_ProdDesc.setCellValueFactory(new PropertyValueFactory<Condicion, String>("nombreProductoDescuento"));
        c_categoriaDesc.setCellValueFactory(new PropertyValueFactory<Condicion, String>("categoriaDescuento"));
        c_cantidadDesc.setCellValueFactory(new PropertyValueFactory<Condicion, Integer>("cantProdDesc"));
        //c_porcentajeDesc.setCellValueFactory(new PropertyValueFactory<Condicion, Double>("valorDescuento"));
        c_fechaInicio.setCellValueFactory(new PropertyValueFactory<Condicion, Timestamp>("fechaInicio"));
        c_fechaFin.setCellValueFactory(new PropertyValueFactory<Condicion, Timestamp>("fechaFin"));
        this.c_porcentajeDesc.setCellValueFactory(value->{
            return new SimpleStringProperty("" + (value.getValue().getValorDescuento() *100));
        });

        tableViewDescuentos.setEditable(true);*/

        this.btn_modificar.setDisable(true);

        this.limpiarTabla();
        this.llenarTabla(this.listaCondicions);
        System.out.println("Cantidad Descuentos -> "+ listaCondicions.size());

        this.idEmpleadoAuditado = this.mainController.getEmpleado().getIdempleado();
    }
}
