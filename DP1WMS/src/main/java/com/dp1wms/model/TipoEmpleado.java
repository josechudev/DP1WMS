package com.dp1wms.model;

public class TipoEmpleado {

    private long idtipoempleado;

    private String descripcion;

    public TipoEmpleado(){}

    public TipoEmpleado(long idtipoempleado, String descripcion){
        this.idtipoempleado = idtipoempleado;
        this.descripcion = descripcion;
    }

    public long getIdtipoempleado() {
        return idtipoempleado;
    }

    public void setIdtipoempleado(long idtipoempleado) {
        this.idtipoempleado = idtipoempleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
