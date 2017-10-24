package com.dp1wms.model;

import java.util.ArrayList;

public class Envio {
    private int idEnvio;
    private int idProforma;
    private String fechaEnvio;
    private String destino;
    private float costoFlete;

    private ArrayList<DetalleEnvio> detalleEnvio;

    public Envio(){
        this.detalleEnvio = new ArrayList<DetalleEnvio>();
    }

    public void eliminarDetalleEnvio(DetalleEnvio de){
        this.detalleEnvio.remove(de);
    }

    public DetalleEnvio agregarProducto(Producto p, int cantidad){

        for(DetalleEnvio de: this.detalleEnvio ){
            System.err.println(de.getProducto().getIdProducto());
            System.err.println(p.getIdProducto());
            if(de.getProducto().getIdProducto() == p.getIdProducto()){
                int c = de.getCantidad();
                de.setCantidad(c + cantidad);
                return null;
            }
        }
        DetalleEnvio newDE = new DetalleEnvio();
        newDE.setCantidad(cantidad);
        newDE.setIdProducto(p.getIdProducto());
        newDE.setProducto(p);
        this.detalleEnvio.add(newDE);
        return newDE;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public float getCostoFlete() {
        return costoFlete;
    }

    public void setCostoFlete(float costoFlete) {
        this.costoFlete = costoFlete;
    }

    public ArrayList<DetalleEnvio> getDetalleEnvio() {
        return detalleEnvio;
    }

    public void setDetalleEnvio(ArrayList<DetalleEnvio> detalleEnvio) {
        this.detalleEnvio = detalleEnvio;
    }

    public int getTotalProductos(){
        int total = 0;
        for(DetalleEnvio de: this.detalleEnvio){
            total += de.getCantidad();
        }
        return total;
    }
}
