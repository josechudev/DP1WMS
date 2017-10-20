package com.dp1wms.model.UsuarioModel;

public class Usuario {
    private int v_id;
    private String v_nombre;
    private String v_password;

    public Usuario(){}

    public Usuario(int v_id, String v_nombre, String v_password){
        this.setV_id(v_id);
        this.setV_nombre(v_nombre);
        this.setV_password(v_password);
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public String getV_nombre() {
        return v_nombre;
    }

    public void setV_nombre(String v_nombre) {
        this.v_nombre = v_nombre;
    }

    public String getV_password() {
        return v_password;
    }

    public void setV_password(String v_password) {
        this.v_password = v_password;
    }
}
