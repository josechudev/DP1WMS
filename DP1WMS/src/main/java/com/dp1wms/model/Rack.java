package com.dp1wms.model;

import javafx.geometry.Point2D;
import org.postgresql.geometric.PGpoint;
import java.util.ArrayList;
import java.util.List;

public class Rack {

    private int idRack;
    private int idArea;
    private int idAlmacen;
    private Point2D posicionInicial;
    private Point2D posicionFinal;
    private int altura;
    private int longitudCajon;
    private List<Cajon> listaCajones = new ArrayList<Cajon>();
    private String codigo;

    public Rack(){

    }

    public int getIdRack() {
        return idRack;
    }

    public void setIdRack(int idRack) {
        this.idRack = idRack;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
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

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLongitudCajon() {
        return longitudCajon;
    }

    public void setLongitudCajon(int longitudCajon) {
        this.longitudCajon = longitudCajon;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Cajon> getListaCajones() {
        return listaCajones;
    }

    public void setListaCajones(List<Cajon> listaCajones) {
        this.listaCajones = listaCajones;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }
}
