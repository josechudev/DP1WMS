package com.dp1wms.controller;

import com.dp1wms.view.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MainController implements FxmlController {

    private final StageManager stageManager;

    @Autowired @Lazy
    public MainController(StageManager stageManager){
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(){}

}
