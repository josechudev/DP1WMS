package com.dp1wms.tabu;

import com.dp1wms.controller.Tabu.GestorDistancias;
import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Nodo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NearestNeighborHelper {

    private GestorDistancias gestorDistancias;
    private Almacen almacen;

    private ArrayList<Nodo> productos;

    private ArrayList<Segmento> segmentos;


    public NearestNeighborHelper(Almacen almacen, GestorDistancias gestorDistancias){
        this.almacen = almacen;
        this.gestorDistancias = gestorDistancias;

        this.productos = new ArrayList<>();
        Point p = this.almacen.getPuntoInicio();
        boolean [][] prods = almacen.getProductos();
        for(Nodo nodo: this.gestorDistancias.getNodos()){
            int i = nodo.x;
            int j = nodo.y;
            if(prods[i][j]){
                this.productos.add(nodo);
            } else if(i == p.x && j == p.y){
                this.productos.add(nodo);
            }
        }
        this.segmentos = new ArrayList<>();
    }

    public void generarCaminosEntreProductos(){
        for(int i = 0; i < productos.size(); i ++){
            for(int j = i + 1; j < productos.size(); j++){
                this.limpiarFlagVisitado();
                //crear camino entre dos productos
                Nodo a = productos.get(i);
                Nodo b = productos.get(j);
                Segmento segmento = crearSegmentoEntreProductos(a,b);
                this.segmentos.add(segmento);
            }
        }
    }

    private void limpiarFlagVisitado(){
        for(Nodo nodo: gestorDistancias.getNodos()){
            nodo.visitado = false;
        }
    }

    /**
     * ------ Eje X +
     * |
     * |
     * |
     * Eje Y +
     */
    private Segmento crearSegmentoEntreProductos(Nodo nodoA, Nodo nodoB){
        /**
         * Se empieza siempre desde b
         * si hay dos o más iguales escoger el que se acerca al otro nodo (euclidiana)
         * si hay dos o más iguales escoger al azar (random)
         */
        Segmento segmento = new Segmento();
        segmento.camino.add(nodoA);
        nodoA.visitado = true;
        while(!segmento.camino.contains(nodoB)){
            Nodo ultimo = segmento.ultimoNodo();
            Nodo cercano = ultimo.getVecinos().get(0);
            int distancia_min = segmento.distanciaEntreNodos(cercano, ultimo);
            for(Nodo nodoVecino: ultimo.getVecinos()){
                int dist = segmento.distanciaEntreNodos(nodoVecino, ultimo);
                if(nodoVecino.visitado) continue;
                if(dist < distancia_min){
                    cercano = nodoVecino;
                } else if (dist == distancia_min){ //Random para generar algún otro camino :p
                    int r = (new Random()).nextInt(2);
                    if(r == 0){
                        cercano = nodoVecino;
                    }
                }
            }
            segmento.camino.add(cercano);
            System.err.println("Segmento: " + segmento.camino.size());
        }
        segmento.calcularDistancia();
        return segmento;
    }

    public int[][] generarMatrizDistancia(){
        int[][] distancias = new int[this.productos.size()][this.productos.size()];

        for(int i = 0; i < distancias.length; i++){
            for (int j = 0; j < distancias.length; j++) {
                if(i == j){
                    distancias[i][j] = 0;
                } else {
                    Nodo prodA = this.productos.get(i);
                    Nodo prodB = this.productos.get(j);
                    int d = this.buscarDistanciaEntreProd(prodA, prodB);
                    distancias[i][j] = d;
                    distancias[j][i] = d;
                }
            }
        }

        return distancias;
    }

    private int buscarDistanciaEntreProd(Nodo prodA, Nodo prodB){
        for(Segmento segmento: this.segmentos){
            Nodo primero = segmento.camino.get(0);
            Nodo ultimo = segmento.ultimoNodo();
            if(primero.equals(prodA) && ultimo.equals(prodB)){
                return segmento.distancia;
            }
            if(primero.equals(prodB) && ultimo.equals(prodA)){
                return segmento.distancia;
            }
        }
        return 0;
    }

    private Segmento buscarSegmento(Nodo prodA, Nodo prodB){
        for(Segmento segmento: this.segmentos){
            Nodo primero = segmento.camino.get(0);
            Nodo ultimo = segmento.ultimoNodo();
            if(primero.equals(prodA) && ultimo.equals(prodB)){
                return segmento;
            }
            if(primero.equals(prodB) && ultimo.equals(prodA)){
                return segmento;
            }
        }
        return null;
    }

    public int[] generarCaminoInicial(){
        int[] camino = new int[this.productos.size() + 1];
        for(int i = 0; i < this.productos.size(); i++){
            camino[i] = i;
        }
        camino[camino.length - 1] = 0;
        return camino;
    }

    public ArrayList<Nodo> convertirANodos(int[] solucion){
        ArrayList<Nodo> nodos = new ArrayList<>();
        boolean primero = true;
        for(int i = 0; i < solucion.length - 1; i++){
            Nodo nodoA = this.productos.get(solucion[i]);
            Nodo nodoB = this.productos.get(solucion[i + 1]);
            Segmento segmento = this.buscarSegmento(nodoA, nodoB);
            if(primero){
                System.err.println(segmento == null);
                System.err.println(solucion[0]);System.err.println(solucion[1]);
                for(Nodo nodo: segmento.camino){
                    nodos.add(nodo);
                }
                primero = false;
            } else {
                for (int j = 1; j < segmento.camino.size() ; j++) {
                    nodos.add(segmento.camino.get(j));
                }
            }
        }
        return nodos;
    }
}
