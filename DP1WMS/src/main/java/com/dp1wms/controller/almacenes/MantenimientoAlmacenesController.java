package com.dp1wms.controller.almacenes;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.impl.RepositoryMantAlmacenImpl;
import com.dp1wms.model.Almacen;
import com.dp1wms.view.AlmacenView;
import com.dp1wms.view.FxmlView;
import com.dp1wms.view.StageManager;
import com.dp1wms.view.MainView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;

import java.util.List;

@Component
public class MantenimientoAlmacenesController implements FxmlController {

    @FXML private GridPane mantenimientoAlmacenes;
    @FXML private VBox vbAlmacenes;

    @Autowired private RepositoryMantAlmacenImpl repositoryMantAlmacen;

    private StageManager stageManager;
    private Almacen almacenSeleccionado;

    @Autowired @Lazy
    public MantenimientoAlmacenesController(StageManager stageManager){
        this.stageManager = stageManager;
    }

    @Override
    public void initialize() {
        this.almacenSeleccionado = null;
        obtenerAlmacenes();
    }

    public int anadirAlmacen(String nombre, String direccion, int largo, int ancho){
        Almacen auxAlmacen = new Almacen();
        auxAlmacen.setNombre(nombre);
        auxAlmacen.setDireccion(direccion);
        auxAlmacen.setLargo(largo);
        auxAlmacen.setAncho(ancho);

        return repositoryMantAlmacen.crearAlmacen(auxAlmacen);
    }

    public void editarAlmacen(Almacen almacen){
        this.almacenSeleccionado = almacen;
        this.stageManager.mostrarModal(AlmacenView.VISTA_ALMACEN);
    }

    public Almacen getAlmacenSeleccionado(){
        return this.almacenSeleccionado;
    }

    public void refrescarAlmacenes(){
        limpiarLista();
        obtenerAlmacenes();
    }

    @FXML
    private void btnClickNuevoAlmacen(ActionEvent event){
        this.stageManager.mostrarModal(MainView.NUEVO_ALMACEN);
        limpiarLista();
        obtenerAlmacenes();
    }

    private void limpiarLista(){
        vbAlmacenes.getChildren().clear();
    }

    private void obtenerAlmacenes(){
        ObservableList vbListAlmacenes = vbAlmacenes.getChildren();
        List<Almacen> almacenes = repositoryMantAlmacen.obtenerAlmacenes();

        for(Almacen alm: almacenes){
            AlmacenItemController itemAlmacen = new AlmacenItemController(this, alm);
            itemAlmacen.setAlmacen(alm);
            vbListAlmacenes.add(itemAlmacen);
        }
    }
}
