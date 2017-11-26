package com.dp1wms.model;

public class Lote {

    private int idLote;

    private int idProducto;

    private int fechaLote;

    private String fechaEntrada;

    private String nombreProducto;

    private int stockParcial;

    private int indiceTableView;

    private String codigoProducto;

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

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }


    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }


    public int getStockParcial() {
        return stockParcial;
    }

    public void setStockParcial(int stockParcial) {
        this.stockParcial = stockParcial;
    }


    public int getIndiceTableView() {
        return indiceTableView;
    }

    public void setIndiceTableView(int indiceTableView) {
        this.indiceTableView = indiceTableView;
    }


    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
}
