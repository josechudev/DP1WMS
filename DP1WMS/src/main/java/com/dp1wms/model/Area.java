package com.dp1wms.model;

import javafx.geometry.Point2D;
import org.postgresql.geometric.PGpoint;

public class Area {

    private int idArea;
    private int idAlmacen;
    private Point2D posicionInicial;
    private Point2D posicionFinal;
    private String codigo;

    public Area() {}

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Point2D getPosicionInicial() {
        return posicionInicial;
    }

    public void setPosicionInicial(Point2D posicionInicial) {
        this.posicionInicial = posicionInicial;
    }

    public void setPosicionInicial(PGpoint posicionInicial) {
        this.posicionInicial = new Point2D(posicionInicial.x, posicionInicial.y);
    }

    public Point2D getPosicionFinal() {
        return posicionFinal;
    }

    public void setPosicionFinal(Point2D posicionFinal) {
        this.posicionFinal = posicionFinal;
    }

    public void setPosicionFinal(PGpoint posicionFinal) {
        this.posicionFinal = new Point2D(posicionFinal.x, posicionFinal.y);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Point2D getVertSupIzq(){
        return this.posicionInicial;
    }

    public Point2D getVertSupDer(){
        return new Point2D(this.posicionFinal.getX(), this.posicionInicial.getY());
    }

    public Point2D getVertInfIzq(){
        return new Point2D(this.posicionInicial.getX(), this.posicionFinal.getY());
    }

    public Point2D getVertInfDer(){
        return this.getPosicionFinal();
    }
}
