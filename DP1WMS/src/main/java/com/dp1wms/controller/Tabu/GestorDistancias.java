package com.dp1wms.controller.Tabu;

import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Nodo;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.abs;

public class GestorDistancias {

    private int [][] matrizDistancia;

    private Almacen almacen;

    private HashMap<String, Nodo> nodosHashMap;

    private List<Nodo> nodos;

    private List<Point> posNodos;

    private int numId =0;

    public GestorDistancias(Almacen almacen) {
        this.almacen = almacen;
        this.setNodos(new ArrayList<Nodo>());
        this.posNodos = new ArrayList<Point>();
        this.nodosHashMap = new HashMap<>();
    }

    public int[][] getMatrizDistancia(){
        int[][] mat = new int[getNodos().size()][getNodos().size()];
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat.length; j++){
                mat[i][j] = matrizDistancia[i][j];
            }
        }
        return mat;
    }

    public int obtenerNumeroNodos(){
        return this.getNodos().size();
    }

    public int [] generarCaminoInicial(){
        int [] camino = new int[this.obtenerNumeroNodos() + 1];

        for(int i = 0; i < this.obtenerNumeroNodos(); i++){
            camino[i] = i;
        }
        camino[camino.length - 1] = 0;
        return camino;
    }

    public int [][] calcularDistancias(){
        generarDistancias();
        return this.matrizDistancia;
    }

    public void generarNodos() {
        //agrego a una lista los nodos a analizar
        boolean[][] almNodos = this.almacen.getNodos();
        for (int i = 0; i < almacen.getAncho(); i++) {
            for (int j = 0; j < almacen.getAlto(); j++) {
                if(almNodos[i][j]){
                    Nodo nodo = new Nodo(i,j,numId);
                    getNodos().add(nodo);
                    posNodos.add(new Point(i,j));
                    nodosHashMap.put(i + "-" + j, nodo);
                    numId++;
                }
            }
        }
    }

    public ArrayList<Nodo> convertirIdANodos(int[] solucionID){
        ArrayList<Nodo> solucionNodo = new ArrayList<>();
        for(int i = 0; i < solucionID.length; i++){
            Nodo nodo = buscarNodoPorId(solucionID[i]);
            if(nodo != null){
                solucionNodo.add(nodo);
            }
        }
        return solucionNodo;
    }

    private Nodo buscarNodoPorId(int id){
        for(Nodo nodo: this.getNodos()){
            if(nodo.getNumId() == id){
                return nodo;
            }
        }
        return null;
    }


    private void generarDistancias(){

        matrizDistancia = new int[getNodos().size()][getNodos().size()];

        for (int i = 0; i < matrizDistancia.length; i++) {
            for (int j = 0; j < matrizDistancia[i].length; j++) {
                if(i==j)
                    matrizDistancia[i][j] =0;
                else
                    matrizDistancia[i][j] = almacen.getAlto()*almacen.getAncho()*almacen.getAlto()*almacen.getAncho();
            }
        }

        for (int i = 0; i < posNodos.size(); i++) {
            //calculo las distancias al nodo en cada direccion
            distanciaXpos(posNodos.get(i), getNodos().get(i).getNumId());
            distanciaYpos(posNodos.get(i), getNodos().get(i).getNumId());
            distanciaXneg(posNodos.get(i), getNodos().get(i).getNumId());
            distanciaYneg(posNodos.get(i), getNodos().get(i).getNumId());
        }

    }

    private void distanciaYneg(Point point, int nodoId) {
        int dis=1;
        for (int i = point.y-1; i >= 0 ; i--) {
            if(!estaEnAlmacen(point.x,i)) break;
            if(almacen.getAlmacen()[point.x][i])
                break;
            else{
                if(almacen.getNodos()[point.x][i]){
                    int pos = posNodos.indexOf(new Point(point.x,i));
                    agregarDistancia(nodoId,pos,abs(dis));
                    break;
                }
            }
            dis++;
        }
    }

    private void distanciaYpos(Point point,int nodoId) {
        int dis =1;
        for (int i = point.y+1; i < this.almacen.getAlto() ; i++) {
            if(!estaEnAlmacen(point.x,i)) break;
            if(almacen.getAlmacen()[point.x][i]==true)
                break;
            else{
                if(almacen.getNodos()[point.x][i]==true){
                    int pos = posNodos.indexOf(new Point(point.x,i));
                    agregarDistancia(nodoId,pos,abs(dis));
                    break;
                }
            }
            dis++;
        }

    }

    private void distanciaXneg(Point point,int nodoId) {
        int dis =1;
        for (int i = point.x-1; i >= 0 ; i--) {
            if(!estaEnAlmacen(i,point.y)) break;
            if(almacen.getAlmacen()[i][point.y]==true)
                break;
            else{
                if(almacen.getNodos()[i][point.y]==true){
                    int pos = posNodos.indexOf(new Point(i,point.y));
                    agregarDistancia(nodoId,pos,abs(dis));
                    break;
                }
            }
            dis++;
        }
    }

    private void distanciaXpos(Point point,int nodoId) {
        int dis = 1;
        for (int i = point.x+1; i < this.almacen.getAncho() ; i++) {
            if(!estaEnAlmacen(i,point.y)) break;
            if(almacen.getAlmacen()[i][point.y]==true)
                break;
            else{
                if(almacen.getNodos()[i][point.y]==true){
                    int pos = posNodos.indexOf(new Point(i,point.y));
                    agregarDistancia(nodoId,pos,abs(dis++));
                    break;
                }
            }
            dis++;
        }
    }

    private void agregarDistancia(int nodoId, int pos, int abs) {
        matrizDistancia[nodoId][pos] = abs;
        matrizDistancia[pos][nodoId] = abs;
    }

    private boolean estaEnAlmacen(int x, int y){
        return x >= 0 && y >= 0 && x < almacen.getAncho() && y < almacen.getAlto();
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(List<Nodo> nodos) {
        this.nodos = nodos;
    }

    public void asignarVecinosANodos(){
        for(Nodo nodo: this.nodos){
            ArrayList<Nodo> vecinos = buscarVecinoPorNodo(nodo);
            nodo.setVecinos(vecinos);
        }
    }

    private ArrayList<Nodo> buscarVecinoPorNodo(Nodo nodo){
        ArrayList<Nodo> vecinos = new ArrayList<>();
        boolean[][] nodos = this.almacen.getNodos();
        boolean[][] almacen = this.almacen.getAlmacen();
        //buscar en x-
        for(int i = nodo.x - 1; i >= 0; i--){
            if(almacen[i][nodo.y]) break;
            if(nodos[i][nodo.y]){
                vecinos.add(this.nodosHashMap.get(i + "-" + nodo.y));
                break;
            }
        }
        //buscar en x+
        for(int i = nodo.x + 1; i < this.almacen.getAncho(); i++){
            if(almacen[i][nodo.y]) break;
            if(nodos[i][nodo.y]){
                vecinos.add(this.nodosHashMap.get(i + "-" + nodo.y));
                break;
            }
        }
        //buscar en y-
        for(int j = nodo.y - 1; j >= 0; j--){
            if(almacen[nodo.x][j]) break;
            if(nodos[nodo.x][j]){
                vecinos.add(this.nodosHashMap.get(nodo.x + "-" + j));
                break;
            }
        }
        //buscar en y+
        for(int j = nodo.y + 1; j < this.almacen.getAlto(); j++){
            if(almacen[nodo.x][j]) break;
            if(nodos[nodo.x][j]){
                vecinos.add(this.nodosHashMap.get(nodo.x + "-" + j));
                break;
            }
        }
        return vecinos;
    }

    public HashMap<String, Nodo> getNodosHashMap() {
        return nodosHashMap;
    }

    public void setNodosHashMap(HashMap<String, Nodo> nodosHashMap) {
        this.nodosHashMap = nodosHashMap;
    }
}
