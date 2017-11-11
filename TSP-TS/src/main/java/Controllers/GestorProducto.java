package Controllers;

import Models.Almacen;
import Models.Producto;
import Models.Rack;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class GestorProducto {

    public static ArrayList<Producto> generarProductos(Almacen almacen, int numProd, Point puntoPartida){

        Random randomNumRack = new Random();

        ArrayList<Producto> productos = new ArrayList<Producto>();
        ArrayList<Rack> racks = almacen.getRacks();

        //Simular punto de partida
        productos.add(new Producto(0, "Punto de Partida", puntoPartida));

        //Generar productos aleatorios
        for(int i = 1; i < numProd+1; i++){
            //escoger un rack al azar
            Rack rack = racks.get(randomNumRack.nextInt(racks.size()));
            Point p = rack.obtenerPosRandAlBorder();
            Producto prod = new Producto(i,"Producto " + String.valueOf(i), p);
            productos.add(prod);
        }

        return productos;
    }

    public static void guardarProductosCSV(String nombre_archivo){

    }

    public static ArrayList<Producto> leerProductosCSV(String nombre_archivo){
        ArrayList<Producto> productos = new ArrayList<Producto>();

        return productos;
    }

    public static int[][] obtenerMatrizDistancia(ArrayList<Producto> productos){
        int[][] distancias = new int[productos.size()][productos.size()];

        for(int i = 0; i < productos.size(); i++){
            distancias[i][i] = 0;
        }
        for(int i = 0; i < productos.size(); i++){
            for(int j = i + 1; j < productos.size(); j++){
                Producto prodA = productos.get(i);
                Producto prodB = productos.get(j);
                int dist = distanciaEntre(prodA, prodB);
                distancias[i][j] = dist;
                distancias[j][i] = dist;
            }
        }

        return distancias;
    }

    public static void imprimirCaminoProductos(ArrayList<Producto> productos, int[]camino ){
        for(int i = 0; i < camino.length; i++){
            int productoId = camino[i];
            Producto prod = buscarProductoPorId(productos, productoId);
            System.out.printf("(%3d,%3d) %s\n",prod.getPosicion().x, prod.getPosicion().y, prod.getDescripcion() );
        }
    }

    private static Producto buscarProductoPorId(ArrayList<Producto> productos, int id){
        for(Producto p: productos){
            if(p.getIdAlgoritmo() == id){
                return p;
            }
        }
        return null;
    }

    public static int distanciaEntre(Producto prodA, Producto prodB){
        Point posA = prodA.getPosicion();
        Point posB = prodB.getPosicion();
        return abs(posA.x - posB.x) + abs(posA.y - posB.y);
    }
}
