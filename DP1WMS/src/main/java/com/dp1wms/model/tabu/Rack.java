package com.dp1wms.model.tabu;

import java.awt.*;
import java.util.Random;

import static java.lang.StrictMath.abs;

public class Rack {

    private Point posIni;
    private Point posFin;
    private int niveles;
    private int id_rack;

    private static Random rand = new Random();

    public Rack (Point ini, Point fin){
        this.posIni = new Point(ini.x, ini.y);
        this.posFin = new Point(fin.x, fin.y);
        this.setNiveles(1); //por defecto - no se utilizará
    }

    public Point getPosIni(){
        return new Point(this.posIni.x, this.posIni.y);
    }

    public Point getPosFin(){
        return new Point(this.posFin.x, this.posFin.y);
    }

    public int getNiveles(){
        return niveles;
    }

    public boolean esVertical(){
        return posIni.x == posFin.x;
    }

    public boolean esHorizontal(){
        return posIni.x != posFin.x;
    }

    public int longitud(){
        if(this.esHorizontal()){
            return abs(posIni.y - posFin.y)+1;
        } else {
            return abs(posIni.x - posFin.x)+1;
        }
    }

    public Point obtenerPosRandAlBorder(){
        int pos = rand.nextInt(this.longitud());
        if(this.esHorizontal()){
            if(posIni.x < posFin.x){
                return new Point(posIni.x + pos, posIni.y);
            } else {
                return new Point(posFin.x + pos, posIni.y);
            }
        } else {
            if(posIni.y < posFin.y){
                return new Point(posIni.x, posIni.y + pos);
            } else {
                return new Point(posIni.x, posFin.y + pos);
            }
        }
    }

    public void imprimir(){
        String posIni = "(" + String.valueOf(this.posIni.x) + ", " + String.valueOf(this.posIni.y) + ")";
        String posFin = "(" + String.valueOf(this.posFin.x) + ", " + String.valueOf(this.posFin.y) + ")";
        System.out.println("INI: " + posIni + " FIN: " + posFin);
    }

    public void setNiveles(int niveles) {
        this.niveles = niveles;
    }

    public int getId_rack() {
        return id_rack;
    }

    public void setId_rack(int id_rack) {
        this.id_rack = id_rack;
    }
}
