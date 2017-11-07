package com.dp1wms.controller.ReporteAlmacen;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.IReporteAlmacen.RepositoryReporteAlmacen;
import com.dp1wms.model.ReporteAlmacen;
import com.dp1wms.view.StageManager;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    /*

                <TableColumn fx:id="c_codigo" prefWidth="78.0" text="Codigo"/>
                <TableColumn fx:id="c_prod" prefWidth="154.0" text="Producto"/>
                <TableColumn fx:id="c_descripcion" prefWidth="154.0" text="Descripcion"/>
                <TableColumn fx:id="c_stockMinimo" prefWidth="154.0" text="Stock Minimo"/>
                <TableColumn fx:id="c_precioCompra" prefWidth="154.0" text="Precio de Compra"/>
                <TableColumn fx:id="c_stockActual" prefWidth="154.0" text="Stock Actual"/>
                <TableColumn fx:id="c_stockPedidos" prefWidth="154.0" text="Pedidos"/>
                <TableColumn fx:id="c_stockTotal" prefWidth="88.0" text="Stock Total"/>
     */

    @FXML private TableView<ReporteAlmacen> tableViewReporteAlmacen;
    @FXML private TableColumn<ReporteAlmacen,String> c_codigo;
    @FXML private TableColumn<ReporteAlmacen,String> c_prod;
    @FXML private TableColumn<ReporteAlmacen,String>c_descripcion;
    @FXML private TableColumn<ReporteAlmacen,Integer> c_stockMinimo;
    @FXML private TableColumn<ReporteAlmacen,Float> c_precioCompra;
    @FXML private TableColumn<ReporteAlmacen,Integer> c_stockActual;
    @FXML private TableColumn<ReporteAlmacen,Integer> c_stockPedidos;
    @FXML private TableColumn<ReporteAlmacen,Integer> c_stockTotal;
    @FXML private DatePicker dp_fecInicio;
    @FXML private DatePicker dp_fecFin;

    @Autowired
    private RepositoryReporteAlmacen repositoryReporteAlmacen;

    @Autowired
    @Lazy
    public ReporteAlmacenController(StageManager stageManager) {

        this.stageManager = stageManager;

    }

    private void llenarGrilla(){
        tableViewReporteAlmacen.getItems().clear();
        List<ReporteAlmacen> reporteAlmacenList = repositoryReporteAlmacen.selectAllKardexFila();
        for (ReporteAlmacen r : reporteAlmacenList){
            tableViewReporteAlmacen.getItems().add(r);
        }
    }

    private void llenarGrilla(String fecInicio,String fecFin){
        tableViewReporteAlmacen.getItems().clear();
        List<ReporteAlmacen> reporteAlmacenList = repositoryReporteAlmacen.selectAllKardexFila(fecInicio,fecFin);
        for (ReporteAlmacen r : reporteAlmacenList){
            tableViewReporteAlmacen.getItems().add(r);
        }
    }

    @FXML
    private  void consultarAlmacen(){
        llenarGrilla(dp_fecInicio.getValue().toString(),dp_fecFin.getValue().toString());
    }
    @Override
    public void initialize() {

        dp_fecInicio.setValue(LOCAL_DATE("01-09-2017"));
        dp_fecFin.setValue(LOCAL_DATE("22-11-2017"));
        c_codigo.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen,String>("codigo"));
        c_prod.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen,String>("nombreproducto"));
        c_descripcion.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen,String>("descripcion"));
        c_stockMinimo.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen,Integer>("stockMinimo"));
        c_precioCompra.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen,Float>("precioCompra"));
        c_stockActual.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen,Integer>("stockFisico"));
        c_stockPedidos.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen,Integer>("cantidadPedido"));
        c_stockTotal.setCellValueFactory(new PropertyValueFactory<ReporteAlmacen,Integer>("stockLogico"));

        llenarGrilla();

    }


    private Timestamp convertirFecha(String fecha) throws ParseException {

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
