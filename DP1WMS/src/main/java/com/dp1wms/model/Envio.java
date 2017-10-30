package com.dp1wms.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Envio {
    private Long idEnvio;
    private Long idPedido;
    private Timestamp fechaEnvio;
    private String destino;
    private float costoFlete;
    private Long idCliente;
    private String razonSocial;
    private int indiceTabla;
    private Boolean realizado;

    private List<DetalleEnvio> detalleEnvio;

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

    public List<DetalleEnvio> getDetalleEnvio() {
        return detalleEnvio;
    }

    public void setDetalleEnvio(List<DetalleEnvio> detalleEnvio) {
        this.detalleEnvio = detalleEnvio;
    }

    public int getTotalProductos(){
        int total = 0;
        for(DetalleEnvio de: this.detalleEnvio){
            total += de.getCantidad();
        }
        return total;
    }

    public Long getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Long idEnvio) {
        this.idEnvio = idEnvio;
    }


    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Timestamp getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Timestamp fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getIndiceTabla() {
        return indiceTabla;
    }

    public void setIndiceTabla(int indiceTabla) {
        this.indiceTabla = indiceTabla;
    }

    public Boolean getRealizado() {
        return realizado;
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }
}
