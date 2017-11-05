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
import javafx.stage.Stage;
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
public class DatosDescuentoController implements FxmlController {


    @FXML
    private TitledPane titledPaneGen;
    @FXML
    private TitledPane titledPaneDesc;
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
    @FXML
    private Label label_ProductoGen;
    @FXML
    private Label labelCategoriaGen;
    @FXML
    private Label labelCantidadGen;
    @FXML
    private Button btn_buscarGen;
    @FXML
    private Label labelCategoriaDesc;
    @FXML
    private Label labelProductoDesc;
    @FXML
    private Button btn_buscarDesc;


    @FXML
    private Label labelValorDesc;
    @FXML
    private Label labelCantidadDesc;


    @FXML
    private Label labelCantidad1;
    @FXML
    private Label labelCantidad2;
    @FXML
    private Label labelCantidad3;
    @FXML
    private Label labelCantidad4;
    @FXML
    private Label labelCantidad5;
    @FXML
    private Label labelCantidad6;
    @FXML
    private Label labelCantidad7;
    @FXML
    private Label labelCantidad8;
    @FXML
    private Label labelCantidad9;
    @FXML
    private Label labelPorcentaje;

    //private Condicion condicion;

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
    public DatosDescuentoController(StageManager stageManager, MantenimientoDescuentoController mantenimientoDescuentoController) {
        this.stageManager = stageManager;
        this.mantenimientoDescuentoController = mantenimientoDescuentoController;
    }


    private void configurarComboBox(ComboBox<CategoriaProducto> combobox) {

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
        if (fecha != null)
            return convertirFecha(fecha);
        else
            return null;

    }

    private Timestamp convertirFecha(String fecha) throws ParseException {

        Date utiltime = null;
        utiltime = ISO8601Utils.parse(fecha, new ParsePosition(0));
        return new Timestamp(utiltime.getTime());

    }

    private void validacionesFormulario(){

    }


    public void guardarFormulario(ActionEvent event) {

        Condicion condicion = new Condicion();


        if(this.cb_tipoDescuento.getValue() != null){
            condicion.setTipoCondicion(this.cb_tipoDescuento.getValue().toString());
        }else{
            this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                    "Debe ingresar un tipo de condición comercial");
            return;
        }

