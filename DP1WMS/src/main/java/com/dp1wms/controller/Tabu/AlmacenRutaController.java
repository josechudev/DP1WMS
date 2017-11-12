package com.dp1wms.controller.Tabu;

import com.dp1wms.controller.FxmlController;

import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Nodo;
import com.dp1wms.model.tabu.Producto;
import com.dp1wms.tabu.Tabu;
import com.dp1wms.view.StageManager;
import com.fasterxml.jackson.databind.JsonSerializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    @FXML private TextField numeroIteraciones;
    @FXML private TextField numIteracionesSinMejora;
    @FXML private TextField listaTabuTamanho;
    @FXML private TextField listaTabuPermanencia;

    private ArrayList<Button> solucion = new ArrayList<Button>();

    @Autowired
    @Lazy
    public AlmacenRutaController(StageManager stageManager) {
        this.stageManager = stageManager;
    }


    @FXML
    public void click_generar_ruta(ActionEvent event){

        for (Button button: solucion) {
            button.setVisible(false);
        }

        Integer tabuTamanho;
        Integer tabuPermanencia;
        Long numIter;
        Long numIterSinMejora;

        try {
            tabuTamanho = Integer.parseInt(listaTabuTamanho.getText());
            tabuPermanencia = Integer.parseInt(listaTabuPermanencia.getText());
            numIter = Long.parseLong(numeroIteraciones.getText());
            numIterSinMejora = Long.parseLong(numIteracionesSinMejora.getText());
        }catch (NumberFormatException e){
            System.out.println("Do Nothing");
            return;
        }

        GestorDistancias gestorDistancias = new GestorDistancias(almacen);
        gestorDistancias.calcularDistancias();
        int[][] distancias = gestorDistancias.getMatrizDistancia();
        int[] caminoInicial = gestorDistancias.generarCaminoInicial();

        for (int i = 0; i < caminoInicial.length ; i++) {
            System.out.print(caminoInicial[i]);
            System.out.print(" - ");
        }
        System.out.println();System.out.println();

        //Ejecutar tabu
        Tabu tabu = new Tabu(distancias, caminoInicial);


        int[] solucion = tabu.generarCamino(numIter, numIterSinMejora, tabuTamanho,tabuPermanencia);


        this.imprimirAlmacen();

        ArrayList<Nodo> solucionNodo = gestorDistancias.convertirIdANodos(solucion);

        //Imprime la solucion
        for (int i = 0; i < solucion.length ; i++) {
            System.out.print(solucion[i]);
            System.out.print(" - ");
        }
        //Tiempo
        System.out.println("\nTiempo Tabu: " + String.valueOf(tabu.getDuracion()));
        imprimirSolucion(solucionNodo);
    }

    public void imprimirAlmacen(){


        //color celdas
        for (int i = 0; i < almacen.getAlto(); i++) {
            for (int j = 0; j < almacen.getAncho(); j++) {
                Region cell = new Region();
                cell.setStyle("-fx-background-color: #B9B8FF;");
                cell.setPrefHeight(20);
                cell.setPrefWidth(20);
                almacen_layout.add(cell,i,j);
            }
        }


        boolean [][] layout = almacen.getAlmacen();
        boolean [][] nodos = almacen.getNodos();
        boolean [][] productos = almacen.getProductos();

        //color racks
        for (int i = 0; i < almacen.getAlto(); i++) {
            for (int j = 0; j < almacen.getAncho(); j++) {
                    if (layout[i][j]){
                        Region cell = new Region();
                        cell.setStyle("-fx-background-color: #4C6CCC;");
                        cell.setPrefHeight(20);
                        cell.setPrefWidth(20);
                        almacen_layout.add(cell,i,j);
                    }
            }
        }

        //color nodos
        for (int i = 0; i < almacen.getAlto(); i++) {
            for (int j = 0; j < almacen.getAncho(); j++) {
                if (nodos[i][j]){
                    Region cell = new Region();
                    cell.setStyle("-fx-background-color: #FFF579;");
                    cell.setPrefHeight(20);
                    cell.setPrefWidth(20);
                    almacen_layout.add(cell,i,j);
                }
            }
        }

        //colocar productos
        for (int i = 0; i < almacen.getAlto(); i++) {
            for (int j = 0; j < almacen.getAncho(); j++) {
                if (productos[i][j]){
                    Region rack = new Region();
                    rack.setStyle("-fx-background-color: #95CC4C;");
                    rack.setPrefHeight(20);
                    rack.setPrefWidth(20);
                    almacen_layout.add(rack,i,j);
                }
            }
        }

    }

    private void imprimirSolucion(ArrayList<Nodo> solucion){

        //colocar productos
        for(int i = 0; i < solucion.size() - 1; i++){
            Nodo nodo = solucion.get(i);
            Button punto = new Button();
            punto.setStyle("-fx-background-color: #ffffff; -fx-padding: 0; -fx-margin: 0; -fx-border-radius: 0;" +
                    " -fx-font-size: 10");
            punto.setPrefHeight(20);
            punto.setPrefWidth(20);
            punto.setText(String.valueOf(i));
            almacen_layout.add(punto, nodo.getX(), nodo.getY());
        }
    }

    @Override
    public void initialize() {
        System.out.println("Eleccion de Algoritmo");

        this.almacen = new Almacen(25,25);


        //construir grid de almacen
        for (int i = 0; i < almacen.getAlto()-1; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(20);
            almacen_layout.getRowConstraints().add(row);
        }
        for (int j = 0; j < almacen.getAncho()-1; j++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPrefWidth(20);
            almacen_layout.getColumnConstraints().add(col);
        }


        GestorAlmacen.generarRacksAletorios(almacen);

        Point puntoInicio = new Point(0,0);

        int numProductos = 5;
        ArrayList<Producto> productos = GestorProducto.generarProductos(almacen, numProductos);
        GestorAlmacen.llenarConProdYPtoPartida(almacen, productos, puntoInicio);

        this.imprimirAlmacen();

        GestorAlmacen.generarNodos(almacen);

        almacen_layout.setGridLinesVisible(true);
    }

    public ArrayList<Button> getSolucion() {
        return solucion;
    }

    public void setSolucion(ArrayList<Button> solucion) {
        this.solucion = solucion;
    }
}