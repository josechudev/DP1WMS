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


    @FXML private TableView<KardexFila> tableViewKardex;
    @FXML private TableColumn<KardexFila,Integer> c_Codigo;
    @FXML private TableColumn<KardexFila,String> c_fechaMov;
    @FXML private TableColumn<KardexFila,String> c_descProd;
    @FXML private TableColumn<KardexFila,Integer> c_cantidad;
    @FXML private TableColumn<KardexFila,String> c_decMov;
    @FXML private TableColumn<KardexFila,String> c_entradas;
    @FXML private TableColumn<KardexFila,String> c_salidas;
    @FXML private DatePicker dp_fecInicio;
    @FXML private DatePicker dp_fecFin;
    @FXML private Button btn_Consulta;

    @FXML private TextField txt_balance;
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
        for (KardexFila k : kardexFilaList) {
            tableViewKardex.getItems().add(k);
            if (k.isEsIngreso()){
                valTotal = valTotal - k.getValorTotal();
            }else{
                valTotal = valTotal + k.getValorTotal();
            }
        }
        txt_balance.setText(String.valueOf(valTotal));
    }
    private void llenarGrilla(String fecInicio,String fecFin) {
        tableViewKardex.getItems().clear();
        List<KardexFila> kardexFilaList = repositoryKardexFila.selectAllKardexFila(fecInicio,fecFin);
        float valTotal = 0;
        for (KardexFila k : kardexFilaList) {
            tableViewKardex.getItems().add(k);
            if (k.isEsIngreso()){
                valTotal = valTotal - k.getValorTotal();
            }else{
                valTotal = valTotal + k.getValorTotal();
            }
        }
        txt_balance.setText(String.valueOf(valTotal));
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
        c_entradas.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().isEsIngreso()?String.valueOf(value.getValue().getCantidad()*value.getValue().getPrecioCompra()):"-");
        });
        c_salidas.setCellValueFactory(value -> {
            return new SimpleStringProperty(!value.getValue().isEsIngreso()?String.valueOf(value.getValue().getCantidad()*value.getValue().getPrecioVenta()):"-");
        });

        llenarGrilla();
    }

    @FXML
    private void consultarKardex(){
        System.out.println(dp_fecInicio.getValue().toString());
        llenarGrilla(dp_fecInicio.getValue().toString(),dp_fecFin.getValue().toString());
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

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }


}
