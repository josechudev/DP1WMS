package com.dp1wms.model;

import java.util.ArrayList;

public class Proforma implements Cabecera{
    private int idProforma;
    private long idEmpleado;
    private long idCliente;
    private float total;
    private String observaciones;
    private String fechaCreacion;

    private ArrayList<DetalleProforma> detallesProforma;

    //objects
    private Cliente cliente;

    public Proforma(){
        this.detallesProforma = new ArrayList<DetalleProforma>();
    }

    public void agregarProducto(Producto p, int cantidad){
        for(DetalleProforma dp: this.detallesProforma){
            if(dp.getProducto().getIdProducto() == p.getIdProducto()){
                dp.setDescuento(0);
                int c = dp.getCantidad();
                dp.setCantidad(c + cantidad);
                return;
            }
        }
        DetalleProforma dp = new DetalleProforma();
        dp.setCantidad(cantidad);
        dp.setDescuento(0);
        dp.setProducto(p);
        this.detallesProforma.add(dp);
    }

    public void eliminarDetalleProforma(DetalleProforma dp){
        this.detallesProforma.remove(dp);
    }

    public long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ArrayList<DetalleProforma> getDetallesProforma() {
        return detallesProforma;
    }

    public void setDetallesProforma(ArrayList<DetalleProforma> detallesProforma) {
        this.detallesProforma = detallesProforma;
    }
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public void calcularTotal(){
        float total = 0;
        for(DetalleProforma dp: this.detallesProforma){
            float desc = dp.getDescuento();
            dp.setSubtotal(dp.getCantidad()*dp.getProducto().getPrecio() - desc);
            total += dp.getSubtotal();
        }
        this.total = total;
    }

    @Override
    public Detalle getDetalle(int i) {
        return this.detallesProforma.get(i);
    }

    public int getCantidadDetalle(){
        return this.detallesProforma.size();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
