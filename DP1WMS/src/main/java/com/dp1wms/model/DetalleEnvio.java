package com.dp1wms.model;

public class DetalleEnvio {
    private Long idDetalleEnvio;
    private Long idEnvio;
    private int idProducto;
    private int cantidad;

    private Producto producto;

    private int indiceTabla;
    private String codigoProducto;
    private String nombreProducto;
    private String loteAsociado; // para la vista de retiro de un envio
    private int idLote;
    private Double peso;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Long getIdDetalleEnvio() {
        return idDetalleEnvio;
    }

    public void setIdDetalleEnvio(Long idDetalleEnvio) {
        this.idDetalleEnvio = idDetalleEnvio;
    }

    public Long getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Long idEnvio) {
        this.idEnvio = idEnvio;
    }


    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }


    public int getIndiceTabla() {
        return indiceTabla;
    }

    public void setIndiceTabla(int indiceTabla) {
        this.indiceTabla = indiceTabla;
    }


    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getLoteAsociado() {
        return loteAsociado;
    }

    public void setLoteAsociado(String loteAsociado) {
        this.loteAsociado = loteAsociado;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }


    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
