package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.DetalleProforma;
import com.dp1wms.model.Envio;
import com.dp1wms.model.Proforma;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class VentaProformaPreviewController implements FxmlController {

    @FXML private TableView<DetalleProforma> proformaTable;
    @FXML private TableColumn<DetalleProforma, String> codigoTC;
    @FXML private TableColumn<DetalleProforma, String> productoTC;
    @FXML private TableColumn<DetalleProforma, Integer> cantidadTC;
    @FXML private TableColumn<DetalleProforma, Float> precioTC;
    @FXML private TableColumn<DetalleProforma, Float> descuentoTC;
    @FXML private TableColumn<DetalleProforma, Float> totalParcialTC;

    @FXML private Label totalSinFleteLabel;
    @FXML private Label costoFleteLabel;
    @FXML private Label totalLabel;

    private StageManager stageManager;
    private VentaProformaController ventaProformaController;

    @FXML
    private void cerrarVentana(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    @Override
    public void initialize(){
        this.proformaTable.getItems().clear();
        this.codigoTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getProducto().getCodigo());
        });
        this.productoTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getProducto().getNombreProducto());
        });
        this.cantidadTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Integer>(value.getValue().getCantidad());
        });
        this.precioTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Float>(value.getValue().getProducto().getPrecio());
        });
        this.descuentoTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Float>(value.getValue().getDescuento());
        });
        this.totalParcialTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Float>(value.getValue().getSubTotal());
        });

        Proforma proforma = this.ventaProformaController.getProforma();
        this.proformaTable.getItems().addAll(proforma.getDetallesProforma());

        this.totalSinFleteLabel.setText(String.valueOf(proforma.getTotalSinFlete()));
        this.costoFleteLabel.setText(String.valueOf(proforma.getCostoFlete()));
        this.totalLabel.setText(String.valueOf(proforma.getTotal()));
    }

    @Autowired @Lazy
    public VentaProformaPreviewController(StageManager stageManager, VentaProformaController ventaProformaController){
        this.stageManager = stageManager;
        this.ventaProformaController = ventaProformaController;
    }
}
