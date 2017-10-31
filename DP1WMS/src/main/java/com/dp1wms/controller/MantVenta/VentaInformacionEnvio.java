package com.dp1wms.controller.MantVenta;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.model.Envio;
import com.dp1wms.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class VentaInformacionEnvio implements FxmlController{


    private Envio envio;

    private StageManager stageManager;
    private VentaPedido ventaPedido;

    @Override
    public void initialize(){

    }

    @Autowired @Lazy
    public VentaInformacionEnvio(StageManager stageManager, VentaPedido ventaPedido){
        this.stageManager = stageManager;
        this.ventaPedido = ventaPedido;
    }

    public Envio getEnvio(){
        return  this.envio;
    }

    public void setEnvio(Envio envio){
        this.envio = envio;
    }
}
