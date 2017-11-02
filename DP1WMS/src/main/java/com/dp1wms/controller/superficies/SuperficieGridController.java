package com.dp1wms.controller.superficies;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
        this.setPrefWidth(uLargo * UNIT_PIXEL_WIDTH);
        this.setPrefHeight(uAncho * UNIT_PIXEL_WIDTH);
        this.setGridLinesVisible(true);
        for (int i = 0; i<uLargo; i++){
            this.addColumn(i, null);
        }
        for (int j = 0; j<uAncho; j++){
            this.addRow(j, null);
        }
        for (int i=0; i<uAncho; i++){
            for (int j=0; j<uLargo; j++){
                Rectangle r = new Rectangle();
                r.setFill(Color.AQUA);
                this.add(r, i, j);
            }
        }
    }
}
