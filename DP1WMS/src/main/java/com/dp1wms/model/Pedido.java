package com.dp1wms.model;

import java.util.ArrayList;

public class Pedido implements Cabecera{

    private int idPedido;
    private int idEstadoPedido;
    private String estado;
    private boolean esDevolucion;
    private String fechaCreacion;
    private String observaciones;
    private int idEmpleadoAuditado;
    private int idCliente;
    private float total;

    //Cabecera

    private ArrayList<DetallePedido> detalles;

    public Pedido(){
        this.setDetalles(new ArrayList<>());
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public void setIdEstadoPedido(int idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isEsDevolucion() {
        return esDevolucion;
    }

    public void setEsDevolucion(boolean esDevolucion) {
        this.esDevolucion = esDevolucion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdEmpleadoAuditado() {
        return idEmpleadoAuditado;
    }

    public void setIdEmpleadoAuditado(int idEmpleadoAuditado) {
        this.idEmpleadoAuditado = idEmpleadoAuditado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }



    public void calcularTotal(){

    }

    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public Detalle getDetalle(int i){
        return this.detalles.get(i);
    }

    public int getCantidadDetalle(){
        return this.detalles.size();
    }
}
