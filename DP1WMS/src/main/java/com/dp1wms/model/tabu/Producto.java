package com.dp1wms.model.tabu;

import java.awt.*;

public class Producto {
    private int idAlgoritmo;
    private String descripcion;
    private Point posicion;
    private Rack rack;

    public Producto(int idAlgoritmo, String descripciton, Point posicion){
        this.idAlgoritmo = idAlgoritmo;
        this.setDescripcion(descripciton);
        this.setPosicion(new Point(posicion.x, posicion.y));
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = new Point(posicion.x, posicion.y);
    }
    public void setPosicion(int x, int y){
        this.posicion = new Point(x,y);
    }

    public int getIdAlgoritmo(){
        return this.idAlgoritmo;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }
}
