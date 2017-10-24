package com.dp1wms.model;

public class DetalleProforma {
    private int idDetalleProforma;
    private int idProforma;
    private int cantidad;
    private float descuento;
    private Producto producto;

    private float subTotal;


    public int getIdDetalleProforma() {
        return idDetalleProforma;
    }

    public void setIdDetalleProforma(int idDetalleProforma) {
        this.idDetalleProforma = idDetalleProforma;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return this.producto.getPrecio();
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
}
