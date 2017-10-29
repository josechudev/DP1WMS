package com.dp1wms.controller.Envios;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ListaEnviosController implements FxmlController {

    private MainController mainController;

    private final StageManager stageManager;

    @Autowired
    @Lazy
    public ListaEnviosController(StageManager stageManager,MainController mainController) {
        this.stageManager = stageManager;
        this.mainController = mainController;
    }

    @Override
    public void initialize() {

    }
}
