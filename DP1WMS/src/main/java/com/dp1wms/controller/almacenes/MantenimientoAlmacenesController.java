package com.dp1wms.controller.almacenes;

import com.dp1wms.dao.RepositoryMantAlmacen;
import com.dp1wms.model.Almacen;
import com.dp1wms.view.FxmlView;
import com.dp1wms.view.StageManager;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;

import java.util.List;

@Component
public class MantenimientoAlmacenesController {

    @FXML
    private GridPane mantenimientoAlmacenes;

    @Autowired
    private RepositoryMantAlmacen repositoryMantAlmacen;

    private StageManager stageManager;

    @Autowired @Lazy
    public MantenimientoAlmacenesController(StageManager stageManager){
        this.stageManager = stageManager;
    }

    public int anadirAlmacen(String nombre, String direccion, int largo, int ancho){
        Almacen auxAlmacen = new Almacen();
        auxAlmacen.setNombre(nombre);
        auxAlmacen.setDireccion(direccion);
        auxAlmacen.setLargo(largo);
        auxAlmacen.setAncho(ancho);

        return repositoryMantAlmacen.crearAlmacen(auxAlmacen);
    }

    @FXML
    private void btnClickNuevoAlmacen(ActionEvent event){
        this.stageManager.mostrarModal(FxmlView.NUEVO_ALMACEN);
    }

    private void obtenerAlmacenes(){
        List<Almacen> almacenes = repositoryMantAlmacen.obtenerAlmacenes();
    }
}
