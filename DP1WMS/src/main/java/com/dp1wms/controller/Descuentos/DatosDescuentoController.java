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
public class DatosDescuentoController implements FxmlController {


    @FXML
    private TitledPane titledPaneGen;
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

    public void guardarFormulario(ActionEvent event) {

        Condicion condicion = new Condicion();

        condicion.setTipoCondicion(this.cb_tipoDescuento.getValue().toString());


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
        Double valorDescuento;
        try {
            valorDescuento = Double.parseDouble(this.txb_valorDescuento.getText());
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
        condicion.setDescripcion(this.txb_descripcion.getText());

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
            condicion.setIdProductoGenerador(condicion.getIdProductoDescuento());
            condicion.setIdCategoriaProdGen(condicion.getIdCategoriaProdDesc());
            condicion.setCantProdGen(1);
            condicion.setCantProdDesc(1);
        }
        if(condicion.getTipoCondicion().equalsIgnoreCase(Condicion.DESC_C)){
            condicion.setIdProductoGenerador(condicion.getIdProductoDescuento());
            condicion.setIdCategoriaProdGen(condicion.getIdCategoriaProdDesc());
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

        if(this.cb_tipoDescuento.getValue() != null){
            String valorComboCondicion = this.cb_tipoDescuento.getValue().toString();
            if(!valorComboCondicion.equalsIgnoreCase("")){
                if(Condicion.DESC_C.equalsIgnoreCase(valorComboCondicion)){
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

        if(!ocultar){ // si se debe ocultar, limpiar los datos para futuras consultas a otros tipos
            this.txb_productoGenerador.setText("");
            this.cb_categoriaGenerador.setValue(null);
            this.txb_cantidadGenerador.setText("");
        }
    }

    private void reestructurarFormulario(String valorCondicion) {
        this.rb_prodGen.setDisable(false);
        this.txb_productoGenerador.setDisable(false);
        this.btn_buscarGen.setDisable(false);
        this.rb_catGen.setDisable(false);
        if (Condicion.DESC_P.equalsIgnoreCase(valorCondicion)) {
            this.ocultarDatosGenerador(false);
        } else {
            this.ocultarDatosGenerador(true);
            // si es descuento por cantidad , cargar datos producto o categoria descuento
            if(Condicion.DESC_C.equalsIgnoreCase(valorCondicion)){
                this.txb_productoGenerador.setText(this.txb_productoDescuento.getText());
                this.rb_prodGen.setDisable(true);
                this.txb_productoGenerador.setDisable(true);
                this.btn_buscarGen.setDisable(true);
                this.cb_categoriaGenerador.setValue(this.cb_categoriaDescuento.getValue());
                this.rb_catGen.setDisable(true);
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

    public void actualizarCategoria(ActionEvent event){
        if(this.cb_tipoDescuento.getValue() != null){
            String valorComboCondicion = this.cb_tipoDescuento.getValue().toString();
            if(!valorComboCondicion.equalsIgnoreCase("")){
                if(Condicion.DESC_C.equalsIgnoreCase(valorComboCondicion)){
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

        this.listaCategorias = repositoryMantMov.obtenerCategoriasProducto();

        this.configurarComboBox(this.cb_categoriaDescuento);
        this.configurarComboBox(this.cb_categoriaGenerador);

        cb_tipoDescuento.getItems().add(Condicion.DESC_C);
        cb_tipoDescuento.getItems().add(Condicion.DESC_B);
        cb_tipoDescuento.getItems().add(Condicion.DESC_P);


        this.txb_productoGenerador.setDisable(true);

        this.txb_productoDescuento.setDisable(true);

        this.activarDatosProdGen(true);
        this.activarDatosProdDesc(true);


        this.esCreacion = this.mantenimientoDescuentoController.esCreacion();

        if (this.esCreacion) {
            l_nombreFormulario.setText("Crear Condicion");
            this.idEmpleadoAuditado = this.mantenimientoDescuentoController.getIdEmpleadoAuditado();
            for (CategoriaProducto categoria : listaCategorias) {
                this.cb_categoriaDescuento.getItems().add(categoria);
                this.cb_categoriaGenerador.getItems().add(categoria);
            }

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
