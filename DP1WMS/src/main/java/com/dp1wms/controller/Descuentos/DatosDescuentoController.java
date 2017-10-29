package com.dp1wms.controller.Descuentos;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryCondicion;
import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.CategoriaProducto;
import com.dp1wms.model.Condicion;
import com.dp1wms.model.Producto;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.List;

@Component
public class DatosDescuentoController implements FxmlController{

    @FXML
    private AnchorPane datosDescuentoAnchorPane;
    @FXML
    private Label l_nombreFormulario;
    @FXML
    private ComboBox<String> cb_tipoDescuento;
    @FXML
    private TextField txb_productoGenerador;
    @FXML
    private TextField txb_productoDescuento;
    @FXML
    private ComboBox<CategoriaProducto> cb_categoriaGenerador;
    @FXML
    private ComboBox<CategoriaProducto> cb_categoriaDescuento;
    @FXML
    private TextField txb_cantidadGenerador;
    @FXML
    private TextField txb_cantidadDescuento;
    @FXML
    private TextField txb_valorDescuento;
    @FXML
    private DatePicker dp_fechaInicio;
    @FXML
    private DatePicker dp_fechaFin;
    @FXML
    private TextField txb_descripcion;
    @FXML
    private RadioButton rb_catDesc;
    @FXML
    private RadioButton rb_prodDesc;
    @FXML
    private RadioButton rb_catGen;
    @FXML
    private RadioButton rb_prodGen;

    @Autowired
    private RepositoryCondicion repositoryCondicion;

    @Autowired
    private RepositoryMantMov repositoryMantMov;

    private MantenimientoDescuentoController mantenimientoDescuentoController;

    private final StageManager stageManager;

    private String tipoDescuentoCantidad = "Cantidad";
    private String tipoBonificacion = "Bonificacion Por Especie";
    private String tipoPorcentaje = "Porcentaje";

    private List<String> listaTiposDescuento;

    private List<CategoriaProducto> listaCategorias;

    private int idproductoGenerador;

    private int idproductoDescuento;

    private Boolean productoDescuento;

    private Boolean esCreacion;

    private int iddescuento;

    Long idEmpleadoAuditado = null;

    @Autowired
    @Lazy
    public DatosDescuentoController(StageManager stageManager,MantenimientoDescuentoController mantenimientoDescuentoController) {
        this.stageManager = stageManager;
        this.mantenimientoDescuentoController = mantenimientoDescuentoController;
    }


    private void configurarComboBox(ComboBox<CategoriaProducto> combobox){

        Callback<ListView<CategoriaProducto>, ListCell<CategoriaProducto>> factory = lv -> new ListCell<CategoriaProducto>() {

            @Override
            protected void updateItem(CategoriaProducto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getDescripcion());
            }

        };

