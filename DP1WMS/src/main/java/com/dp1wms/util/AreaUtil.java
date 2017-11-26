package com.dp1wms.util;

import com.dp1wms.model.Area;

import javafx.geometry.Point2D;

public class AreaUtil {

    public static int getLargo(Area area){
        Point2D pIni = area.getPosicionInicial();
        Point2D pFin = area.getPosicionFinal();
        return (int) pFin.getX() - (int) pIni.getX() + 1;
    }

    public static int getAncho(Area area){
        Point2D pIni = area.getPosicionInicial();
        Point2D pFin = area.getPosicionFinal();
        return (int) pFin.getY() - (int) pIni.getY() + 1;
    }
}
