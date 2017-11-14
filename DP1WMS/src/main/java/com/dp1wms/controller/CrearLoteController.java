package com.dp1wms.controller;

import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.*;
import com.dp1wms.util.DateParser;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CrearLoteController implements FxmlController{
    @FXML
    private TextField txb_nombreProducto;

    @FXML
    private TextField txb_cantidad;

    @FXML
    private TextField txb_stock;

    @FXML
    private DatePicker dp_fechaLote;

    @FXML
    private DatePicker dp_fechaEntrada;

    @FXML
    private AnchorPane crearLoteAnchorPane;
    @FXML
    private ComboBox<Almacen> cb_almacen;
    @FXML
    private ComboBox<Area> cb_area;
    @FXML
    private ComboBox<Rack> cb_rack;
    @FXML
    private ComboBox<Cajon> cb_cajon;

    private Integer idProducto=null;

    private  MainController mainController;

    private final StageManager stageManager;

    private List<Almacen> listaAlmacenes = new ArrayList<Almacen>();

    @Autowired
    private RepositoryMantMov repositoryMantMov;

    @Autowired
    @Lazy
    public CrearLoteController(StageManager stageManager,MainController mainController){
        this.stageManager = stageManager;
        this.mainController = mainController;
    }



    public void buscarProducto(ActionEvent event) {
        System.out.println("Buscar Producto");
        this.stageManager.mostrarModal(MainView.BUSQUEDA_PRODUCTO);


    }

    public void actualizarDataProducto(Producto producto){
        this.txb_nombreProducto.setText(producto.getNombreProducto());
        this.txb_stock.setText(""+producto.getStock());
        this.idProducto = producto.getIdProducto();
    }

    public void registrarLote(ActionEvent event){
        if (!validarDatos()){
            return;
        }

        Timestamp fechaLote = DateParser.localdateToTimestamp(this.dp_fechaLote.getValue());
        Timestamp fechaEntrada = DateParser.localdateToTimestamp(this.dp_fechaEntrada.getValue());

        int cantidad = Integer.parseInt(this.txb_cantidad.getText());

        Long idEmpleadoAuditado = this.mainController.getEmpleado().getIdempleado();
        int idCajon = cb_cajon.getValue().getIdCajon();

        repositoryMantMov.registrarLote(this.idProducto,fechaLote,fechaEntrada,cantidad,idEmpleadoAuditado,idCajon);
        stageManager.cerrarVentana(event);
    }

    private boolean validarDatos(){

        if(this.txb_nombreProducto.getText().equals("")){
            this.stageManager.mostrarErrorDialog("Error creacion lote", null,
                    "Debe seleccionar un producto");
            return false;
        }

        if(this.dp_fechaLote.getValue() == null){
            this.stageManager.mostrarErrorDialog("Error creacion lote", null,
                    "Debe ingresar una fecha para el lote");
            return false;
        }

        if(this.dp_fechaEntrada.getValue() == null){
            this.stageManager.mostrarErrorDialog("Error creacion lote", null,
                    "Debe ingresar una fecha de entrada para el lote");
            return false;
        }

        if(this.txb_cantidad == null){
            this.stageManager.mostrarErrorDialog("Error creacion lote", null,
                    "Debe ingresar una cantidad de ingreso para el lote");
            return false;
        }

        if(this.txb_cantidad.getText().equalsIgnoreCase("")){
            this.stageManager.mostrarErrorDialog("Error creacion lote", null,
                    "Debe ingresar una cantidad de ingreso para el lote");
            return false;
        }

        if(this.txb_nombreProducto.getText().equalsIgnoreCase("")){
            this.stageManager.mostrarErrorDialog("Error creacion lote", null,
                    "Debe ingresar una producto para el lote");
            return false;
        }

        if (this.dp_fechaLote.getValue() == null){
            this.stageManager.mostrarErrorDialog("Error creacion lote", null,
                    "Debe ingresar la fecha del lote");
            return false;
        }

        if (this.dp_fechaEntrada.getValue() == null){
            this.stageManager.mostrarErrorDialog("Error creacion lote", null,
                    "Debe ingresar la fecha de entrada del lote");
            return false;
        }

        if (this.txb_cantidad.getText().equals("")){
            this.stageManager.mostrarErrorDialog("Error creacion lote", null,
                    "Debe ingresar la cantidad de productos del lote");
            return false;
        }

        try {
            int cant = Integer.parseInt(txb_cantidad.getText());
        } catch (NumberFormatException e){
            this.stageManager.mostrarErrorDialog("Error creacion lote", null,
                    "La cantidad ingresada debe ser un número");
            return false;
        }

        if (cb_cajon.getValue() == null){
            this.stageManager.mostrarErrorDialog("Error creacion lote", null,
                    "Debe seleccionar un cajón donde guardar el lote");
            return false;
        }

        return true;

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

    public void cancelarRegistrarLote(ActionEvent event){
        System.out.println("Se cancelo el registro del Lote");
        crearLoteAnchorPane.getScene().getWindow().hide();
    }

    public void actualizarListasPorRack(ActionEvent event){
        int idRack= cb_rack.getValue().getIdRack();
        for(Almacen almacen : this.listaAlmacenes){
            List<Area> listaAreaBusqueda = almacen.getListaArea();
            for(Area area: listaAreaBusqueda){
                List<Rack> listaRackBusqueda = area.getListaRack();
                for(Rack rack : listaRackBusqueda){
                    if(idRack == rack.getIdRack()){
                        this.llenarComboBoxNoRack(rack);
                        return;
                    }
                }
            }
        }
    }

    private void llenarComboBoxNoRack(Rack rack){
        cb_cajon.getItems().clear();
        List<Cajon> cajones = rack.getListaCajones();
        for(Cajon cajon : cajones){
            cb_cajon.getItems().add(cajon);
        }
    }

    public void actualizarListasPorArea(ActionEvent event){
        int idArea = cb_area.getValue().getIdArea();
        for(Almacen almacen : this.listaAlmacenes){
            List<Area> listaAreaBusqueda = almacen.getListaArea();
            for(Area area: listaAreaBusqueda){
                if(area.getIdArea() == idArea){
                    llenarComboBoxNoArea(area);
                    return;
                }
            }
        }
    }
    private void llenarComboBoxNoArea(Area area){
        cb_cajon.getItems().clear();
        cb_rack.getItems().clear();
        List<Rack> racks = area.getListaRack();
        for(Rack rack : racks){
            cb_rack.getItems().add(rack);
            List<Cajon> cajones = rack.getListaCajones();
            for(Cajon cajon : cajones){
                cb_cajon.getItems().add(cajon);
            }
        }
    }


    public void actualizarListasPorAlmacen(ActionEvent event){
        int idAlmacen = cb_almacen.getValue().getIdAlmacen();
        List<Almacen> listaAlmacenAux = new ArrayList<Almacen>();
        for(Almacen almacen : this.listaAlmacenes){
            if(almacen.getIdAlmacen() == idAlmacen){
                listaAlmacenAux.add(almacen);
            }
        }
        this.llenarComboBoxNoAlmacen(listaAlmacenAux);
    }

    private void llenarComboBoxNoAlmacen(List<Almacen> listaAlmacenesLlenar){
        cb_cajon.getItems().clear();
        cb_rack.getItems().clear();
        cb_area.getItems().clear();
        for(Almacen almacen:listaAlmacenesLlenar){
            List<Area> areas = almacen.getListaArea();
            for(Area area : areas){
                cb_area.getItems().add(area);
                List<Rack> racks = area.getListaRack();
                for(Rack rack : racks){
                    cb_rack.getItems().add(rack);
                    List<Cajon> cajones = rack.getListaCajones();
                    for(Cajon cajon : cajones){
                        cb_cajon.getItems().add(cajon);
                    }
                }
            }
        }
    }

    private void llenarComboBox(List<Almacen> listaAlmacenesLlenar){
        for(Almacen almacen:listaAlmacenesLlenar){
            cb_almacen.getItems().add(almacen);
            List<Area> areas = almacen.getListaArea();
            for(Area area : areas){
                cb_area.getItems().add(area);
                List<Rack> racks = area.getListaRack();
                for(Rack rack : racks){
                    cb_rack.getItems().add(rack);
                    List<Cajon> cajones = rack.getListaCajones();
                    for(Cajon cajon : cajones){
                        cb_cajon.getItems().add(cajon);
                    }
                }
            }
        }
    }

    private void configurarComboBoxAlmacen(){

        Callback<ListView<Almacen>, ListCell<Almacen>> factory = lv -> new ListCell<Almacen>() {

            @Override
            protected void updateItem(Almacen item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };

        cb_almacen.setCellFactory(factory);
        cb_almacen.setButtonCell(factory.call(null));
    }

    private void configurarComboBoxArea(){

        Callback<ListView<Area>, ListCell<Area>> factory = lv -> new ListCell<Area>() {

            @Override
            protected void updateItem(Area item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getCodigo());
            }

        };

        cb_area.setCellFactory(factory);
        cb_area.setButtonCell(factory.call(null));
    }

    private void configurarComboBoxRack(){

        Callback<ListView<Rack>, ListCell<Rack>> factory = lv -> new ListCell<Rack>() {

            @Override
            protected void updateItem(Rack item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getCodigo());
            }

        };

        cb_rack.setCellFactory(factory);
        cb_rack.setButtonCell(factory.call(null));
    }

    private void configurarComboBoxCajon(){

        Callback<ListView<Cajon>, ListCell<Cajon>> factory = lv -> new ListCell<Cajon>() {

            @Override
            protected void updateItem(Cajon item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : ""+item.getIdCajon());
            }

        };

        cb_cajon.setCellFactory(factory);
        cb_cajon.setButtonCell(factory.call(null));
    }



    @Override
    public void initialize() {
        this.txb_stock.setDisable(true);
        this.txb_nombreProducto.setDisable(true);
        this.configurarComboBoxAlmacen();
        this.configurarComboBoxArea();
        this.configurarComboBoxRack();
        this.configurarComboBoxCajon();

        this.listaAlmacenes = this.repositoryMantMov.obtenerAlmacenes();
        this.llenarComboBox(this.listaAlmacenes);
    }
}
