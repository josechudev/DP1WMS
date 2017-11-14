package com.dp1wms.controller.Tabu;

import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Producto;
import com.dp1wms.model.tabu.Rack;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GestorProducto {

    public static ArrayList<Producto> generarProductos(Almacen almacen, int numProd){

        Random randomNumRack = new Random();

        ArrayList<Producto> productos = new ArrayList<Producto>();
        ArrayList<Rack> racks = almacen.getRacks();

        //Generar productos aleatorios
        for(int i = 1; i < numProd+1; i++){
            //escoger un rack al azar
            Rack rack = racks.get(randomNumRack.nextInt(racks.size()));
            Point p = rack.obtenerPosRandAlBorder();
            Producto prod = new Producto(i,"Producto " + String.valueOf(i), p);
            prod.setRack(rack);
            productos.add(prod);
        }

        return productos;
    }


}
