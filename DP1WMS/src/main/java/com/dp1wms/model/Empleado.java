package com.dp1wms.model;

import java.io.Serializable;

public class Empleado implements Serializable {

    private long idempleado;

    private long idusuario;

    private String nombre;

    private String apellidos;

    private String email;

    private long idtipoempleado;

    /**
     *  No serializar
     */
    private TipoEmpleado tipoEmpleado;

}
