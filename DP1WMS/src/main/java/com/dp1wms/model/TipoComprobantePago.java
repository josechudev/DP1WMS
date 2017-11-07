package com.dp1wms.model;

public class TipoComprobantePago {

    private Long v_id;
    private String v_descripcion;

    public TipoComprobantePago(){}

    public TipoComprobantePago(Long v_id, String v_descripcion){
        this.setV_id(v_id);
        this.setV_descripcion(v_descripcion);
    }

    public Long getV_id() {
        return v_id;
    }

    public void setV_id(Long v_id) {
        this.v_id = v_id;
    }

    public String getV_descripcion() {
        return v_descripcion;
    }

    public void setV_descripcion(String v_descripcion) {
        this.v_descripcion = v_descripcion;
    }
}
