package com.dp1wms.model;

import java.util.ArrayList;

public class Proforma implements Cabecera{
    private int idProforma;
    private long idEmpleado;
    private long idCliente;
    private float total;
    private String observaciones;

    private ArrayList<DetalleProforma> detallesProforma;

    public Proforma(){
        this.detallesProforma = new ArrayList<DetalleProforma>();
    }

    public DetalleProforma agregarProducto(Producto p, int cantidad){
        for(DetalleProforma dp: this.detallesProforma){
            if(dp.getProducto().getIdProducto() == p.getIdProducto()){
                dp.setDescuento(0);
                int c = dp.getCantidad();
                dp.setCantidad(c + cantidad);
                return null;
            }
        }
        DetalleProforma dp = new DetalleProforma();
        dp.setCantidad(cantidad);
        dp.setDescuento(0);
        dp.setProducto(p);
        this.detallesProforma.add(dp);
        return dp;
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
}
