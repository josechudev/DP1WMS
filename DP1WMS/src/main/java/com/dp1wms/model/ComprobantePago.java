package com.dp1wms.model;

public class ComprobantePago {
    private Long v_id;
    private String v_tipoComprobante;
    //private Long v_idCliente;
    private String v_cliente;
    //private Long v_idEmpleado;
    private String v_empleado;
    private float v_subtotal;
    private float v_igv;
    private float v_total;
    private String v_estadoComprobante;
    //private boolean v_activo;

    public ComprobantePago(){

    }

    public ComprobantePago(Long v_id, String v_tipoComprobante,
                           //Long v_idCliente,
                           String v_cliente,
                           //Long v_idEmpleado,
                           String v_empleado, float v_subtotal, float v_igv,
                           float v_total, String v_estadoComprobante
                           //, boolean v_activo)
                            ){
        this.setV_id(v_id);
        this.setV_tipoComprobante(v_tipoComprobante);
        //this.setV_idCliente(v_idCliente);
        this.setV_cliente(v_cliente);
        //this.setV_idEmpleado(v_idEmpleado);
        this.setV_empleado(v_empleado);
        this.setV_subtotal(v_subtotal);
        this.setV_igv(v_igv);
        this.setV_total(v_total);
        this.setV_estadoComprobante(v_estadoComprobante);
        //this.setV_activo(v_activo);
    }

    public Long getV_id() {
        return v_id;
    }

    public void setV_id(Long v_id) {
        this.v_id = v_id;
    }

    public String getV_tipoComprobante() {
        return v_tipoComprobante;
    }

    public void setV_tipoComprobante(String v_tipoComprobante) {
        this.v_tipoComprobante = v_tipoComprobante;
    }

    /*public Long getV_idCliente() {
        return v_idCliente;
    }
    */
/*
    public void setV_idCliente(Long v_idCliente) {
        this.v_idCliente = v_idCliente;
    }
    */

    public String getV_cliente() {
        return v_cliente;
    }

    public void setV_cliente(String v_cliente) {
        this.v_cliente = v_cliente;
    }
/*
    public Long getV_idEmpleado() {
        return v_idEmpleado;
    }
    */
/*
    public void setV_idEmpleado(Long v_idEmpleado) {
        this.v_idEmpleado = v_idEmpleado;
    }
    */

    public String getV_empleado() {
        return v_empleado;
    }

    public void setV_empleado(String v_empleado) {
        this.v_empleado = v_empleado;
    }

    public float getV_subtotal() {
        return v_subtotal;
    }

    public void setV_subtotal(float v_subtotal) {
        this.v_subtotal = v_subtotal;
    }

    public float getV_igv() {
        return v_igv;
    }

    public void setV_igv(float v_igv) {
        this.v_igv = v_igv;
    }

    public float getV_total() {
        return v_total;
    }

    public void setV_total(float v_total) {
        this.v_total = v_total;
    }

    public String getV_estadoComprobante() {
        return v_estadoComprobante;
    }

    public void setV_estadoComprobante(String v_estadoComprobante) {
        this.v_estadoComprobante = v_estadoComprobante;
    }
/*
    public boolean isV_activo() {
        return v_activo;
    }
    */
/*
    public void setV_activo(boolean v_activo) {
        this.v_activo = v_activo;
    }
    */
}
