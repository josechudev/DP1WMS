package com.dp1wms.model;

public interface Detalle {

    Producto getProducto();

    int getCantidad();

    void setDescuento(float descuento);

    float getDescuento();
}
