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
    private long idCliente;
    private float total;

    private Cliente cliente;

    //Cabecera
    private ArrayList<DetallePedido> detalles;

    public Pedido(){
        this.setDetalles(new ArrayList<>());
    }

    public void agregarProducto(Producto p, int cantidad){
        for(DetallePedido dp: this.detalles){
            if(dp.getProducto().getIdProducto() == p.getIdProducto()){
                dp.setDescuento(0);
                int c = dp.getCantidad();
                dp.setCantidad(c + cantidad);
                return;
            }
        }
        DetallePedido dp = new DetallePedido();
        dp.setCantidad(cantidad);
        dp.setDescuento(0);
        dp.setProducto(p);
        this.detalles.add(dp);
    }

    public void eliminarDetalle(DetallePedido dp){
        this.detalles.remove(dp);
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

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }



    public void calcularTotal(){
        float total = 0;
        for(DetallePedido dp: this.detalles){
            float desc = dp.getDescuento();
            dp.setSubtotal(dp.getCantidad()*dp.getProducto().getPrecio() - desc);
            total += dp.getSubtotal();
        }
        this.total = total;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
