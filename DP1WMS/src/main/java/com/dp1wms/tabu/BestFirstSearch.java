package com.dp1wms.tabu;

import com.dp1wms.controller.Tabu.GestorDistancias;
import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Nodo;

import java.awt.*;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class BestFirstSearch {

    private GestorDistancias gestorDistancias;
    private Almacen almacen;

    private ArrayList<Nodo> productos;

    private ArrayList<Segmento> segmentos;


    public BestFirstSearch(Almacen almacen, GestorDistancias gestorDistancias){
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
     *  Nodo BFS
     */
    private class NodoBFS implements Comparable<NodoBFS> {
        public int heuristic;
        public Nodo valor;

        public NodoBFS anterior;

        public NodoBFS(Nodo valor, int heuristic){
            this.valor = valor;
            this.heuristic  = heuristic;
            this.anterior = null;
        }

        @Override
        public int compareTo(NodoBFS o) {
            if(this.heuristic < o.heuristic) return -1;
            if(this.heuristic > o.heuristic) return 1;
            return 0;
        }
        @Override
        public boolean equals(Object obj){
            if(obj instanceof NodoBFS){
                NodoBFS nodoBFS = (NodoBFS) obj;
                return valor.equals(nodoBFS.valor);
            }
            return false;
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
        Segmento segmento = new Segmento();
        ArrayList<NodoBFS> queue = new ArrayList<>();
        HashSet<Nodo> visitados = new HashSet<>();
        queue.add(new NodoBFS(nodoA, 0));

        NodoBFS evaluationNode = null;
        while(!queue.isEmpty()){
            //el nodo con menos prioridad
            evaluationNode = queue.get(0); queue.remove(0);
            if(evaluationNode.valor.equals(nodoB)){
                break;
            }
            visitados.add(evaluationNode.valor);
            for(Nodo vecino: evaluationNode.valor.getVecinos()){
                NodoBFS vecinoBFS = new NodoBFS(vecino, 0);
                if(!visitados.contains(vecino) && !queue.contains(vecinoBFS)){
                    vecinoBFS.anterior = evaluationNode;
                    visitados.add(vecino);
                    queue.add(vecinoBFS);
                }
            }
        }
        if(evaluationNode != null){ //se encontró solucion
            while(evaluationNode != null){
                Nodo nodo = evaluationNode.valor;
                segmento.camino.add(0, nodo);
                evaluationNode = evaluationNode.anterior;
            }
        } else{
            System.err.println("GG");
        }
        queue.clear();
        visitados.clear();
        segmento.calcularDistancia();
        return segmento;
    }


    private Segmento crearSegmentoEntreProductos2(Nodo nodoA, Nodo nodoB){
        Segmento segmento = new Segmento();
        ArrayList<NodoBFS> queue = new ArrayList<>();
        HashSet<Nodo> visitados = new HashSet<>();
        queue.add(new NodoBFS(nodoA, 0));

        NodoBFS evaluationNode = null;
        while(!queue.isEmpty()){
            //el nodo con menos prioridad
            queue.sort((a,b)->{
                return a.heuristic - b.heuristic;
            });
            evaluationNode = queue.get(0); queue.remove(0);
            if(evaluationNode.valor.equals(nodoB)){//encontró el camino
                break;
            }
            visitados.add(evaluationNode.valor);
            for(Nodo vecino: evaluationNode.valor.getVecinos()){
                NodoBFS vecinoBFS = new NodoBFS(vecino, segmento.distanciaEntreNodos(vecino, nodoB));
                if(!visitados.contains(vecino) && !queue.contains(vecinoBFS)){
                    vecinoBFS.anterior = evaluationNode;
                    visitados.add(vecino);
                    queue.add(vecinoBFS);
                }
            }
        }
        if(evaluationNode != null){ //se encontró solucion
            while(evaluationNode != null){
                Nodo nodo = evaluationNode.valor;
                segmento.camino.add(0, nodo);
                evaluationNode = evaluationNode.anterior;
            }
        }
        queue.clear();
        visitados.clear();
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
                if(segmento.camino.get(0).equals(nodoA)){
                    for(Nodo nodo: segmento.camino){
                        nodos.add(nodo);
                    }
                } else {
                    for (int j = segmento.camino.size() -1; j>= 0 ; j--) {
                        nodos.add(segmento.camino.get(j));
                    }
                }
                primero = false;
            } else {
                if(segmento.camino.get(0).equals(nodoA)){
                    for (int j = 1; j < segmento.camino.size() ; j++) {
                        nodos.add(segmento.camino.get(j));
                    }
                } else {
                    for (int j = segmento.camino.size() -2; j >=0 ; j--) {
                        nodos.add(segmento.camino.get(j));
                    }
                }
            }
        }
        return nodos;
    }
}
