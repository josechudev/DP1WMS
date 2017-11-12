package com.dp1wms.controller.almacenes;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.impl.RepositoryMantAreaImpl;
import com.dp1wms.model.Area;
import com.dp1wms.view.StageManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.FlowPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MantenimientoAreasController implements FxmlController {

    @FXML private FlowPane fpAreas;
    
    private ObservableList<Node> fpItems;

    private ArrayList<Area> areasAEliminar;

    private StageManager stageManager;
    private AlmacenController almacenController;    

    @Autowired
    private RepositoryMantAreaImpl repositoryMantArea;

    @Autowired @Lazy
    public MantenimientoAreasController(StageManager stageManager, AlmacenController almacenController){
        this.stageManager = stageManager;
        this.almacenController = almacenController;
    }

    @Override
    public void initialize() {
        fpItems = fpAreas.getChildren();
        areasAEliminar = new ArrayList<>();
        dibujarAreas();
    }

    @FXML
    private void guardarAreas(ActionEvent event){
        AreaGridItemController controller;
        Area area;
        for(Node item: fpItems){
            controller = (AreaGridItemController) item;
            area = controller.guardarCambios();
            if (repositoryMantArea.editarArea(area) == 0){
                area.setIdAlmacen(almacenController.getAlmacen().getIdAlmacen());
                repositoryMantArea.crearArea(area);
            }
        }
        for(Area areaEliminada: this.areasAEliminar){
            repositoryMantArea.eliminarArea(areaEliminada);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Se actualizaron las áreas", ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK){
            stageManager.cerrarVentana(event);
            almacenController.refrescarAreas();
        }
    }

    private List<Area> obtenerAreas(){
        return repositoryMantArea.getAreasByIdAlmacen(almacenController.getAlmacen().getIdAlmacen());
    }

    private void dibujarAreas(){
        List<Area> areas = obtenerAreas();
        for(Area area: areas){
           AreaGridItemController areaGridItemController = new AreaGridItemController(this);
           areaGridItemController.setArea(area);
           areaGridItemController.setControles();
           fpItems.add(areaGridItemController);
        }

    }

    @FXML
    private void crearArea(ActionEvent event){
        AreaGridItemController nuevaAreaGridItemController = new AreaGridItemController(this);
        nuevaAreaGridItemController.setArea(new Area());
        fpItems.add(nuevaAreaGridItemController);
    }

    public void anadirAreaAEliminar(Area areaEliminada, AreaGridItemController areaGridItemController){
        this.areasAEliminar.add(areaEliminada);
        fpItems.remove(areaGridItemController);
    }

}
