package com.dp1wms.controller.Tabu;

import com.dp1wms.controller.FxmlController;

import com.dp1wms.dao.RepositoryAlmacen;
import com.dp1wms.dao.RepositoryEnvio;
import com.dp1wms.model.Cajon;
import com.dp1wms.model.Envio;
import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Nodo;
import com.dp1wms.model.tabu.Producto;
import com.dp1wms.model.tabu.Rack;
import com.dp1wms.tabu.Tabu;
import com.dp1wms.view.StageManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlmacenRutaController implements FxmlController {

    @FXML GridPane almacen_layout;

    private StageManager stageManager;

    private Almacen almacen;
    private GestorDistancias gestorDistancias;

    @FXML private TextField numeroIteraciones;
    @FXML private TextField tiempoMaximoTF;
    @FXML private TextField listaTabuTamanho;
    @FXML private TextField numIteracionesSinMejora;
    @FXML private ComboBox ids_envios;

    private ArrayList<Node> nodes;

    @Autowired
    private RepositoryAlmacen repositoryAlmacen;
    private RepositoryEnvio repositoryEnvio;


    @Autowired
    @Lazy
    public AlmacenRutaController(StageManager stageManager) {
        this.stageManager = stageManager;
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

        //matriz distancia calculada y camino inicial
        int[][] distancias = this.gestorDistancias.getMatrizDistancia();
        int[] caminoInicial = this.gestorDistancias.generarCaminoInicial();

        //Imprime camino inicial
        for (int i = 0; i < caminoInicial.length ; i++) {
            System.out.print(caminoInicial[i]);
            System.out.print(" - ");
        }
        System.out.println();System.out.println();

        Thread thread = new Thread(()->{
            //Ejecutar tabu
            Tabu tabu = new Tabu(distancias, caminoInicial);
            int[] solucion = tabu.generarCamino(numIter, numIterSinMejora, tabuTamanho,tabuPermanencia, tiempoMaximo);
             Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    //Imprimie Almacen
                    imprimirAlmacen();
                    //Obtiene los puntos con la solucion
                    ArrayList<Nodo> solucionNodo = gestorDistancias.convertirIdANodos(solucion);
                    //Imprime la solucion consola
                    for (int i = 0; i < solucion.length ; i++) {
                        System.out.print(solucion[i]);
                        System.out.print(" - ");
                    }
                    //Tiempo
                    System.out.println("\nTiempo Tabu: " + String.valueOf(tabu.getDuracion()));

                    //Dibuja la solucion en pantalla
                    imprimirSolucion(solucionNodo);
                }
            });
        });
       thread.start();
    }

    //al seleccionar envio deberia imprimir en el almacen solo los cajones a visitar
    @FXML void seleccion_envios(ActionEvent e){
        Long idenvio = Long.parseLong(ids_envios.getValue().toString());
        List<Cajon> cajones = repositoryAlmacen.obtenerCajones(idenvio);
        this.ubicarProductos(cajones);

        System.out.println(cajones.get(0).getIdRack());
        System.out.println(cajones.get(0).getPosX());
        this.imprimirAlmacen();
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
                    System.out.println("imprimiendo products");
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

    private void limpiarproductos(){
        boolean [][] productos = new boolean [almacen.getAncho()][almacen.getAlto()];
        for (int i = 0; i < almacen.getAncho(); i++) {
            for (int j = 0; j < almacen.getAlto(); j++) {
                productos[i][j] = false;
            }
        }
        almacen.setProductos(productos);
    }

    //luego de que obtengo una lista de cajones a visitar los ubico en el almacen
    private void ubicarProductos(List<Cajon> cajones){
        this.limpiarproductos();

        Rack rack;
        int rack_id;
        boolean [][] productos = new boolean[almacen.getAncho()][almacen.getAlto()];

        for (int i = 0; i < almacen.getAncho(); i++) {
            for (int j = 0; j < almacen.getAncho(); j++) {
                productos[i][j] = false;
            }
        }

        List <Producto> lista_productos = new ArrayList<>();

        int i = 0;
        for (Cajon cajon: cajones) {

            i++;
            //posicion del rack
            rack = this.almacen.getRackporId(cajon.getIdRack());

            productos[rack.getPosIni().x + cajon.getPosX()][rack.getPosIni().y] = true;

            Point p = new Point(rack.getPosIni().x + cajon.getPosX(),rack.getPosIni().y);
            Producto prod = new Producto(i, "Producto" + String.valueOf(i), p);
            prod.setRack(rack);
            lista_productos.add(prod);
            
        }

        //productos set
        System.out.println("procuctos set");
        almacen.setProductos(productos);
    }


    @Override
    public void initialize() {
        this.initFields();
        this.nodes = new ArrayList<>();

        this.almacen = repositoryAlmacen.obtenerAlmacen();

        //agregar filas
        for (int i = 0; i < almacen.getAlto() - 1; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(20);
            almacen_layout.getRowConstraints().add(row);
        }

        //agregar columnas
        for (int j = 0; j < almacen.getAncho() - 1; j++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPrefWidth(20);
            almacen_layout.getColumnConstraints().add(col);
        }

//        Point puntoInicio = new Point(0,0);

        //agregar racks
        almacen.setRacks(new ArrayList<>(repositoryAlmacen.obtenerRacks()));
        almacen.agregar_racks();


        //llenar combo box con lista de envios
        List<Envio> envios = repositoryAlmacen.obtenerEnvios();
        System.out.println(envios.size());
        ArrayList<Long> ids = new ArrayList<Long>();
        for (Envio envio: envios) {
            ids.add(envio.getIdEnvio());
        }
        ObservableList<Long> envios_combo= FXCollections.observableArrayList(ids);
        ids_envios.setItems(envios_combo);




        //imprimir almacen
        this.imprimirAlmacen();



//        //Puntos de interes
//        GestorAlmacen.generarNodos(almacen);
//
//        //Calcula distancias y camino inicial
//        this.gestorDistancias = new GestorDistancias(almacen);
//        this.gestorDistancias.calcularDistancias();
//
//        almacen_layout.setGridLinesVisible(true);

    }

    private void initFields(){
        this.numeroIteraciones.setText("100000");
        this.tiempoMaximoTF.setText("300");
        this.listaTabuTamanho.setText("30");
        this.numIteracionesSinMejora.setText("100000");
    }
}