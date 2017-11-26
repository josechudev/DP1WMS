package com.dp1wms.model;

import java.awt.*;

public class Ubicacion {

    private int idAlmacen;
    private String nombreAlmacen;
    private int idArea;
    private String codigoArea;
    private int idRack;
    private String codigoRack;
    private int idCajon;
    private int cajonPosicionX;
    private int CajonPosicionY;
    private int idLote;
    private int idProducto;
    private int cantidad;
    private Integer indiceTabla;
    private String nombreProducto;


    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public int getIdRack() {
        return idRack;
    }

    public void setIdRack(int idRack) {
        this.idRack = idRack;
    }

    public String getCodigoRack() {
        return codigoRack;
    }

    public void setCodigoRack(String codigoRack) {
        this.codigoRack = codigoRack;
    }

    public int getIdCajon() {
        return idCajon;
    }

    public void setIdCajon(int idCajon) {
        this.idCajon = idCajon;
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

    public int getCajonPosicionX() {
        return cajonPosicionX;
    }

    public void setCajonPosicionX(int cajonPosicionX) {
        this.cajonPosicionX = cajonPosicionX;
    }

    public int getCajonPosicionY() {
        return CajonPosicionY;
    }

    public void setCajonPosicionY(int cajonPosicionY) {
        CajonPosicionY = cajonPosicionY;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public Integer getIndiceTabla() {
        return indiceTabla;
    }

    public void setIndiceTabla(Integer indiceTabla) {
        this.indiceTabla = indiceTabla;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
