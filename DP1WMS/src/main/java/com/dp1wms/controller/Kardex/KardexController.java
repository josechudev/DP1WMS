package com.dp1wms.controller.Kardex;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.IKardex.RepositoryKardexFila;
import com.dp1wms.model.KardexFila;
import com.dp1wms.view.StageManager;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import javafx.beans.property.SimpleStringProperty;
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

@Component
public class KardexController implements FxmlController {

    private final StageManager stageManager;
    /*
                <TableColumn fx:id="c_Codigo" prefWidth="78.0" text="Codigo"/>
                <TableColumn fx:id="c_fechaMov" prefWidth="154.0" text="Fecha Movimiento"/>
                <TableColumn fx:id="c_descProd" prefWidth="154.0" text="Kardex"/>
                <TableColumn fx:id="c_cantidad" prefWidth="154.0" text="Cantidad"/>
                <TableColumn fx:id="c_decMov" prefWidth="154.0" text="Descripcion"/>
                <TableColumn fx:id="c_entradas" prefWidth="154.0" text="Entradas"/>
                <TableColumn fx:id="c_salidas" prefWidth="154.0" text="Salidas"/>
    */


    @FXML
    private TableView<KardexFila> tableViewKardex;
    @FXML
    private TableColumn<KardexFila, Integer> c_Codigo;
    @FXML
    private TableColumn<KardexFila, String> c_fechaMov;
    @FXML
    private TableColumn<KardexFila, String> c_descProd;
    @FXML
    private TableColumn<KardexFila, Integer> c_cantidad;
    @FXML
    private TableColumn<KardexFila, String> c_decMov;
    @FXML
    private TableColumn<KardexFila, String> c_entradas;
    @FXML
    private TableColumn<KardexFila, String> c_salidas;
    @FXML
    private TableColumn<KardexFila, String> c_cantEntrada;
    @FXML
    private TableColumn<KardexFila, String> c_cantSalida;
    @FXML
    private DatePicker dp_fecInicio;
    @FXML
    private DatePicker dp_fecFin;
    @FXML
    private Button btn_Consulta;
    @FXML
    private TableColumn c_precioCompra;
    @FXML
    private TableColumn c_importe;
    @FXML
    private TextField txt_totMov;
    @FXML
    private TextField txt_totCompras;
    @FXML
    private TextField txt_totVentas;
    @FXML
    private TextField txt_totMovEntradas;
    @FXML
    private TextField txt_totMovSalidas;
    @FXML
    private TextField txt_totMovInternos;

    @FXML
    private TextField txt_balance;

    @Autowired
    private RepositoryKardexFila repositoryKardexFila;


    @Autowired
    @Lazy
    public KardexController(StageManager stageManager) {
        /*
                this.idMovimiento = idMovimiento;
        this.idTipoMovimiento = idTipoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.valorTotal = valorTotal;
        this.esIngreso = esIngreso;
         */
        this.stageManager = stageManager;

    }

    private void llenarGrilla() {
        tableViewKardex.getItems().clear();
        List<KardexFila> kardexFilaList = repositoryKardexFila.selectAllKardexFila();
        float valTotal = 0;
        float comprasTot = 0;
        float ventasTot = 0;
        int movEntradas = 0;
        int movSalidas = 0;
        int movInternos = 0;
        int totMov = 0;
        for (KardexFila k : kardexFilaList) {
            totMov += k.getCantidad();
            tableViewKardex.getItems().add(k);
            if (k.getAuxIngreso() == 1) {
                valTotal = valTotal - k.getValorTotal();
                comprasTot += k.getCantidad() * k.getPrecioCompra();
                movEntradas += k.getCantidad();
            } else {
                if (k.getAuxIngreso() == 2) {
                    valTotal = valTotal + k.getValorTotal();
                    ventasTot += k.getCantidad() * k.getPrecioVenta();
                    movSalidas += k.getCantidad();
                } else {
                    movInternos += k.getCantidad();
                }

            }
        }
        txt_balance.setText(String.valueOf(ventasTot - comprasTot));
        txt_totCompras.setText(String.valueOf(comprasTot));
        txt_totVentas.setText(String.valueOf(ventasTot));
        txt_totMov.setText(String.valueOf(totMov));
        txt_totMovEntradas.setText(String.valueOf(movEntradas));
        txt_totMovSalidas.setText(String.valueOf(movSalidas));
        txt_totMovInternos.setText(String.valueOf(movInternos));
    }

