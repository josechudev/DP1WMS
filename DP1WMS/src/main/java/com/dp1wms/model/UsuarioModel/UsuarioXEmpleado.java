package com.dp1wms.model.UsuarioModel;

public class UsuarioXEmpleado {
    private int v_id_user;
    private String v_user;
    private String v_numDoc;
    private String v_nombre;
    private String v_apellido;
    private String v_descripcion;
    private boolean v_activo;
    private String v_mostrarActivo;

    public UsuarioXEmpleado(){
        this.setV_id_user(0);
        this.setV_user(null);
        this.setV_numDoc(null);
        this.setV_nombre(null);
        this.setV_apellido(null);
        this.setV_descripcion(null);
    }

    public UsuarioXEmpleado(int v_id_user, String v_user, String v_numDoc, String v_nombre, String v_apellido, String v_descripcion, boolean v_activo){
        this.setV_id_user(v_id_user);
        this.setV_user(v_user);
        this.setV_numDoc(v_numDoc);
        this.setV_nombre(v_nombre);
        this.setV_apellido(v_apellido);
        this.setV_descripcion(v_descripcion);
        this.setV_activo(v_activo);
    }

    public int getV_id_user() {
        return v_id_user;
    }

    public void setV_id_user(int v_id_user) {
        this.v_id_user = v_id_user;
    }

    public String getV_user() {
        return v_user;
    }

    public void setV_user(String v_user) {
        if( v_user == null )
            v_user = "";
        this.v_user = v_user;
    }

    public String getV_numDoc() {
        return v_numDoc;
    }

    public void setV_numDoc(String v_numDoc) {
        if( v_numDoc == null )
            v_numDoc = "";
        this.v_numDoc = v_numDoc;
    }

    public String getV_nombre() {
        return v_nombre;
    }

    public void setV_nombre(String v_nombre) {
        if( v_nombre == null )
            v_nombre = "";
        this.v_nombre = v_nombre;
    }

    public String getV_apellido() {
        return v_apellido;
    }

    public void setV_apellido(String v_apellido) {
        if( v_apellido == null )
            v_apellido = "";
        this.v_apellido = v_apellido;
    }

    public String getV_descripcion() {
        return v_descripcion;
    }

    public void setV_descripcion(String v_descripcion) {
        if( v_descripcion == null )
            v_descripcion = "";
        this.v_descripcion = v_descripcion;
    }

    public boolean getV_activo() {
        return v_activo;
    }

    public void setV_activo(boolean v_activo) {
        this.v_activo = v_activo;
        if(this.v_activo)
            setV_mostrarActivo("Habilitado");
        else
            setV_mostrarActivo("Deshabilitado");
    }

    public String getV_mostrarActivo(){
        return v_mostrarActivo;
    }

    public void setV_mostrarActivo(String v_mostrarActivo) {
        if( v_mostrarActivo == null )
            v_mostrarActivo = "";
        this.v_mostrarActivo = v_mostrarActivo;
    }
}
