package com.dp1wms.model;

import java.io.Serializable;

public class Empleado implements Serializable {

    private long idempleado;

    private long idusuario;

    private String numDoc;

    private String nombre;

    private String apellidos;

    private String email;

    private long idtipoempleado;

    private TipoEmpleado tipoEmpleado;

    public long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(long idempleado) {
        this.idempleado = idempleado;
    }

    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
        else
            this.nombre = "";
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        if(apellidos != null)
            this.apellidos = apellidos;
        else
            this.apellidos = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email != null)
            this.email = email;
        else
            this.email = "";
    }

    public long getIdtipoempleado() {
        return idtipoempleado;
    }

    public void setIdtipoempleado(long idtipoempleado) {
        this.idtipoempleado = idtipoempleado;
    }

    /**
     *  No serializar
     */
    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        if(numDoc != null)
            this.numDoc = numDoc;
        else
            this.numDoc = "";
    }
}
