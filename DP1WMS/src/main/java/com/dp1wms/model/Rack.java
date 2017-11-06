package com.dp1wms.model;

import java.util.ArrayList;
import java.util.List;

public class Rack {

    private int idRack;
    private String codigo;
    private List<Cajon> listaCajones = new ArrayList<Cajon>();
    private int idArea;


    public int getIdRack() {
        return idRack;
    }

    public void setIdRack(int idRack) {
        this.idRack = idRack;
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

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
}
