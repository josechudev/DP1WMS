package com.dp1wms.model;

public class Lote {

    private int idLote;

    private int idProducto;

    private int fechaLote;

    private int fechaEntrada;


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

    public int getFechaLote() {
        return fechaLote;
    }

    public void setFechaLote(int fechaLote) {
        this.fechaLote = fechaLote;
    }

    public int getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(int fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
}
