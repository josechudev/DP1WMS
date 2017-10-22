package com.dp1wms.controller;

import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.view.FxmlView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class MantenimientoMovController implements FxmlController{
    @Autowired
    private RepositoryMantMov repositoryMantMov;

    private final StageManager stageManager;

    @Autowired @Lazy
     public  MantenimientoMovController(StageManager stageManager){
                this.stageManager = stageManager;
            }


    @FXML
    public void ingresoLote(ActionEvent event){
        System.out.println("Ingreso de Lote");
        this.stageManager.mostrarModal(FxmlView.CREAR_LOTE);
     }

    public void movimientoProducto(ActionEvent event){
        System.out.println("Movimiento de un Producto");
        this.stageManager.mostrarModal(FxmlView.INGRESO_PRODUCTO);
    }

    @Override
    public void initialize() {

    }
}