        if(condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_P) || condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_B)  || condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_C) ){
            if(rb_prodGen.isSelected()){
                if(txb_productoGenerador.getText().equalsIgnoreCase("")){
                    this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                            "Debe seleccionar un producto");
                    return;
                }


            }else if(rb_catGen.isSelected()){
                if(this.cb_categoriaGenerador.getValue() == null){
                    this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                            "Debe seleccionar una categoria");
                    return;
                }
            }

            if(txb_valorDescuento.getText().equalsIgnoreCase("")){
                this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                        "Debe ingresar un procentaje de descuento sobre el precio.Si desea que sea gratis ingresar 100%");
                return;
            }
        }

        if( condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_B)  || condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_C)){
            if(txb_cantidadGenerador.getText().equalsIgnoreCase("")){
                this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                        "Debe ingresar una cantidad para la condicion ");
                return;
            }
        }

        if( condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_B)){
            if(rb_prodDesc.isSelected()){
                if(txb_productoDescuento.getText().equalsIgnoreCase("")){
                    this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                            "Debe seleccionar un producto sobre el que se aplicará la condición comercial");
                    return;
                }


            }else if(rb_catDesc.isSelected()){
                if(this.cb_categoriaDescuento.getValue() == null){
                    this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                            "Debe seleccionar una categoria sobre la que se aplicará la condición comercial");
                    return;
                }
            }


            if(txb_cantidadDescuento.getText().equalsIgnoreCase("")){
                this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                        "Debe ingresar una cantidad sobre la que aplicará la condicion ");
                return;
            }
        }

        if( condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FP)){
            if(txb_cantidadGenerador.getText().equalsIgnoreCase("")){
                this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                        "Debe ingresar el peso sobre el que se aplicará el costo del flete");
                return;
            }
        }

        if(condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FD)){
            if(txb_cantidadGenerador.getText().equalsIgnoreCase("")){
                this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                        "Debe ingresar la distancia sobre la que se aplicará el costo del flete");
                return;
            }
        }

        if( condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FP)  || condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FD)){
            if(txb_valorDescuento.getText().equalsIgnoreCase("")){
                this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                        "Debe ingresar el costo del flete");
                return;
            }
        }

        if(this.dp_fechaInicio.getValue() == null){
            this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                    "Debe ingresar una fecha de inicio para la condición comercial");
            return;
        }

        if(this.dp_fechaFin.getValue() == null){
            this.stageManager.mostrarErrorDialog("Error condición comercial", null,
                    "Debe ingresar una fecha de final para la condición comercial");
            return;
        }

        if (this.txb_productoGenerador.getText().equalsIgnoreCase("")) {
            condicion.setIdProductoGenerador(-1);
        } else {
            condicion.setIdProductoGenerador(this.idproductoGenerador);
        }


        if (this.cb_categoriaGenerador.getValue() == null) {
            condicion.setIdCategoriaProdGen(-1);
        } else {
            condicion.setIdCategoriaProdGen(this.cb_categoriaGenerador.getValue().getIdCategoria());
        }

        int cantidadProdGen = 1;
        if (!this.txb_cantidadGenerador.getText().equalsIgnoreCase("")) {
            try {
                cantidadProdGen = Integer.parseInt(this.txb_cantidadGenerador.getText());
            } catch (Exception e) {
                System.out.println("Error al ingresar la cantidad generador descrita");
                return;
            }
        }

        condicion.setCantProdGen(cantidadProdGen);


        if (this.txb_productoDescuento.getText().equalsIgnoreCase("")) {
            condicion.setIdProductoDescuento(-1);
        } else {
            condicion.setIdProductoDescuento(this.idproductoDescuento);
        }

        if (this.cb_categoriaDescuento.getValue() == null) {
            condicion.setIdCategoriaProdDesc(-1);
        } else {
            condicion.setIdCategoriaProdDesc(this.cb_categoriaDescuento.getValue().getIdCategoria());
        }


        int cantidadProdDesc = 1;

        if (!this.txb_cantidadDescuento.getText().equalsIgnoreCase("")) {
            try {
                cantidadProdDesc = Integer.parseInt(this.txb_cantidadDescuento.getText());
            } catch (Exception e) {
                System.out.println("Error al ingresar la cantidad descuento descrita");
                return;
            }
        }

        condicion.setCantProdDesc(cantidadProdDesc);
        float valorDescuento;
        try {
            valorDescuento = Float.parseFloat(this.txb_valorDescuento.getText());
        } catch (Exception e) {
            System.out.println("Error al ingresar valor descuento");
            return;
        }
        condicion.setValorDescuento(valorDescuento / 100);

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


        if(txb_descripcion.getText().equalsIgnoreCase("")){
            condicion.setDescripcion(null);
        }else{
            condicion.setDescripcion(this.txb_descripcion.getText());
        }



        if (this.rb_prodGen.isSelected()) {
            condicion.setIdCategoriaProdGen(-1);
        } else {
            condicion.setIdProductoGenerador(-1);
        }

        if (this.rb_prodDesc.isSelected()) {
            condicion.setIdCategoriaProdDesc(-1);
        } else {
            condicion.setIdProductoDescuento(-1);
        }

        if (condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_P)) {
            condicion.setIdProductoDescuento(condicion.getIdProductoGenerador());
            condicion.setIdCategoriaProdDesc(condicion.getIdCategoriaProdGen());
            //condicion.setIdProductoGenerador(condicion.getIdProductoDescuento());
            //condicion.setIdCategoriaProdGen(condicion.getIdCategoriaProdDesc());
            condicion.setCantProdGen(1);
            condicion.setCantProdDesc(1);
        }
        if (condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_C)) {
            //condicion.setIdProductoGenerador(condicion.getIdProductoDescuento());
            //condicion.setIdCategoriaProdGen(condicion.getIdCategoriaProdDesc());
            condicion.setIdProductoDescuento(condicion.getIdProductoGenerador());
            condicion.setIdCategoriaProdDesc(condicion.getIdCategoriaProdGen());
        }
        if(condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FD) || condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_FP)){
            condicion.setFactorFlete(condicion.getValorDescuento()*100);
            condicion.setValorDescuento(0);
            condicion.setCantProdDesc(condicion.getCantProdGen());
        }

        condicion.setIdEmpleadoAuditado(this.idEmpleadoAuditado);
        if (this.esCreacion) {
            repositoryCondicion.registrarDescuento(condicion);
        } else {
            //update
            condicion.setIdCondicion(this.iddescuento);
            repositoryCondicion.actualizarDescuento(condicion);
        }

        this.mantenimientoDescuentoController.actualizarTabla();
        this.datosDescuentoAnchorPane.getScene().getWindow().hide();
    }

    public void cancelarFormulario(ActionEvent event) {
        this.datosDescuentoAnchorPane.getScene().getWindow().hide();
    }

    public void buscarProductoGen(ActionEvent event) {
        productoDescuento = false;
        this.stageManager.mostrarModal(MainView.BUSQUEDA_PRODUCTO_DESC);
    }

    public void buscarProductoDesc(ActionEvent event) {
        productoDescuento = true;
        this.stageManager.mostrarModal(MainView.BUSQUEDA_PRODUCTO_DESC);

        if (this.cb_tipoDescuento.getValue() != null) {
            String valorComboCondicion = this.cb_tipoDescuento.getValue().toString();
            if (!valorComboCondicion.equalsIgnoreCase("")) {
                if (Condicion.DESC_C.equalsIgnoreCase(valorComboCondicion)) {
                    this.txb_productoGenerador.setText(this.txb_productoDescuento.getText());
                }
            }
        }

    }

    public Boolean busquedaParaGenerador() {
        return productoDescuento == false;
    }

    public void actualizarProducto(Producto producto, Boolean productoGenerador) {
        if (productoGenerador == true) {
            System.out.println("Actualizar producto generador");
            this.txb_productoGenerador.setText(producto.getNombreProducto());
            this.idproductoGenerador = producto.getIdProducto();
        } else {
            System.out.println("Actualizar producto descuento");
            this.txb_productoDescuento.setText(producto.getNombreProducto());
            this.idproductoDescuento = producto.getIdProducto();
        }
    }

    private void llenarFormulario(Condicion condicion) {

        System.out.println("Se va a llenar el formulario...");
        this.reestructurarFormulario(condicion.getTipoCondicion());

        this.cb_tipoDescuento.setValue(condicion.getTipoCondicion());
        this.txb_productoGenerador.setText(condicion.getNombreProductoGenerador());
        this.idproductoGenerador = condicion.getIdProductoGenerador();
        this.txb_cantidadGenerador.setText("" + condicion.getCantProdGen());
        this.txb_productoDescuento.setText(condicion.getNombreProductoDescuento());
        this.idproductoDescuento = condicion.getIdProductoDescuento();
        this.txb_cantidadDescuento.setText("" + condicion.getCantProdDesc());
        //this.dp_fechaInicio.setValue(condicion.getFechaInicio());
        //this.dp_fechaFin
        this.txb_descripcion.setText(condicion.getDescripcion());
        this.txb_valorDescuento.setText("" + (condicion.getValorDescuento() * 100));
        this.iddescuento = condicion.getIdCondicion();
    }

    // false ocultar , true mostar
    public void ocultarDatosGenerador(Boolean ocultar) {
        this.titledPaneGen.setVisible(ocultar);
        this.rb_prodGen.setVisible(ocultar);
        this.rb_catGen.setVisible(ocultar);
        this.txb_productoGenerador.setVisible(ocultar);
        this.cb_categoriaGenerador.setVisible(ocultar);
        this.txb_cantidadGenerador.setVisible(ocultar);
        this.label_ProductoGen.setVisible(ocultar);
        this.labelCantidadGen.setVisible(ocultar);
        this.labelCategoriaGen.setVisible(ocultar);
        this.btn_buscarGen.setVisible(ocultar);

        if (!ocultar) { // si se debe ocultar, limpiar los datos para futuras consultas a otros tipos
            this.txb_productoGenerador.setText("");
            this.cb_categoriaGenerador.setValue(null);
            this.txb_cantidadGenerador.setText("");
        }
    }


    private void mostrarTodosElementos(Boolean mostrar) {
        labelCantidad4.setVisible(mostrar);
        txb_valorDescuento.setVisible(mostrar);
        labelPorcentaje.setVisible(mostrar);
        rb_prodGen.setVisible(mostrar);
        label_ProductoGen.setVisible(mostrar);
        txb_productoGenerador.setVisible(mostrar);
        btn_buscarGen.setVisible(mostrar);
        this.labelCantidad1.setVisible(mostrar);
        this.txb_cantidadGenerador.setVisible(mostrar);
        this.labelCantidad2.setVisible(mostrar);
        this.rb_catGen.setVisible(mostrar);
        this.labelCategoriaGen.setVisible(mostrar);
        this.cb_categoriaGenerador.setVisible(mostrar);
        this.labelCantidad3.setVisible(mostrar);
        this.txb_cantidadDescuento.setVisible(mostrar);
        this.labelCantidad5.setVisible(mostrar);
        this.rb_prodDesc.setVisible(mostrar);
        this.labelProductoDesc.setVisible(mostrar);
        this.txb_productoDescuento.setVisible(mostrar);
        this.btn_buscarDesc.setVisible(mostrar);
        this.rb_catDesc.setVisible(mostrar);
        this.labelCategoriaDesc.setVisible(mostrar);
        this.cb_categoriaDescuento.setVisible(mostrar);

        this.labelCantidad6.setVisible(mostrar);
        this.txb_cantidadGenerador.setVisible(mostrar);
        this.labelCantidad7.setVisible(mostrar);
        this.txb_valorDescuento.setVisible(mostrar);
        this.labelCantidad8.setVisible(mostrar);
        this.labelCantidad9.setVisible(mostrar);

        this.titledPaneDesc.setVisible(false);
        this.titledPaneGen.setVisible(false);
        this.labelValorDesc.setVisible(false);
        this.labelCantidadGen.setVisible(false);
        this.labelCantidadDesc.setVisible(false);
    }

    private void mostrarFletePorDistancia(){
        mostrarTodosElementos(true);
        this.labelCantidad6.relocate(27, 146);
        this.txb_cantidadGenerador.relocate(labelCantidad6.getLayoutX()+labelCantidad6.getWidth()+10,146);
        this.labelCantidad8.relocate(txb_cantidadGenerador.getLayoutX()+txb_cantidadGenerador.getWidth()+10,146);
        this.txb_valorDescuento.relocate(labelCantidad8.getLayoutX()+labelCantidad8.getWidth()+10,146);
        this.labelCantidad9.relocate(txb_valorDescuento.getLayoutX()+txb_valorDescuento.getWidth()+10,146);

        labelCantidad4.setVisible(false);
        labelPorcentaje.setVisible(false);
        rb_prodGen.setVisible(false);
        label_ProductoGen.setVisible(false);
        txb_productoGenerador.setVisible(false);
        btn_buscarGen.setVisible(false);
        this.labelCantidad1.setVisible(false);
        this.labelCantidad2.setVisible(false);
        this.rb_catGen.setVisible(false);
        this.labelCategoriaGen.setVisible(false);
        this.cb_categoriaGenerador.setVisible(false);
        this.labelCantidad3.setVisible(false);
        this.txb_cantidadDescuento.setVisible(false);
        this.labelCantidad5.setVisible(false);
        this.rb_prodDesc.setVisible(false);
        this.labelProductoDesc.setVisible(false);
        this.txb_productoDescuento.setVisible(false);
        this.btn_buscarDesc.setVisible(false);
        this.rb_catDesc.setVisible(false);
        this.labelCategoriaDesc.setVisible(false);
        this.cb_categoriaDescuento.setVisible(false);

        txb_productoGenerador.setText("");
        cb_categoriaGenerador.setValue(null);
        txb_productoDescuento.setText("");
        cb_categoriaDescuento.setValue(null);

        this.labelCantidad7.setVisible(false);

    }


    private void mostrarFletePorPeso(){
        mostrarTodosElementos(true);
        this.labelCantidad6.relocate(27, 146);
        this.txb_cantidadGenerador.relocate(labelCantidad6.getLayoutX()+labelCantidad6.getWidth()+10,146);
        this.labelCantidad7.relocate(txb_cantidadGenerador.getLayoutX()+txb_cantidadGenerador.getWidth()+10,146);
        this.txb_valorDescuento.relocate(labelCantidad7.getLayoutX()+labelCantidad7.getWidth()+10,146);
        this.labelCantidad9.relocate(txb_valorDescuento.getLayoutX()+txb_valorDescuento.getWidth()+10,146);

        labelCantidad4.setVisible(false);
        labelPorcentaje.setVisible(false);
        rb_prodGen.setVisible(false);
        label_ProductoGen.setVisible(false);
        txb_productoGenerador.setVisible(false);
        btn_buscarGen.setVisible(false);
        this.labelCantidad1.setVisible(false);
        this.labelCantidad2.setVisible(false);
        this.rb_catGen.setVisible(false);
        this.labelCategoriaGen.setVisible(false);
        this.cb_categoriaGenerador.setVisible(false);
        this.labelCantidad3.setVisible(false);
        this.txb_cantidadDescuento.setVisible(false);
        this.labelCantidad5.setVisible(false);
        this.rb_prodDesc.setVisible(false);
        this.labelProductoDesc.setVisible(false);
        this.txb_productoDescuento.setVisible(false);
        this.btn_buscarDesc.setVisible(false);
        this.rb_catDesc.setVisible(false);
        this.labelCategoriaDesc.setVisible(false);
        this.cb_categoriaDescuento.setVisible(false);


        txb_productoGenerador.setText("");
        cb_categoriaGenerador.setValue(null);
        txb_productoDescuento.setText("");
        cb_categoriaDescuento.setValue(null);

        this.labelCantidad8.setVisible(false);

    }


    private void mostrarDescuentoPorPorcentaje() {
        mostrarTodosElementos(true);

        this.labelCantidad4.relocate(27, 146);
        this.txb_valorDescuento.relocate(labelCantidad4.getLayoutX() + labelCantidad4.getWidth() + 10, 146);
        this.labelPorcentaje.relocate(txb_valorDescuento.getLayoutX() + txb_valorDescuento.getWidth() + 10, 146);
        this.labelCantidad3.relocate(labelPorcentaje.getLayoutX() + labelPorcentaje.getWidth() + 10, 146);
        this.labelCantidad2.relocate(labelCantidad3.getLayoutX() + labelCantidad3.getWidth() + 10, 146);

        this.rb_prodGen.relocate(labelCantidad2.getLayoutX() + labelCantidad2.getWidth() + 10, 146);
        this.label_ProductoGen.relocate(rb_prodGen.getLayoutX() + 20, 146);
        this.txb_productoGenerador.relocate(label_ProductoGen.getLayoutX() + label_ProductoGen.getWidth() + 10, 146);
        this.btn_buscarGen.relocate(txb_productoGenerador.getLayoutX() + txb_productoGenerador.getWidth() + 10, 146);

        this.rb_catGen.relocate(rb_prodGen.getLayoutX(), 180);
        this.labelCategoriaGen.relocate(rb_catGen.getLayoutX() + 20, 180);
        this.cb_categoriaGenerador.relocate(labelCategoriaGen.getLayoutX() + labelCantidadGen.getWidth() + 10, 180);


        this.labelCantidad1.setVisible(false);
        this.txb_cantidadGenerador.setVisible(false);
        this.txb_cantidadDescuento.setVisible(false);
        this.labelCantidad5.setVisible(false);
        this.rb_prodDesc.setVisible(false);
        this.labelProductoDesc.setVisible(false);
        this.txb_productoDescuento.setVisible(false);
        this.btn_buscarDesc.setVisible(false);
        this.rb_catDesc.setVisible(false);
        this.labelCategoriaDesc.setVisible(false);
        this.cb_categoriaDescuento.setVisible(false);
        this.titledPaneDesc.setVisible(false);
        this.titledPaneGen.setVisible(false);
        this.labelValorDesc.setVisible(false);
        this.labelCantidadGen.setVisible(false);
        this.labelCantidadDesc.setVisible(false);

        txb_cantidadGenerador.setText("");
        txb_cantidadDescuento.setText("");
        txb_productoDescuento.setText("");
        cb_categoriaDescuento.setValue(null);


        this.labelCantidad6.setVisible(false);
        this.labelCantidad7.setVisible(false);
        this.labelCantidad8.setVisible(false);
        this.labelCantidad9.setVisible(false);
    }


    private void mostrarBonificacionXespecie() {
        mostrarTodosElementos(true);

        this.labelCantidad1.relocate(27, 146);
        this.txb_cantidadGenerador.relocate(27 + labelCantidad1.getWidth() + 5, 146);
        this.labelCantidad2.relocate(txb_cantidadGenerador.getLayoutX() + txb_cantidadGenerador.getWidth() + 10, 146);
        this.rb_prodGen.relocate(labelCantidad2.getLayoutX() + labelCantidad2.getWidth() + 5, 146);
        this.label_ProductoGen.relocate(rb_prodGen.getLayoutX() + 20, 146);
        this.txb_productoGenerador.relocate(label_ProductoGen.getLayoutX() + label_ProductoGen.getWidth() + 10, 146);
        this.btn_buscarGen.relocate(txb_productoGenerador.getLayoutX() + txb_productoGenerador.getWidth() + 10, 146);


        this.rb_catGen.relocate(rb_prodGen.getLayoutX(), 180);
        this.labelCategoriaGen.relocate(rb_catGen.getLayoutX() + 20, 180);
        this.cb_categoriaGenerador.relocate(labelCategoriaGen.getLayoutX() + labelCantidadGen.getWidth() + 10, 180);

        this.labelCantidad3.relocate(27, 230);
        this.txb_cantidadDescuento.relocate(labelCantidad3.getLayoutX() + labelCantidad3.getWidth() + 10, 230);
        this.labelCantidad5.relocate(txb_cantidadDescuento.getLayoutX() + txb_cantidadDescuento.getWidth() + 10, 230);
        this.rb_prodDesc.relocate(labelCantidad5.getLayoutX() + labelCantidad5.getWidth() + 5, 230);
        this.labelProductoDesc.relocate(rb_prodDesc.getLayoutX() + 20, 230);
        this.txb_productoDescuento.relocate(labelProductoDesc.getLayoutX() + labelProductoDesc.getWidth() + 10, 230);
        this.btn_buscarDesc.relocate(txb_productoDescuento.getLayoutX() + txb_productoDescuento.getWidth() + 10, 230);


        this.rb_catDesc.relocate(rb_prodDesc.getLayoutX(), 260);
        this.labelCategoriaDesc.relocate(rb_catDesc.getLayoutX() + 20, 260);
        this.cb_categoriaDescuento.relocate(labelCategoriaDesc.getLayoutX() + labelCategoriaDesc.getWidth() + 10, 260);

        this.labelCantidad4.relocate(27, 290);
        this.txb_valorDescuento.relocate(labelCantidad4.getLayoutX() + labelCantidad4.getWidth() + 10, 290);
        this.labelPorcentaje.relocate(txb_valorDescuento.getLayoutX() + txb_valorDescuento.getWidth() + 10, 290);

        this.titledPaneDesc.setVisible(false);
        this.titledPaneGen.setVisible(false);
        this.labelValorDesc.setVisible(false);
        this.labelCantidadGen.setVisible(false);

        this.labelCantidad6.setVisible(false);
        this.labelCantidad7.setVisible(false);
        this.labelCantidad8.setVisible(false);
        this.labelCantidad9.setVisible(false);
    }


    private void mostrarDescuentoXcantidad() {
        mostrarTodosElementos(true);

        this.labelCantidad1.relocate(27, 146);
        this.txb_cantidadGenerador.relocate(27 + labelCantidad1.getWidth() + 5, 146);
        this.labelCantidad2.relocate(txb_cantidadGenerador.getLayoutX() + txb_cantidadGenerador.getWidth() + 10, 146);
        this.rb_prodGen.relocate(labelCantidad2.getLayoutX() + labelCantidad2.getWidth() + 5, 146);
        this.label_ProductoGen.relocate(rb_prodGen.getLayoutX() + 20, 146);
        this.txb_productoGenerador.relocate(label_ProductoGen.getLayoutX() + label_ProductoGen.getWidth() + 10, 146);
        this.btn_buscarGen.relocate(txb_productoGenerador.getLayoutX() + txb_productoGenerador.getWidth() + 10, 146);


        this.rb_catGen.relocate(rb_prodGen.getLayoutX(), 180);
        this.labelCategoriaGen.relocate(rb_catGen.getLayoutX() + 20, 180);
        this.cb_categoriaGenerador.relocate(labelCategoriaGen.getLayoutX() + labelCantidadGen.getWidth() + 10, 180);


        this.labelCantidad3.relocate(27, 210);
        this.txb_cantidadDescuento.relocate(labelCantidad3.getLayoutX() + labelCantidad3.getWidth() + 10, 210);
        this.labelCantidad4.relocate(txb_cantidadDescuento.getLayoutX() + txb_cantidadDescuento.getWidth() + 10, 210);
        this.txb_valorDescuento.relocate(labelCantidad4.getLayoutX() + labelCantidad4.getWidth() + 10, 210);
        this.labelPorcentaje.relocate(txb_valorDescuento.getLayoutX() + txb_valorDescuento.getWidth() + 10, 210);

        //ocultar resto
        this.btn_buscarDesc.setVisible(false);
        this.rb_catDesc.setVisible(false);
        this.rb_prodDesc.setVisible(false);
        this.labelProductoDesc.setVisible(false);
        this.labelCategoriaDesc.setVisible(false);
        this.txb_productoDescuento.setVisible(false);
        this.titledPaneDesc.setVisible(false);
        this.titledPaneGen.setVisible(false);
        this.labelCantidadGen.setVisible(false);
        this.cb_categoriaDescuento.setVisible(false);
        this.labelCantidadDesc.setVisible(false);
        this.labelValorDesc.setVisible(false);
        this.labelCantidad5.setVisible(false);

        this.labelCantidad6.setVisible(false);
        this.labelCantidad7.setVisible(false);
        this.labelCantidad8.setVisible(false);
        this.labelCantidad9.setVisible(false);

        txb_productoDescuento.setText("");
        cb_categoriaDescuento.setValue(null);
    }
    private void reestructurarFormulario(String valorCondicion) {
        this.rb_prodGen.setDisable(false);
        this.txb_productoGenerador.setDisable(false);
        this.btn_buscarGen.setDisable(false);
        this.rb_catGen.setDisable(false);

        switch(valorCondicion){
            case Condicion.DESC_P:
                this.mostrarDescuentoPorPorcentaje();
                break;
            case Condicion.DESC_C:
                this.mostrarDescuentoXcantidad();
                break;
            case Condicion.DESC_B:
                this.mostrarBonificacionXespecie();
                break;
            case Condicion.DESC_FP:
                this.mostrarFletePorPeso();
                break;
            case Condicion.DESC_FD:
                this.mostrarFletePorDistancia();
                break;
        }
    }

    private void reestructurarFormulario2(String valorCondicion) {
        this.rb_prodGen.setDisable(false);
        this.txb_productoGenerador.setDisable(false);
        this.btn_buscarGen.setDisable(false);
        this.rb_catGen.setDisable(false);
        if (Condicion.DESC_P.equalsIgnoreCase(valorCondicion)) {
            //this.ocultarDatosGenerador(false);
            this.mostrarDescuentoPorPorcentaje();
        } else {
            //this.ocultarDatosGenerador(true); //RECIENTE
            // si es descuento por cantidad , cargar datos producto o categoria descuento
            if (Condicion.DESC_C.equalsIgnoreCase(valorCondicion)) {
                this.mostrarDescuentoXcantidad();
                //RECIENTE
                /*this.txb_productoGenerador.setText(this.txb_productoDescuento.getText());
                this.rb_prodGen.setDisable(true);
                this.txb_productoGenerador.setDisable(true);
                this.btn_buscarGen.setDisable(true);
                this.cb_categoriaGenerador.setValue(this.cb_categoriaDescuento.getValue());
                this.rb_catGen.setDisable(true);*/
            } else {
                mostrarBonificacionXespecie();
            }
        }
    }

    public void cambiarTipoDescuento(ActionEvent event) {
        String valorComboCondicion = this.cb_tipoDescuento.getValue().toString();
        this.reestructurarFormulario(valorComboCondicion);
    }

    // true activa los datos de producto generador, false los datos de la categoria
    public void activarDatosProdGen(Boolean accion) {
        this.rb_prodGen.setSelected(accion);
        this.label_ProductoGen.setDisable(!accion);
        this.txb_productoGenerador.setDisable(!accion);
        this.btn_buscarGen.setDisable(!accion);

        this.rb_catGen.setSelected(!accion);
        this.labelCategoriaGen.setDisable(accion);
        this.cb_categoriaGenerador.setDisable(accion);
    }

    public void actualizarCategoria(ActionEvent event) {
        if (this.cb_tipoDescuento.getValue() != null) {
            String valorComboCondicion = this.cb_tipoDescuento.getValue().toString();
            if (!valorComboCondicion.equalsIgnoreCase("")) {
                if (Condicion.DESC_C.equalsIgnoreCase(valorComboCondicion)) {
                    this.cb_categoriaGenerador.setValue(this.cb_categoriaDescuento.getValue());
                }
            }
        }
    }

    public void radioButtonProdGenAccion(ActionEvent event) {
        this.activarDatosProdGen(true);
    }

    public void radioButtonCatGenAccion(ActionEvent event) {
        this.activarDatosProdGen(false);
    }

    // true activa los datos de producto descuento, false los datos de la categoria
    public void activarDatosProdDesc(Boolean accion) {
        this.rb_prodDesc.setSelected(accion);
        this.labelProductoDesc.setDisable(!accion);
        this.txb_productoDescuento.setDisable(!accion);
        this.btn_buscarDesc.setDisable(!accion);

        this.rb_catDesc.setSelected(!accion);
        this.labelCategoriaDesc.setDisable(accion);
        this.cb_categoriaDescuento.setDisable(accion);
    }


    public void radioButtonProdDescAccion(ActionEvent event) {
        this.activarDatosProdDesc(true);
    }

    public void radioButtonCatDescAccion(ActionEvent event) {
        this.activarDatosProdDesc(false);
    }

    @Override
    public void initialize() {

        System.out.println("SE HIZO INITIALIZE");
        this.listaCategorias = repositoryMantMov.obtenerCategoriasProducto();

        this.configurarComboBox(this.cb_categoriaDescuento);
        this.configurarComboBox(this.cb_categoriaGenerador);

        cb_tipoDescuento.getItems().add(Condicion.DESC_C);
        cb_tipoDescuento.getItems().add(Condicion.DESC_B);
        cb_tipoDescuento.getItems().add(Condicion.DESC_P);
        cb_tipoDescuento.getItems().add(Condicion.DESC_FP);
        cb_tipoDescuento.getItems().add(Condicion.DESC_FD);

        this.txb_productoGenerador.setDisable(true);

        this.txb_productoDescuento.setDisable(true);

        this.activarDatosProdGen(true);
        this.activarDatosProdDesc(true);


        this.esCreacion = this.mantenimientoDescuentoController.esCreacion();


        mostrarTodosElementos(false);

        if (this.esCreacion) {
            l_nombreFormulario.setText("Crear Condicion");
            this.idEmpleadoAuditado = this.mantenimientoDescuentoController.getIdEmpleadoAuditado();
            for (CategoriaProducto categoria : listaCategorias) {
                this.cb_categoriaDescuento.getItems().add(categoria);
                this.cb_categoriaGenerador.getItems().add(categoria);
            }
            //cb_tipoDescuento.setValue(cb_tipoDescuento.getItems().get(0));

        } else {
            l_nombreFormulario.setText("Modificar Condicion");

            System.out.println("controller == null ? " + this.mantenimientoDescuentoController == null);
            Condicion condicion = this.mantenimientoDescuentoController.getDescuento();
            this.idEmpleadoAuditado = condicion.getIdEmpleadoAuditado();
            System.out.println("id condicion -> " + condicion.getIdCondicion());
            for (CategoriaProducto categoria : listaCategorias) {
                this.cb_categoriaDescuento.getItems().add(categoria);
                this.cb_categoriaGenerador.getItems().add(categoria);
                if (categoria.getIdCategoria() == condicion.getIdCategoriaProdGen()) {
                    this.cb_categoriaGenerador.getSelectionModel().select(categoria);
                    this.rb_catGen.setSelected(true);
                    this.rb_prodGen.setSelected(false);
                }
                if (categoria.getIdCategoria() == condicion.getIdCategoriaProdDesc()) {
                    this.cb_categoriaDescuento.getSelectionModel().select(categoria);
                    this.rb_catDesc.setSelected(true);
                    this.rb_prodDesc.setSelected(false);
                }
            }
            this.llenarFormulario(condicion);
        }
    }
}
