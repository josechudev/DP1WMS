package com.dp1wms.model;

public class DetalleComprobantePago {

    private Long v_id;
    private Long v_idComprobantePago;
    private Long v_idProducto;
    private String v_codigoProducto;
    private String v_nombreProducto;
    private Long v_cant;
    private float v_precioUnitario;
    private float v_descuento;
    private float v_subtotal;

    public DetalleComprobantePago(){

    }

    public DetalleComprobantePago(Long v_id,
                                  Long v_idComprobantePago,
                                  Long v_idProducto,
                                  String v_codigoProducto,
                                  String v_nombreProducto,
                                  Long v_cant,
                                  float v_precioUnitario,
                                  float v_descuento,
                                  float v_subtotal){
        this.setV_id(v_id);
        this.setV_idComprobantePago(v_idComprobantePago);
        this.setV_idProducto(v_idProducto);
        this.setV_codigoProducto(v_codigoProducto);
        this.setV_nombreProducto(v_nombreProducto);
        this.setV_cant(v_cant);
        this.setV_precioUnitario(v_precioUnitario);
        this.setV_descuento(v_descuento);
        this.setV_subtotal(v_subtotal);
    }


    public Long getV_id() {
        return v_id;
    }

    public void setV_id(Long v_id) {
        this.v_id = v_id;
    }

    public Long getV_idComprobantePago() {
        return v_idComprobantePago;
    }

    public void setV_idComprobantePago(Long v_idComprobantePago) {
        this.v_idComprobantePago = v_idComprobantePago;
    }

    public Long getV_idProducto() {
        return v_idProducto;
    }

    public void setV_idProducto(Long v_idProducto) {
        this.v_idProducto = v_idProducto;
    }

    public Long getV_cant() {
        return v_cant;
    }

    public void setV_cant(Long v_cant) {
        this.v_cant = v_cant;
    }

    public float getV_precioUnitario() {
        return v_precioUnitario;
    }

    public void setV_precioUnitario(float v_precioUnitario) {
        this.v_precioUnitario = v_precioUnitario;
    }

    public float getV_descuento() {
        return v_descuento;
    }

    public void setV_descuento(float v_descuento) {
        this.v_descuento = v_descuento;
    }

    public float getV_subtotal() {
        return v_subtotal;
    }

    public void setV_subtotal(float v_subtotal) {
        this.v_subtotal = v_subtotal;
    }

    public String getV_codigoProducto() {
        return v_codigoProducto;
    }

    public void setV_codigoProducto(String v_codigoProducto) {
        this.v_codigoProducto = v_codigoProducto;
    }

    public String getV_nombreProducto() {
        return v_nombreProducto;
    }

    public void setV_nombreProducto(String v_nombreProducto) {
        this.v_nombreProducto = v_nombreProducto;
    }
}
