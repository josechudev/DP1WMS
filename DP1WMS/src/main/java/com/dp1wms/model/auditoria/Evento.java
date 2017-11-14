package com.dp1wms.model.auditoria;

import java.sql.Timestamp;

public class Evento {

    private int idAudtoria;
    private String tabla;
    private String nombreEmpleado;
    private String fechaAccion;
    private String accion;
    private String dataOriginal;
    private String dataNueva;

    public Evento(){

    }

    public int getIdAudtoria() {
        return idAudtoria;
    }

    public void setIdAudtoria(int idAudtoria) {
        this.idAudtoria = idAudtoria;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(String fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDataOriginal() {
        return dataOriginal;
    }

    public void setDataOriginal(String dataOriginal) {
        this.dataOriginal = dataOriginal;
    }

    public String getDataNueva() {
        return dataNueva;
    }

    public void setDataNueva(String dataNueva) {
        this.dataNueva = dataNueva;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
}
