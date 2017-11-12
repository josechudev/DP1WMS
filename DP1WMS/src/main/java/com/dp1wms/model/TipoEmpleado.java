package com.dp1wms.model;

import java.util.List;

public class TipoEmpleado {

    private long idtipoempleado;

    private String descripcion;
    private List<Seccion> permisos;
    private Boolean activo;

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

    public List<Seccion> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Seccion> permisos) {
        this.permisos = permisos;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
