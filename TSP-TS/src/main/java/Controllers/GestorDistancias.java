package Controllers;

import Models.Almacen;
import Models.Nodo;
import Models.Producto;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.abs;

public class GestorDistancias {
    private int [][] matrizDistancia;

    private Almacen almacen;

    private List<Nodo> nodos;

    private List<Point> posNodos;

    private int numId =0;

    private boolean[][] almacenAux;

    public GestorDistancias(Almacen almacen) {
        this.almacen = almacen;
        this.nodos = new ArrayList<Nodo>();
        this.posNodos = new ArrayList<Point>();
        almacenAux = almacen.getAlmacen();
    }

    public List<Point> getPosNodos() {
        return posNodos;
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
        //imprimirMatriz();
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

        //imprimirNodos();
        //System.out.println("\nMatriz de distancia\n");
    }

    public int obtenerNodoId(int i, int j){
        for(Nodo n: this.nodos){
            if (n.getX() == i && n.getY() == j){
                return n.getNumId();
            }
        }
        return -1;
    }

    public void imprimirMatriz() {
        for (int i = 0; i < matrizDistancia.length; i++) {
            for (int j = 0; j < matrizDistancia[i].length; j++) {
                System.out.print(matrizDistancia[i][j]+" ");
            }
            System.out.println();
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
        //System.out.println(posNodos.size());
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
                    //debug System.out.println(pos);
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
        //debug if (abs==0) System.out.print("Cero "+ nodoId + " " + pos + " \n");
        matrizDistancia[nodoId][pos] = abs;
        matrizDistancia[pos][nodoId] = abs;
    }


    private void imprimirNodos(){
        System.out.println("\nNodos \n");
        for (int i = 0; i < nodos.size(); i++) {
            System.out.print(nodos.get(i).getNumId()+ " ");
        }
        System.out.println("\n\nPosiciones \n");
        for (int i = 0; i <nodos.size() ; i++) {
            System.out.print("(" +posNodos.get(i).x+"," + posNodos.get(i).y+") ");
        }
    }

    private boolean estaEnAlmacen(int x, int y){
        return x >= 0 && y >= 0 && x < almacen.getAncho() && y < almacen.getAlto();
    }

    public int[] obtenerProdsId(ArrayList<Producto> productos){
        int[] prods = new int[productos.size()];

        int i = 0;
        for (Producto p: productos) {
            Nodo n = buscarNodoPorProducto(p);
            prods[i] = n.getNumId();
            i++;
        }
        return prods;
    }

    public int[][] obtenerDistanciasProductos(int[] prodEnMatrizDistancia, int[][] matDistancias,
                                              ArrayList<Producto> productos){
        int[][] distancias = new int[productos.size()][productos.size()];

        for(int i = 0; i < distancias.length; i++){
            distancias[i][i] = 0;
        }

        for(int i = 0; i < productos.size(); i++){
            for (int j = i+1; j < productos.size(); j++) {
                Producto pA = productos.get(i);
                Producto pB = productos.get(j);
                Nodo nodoPA = buscarNodoPorProducto(pA);
                Nodo nodoPB = buscarNodoPorProducto(pB);

                int indiceA = encontrarIndice(prodEnMatrizDistancia, nodoPA.getNumId());
                int indiceB = encontrarIndice(prodEnMatrizDistancia, nodoPB.getNumId());

                distancias[i][j] = matDistancias[indiceA][indiceB];
                distancias[j][i] = matDistancias[indiceB][indiceA];
            }
        }

        return distancias;
    }

    private int encontrarIndice(int[] array, int valor){
        for (int i = 0; i < array.length ; i++) {
            if(array[i] == valor)
                return i;
        }
        return -1;
    }

    private Nodo buscarNodoPorProducto(Producto p){
        for (Nodo n: this.nodos) {
            if(n.getX() == p.getPosicion().x && n.getY() == p.getPosicion().y){
                return n;
            }
        }
        return null;
    }
}
