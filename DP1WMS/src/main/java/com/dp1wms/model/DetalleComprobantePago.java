package com.dp1wms.model;

public class DetalleComprobantePago {
    private Long idDetalleComprobante;
    private Long idComprobante;
    private Long idProducto;
    private String nombreProducto;
    private String codigoProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double descuento;
    private Double subtotal;
    private Integer indiceTabla;


    public Long getIdDetalleComprobante() {
        return idDetalleComprobante;
    }

    public void setIdDetalleComprobante(Long idDetalleComprobante) {
        this.idDetalleComprobante = idDetalleComprobante;
    }

    public Long getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Long idComprobante) {
        this.idComprobante = idComprobante;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getIndiceTabla() {
        return indiceTabla;
    }

    public void setIndiceTabla(Integer indiceTabla) {
        this.indiceTabla = indiceTabla;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
}
