package com.dp1wms.tabu;

import com.dp1wms.controller.Tabu.GestorAlmacen;
import com.dp1wms.controller.Tabu.GestorDistancias;
import com.dp1wms.controller.Tabu.GestorProducto;
import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Producto;

import java.awt.*;
import java.util.ArrayList;

public class Test {

    public static void run(){
        //Almacen random para las pruebas
        Almacen alm = new Almacen(20,20);
        //Convertir lista de racks a matriz boolean[][]
        GestorAlmacen.generarRacksAletorios(alm);

        Point puntoInicio = new Point(0,0);

        //Productos aleatorios
        int numProductos = 5;
        ArrayList<Producto> productos = GestorProducto.generarProductos(alm, numProductos);


        //puntos de interes inicial
        GestorAlmacen.llenarConProdYPtoPartida(alm, productos, puntoInicio);

        //Puntos de interés
        GestorAlmacen.generarNodos(alm);

        //imprime almacen y rack
        alm.imprimirAlmacen();

        //Calcula distancias y camino inicial
        GestorDistancias gestorDistancias = new GestorDistancias(alm);
        gestorDistancias.calcularDistancias();
        int[][] distancias = gestorDistancias.getMatrizDistancia();
        int[] caminoInicial = gestorDistancias.generarCaminoInicial();

        for (int i = 0; i < caminoInicial.length ; i++) {
            System.out.print(caminoInicial[i]);
            System.out.print(" - ");
        }
        System.out.println();System.out.println();

        //Ejecutar tabu
        Tabu tabu = new Tabu(distancias, caminoInicial);
        int[] solucion = tabu.generarCamino(1000, 1000,
                5, 5, 999999999);

        //Imprime la solucion
        for (int i = 0; i < solucion.length ; i++) {
            System.out.print(solucion[i]);
            System.out.print(" - ");
        }
        System.out.println();System.out.println();
    }
}
