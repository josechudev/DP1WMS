package com.dp1wms.controller.almacenes;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantArea;
import com.dp1wms.dao.impl.RepositoryMantAlmacenImpl;
import com.dp1wms.model.Almacen;
import com.dp1wms.model.Area;
import com.dp1wms.util.PosicionFormatter;
import com.dp1wms.view.AlmacenView;
import com.dp1wms.view.StageManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlmacenController implements FxmlController{

    @FXML private TextField tfNombre;
    @FXML private TextField tfLargo;
    @FXML private TextField tfAncho;
    @FXML private TextField tfDireccion;
    @FXML private Label lbSuperficie;
    @FXML private AnchorPane apAlmacen;
    @FXML private VBox vbAreaDetalles;
    @FXML private Label lbCodigoArea;
    @FXML private Label lbSupIzq;
    @FXML private Label lbSupDer;
    @FXML private Label lbInfIzq;
    @FXML private Label lbInfDer;

    @Autowired
    RepositoryMantAlmacenImpl repositoryMantAlmacen;

    @Autowired
    RepositoryMantArea repositoryMantArea;

    private StageManager stageManager;
    private MantenimientoAlmacenesController mantenimientoAlmacenesController;
    private Almacen almacen;
    private List<Area> areas;
    private final int PIXELS_WIDTH_M2 = 5;

    @Autowired @Lazy
    public AlmacenController(StageManager stageManager, MantenimientoAlmacenesController mantenimientoAlmacenesController){
        this.stageManager = stageManager;
        this.mantenimientoAlmacenesController = mantenimientoAlmacenesController;
    }

    @Override
    public void initialize() {
        almacen = mantenimientoAlmacenesController.getAlmacenSeleccionado();
        areas = repositoryMantArea.getAreasByIdAlmacen(almacen.getIdAlmacen());
        tfNombre.setText(almacen.getNombre());
        tfDireccion.setText(almacen.getDireccion());
        tfLargo.setText(String.valueOf(almacen.getLargo()));
        tfAncho.setText(String.valueOf(almacen.getAncho()));
        lbSuperficie.setText(String.valueOf(almacen.getLargo()*almacen.getAncho()) + " m2");
        apAlmacen.setPrefWidth(almacen.getLargo()*10);
        apAlmacen.setPrefHeight(almacen.getAncho()*10);
        dibujarAlmacen();
        dibujarAreas();
    }

    public void eliminarAlmacen(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, String.format("¿Esta seguro que desea elminar el almacen %s", almacen.getNombre()), ButtonType.CANCEL, ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK){
            repositoryMantAlmacen.eliminarAlmacen(almacen);
            alert.close();
            stageManager.cerrarVentana(event);
            mantenimientoAlmacenesController.refrescarAlmacenes();
        } else {
            alert.close();
        }
    }

    public void guardarCambios(ActionEvent event){
        // Falta hacer comprobacion del input
        almacen.setNombre(tfNombre.getText());
        almacen.setDireccion(tfDireccion.getText());
        almacen.setLargo(Integer.parseInt(tfLargo.getText()));
        almacen.setAncho(Integer.parseInt(tfAncho.getText()));
        repositoryMantAlmacen.editarAlmacen(almacen);
        stageManager.cerrarVentana(event);
        mantenimientoAlmacenesController.refrescarAlmacenes();
    }

    public void cancelarCambios(ActionEvent event){
        stageManager.cerrarVentana(event);
    }

    public void refrescarAreas(){
        areas = repositoryMantArea.getAreasByIdAlmacen(almacen.getIdAlmacen());
        dibujarAreas();
    }

    private void dibujarAlmacen(){

        if (areas.size() == 0){
            return;
        }

        ObservableList apItems = apAlmacen.getChildren();

        int gLargo = almacen.getLargo()*10;
        int gAncho = almacen.getAncho()*10;

        Rectangle rectAlmacen = new Rectangle();
        rectAlmacen.setWidth(gLargo);
        rectAlmacen.setHeight(gAncho);
        rectAlmacen.setFill(Color.web("#95989A", 0.2));
        rectAlmacen.setStroke(Color.BLACK);

        AnchorPane.setTopAnchor(rectAlmacen, 0.0);
        AnchorPane.setLeftAnchor(rectAlmacen, 0.0);

        apItems.add(rectAlmacen);
        vbAreaDetalles.setVisible(false);
    }

    private void dibujarAreas(){
        ObservableList apItems = apAlmacen.getChildren();

        apItems.clear();

        Point2D pIni, pFin;
        double x1, y1, x2, y2;
        double aLargo, aAncho;

        if (areas.size() == 0){
            Label lbSinAreas= new Label("No hay áreas definidas para este almacén");
            lbSinAreas.setLayoutX(5.0);
            lbSinAreas.setLayoutY(5.0);
            apItems.add(lbSinAreas);
            return;
        }

        for (Area area: areas){
            pIni = area.getPosicionInicial();
            pFin = area.getPosicionFinal();

            x1 = pIni.getX();
            y1 = pIni.getY();
            x2 = pFin.getX();
            y2 = pFin.getY();

            aLargo = x2-x1;
            aAncho = y2-y1;

            Rectangle rectArea = new Rectangle();
            rectArea.setWidth(aLargo*PIXELS_WIDTH_M2);
            rectArea.setHeight(aAncho*PIXELS_WIDTH_M2);
            rectArea.setFill(Color.web("#46ACC2", 0.2));
            rectArea.setStroke(Color.GRAY);

            rectArea.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (Rectangle r: (List<Rectangle>) apItems) {
                        r.setFill(Color.web("#46ACC2", 0.2));
                    }
                    rectArea.setFill(Color.web("#46ACC2", 0.8));
                    mostrarDetallesArea(area);
                }
            });

            AnchorPane.setTopAnchor(rectArea, y1*PIXELS_WIDTH_M2);
            AnchorPane.setLeftAnchor(rectArea, x1*PIXELS_WIDTH_M2);

            apItems.add(rectArea);
        }
    }

    private void mostrarDetallesArea(Area area){
        if (!vbAreaDetalles.isVisible()) {
            vbAreaDetalles.setVisible(true);
        }

        lbCodigoArea.setText(area.getCodigo());
        lbSupIzq.setText("Sup. Izq: " + PosicionFormatter.pointToXYPair(area.getVertSupIzq()));
        lbSupDer.setText("Sup. Der: " + PosicionFormatter.pointToXYPair(area.getVertSupDer()));
        lbInfIzq.setText("Inf. Izq: " + PosicionFormatter.pointToXYPair(area.getVertInfIzq()));
        lbInfDer.setText("Inf. Der: " + PosicionFormatter.pointToXYPair(area.getVertInfDer()));
    }

    @FXML
    private void editarAreas(ActionEvent event){
        stageManager.mostrarModal(AlmacenView.VISTA_AREAS);
    }

    public Almacen getAlmacen(){
        return this.almacen;
    }

}
