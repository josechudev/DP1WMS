package com.dp1wms.model;

public class Ruta {
    private int idRuta;
    private int idEnvio;
    private Integer[] camino_x;
    private Integer[] camino_y;
    private int costo;

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Integer[] getCamino_x() {
        return camino_x;
    }

    public void setCamino_x(Integer[] camino_x) {
        this.camino_x = camino_x;
    }

    public Integer[] getCamino_y() {
        return camino_y;
    }

    public void setCamino_y(Integer[] camino_y) {
        this.camino_y = camino_y;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}
