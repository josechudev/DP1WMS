package com.dp1wms.controller.Tabu;

import com.dp1wms.controller.FxmlController;

import com.dp1wms.dao.RepositoryRuta;
import com.dp1wms.model.Envio;
import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Nodo;
import com.dp1wms.model.tabu.Producto;
import com.dp1wms.tabu.BestFirstSearch;
import com.dp1wms.tabu.Tabu;
import com.dp1wms.view.AlmacenView;
import com.dp1wms.view.StageManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private VerHistorial verHistorial;

    private Almacen almacen;
    private GestorDistancias gestorDistancias;

    @FXML private TextField numeroIteraciones;
    @FXML private TextField tiempoMaximoTF;
    @FXML private TextField listaTabuTamanho;
    @FXML private TextField numIteracionesSinMejora;


    private ArrayList<Node> nodes;
    private ArrayList<Nodo> solucionTabu;
    private Envio envio;

    @Autowired
    private RepositoryRuta repositoryRuta;

    @Autowired
    @Lazy
    public AlmacenRutaController(StageManager stageManager,
                                 VerHistorial verHistorial) {
        this.stageManager = stageManager;
        this.verHistorial = verHistorial;
    }

    @FXML
    private void limpiarGrilla(){
        this.imprimirAlmacen();
    }

    @FXML
    private void click_generar_ruta(ActionEvent event){
        Integer tabuTamanho;
        Integer tabuPermanencia;
        Long numIter;
        Long numIterSinMejora;
        Long tiempoMaximo;

        //obtiene valores para tabu
        try {
            tabuTamanho = Integer.parseInt(listaTabuTamanho.getText());
            tabuPermanencia =tabuTamanho;
            numIter = Long.parseLong(numeroIteraciones.getText());
            numIterSinMejora =  Long.parseLong(numIteracionesSinMejora.getText());
            tiempoMaximo = Long.parseLong(tiempoMaximoTF.getText()) * 1000;
        }catch (NumberFormatException e){
            this.stageManager.mostrarErrorDialog("Error Generar Ruta", null,
                    "Debe ingresar un valor válido");
            return;
        }


        Thread thread = new Thread(()->{
            System.err.println("Empezando");
            //generar caminos entra productos
            BestFirstSearch bestFirstSearch = new BestFirstSearch(this.almacen, this.gestorDistancias);
            bestFirstSearch.generarCaminosEntreProductos();

            //matriz distancia calculada y camino inicial
            int[][] distancias = bestFirstSearch.generarMatrizDistancia();
            int[] caminoInicial = bestFirstSearch.generarCaminoInicial();

            //Crear caminos entre productos y punto de partida

            //Imprime camino inicial
            for (int i = 0; i < caminoInicial.length ; i++) {
                System.out.print(caminoInicial[i]);
                System.out.print(" - ");
            }
            System.out.println();System.out.println();

            //Ejecutar tabu
            Tabu tabu = new Tabu(distancias, caminoInicial);
            int[] solucion = tabu.generarCamino(numIter, numIterSinMejora, tabuTamanho,tabuPermanencia, tiempoMaximo);
             Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    //Imprimie Almacen
                    imprimirAlmacen();
                    //Obtiene los puntos con la solucion
                    solucionTabu = bestFirstSearch.convertirANodos(solucion);
                    //Imprime la solucion consola
                    for (int i = 0; i < solucion.length ; i++) {
                        System.out.print(solucion[i]);
                        System.out.print(" - ");
                    }
                    //Tiempo
                    System.out.println("\nTiempo Tabu: " + String.valueOf(tabu.getDuracion()));

                    //Dibuja la solucion en pantalla
                    imprimirSolucion(solucionTabu);
                }
            });
        });
       thread.start();
    }

    @FXML
    private void guardarRuta(){

        this.envio = new Envio();
        this.envio.setIdEnvio((long) 1);
        if(this.solucionTabu != null && this.solucionTabu.size() > 0){
            boolean res = this.repositoryRuta.guardarRuta(this.solucionTabu, this.envio);
            if(res){
                this.stageManager.mostrarInfoDialog("Generar Ruta", null,
                        "Se guardó la ruta generada.");
            } else {
                this.stageManager.mostrarInfoDialog("Error Gnerar Ruta", null,
                        "Hubo un error al intentar guardar la ruta. Inténtelo otra vez.");
            }
        } else {
            this.stageManager.mostrarErrorDialog("Error Generer Ruta", null,
                    "Debe haber generado una ruta para poder guardarlo");
        }
    }

    public void imprimirAlmacen(){
        //color celdas

        for(Node node: this.nodes){
            this.almacen_layout.getChildren().remove(node);
        }
        this.nodes.clear();

        for (int i = 0; i < almacen.getAncho(); i++) {
            for (int j = 0; j < almacen.getAlto(); j++) {
                Region cell = new Region();
                cell.setStyle("-fx-background-color: #B9B8FF;");
                cell.setPrefHeight(20);
                cell.setPrefWidth(20);
                this.nodes.add(cell);
                almacen_layout.add(cell,i,j);
            }
        }


        boolean [][] layout = almacen.getAlmacen();
        boolean [][] nodos = almacen.getNodos();
        boolean [][] productos = almacen.getProductos();

        //color racks
        for (int i = 0; i < almacen.getAncho(); i++) {
            for (int j = 0; j < almacen.getAlto(); j++) {
                    if (layout[i][j]){
                        Region cell = new Region();
                        cell.setStyle("-fx-background-color: #4C6CCC;");
                        cell.setPrefHeight(20);
                        cell.setPrefWidth(20);
                        this.nodes.add(cell);
                        almacen_layout.add(cell,i,j);
                    }
            }
        }

        //color nodos
        for (int i = 0; i < almacen.getAncho(); i++) {
            for (int j = 0; j < almacen.getAlto(); j++) {
                if (nodos[i][j]){
                    Region cell = new Region();
                    cell.setStyle("-fx-background-color: #FFF579;");
                    cell.setPrefHeight(20);
                    cell.setPrefWidth(20);
                    this.nodes.add(cell);
                    almacen_layout.add(cell,i,j);
                }
            }
        }

        //colocar productos
        for (int i = 0; i < almacen.getAncho(); i++) {
            for (int j = 0; j < almacen.getAlto(); j++) {
                if (productos[i][j]){
                    Region rack = new Region();
                    rack.setStyle("-fx-background-color: #95CC4C;");
                    rack.setPrefHeight(20);
                    rack.setPrefWidth(20);
                    this.nodes.add(rack);
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
            this.nodes.add(punto);
            almacen_layout.add(punto, nodo.getX(), nodo.getY());
        }
    }

    @Override
    public void initialize() {
        this.initFields();
        this.nodes = new ArrayList<>();

        //Almacen nuevo
        this.almacen = new Almacen(30,30);

        //construir grid de almacen
        for (int i = 0; i < almacen.getAncho() - 1; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(20);
            almacen_layout.getRowConstraints().add(row);
        }
        for (int j = 0; j < almacen.getAlto() - 1; j++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPrefWidth(20);
            almacen_layout.getColumnConstraints().add(col);
        }

        //Racks aleatorio para test
        GestorAlmacen.generarRacksAletorios(almacen);
        Point puntoInicio = new Point(0,0);

        //Productos aleatorios para test
        int numProductos = 50;
        ArrayList<Producto> productos = GestorProducto.generarProductos(almacen, numProductos);

        GestorAlmacen.llenarConProdYPtoPartida(almacen, productos, puntoInicio);

        //Imprime el almacen
        this.imprimirAlmacen();

        //Puntos de interes
        GestorAlmacen.generarNodos(almacen);


        //Calcula distancias y camino inicial
        this.gestorDistancias = new GestorDistancias(almacen);

        //Generar lista & hash de nodos
        this.gestorDistancias.generarNodos();

        //Asignar vecinos a cada nodo
        this.gestorDistancias.asignarVecinosANodos();

        //this.gestorDistancias.calcularDistancias();

        almacen_layout.setGridLinesVisible(true);
    }

    private void initFields(){
        this.numeroIteraciones.setText("100000");
        this.tiempoMaximoTF.setText("300");
        this.listaTabuTamanho.setText("30");
        this.numIteracionesSinMejora.setText("100000");
    }

    @FXML
    private void verHistorialModal(){
        this.envio = new Envio();
        this.envio.setIdEnvio((long)1);
        this.verHistorial.setEnvio(this.envio);
        this.stageManager.mostrarModal(AlmacenView.HISTORIAL_RUTAS);
    }

}