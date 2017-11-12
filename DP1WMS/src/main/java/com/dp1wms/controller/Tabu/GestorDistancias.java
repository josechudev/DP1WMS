package com.dp1wms.controller.Tabu;

import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Nodo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class GestorDistancias {

    private int [][] matrizDistancia;

    private Almacen almacen;

    private List<Nodo> nodos;

    private List<Point> posNodos;

    private int numId =0;

    public GestorDistancias(Almacen almacen) {
        this.almacen = almacen;
        this.nodos = new ArrayList<Nodo>();
        this.posNodos = new ArrayList<Point>();
    }

    public int[][] getMatrizDistancia(){
        int[][] mat = new int[nodos.size()][nodos.size()];
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat.length; j++){
                mat[i][j] = matrizDistancia[i][j];
            }
        }
        return mat;
    }

    public int obtenerNumeroNodos(){
        return this.nodos.size();
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
        obtenerNodos();
        generarDistancias();
        return this.matrizDistancia;
    }

    private void obtenerNodos() {
        //agrego a una lista los nodos a analizar
        boolean[][] almNodos = this.almacen.getNodos();
        for (int i = 0; i < almacen.getAlmacen().length; i++) {
            for (int j = 0; j < almacen.getAlmacen()[i].length; j++) {
                if(almNodos[i][j]){
                    nodos.add(new Nodo(i,j,numId));
                    posNodos.add(new Point(i,j));
                    numId++;
                }
            }
        }
    }


    private void generarDistancias(){

        matrizDistancia = new int[nodos.size()][nodos.size()];

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
            distanciaXpos(posNodos.get(i),nodos.get(i).getNumId());
            distanciaYpos(posNodos.get(i),nodos.get(i).getNumId());
            distanciaXneg(posNodos.get(i),nodos.get(i).getNumId());
            distanciaYneg(posNodos.get(i),nodos.get(i).getNumId());
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

}
