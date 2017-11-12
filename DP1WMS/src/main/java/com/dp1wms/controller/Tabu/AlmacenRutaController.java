package com.dp1wms.controller.Tabu;

import com.dp1wms.controller.FxmlController;

import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Producto;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.Point;
import java.util.ArrayList;

@Component
public class AlmacenRutaController implements FxmlController {

    @FXML GridPane almacen_layout;
    private StageManager stageManager;
    private Almacen almacen;

    @Autowired
    @Lazy
    public AlmacenRutaController(StageManager stageManager) {
        this.stageManager = stageManager;
    }


    @FXML
    public void click_generar_ruta(ActionEvent event){
        System.out.println("plin");
    }

    public void imprimirAlmacen(){
        for (int i = 0; i < almacen.getAlto(); i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(20);
            almacen_layout.getRowConstraints().add(row);
        }
        for (int j = 0; j < almacen.getAncho(); j++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPrefWidth(20);
            almacen_layout.getColumnConstraints().add(col);
        }

        boolean [][] layout = almacen.getAlmacen();
        boolean [][] nodos = almacen.getNodos();
        for (int i = 0; i < almacen.getAlto(); i++) {
            for (int j = 0; j < almacen.getAncho(); j++) {
                if(nodos[i][j] == true){
                    Region rack = new Region();
                    rack.setStyle("-fx-background-color: #730000;");
                    rack.setPrefHeight(20);
                    rack.setPrefWidth(20);
                    almacen_layout.add(rack,i,j);

                } else{
                    if (layout[i][j] == true){
                        Region rack = new Region();
                        rack.setStyle("-fx-background-color: #380B61;");
                        rack.setPrefHeight(20);
                        rack.setPrefWidth(20);
                        almacen_layout.add(rack,i,j);
                    }

                }


            }
        }

    }

    @Override
    public void initialize() {
        System.out.println("Eleccion de Algoritmo");

        this.almacen = new Almacen(50,50);
        GestorAlmacen.generarRacksAletorios(almacen);

        Point puntoInicio = new Point(0,0);

        int numProductos = 5;
        ArrayList<Producto> productos = GestorProducto.generarProductos(almacen, numProductos);
        GestorAlmacen.llenarConProdYPtoPartida(almacen, productos, puntoInicio);

        this.imprimirAlmacen();

        almacen_layout.setGridLinesVisible(true);
    }
}




