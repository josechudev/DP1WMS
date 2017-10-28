package com.dp1wms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PopUpController implements FxmlController{
    @FXML private Label labelPopUp;

    @Override
    public void initialize() {
    }

    public void setLabelPopUp(String auxTextLabel){
        this.labelPopUp.setText(auxTextLabel);
    }
}
