package com.dp1wms.model;

public class ReporteAlmacen {
    private String codigo;
    private String nombreproducto;
    private String descripcion;
    private int stockMinimo;
    private int stockFisico;
    private float precioCompra;
    private int cantidadPedido;
    private int stockLogico;

    public ReporteAlmacen(String codigo, String nombreproducto, String descripcion, int stockMinimo, int stockFisico, float precioCompra, int cantidadPedido, int stockLogico) {
        this.codigo = codigo;
        this.nombreproducto = nombreproducto;
        this.descripcion = descripcion;
        this.stockMinimo = stockMinimo;
        this.stockFisico = stockFisico;
        this.precioCompra = precioCompra;
        this.cantidadPedido = cantidadPedido;
        this.stockLogico = stockLogico;
    }

    public ReporteAlmacen() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getStockFisico() {
        return stockFisico;
    }

    public void setStockFisico(int stockFisico) {
        this.stockFisico = stockFisico;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getCantidadPedido() {
        return cantidadPedido;
    }

    public void setCantidadPedido(int cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }

    public int getStockLogico() {
        return stockLogico;
    }

    public void setStockLogico(int stockLogico) {
        this.stockLogico = stockLogico;
    }
}
