package com.dp1wms.controller;

import com.dp1wms.controller.DevolucionPedido.ListaPedidoDevolucionController;
import com.dp1wms.dao.RepositoryCargaMasiva;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class VentanaConfiguracionController implements FxmlController{
    private final StageManager stageManager;

    private MainController mainController;

    @Autowired
    private RepositoryCargaMasiva repositoryCargaMasiva;

    @Autowired
    @Lazy
    public VentanaConfiguracionController(StageManager stageManager,  MainController mainController) {
        this.stageManager = stageManager;
        this.mainController = mainController;
    }


    public void limpiarCargaDatos(ActionEvent event){
        this.repositoryCargaMasiva.storeProcedure_cargarLimpiarCargaMasiva();
        this.stageManager.mostrarInfoDialog("Confirmacion","Eliminar data","Se elimino la data de prueba correctamente");
    }

    @Override
    public void initialize() {

    }
}