        combobox.setCellFactory(factory);
        combobox.setButtonCell(factory.call(null));
    }

    private Timestamp obtenerFecha(String fecha) throws ParseException {
        if(fecha != null)
            return convertirFecha(fecha);
        else
            return null;

    }

    private Timestamp convertirFecha(String fecha) throws ParseException  {

        Date utiltime = null;
        utiltime = ISO8601Utils.parse(fecha, new ParsePosition(0));
        return new Timestamp(utiltime.getTime());

    }

    public void guardarFormulario(ActionEvent event){

        Condicion condicion = new Condicion();
        condicion.setTipoCondicion(this.cb_tipoDescuento.getValue().toString());
        condicion.setIdProductoGenerador(this.idproductoGenerador);
        condicion.setIdCategoriaProdGen(this.cb_categoriaGenerador.getValue().getIdCategoria());
        int cantidadProdGen;
        try{
            cantidadProdGen = Integer.parseInt(this.txb_cantidadGenerador.getText());
        }catch(Exception e){
            System.out.println("Error al ingresar la cantidad descrita");
            return;
        }
        condicion.setCantProdGen(cantidadProdGen);
        condicion.setIdProductoDescuento(this.idproductoDescuento);
        condicion.setIdCategoriaProdDesc(this.cb_categoriaDescuento.getValue().getIdCategoria());
        int cantidadProdDesc;
        try{
            cantidadProdDesc = Integer.parseInt(this.txb_cantidadDescuento.getText());
        }catch(Exception e){
            System.out.println("Error al ingresar la cantidad descrita");
            return;
        }
        condicion.setCantProdDesc(cantidadProdDesc);
        Double valorDescuento;
        try{
            valorDescuento = Double.parseDouble(this.txb_valorDescuento.getText());
        }catch(Exception e){
            System.out.println("Error al ingresar la cantidad descrita");
            return;
        }
        condicion.setValorDescuento(valorDescuento);

        Timestamp fechaInicio = null;
        try {
            fechaInicio = this.obtenerFecha(this.dp_fechaInicio.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error al ingresar la fecha");
            return;
        }
        Timestamp fechaFin = null;
        try {
            fechaFin = this.obtenerFecha(this.dp_fechaFin.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error al ingresar la fecha");
            return;
        }

        condicion.setFechaInicio(fechaInicio);
        condicion.setFechaFin(fechaFin);
        condicion.setDescripcion(this.txb_descripcion.getText());

        if(this.rb_prodGen.isSelected()){
            condicion.setIdCategoriaProdGen(-1);
        }else{
            condicion.setIdProductoGenerador(-1);
        }

        if(this.rb_prodDesc.isSelected()){
            condicion.setIdCategoriaProdDesc(-1);
        }else{
            condicion.setIdProductoDescuento(-1);
        }

        condicion.setIdEmpleadoAuditado(this.idEmpleadoAuditado);
        if(this.esCreacion){
            repositoryCondicion.registrarDescuento(condicion);
        }else{
            //update
            condicion.setIdCondicion(this.iddescuento);
            repositoryCondicion.actualizarDescuento(condicion);
        }

        this.mantenimientoDescuentoController.actualizarTabla();
        this.datosDescuentoAnchorPane.getScene().getWindow().hide();
    }

    public void cancelarFormulario(ActionEvent event){
        this.datosDescuentoAnchorPane.getScene().getWindow().hide();
    }

    public void buscarProductoGen(ActionEvent event){
        productoDescuento = false;
        this.stageManager.mostrarModal(MainView.BUSQUEDA_PRODUCTO_DESC);
    }

    public void buscarProductoDesc(ActionEvent event){
        productoDescuento = true;
        this.stageManager.mostrarModal(MainView.BUSQUEDA_PRODUCTO_DESC);
    }

    public Boolean busquedaParaGenerador(){
        return productoDescuento == false;
    }

    public void actualizarProducto(Producto producto,Boolean productoGenerador){
        if(productoGenerador == true){
            System.out.println("Actualizar producto generador");
            this.txb_productoGenerador.setText(producto.getNombreProducto());
            this.idproductoGenerador = producto.getIdProducto();
        }else{
            System.out.println("Actualizar producto descuento");
            this.txb_productoDescuento.setText(producto.getNombreProducto());
            this.idproductoDescuento = producto.getIdProducto();
        }
    }

    private void llenarFormulario(Condicion condicion){

        System.out.println("Se va a llenar el formulario...");
        this.cb_tipoDescuento.setValue(condicion.getTipoCondicion());
        this.txb_productoGenerador.setText(condicion.getNombreProductoGenerador());
        this.idproductoGenerador = condicion.getIdProductoGenerador();
        this.txb_cantidadGenerador.setText(""+ condicion.getCantProdGen());
        this.txb_productoDescuento.setText(condicion.getNombreProductoDescuento());
        this.idproductoDescuento = condicion.getIdProductoDescuento();
        this.txb_cantidadDescuento.setText(""+ condicion.getCantProdDesc());
        //this.dp_fechaInicio.setValue(condicion.getFechaInicio());
        //this.dp_fechaFin
        this.txb_descripcion.setText(condicion.getDescripcion());
        this.txb_valorDescuento.setText(""+ condicion.getValorDescuento());
        this.iddescuento = condicion.getIdCondicion();
    }

    public void radioButtonProdGenAccion(ActionEvent event){
            this.rb_prodGen.setSelected(true);
            this.rb_catGen.setSelected(false);
    }
    public void radioButtonCatGenAccion(ActionEvent event){
        this.rb_prodGen.setSelected(false);
        this.rb_catGen.setSelected(true);
    }

    public void radioButtonProdDescAccion(ActionEvent event){
        this.rb_prodDesc.setSelected(true);
        this.rb_catDesc.setSelected(false);
    }
    public void radioButtonCatDescAccion(ActionEvent event){
        this.rb_prodDesc.setSelected(false);
        this.rb_catDesc.setSelected(true);
    }

    @Override
    public void initialize() {

        this.listaCategorias = repositoryMantMov.obtenerCategoriasProducto();

        this.configurarComboBox(this.cb_categoriaDescuento);
        this.configurarComboBox(this.cb_categoriaGenerador);

        cb_tipoDescuento.getItems().add(Condicion.DESC_C);
        cb_tipoDescuento.getItems().add(Condicion.DESC_B);
        cb_tipoDescuento.getItems().add(Condicion.DESC_P);



        this.txb_productoGenerador.setDisable(true);

        this.txb_productoDescuento.setDisable(true);


        this.rb_prodDesc.setSelected(true);
        this.rb_catDesc.setSelected(false);
        this.rb_prodGen.setSelected(true);
        this.rb_catGen.setSelected(false);


        this.esCreacion = this.mantenimientoDescuentoController.esCreacion();

        if(this.esCreacion){
            l_nombreFormulario.setText("Crear Condicion");
            this.idEmpleadoAuditado = this.mantenimientoDescuentoController.getIdEmpleadoAuditado();
            for(CategoriaProducto categoria : listaCategorias){
                this.cb_categoriaDescuento.getItems().add(categoria);
                this.cb_categoriaGenerador.getItems().add(categoria);
            }

        }else{
            l_nombreFormulario.setText("Modificar Condicion");

            System.out.println("controller == null ? " +this.mantenimientoDescuentoController == null );
            Condicion condicion = this.mantenimientoDescuentoController.getDescuento();
            this.idEmpleadoAuditado = condicion.getIdEmpleadoAuditado();
            System.out.println("id condicion -> " + condicion.getIdCondicion());
            for(CategoriaProducto categoria : listaCategorias){
                this.cb_categoriaDescuento.getItems().add(categoria);
                this.cb_categoriaGenerador.getItems().add(categoria);
                if(categoria.getIdCategoria() == condicion.getIdCategoriaProdGen()){
                    this.cb_categoriaGenerador.getSelectionModel().select(categoria);
                    this.rb_catGen.setSelected(true);
                    this.rb_prodGen.setSelected(false);
                }
                if(categoria.getIdCategoria() == condicion.getIdCategoriaProdDesc()){
                    this.cb_categoriaDescuento.getSelectionModel().select(categoria);
                    this.rb_catDesc.setSelected(true);
                    this.rb_prodDesc.setSelected(false);
                }
            }
            this.llenarFormulario(condicion);
        }
    }

}
