package com.dp1wms.model;

import javafx.geometry.Point2D;

public class Cajon {

    private int idCajon;
    private int idRack;
    private int posX;
    private int posY;
    private Point2D posicion;
    private int idLote;
    private int idProducto;
    private String nombreProducto;
    private String codigoRack;
    private int cantidad;

    public Cajon(){

    }

    public int getIdCajon() {
        return idCajon;
    }

    public void setIdCajon(int idCajon) {
        this.idCajon = idCajon;
    }

    public int getIdRack() {
        return idRack;
    }

    public void setIdRack(int idRack) {
        this.idRack = idRack;
    }

    public Point2D getPosicion() {
        return posicion;
    }

    public void setPosicion(Point2D posicion) {
        this.posicion = posicion;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }


    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }


    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCodigoRack() {
        return codigoRack;
    }

    public void setCodigoRack(String codigoRack) {
        this.codigoRack = codigoRack;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