    private void llenarGrilla(String fecInicio, String fecFin) {
        tableViewKardex.getItems().clear();
        List<KardexFila> kardexFilaList = repositoryKardexFila.selectAllKardexFila(fecInicio, fecFin);
        float valTotal = 0;
        float comprasTot = 0;
        float ventasTot = 0;
        int movEntradas = 0;
        int movSalidas = 0;
        int movInternos = 0;
        int totMov = 0;

        for (KardexFila k : kardexFilaList) {
            totMov += k.getCantidad();
            tableViewKardex.getItems().add(k);
            if (k.getAuxIngreso() == 1) {
                valTotal = valTotal - k.getValorTotal();
                comprasTot += k.getCantidad() * k.getPrecioCompra();
                movEntradas += k.getCantidad();
            } else {
                if (k.getAuxIngreso() == 2) {
                    valTotal = valTotal + k.getValorTotal();
                    ventasTot += k.getCantidad() * k.getPrecioVenta();
                    movSalidas += k.getCantidad();
                } else {
                    movInternos += k.getCantidad();
                }

            }
        }
        txt_balance.setText(String.valueOf(ventasTot - comprasTot));
        txt_totCompras.setText(String.valueOf(comprasTot));
        txt_totVentas.setText(String.valueOf(ventasTot));
        txt_totMov.setText(String.valueOf(totMov));
        txt_totMovEntradas.setText(String.valueOf(movEntradas));
        txt_totMovSalidas.setText(String.valueOf(movSalidas));
        txt_totMovInternos.setText(String.valueOf(movInternos));
    }


    @Override
    public void initialize() {
        dp_fecInicio.setValue(LOCAL_DATE("01-09-2017"));
        dp_fecFin.setValue(LOCAL_DATE("22-11-2017"));

        c_Codigo.setCellValueFactory(new PropertyValueFactory<KardexFila,Integer>("idMovimiento"));
        c_fechaMov.setCellValueFactory(new PropertyValueFactory<KardexFila,String>("fechaMovimiento"));
        c_descProd.setCellValueFactory(new PropertyValueFactory<KardexFila,String>("nombreProducto"));
        c_cantidad.setCellValueFactory(new PropertyValueFactory<KardexFila,Integer>("cantidad"));
        c_decMov.setCellValueFactory(new PropertyValueFactory<KardexFila,String>("descripcion"));
        c_precioCompra.setCellValueFactory(new PropertyValueFactory<KardexFila,Float>("PrecioCompra"));
        c_importe.setCellValueFactory(new PropertyValueFactory<KardexFila,Float>("PrecioVenta"));
        c_entradas.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().isEsIngreso() ? String.valueOf(value.getValue().getCantidad() * value.getValue().getPrecioCompra()) : "-");
        });
        c_cantEntrada.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getAuxIngreso() == 1 ? String.valueOf(value.getValue().getCantidad()) : "-");
        });

        c_cantSalida.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getAuxIngreso() == 2 ? String.valueOf(value.getValue().getCantidad()) : "-");
        });
        c_cantEntrada.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getAuxIngreso()==1?String.valueOf(value.getValue().getCantidad()):"-");
        });

        c_cantSalida.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getAuxIngreso()==2?String.valueOf(value.getValue().getCantidad()):"-");
        });
        c_salidas.setCellValueFactory(value -> {
            return new SimpleStringProperty(!value.getValue().isEsIngreso() ? String.valueOf(value.getValue().getCantidad() * value.getValue().getPrecioVenta()) : "-");
        });


        llenarGrilla();
    }

    @FXML
    private void consultarKardex() {
        System.out.println(dp_fecInicio.getValue().toString());
        LocalDate desdeLD = this.dp_fecInicio.getValue();
        LocalDate hastaLD = this.dp_fecFin.getValue();

        //mas validacion
        if (desdeLD == null || hastaLD == null || desdeLD.isAfter(hastaLD)) {
            this.stageManager.mostrarErrorDialog("Error Buscar Proforma", null,
                    "Debe seleccionar un rango de fecha adecuado");
        } else {
            llenarGrilla(dp_fecInicio.getValue().toString(), dp_fecFin.getValue().toString());
        }
    }

    @FXML
    private void actualizarKardex() {
        llenarGrilla();
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

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }


}
