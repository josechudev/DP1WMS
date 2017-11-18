package com.dp1wms.controller.Tabu;

import com.dp1wms.controller.FxmlController;

import com.dp1wms.dao.RepositoryAlmacen;
import com.dp1wms.model.Cajon;
import com.dp1wms.model.Envio;
import com.dp1wms.dao.RepositoryRuta;
import com.dp1wms.model.Ruta;
import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Nodo;
import com.dp1wms.model.tabu.Producto;
import com.dp1wms.model.tabu.Rack;
import com.dp1wms.tabu.BFSAlgorithm;
import com.dp1wms.tabu.Tabu;
import com.dp1wms.view.AlmacenView;
import com.dp1wms.view.StageManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
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
import java.util.List;

@Component
public class AlmacenRutaController implements FxmlController {

    @FXML GridPane almacen_layout;

    private StageManager stageManager;
    private VerHistorial verHistorial;
    private TabuBuscarEnvioCtrl tabuBuscarEnvioCtrl;

    private Almacen almacen;
    private GestorDistancias gestorDistancias;

    @FXML private TextField numeroIteraciones;
    @FXML private TextField tiempoMaximoTF;
    @FXML private TextField listaTabuTamanho;
    @FXML private TextField numIteracionesSinMejora;
    @FXML private ListView lista_productos;

    @FXML private ComboBox<String> complementoCB;

    private ArrayList<Node> nodes;
    private ArrayList<Nodo> solucionTabu;
    private ArrayList<Producto> productos;
    private Envio envio;
    private Point puntoInicio = new Point(0,0);


    @Autowired
    private RepositoryAlmacen repositoryAlmacen;
    @Autowired
    private RepositoryRuta repositoryRuta;

    @Autowired
    @Lazy
    public AlmacenRutaController(StageManager stageManager,
                                 VerHistorial verHistorial,
                                 TabuBuscarEnvioCtrl tabuBuscarEnvioCtrl) {
        this.stageManager = stageManager;
        this.verHistorial = verHistorial;
        this.tabuBuscarEnvioCtrl = tabuBuscarEnvioCtrl;
    }

    @FXML
    private void limpiarGrilla(){
        this.imprimirAlmacen();
    }

