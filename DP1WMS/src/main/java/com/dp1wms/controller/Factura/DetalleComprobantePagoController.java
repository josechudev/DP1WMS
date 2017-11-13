package com.dp1wms.controller.Factura;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.ComprobantePago;
import com.dp1wms.model.DetalleComprobantePago;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class DetalleComprobantePagoController implements FxmlController {

    @FXML private TableView<DetalleComprobantePago> e_table;
    @FXML private TableColumn<DetalleComprobantePago, String> e_codigoProducto;
    @FXML private TableColumn<DetalleComprobantePago, String> e_nombreProducto;
    @FXML private TableColumn<DetalleComprobantePago, Integer> e_cantidad;
    @FXML private TableColumn<DetalleComprobantePago, Float> e_precioUnitario;
    @FXML private TableColumn<DetalleComprobantePago, Float> e_descuento;
    @FXML private TableColumn<DetalleComprobantePago, Float> e_subtotal;

    @FXML private TextField e_TFtipoComprobante, e_TFcliente, e_TFfecha, e_TFflete, e_TFsubtotal, e_TFigv, e_TFtotal;



    @Override
    public void initialize(){
        e_codigoProducto.setCellValueFactory(new PropertyValueFactory<DetalleComprobantePago, String>("v_codigoProducto"));
        e_nombreProducto.setCellValueFactory(new PropertyValueFactory<DetalleComprobantePago, String>("v_nombreProducto"));
        e_cantidad.setCellValueFactory(new PropertyValueFactory<DetalleComprobantePago, Integer>("v_cant"));
        e_precioUnitario.setCellValueFactory(new PropertyValueFactory<DetalleComprobantePago, Float>("v_precioUnitario"));
        e_descuento.setCellValueFactory(new PropertyValueFactory<DetalleComprobantePago, Float>("v_descuento"));
        e_subtotal.setCellValueFactory(new PropertyValueFactory<DetalleComprobantePago, Float>("v_subtotal"));
    }

    public void _setData(ComprobantePago auxComprobantePago, List<DetalleComprobantePago> auxListComprobantePago){
        e_table.getItems().clear();

        e_table.getItems().addAll(auxListComprobantePago);

        e_TFtipoComprobante.setText(auxComprobantePago.getV_tipoComprobante());
        e_TFcliente.setText(auxComprobantePago.getV_cliente());
        e_TFfecha.setText(auxComprobantePago.getV_fechaCreacion().substring(0, 19));
        e_TFflete.setText(Float.toString(auxComprobantePago.getV_flete()));
        e_TFsubtotal.setText(Float.toString(auxComprobantePago.getV_subtotal()));
        e_TFigv.setText(Float.toString(auxComprobantePago.getV_igv()));
        e_TFtotal.setText(Float.toString(auxComprobantePago.getV_total()));
    }
}
