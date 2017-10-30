package com.dp1wms.model;

public class DetallePedido implements Detalle{
    private int idDetallePedido;
    //private int idProducto;
    private Producto producto;
    private int cantidad;
    //private float precioUnitario;
    private float descuento;
    private float subtotal;

    public String getCodigoProducto(){
        return this.producto.getCodigo();
    }

    public int getIdProducto(){
        return this.producto.getIdProducto();
    }

    public float getPrecioUnitario(){
        return this.producto.getPrecio();
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}
