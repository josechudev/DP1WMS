package com.dp1wms.util;

import com.dp1wms.model.Rack;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class RackUtil {

    public static ArrayList<Point2D> getListaDePosicionesOcupadas(Rack rack){
        ArrayList<Point2D> posiciones = new ArrayList<>();

        if (tieneOrientacionHorizontal(rack)){
            int y = (int) rack.getPosicionInicial().getY();
            int x1 = (int) rack.getPosicionInicial().getX();
            int x2 = (int)rack.getPosicionFinal().getX();
            for (int i=x1; i<x2; i++){
                Point2D p = new Point2D(i, y);
                posiciones.add(p);
            }
        } else if (tieneOrientacionVertical(rack)){
            int x = (int) rack.getPosicionInicial().getX();
            int y1 = (int) rack.getPosicionInicial().getY();
            int y2 = (int)rack.getPosicionFinal().getY();
            for (int j= y1; j<y2; j++){
                Point2D p = new Point2D(x, j);
                posiciones.add(p);
            }
        }

        return posiciones;
    }

    public static boolean tieneOrientacionHorizontal(Rack rack){
        int y1 = (int) rack.getPosicionInicial().getY();
        int y2 = (int) rack.getPosicionFinal().getY();

        return y1 == y2;
    }

    public static boolean tieneOrientacionVertical(Rack rack){
        int x1 = (int) rack.getPosicionInicial().getX();
        int x2 = (int) rack.getPosicionFinal().getX();

        return x1 == x2;
    }
}
