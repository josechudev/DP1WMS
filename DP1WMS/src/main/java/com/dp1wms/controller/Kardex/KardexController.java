package com.dp1wms.controller.Kardex;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.IKardexFila.RepositoryKardexFila;
import com.dp1wms.model.KardexFila;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KardexController implements FxmlController {

    private final StageManager stageManager;
    /*
                <TableColumn fx:id="c_Codigo" prefWidth="78.0" text="Codigo"/>
                <TableColumn fx:id="c_fechaMov" prefWidth="154.0" text="Fecha Movimiento"/>
                <TableColumn fx:id="c_descProd" prefWidth="154.0" text="KardexFila"/>
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
    @FXML private TextField txt_balance;
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


    @Override
    public void initialize() {

    }
}
