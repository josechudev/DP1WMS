package com.dp1wms.controller.racks;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class SuperficieGridController extends GridPane {

    private int UNIT_PIXEL_WIDTH = 5;
    private int uLargo;
    private int uAncho;

    public SuperficieGridController(int uLargo, int uAncho){
        super();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Superficies/SuperficieGrid.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.uLargo = uLargo;
        this.uAncho = uAncho;
        dibujarGrid();
    }

    private void dibujarGrid(){
        this.setPrefWidth(uLargo*UNIT_PIXEL_WIDTH);
        this.setPrefHeight(uAncho*UNIT_PIXEL_WIDTH);
        for (int i = 0; i<uLargo; i++){
            for (int j = 0; j<uAncho; j++){

            }
        }
    }
}
