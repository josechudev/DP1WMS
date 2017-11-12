package com.dp1wms.controller.almacenes;

import com.dp1wms.model.Area;
import com.dp1wms.util.PosicionFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class AreaGridItemController extends GridPane {

    private MantenimientoAreasController mantenimientoAreasController;
    private Area area;

    @FXML private TextField tfVertSI;
    @FXML private TextField tfVertSD;
    @FXML private TextField tfVertII;
    @FXML private TextField tfVertID;
    @FXML private TextField tfCodigo;

    public AreaGridItemController(MantenimientoAreasController mantenimientoAreasController){
        super();

        this.mantenimientoAreasController = mantenimientoAreasController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Almacen/AreaGridItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setArea(Area area){
        this.area = area;
    }

    public void setControles(){
        tfVertSI.setText(PosicionFormatter.pointToXYPair(area.getVertSupIzq()));
        tfVertSD.setText(PosicionFormatter.pointToXYPair(area.getVertSupDer()));
        tfVertII.setText(PosicionFormatter.pointToXYPair(area.getVertInfIzq()));
        tfVertID.setText(PosicionFormatter.pointToXYPair(area.getVertInfDer()));
        tfCodigo.setText(area.getCodigo());
    }

    public Area guardarCambios(){
        String txtVertSI = tfVertSI.getText();
        String txtVertID = tfVertID.getText();
        String txtCodigo = tfCodigo.getText();

        area.setPosicionInicial(PosicionFormatter.stringToPoint(txtVertSI));
        area.setPosicionFinal(PosicionFormatter.stringToPoint(txtVertID));
        area.setCodigo(txtCodigo);

        return area;
    }

    public void eliminarArea(ActionEvent event){
        mantenimientoAreasController.anadirAreaAEliminar(this.area, this);
    }
}
