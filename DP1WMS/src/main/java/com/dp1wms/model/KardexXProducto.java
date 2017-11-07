package com.dp1wms.model;

public class KardexXProducto {
    private String nombre;
    private String descripcion;
    private int cIngresos;
    private int cSalidas;
    private float precioVenta;
    private float precioCompra;
    private float valorTotalV;
    private float valorTotalC;
    private boolean ingreso;
    public KardexXProducto(){ }

    public KardexXProducto(String nombre, String descripcion, int cIngresos, int cSalidas, float precioVenta, float precioCompra, float valorTotalV, float valorTotalC, boolean ingreso) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cIngresos = cIngresos;
        this.cSalidas = cSalidas;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.valorTotalV = valorTotalV;
        this.valorTotalC = valorTotalC;
        this.ingreso = ingreso;
    }

    public int getcIngresos() {
        return cIngresos;
    }

    public void setcIngresos(int cIngresos) {
        this.cIngresos = cIngresos;
    }

    public int getcSalidas() {
        return cSalidas;
    }

    public void setcSalidas(int cSalidas) {
        this.cSalidas = cSalidas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getValorTotalV() {
        return valorTotalV;
    }

    public void setValorTotalV(float valorTotalV) {
        this.valorTotalV = valorTotalV;
    }

    public float getValorTotalC() {
        return valorTotalC;
    }

    public void setValorTotalC(float valorTotalC) {
        this.valorTotalC = valorTotalC;
    }

    public boolean isIngreso() {
        return ingreso;
    }

    public void setIngreso(boolean ingreso) {
        this.ingreso = ingreso;
    }
}
