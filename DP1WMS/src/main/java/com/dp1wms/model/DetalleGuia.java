package com.dp1wms.model;

public class DetalleGuia {

    private Long idDetalleGuia;
    private Long idGuiaRemision;
    private int idProducto;
    private Integer cantidad;
    private Long idEstado;
    private Float peso;

    public Long getIdDetalleGuia() {
        return idDetalleGuia;
    }

    public void setIdDetalleGuia(Long idDetalleGuia) {
        this.idDetalleGuia = idDetalleGuia;
    }

    public Long getIdGuiaRemision() {
        return idGuiaRemision;
    }

    public void setIdGuiaRemision(Long idGuiaRemision) {
        this.idGuiaRemision = idGuiaRemision;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }
}
