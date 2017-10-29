package com.dp1wms.model;

public class Almacen {

    private int idAlmacen;
    private String direccion;
    private int largo;
    private int ancho;
    private String nombre;

    public Almacen() { }

    public Almacen(String nombre, int largo, int alto){
        this.nombre = nombre;
        this.largo = largo;
        this.ancho = alto;
    }


    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
