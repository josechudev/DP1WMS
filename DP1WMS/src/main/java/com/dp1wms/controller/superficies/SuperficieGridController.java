package com.dp1wms.controller.superficies;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class SuperficieGridController extends GridPane {

    public final int UNIT_PIXEL_WIDTH = 10;
    private int uLargo;
    private int uAncho;

    public Node[][] nodeGrid;

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

        nodeGrid = new Node[uLargo][uAncho];

        dibujarGrid();
    }

    private void dibujarGrid(){
        this.setPrefWidth(uLargo * UNIT_PIXEL_WIDTH);
        this.setPrefHeight(uAncho * UNIT_PIXEL_WIDTH);
        for (int i=0; i<uLargo; i++){
            for (int j=0; j<uAncho; j++){
                SuperficieTile tile = new SuperficieTile();
                this.add(tile, i, j);
                nodeGrid[i][j] = tile;
            }
        }
    }

    public void cellClicked(MouseEvent event, Rectangle rectangle){

    }

    public Node get(int i, int j){
        return nodeGrid[i][j];
    }

    public void remove(int i, int j){
        this.getChildren().remove(nodeGrid[i][j]);
        nodeGrid[i][j] = null;
    }

    public void resetTile(int i, int j){
        SuperficieTile tile = new SuperficieTile();
        this.add(tile, i, j);
        nodeGrid[i][j] = tile;
    }

    @Override
    public void add(Node child, int columnIndex, int rowIndex) {
        super.add(child, columnIndex, rowIndex);
        nodeGrid[columnIndex][rowIndex] = child;
        child.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cellClicked(event, (Rectangle) child);
            }
        });
    }

    private class SuperficieTile extends Rectangle {
        public SuperficieTile(){
            super();
            this.setHeight(UNIT_PIXEL_WIDTH);
            this.setWidth(UNIT_PIXEL_WIDTH);
            this.setFill(Color.WHITESMOKE);
            this.setStroke(Color.GRAY);
            this.setStrokeWidth(0.5);
        }
    }
}
