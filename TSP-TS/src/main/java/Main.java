import Controllers.GestorAlmacen;
import Controllers.GestorDistancias;
import Controllers.GestorImpresion;
import Controllers.GestorProducto;
import Models.Almacen;
import Models.GeneradorAlmacen;
import Models.Producto;
import Tabu.*;
import Utils.ExpNumerica;
import Utils.NearestInsertion;
import Utils.NearestNeighbor;
import Utils.NearestNeighborNoCyclic;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static Random random = new Random();



    public static void main(String[] args) {

        Almacen alm = new Almacen(20,20);
        GestorAlmacen.generarRacksAletorios(alm);
        Point puntoInicio = new Point(0,0);

        int numProductos = 5;
        ArrayList<Producto> productos = GestorProducto.generarProductos(alm, numProductos, puntoInicio);

        GestorAlmacen.llenarConProdYPtoPartida(alm, productos);


        //almacen con productos en racks
        alm.imprimirAlmacen();


        GestorAlmacen.generarNodos(alm);



        GestorDistancias dist = new GestorDistancias(alm);
        dist.calcularDistancias();
        int[][] distanciasVert = dist.getMatrizDistancia();

        int[] prodEnMatrizDistancia = dist.obtenerProdsId(productos);
        NearestNeighborNoCyclic nnnc = new NearestNeighborNoCyclic(distanciasVert, prodEnMatrizDistancia);
        nnnc.generar();
        int[][] matDistancias = nnnc.obtenerMatrizNodos();
        int[][] distancias = dist.obtenerDistanciasProductos(prodEnMatrizDistancia, matDistancias, productos);

        NearestNeighbor nn = new NearestNeighbor(distancias, 0);
        nn.generar();
        int[] caminoInicial = nn.obtenerSolucion();

        for (int i = 0; i < caminoInicial.length; i++) {
            System.out.print(caminoInicial[i]);
            System.out.print(" ");
        }

        Tabu tabu = new Tabu(distancias, caminoInicial);
        int[] solucion = tabu.generarCamino(1000, 1000, 5, 5);

        for (int i = 0; i < solucion.length ; i++) {
            System.out.println(solucion[i]);
        }
    }

}