    @FXML
    private void generarRuta(ActionEvent event){
        Integer tabuTamanho;
        Integer tabuPermanencia;
        Long numIter;
        Long numIterSinMejora;
        Long tiempoMaximo;

        if(this.envio == null){
            this.stageManager.mostrarErrorDialog("Generar Ruta", null,
                    "Debe seleccionar un envio");
            return;
        }
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

        String algorithm = this.complementoCB.getSelectionModel().getSelectedItem();


        Thread thread = new Thread(()->{
            //generar caminos entra productos
            BFSAlgorithm BFSAlgorithm = new BFSAlgorithm(this.almacen, this.gestorDistancias, algorithm);
            BFSAlgorithm.generarCaminosEntreProductos();

            //matriz distancia calculada y camino inicial
            int[][] distancias = BFSAlgorithm.generarMatrizDistancia();
            int[] caminoInicial = BFSAlgorithm.generarCaminoInicial();

            //Crear caminos entre productos y punto de partida

            //Imprime camino inicial
            /*for (int i = 0; i < caminoInicial.length ; i++) {
                System.out.print(caminoInicial[i]);
                System.out.print(" - ");
            }
            System.out.println();System.out.println();*/

            //Ejecutar tabu
            Tabu tabu = new Tabu(distancias, caminoInicial);
            int[] solucion = tabu.generarCamino(numIter, numIterSinMejora, tabuTamanho,tabuPermanencia, tiempoMaximo);
             Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    //Imprimie Almacen
                    imprimirAlmacen();
                    //Obtiene los puntos con la solucion
                    solucionTabu = BFSAlgorithm.convertirANodos(solucion);
                    /*//Imprime la solucion consola
                    for (int i = 0; i < solucion.length ; i++) {
                        System.out.print(solucion[i]);
                        System.out.print(" - ");
                    }*/
                    //Tiempo
                    System.out.println("\nTiempo Tabu: " + String.valueOf(tabu.getDuracion()));

                    //Dibuja la solucion en pantalla
                    imprimirSolucion(solucionTabu);
                }
            });
        });
       thread.start();
    }



    //al seleccionar envio deberia imprimir en el almacen solo los cajones a visitar
    @FXML
    private void seleccionarEnvio(ActionEvent e){
        this.stageManager.mostrarModal(AlmacenView.BUSCAR_ENIVOS);
        Envio envio = this.tabuBuscarEnvioCtrl.getEnvio();
        if(envio == null){
            return;
        }

        this.envio = envio;
        Long idenvio = this.envio.getIdEnvio();



        ObservableList<String> nombres_productos = FXCollections.observableArrayList();
        List<String> nombres = repositoryAlmacen.obtenerNombresProductos(idenvio);

        for (String nombre:nombres) {
            nombres_productos.add(nombre);
        }
        lista_productos.setItems(nombres_productos);


        //Obtener una lista productos
        List<Cajon> cajones = repositoryAlmacen.obtenerCajones(idenvio);
        this.ubicarProductos(cajones);

        for (Cajon cajon: cajones) {
            System.out.println(cajon.getPosX());
            System.out.println(cajon.getPosY());
        }


//        for (Producto producto: productos) {
//            System.out.println(producto.getPosicion());
//        }
//        //lista_productos.setItems(nombres_productos);

        //Llenar matriz prod boolean con list prods
        GestorAlmacen.llenarConProdYPtoPartida(almacen, this.productos, this.puntoInicio);
        //Puntos de interes
        GestorAlmacen.generarNodos(almacen);

        //
        this.gestorDistancias = new GestorDistancias(almacen);
        //Generar lista & hash de nodos
        this.gestorDistancias.generarNodos();
        //Asignar vecinos a cada nodo
        this.gestorDistancias.asignarVecinosANodos();

        this.imprimirAlmacen();
    }




    @FXML
    private void guardarRuta(){

        if(this.envio == null){
            this.stageManager.mostrarErrorDialog("Generar Ruta", null,
                    "Debe seleccionar un envio");
            return;
        }

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

    @FXML
    private void verHistorialModal(){

        if(this.envio == null){
            this.stageManager.mostrarErrorDialog("Generar Ruta", null,
                    "Debe seleccionar un envio");
            return;
        }

        this.verHistorial.setEnvio(this.envio);
        this.stageManager.mostrarModal(AlmacenView.HISTORIAL_RUTAS);
        Ruta ruta = this.verHistorial.getRuta();
        if(ruta != null){
            this.dibujuarRuta(ruta);
        }
    }

    @Override
    public void initialize() {
        this.initFields();
        this.nodes = new ArrayList<>();
        this.envio = null;
        this.productos = null;

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


        //agregar racks
        almacen.setRacks(new ArrayList<>(repositoryAlmacen.obtenerRacks()));
        almacen.agregar_racks();


        //imprimir almacen
        this.imprimirAlmacen();

        almacen_layout.setGridLinesVisible(true);
    }

    private void initFields(){
        this.numeroIteraciones.setText("10000");
        this.tiempoMaximoTF.setText("60");
        this.listaTabuTamanho.setText("7");
        this.numIteracionesSinMejora.setText("10000");
        this.complementoCB.getItems().clear();
        this.complementoCB.getItems().add(BFSAlgorithm.BREATH_ALG);
        this.complementoCB.getItems().add(BFSAlgorithm.BEST_ALG);
        this.complementoCB.getSelectionModel().select(0);
    }

    private void dibujuarRuta(Ruta ruta){
        this.imprimirAlmacen();
        Integer[] camino_x = ruta.getCamino_x();
        Integer[] camino_y = ruta.getCamino_y();
        boolean[][] productos = this.almacen.getProductos();
        for(int i = 0; i < camino_x.length - 1; i++){
            Button punto = new Button();
            if(productos[camino_x[i]][camino_y[i]]){
                punto.setStyle("-fx-background-color: #95CC4C; -fx-padding: 0; -fx-margin: 0; -fx-border-radius: 0;" +
                        " -fx-font-size: 10");
            } else {
                punto.setStyle("-fx-background-color: #ffffff; -fx-padding: 0; -fx-margin: 0; -fx-border-radius: 0;" +
                        " -fx-font-size: 10");
            }
            punto.setPrefHeight(20);
            punto.setPrefWidth(20);
            punto.setText(String.valueOf(i));
            this.nodes.add(punto);
            almacen_layout.add(punto, camino_x[i], camino_y[i]);
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

       /*
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
        }*/

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
        boolean[][] productos = this.almacen.getProductos();
        for(int i = 0; i < solucion.size() - 1; i++){
            Nodo nodo = solucion.get(i);
            Button punto = new Button();

            if(productos[nodo.x][nodo.y]){
                punto.setStyle("-fx-background-color: #95CC4C; -fx-padding: 0; -fx-margin: 0; -fx-border-radius: 0;" +
                        " -fx-font-size: 10");
            } else {
                punto.setStyle("-fx-background-color: #ffffff; -fx-padding: 0; -fx-margin: 0; -fx-border-radius: 0;" +
                        " -fx-font-size: 10");
            }
            punto.setPrefHeight(20);
            punto.setPrefWidth(20);
            punto.setText(String.valueOf(i));
            this.nodes.add(punto);
            almacen_layout.add(punto, nodo.getX(), nodo.getY());
        }
    }

    private void limpiarproductos(){
        boolean [][] productos = this.almacen.getProductos();
        boolean [][] nodos = this.almacen.getNodos();
        for (int i = 0; i < almacen.getAncho(); i++) {
            for (int j = 0; j < almacen.getAlto(); j++) {
                productos[i][j] = false;
                nodos[i][j] = false;
            }
        }
        almacen.setProductos(productos);
        almacen.setNodos(nodos);
    }

    //luego de que obtengo una lista de cajones a visitar los ubico en el almacen
    private void ubicarProductos(List<Cajon> cajones){
        this.limpiarproductos();
        Rack rack;
        this.productos = new ArrayList<>();

        int i = 0;
        for (Cajon cajon: cajones) {

            i++;
            //posicion del rack
            rack = this.almacen.getRackporId(cajon.getIdRack());

            Point p = null;
            if(rack.esHorizontal()){
                p = new Point(rack.getPosIni().x + cajon.getPosX(),rack.getPosIni().y);
            } else {
                p = new Point(rack.getPosIni().x,rack.getPosIni().y + cajon.getPosX());
            }
            Producto prod = new Producto(i, "Producto" + String.valueOf(i), p);
            prod.setRack(rack);
            this.productos.add(prod);
        }
    }

}