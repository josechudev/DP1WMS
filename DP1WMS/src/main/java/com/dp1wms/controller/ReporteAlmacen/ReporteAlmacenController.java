package com.dp1wms.controller.ReporteAlmacen;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.IReporteAlmacen.RepositoryReporteAlmacen;
import com.dp1wms.model.ReporteAlmacen;
import com.dp1wms.view.StageManager;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static com.dp1wms.controller.Kardex.KardexController.LOCAL_DATE;

@Component
public class ReporteAlmacenController implements FxmlController {

    private final StageManager stageManager;


    @FXML
    private TableView<ReporteAlmacen> tableViewReporteAlmacen;
    @FXML
    private TableColumn<ReporteAlmacen, String> c_codigo;
    @FXML
    private TableColumn<ReporteAlmacen, String> c_prod;
    @FXML
    private TableColumn<ReporteAlmacen, String> c_descripcion;
    @FXML
    private TableColumn<ReporteAlmacen, Integer> c_stockMinimo;
    @FXML
    private TableColumn<ReporteAlmacen, Float> c_precioCompra;
    @FXML
    private TableColumn<ReporteAlmacen, Integer> c_stockActual;
    @FXML
    private TableColumn<ReporteAlmacen, Integer> c_stockPedidos;
    @FXML
    private TableColumn<ReporteAlmacen, Integer> c_stockTotal;
    @FXML
    private DatePicker dp_fecInicio;
    @FXML
    private DatePicker dp_fecFin;
    @FXML
    private TextField txt_Producto;

    private ObservableList<ReporteAlmacen> masterData = FXCollections.observableArrayList();

    @Autowired
    private RepositoryReporteAlmacen repositoryReporteAlmacen;

    @Autowired
    @Lazy
    public ReporteAlmacenController(StageManager stageManager) {

        this.stageManager = stageManager;

    }

    private void llenarGrilla() {
        for ( int i = 0; i<tableViewReporteAlmacen.getItems().size(); i++) {
            tableViewReporteAlmacen.getItems().clear();
        }

        if(!masterData.isEmpty()){
            masterData.removeAll();
        }
        List<ReporteAlmacen> reporteAlmacenList = repositoryReporteAlmacen.selectAllKardexFila();
        for (ReporteAlmacen r : reporteAlmacenList) {
            tableViewReporteAlmacen.getItems().add(r);
            masterData.add(r);
        }
    }

    private void llenarGrilla(String fecInicio, String fecFin, String producto) {
        for ( int i = 0; i<tableViewReporteAlmacen.getItems().size(); i++) {
            tableViewReporteAlmacen.getItems().clear();
        }
        if(!masterData.isEmpty()){
            masterData.removeAll();
        }
        List<ReporteAlmacen> reporteAlmacenList = repositoryReporteAlmacen.selectAllKardexFila(fecInicio, fecFin,'%'+producto+'%');
        for (ReporteAlmacen r : reporteAlmacenList) {
            tableViewReporteAlmacen.getItems().add(r);
            masterData.add(r);
        }
    }

    @FXML
    private void consultarAlmacen() {

        LocalDate desdeLD = this.dp_fecInicio.getValue();
        LocalDate hastaLD = this.dp_fecFin.getValue();

        //mas validacion
        if (desdeLD == null || hastaLD == null || desdeLD.isAfter(hastaLD)) {
            this.stageManager.mostrarErrorDialog("Error Buscar Proforma", null,
                    "Debe seleccionar un rango de fecha adecuado");
        } else {
            llenarGrilla(dp_fecInicio.getValue().toString(), dp_fecFin.getValue().toString(),txt_Producto.getText());

        }
    }


    @FXML
    private void actualizarAlmacen() {
        llenarGrilla();
    }

    @Override
    public void initialize() {

        dp_fecInicio.setValue(LOCAL_DATE("01-09-2017"));
        dp_fecFin.setValue(LOCAL_DATE("22-11-2017"));
        c_codigo.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen, String>("codigo"));
        c_prod.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen, String>("nombreproducto"));
        c_descripcion.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen, String>("descripcion"));
        c_stockMinimo.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen, Integer>("stockMinimo"));
        c_precioCompra.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen, Float>("precioCompra"));
        c_stockActual.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen, Integer>("stockFisico"));
        c_stockPedidos.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen, Integer>("cantidadPedido"));
        c_stockTotal.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen, Integer>("stockLogico"));
        c_prod.setSortType(TableColumn.SortType.ASCENDING);

        llenarGrilla();



    }


    private Timestamp convertirFecha(String fecha) throws ParseException {

        Date utiltime = null;
        utiltime = ISO8601Utils.parse(fecha, new ParsePosition(0));
        return new Timestamp(utiltime.getTime());

    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }


}
