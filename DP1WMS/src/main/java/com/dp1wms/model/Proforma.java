package com.dp1wms.model;

import java.util.ArrayList;

public class Proforma {
    private long idEmpleado;
    private long idCliente;
    private float monto;
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
                dp.setCantidadSinAsignar(c + cantidad);
                dp.setSubTotal(dp.getCantidad() * p.getPrecio());
                return null;
            }
        }
        DetalleProforma dp = new DetalleProforma();
        dp.setCantidad(cantidad);
        dp.setCantidadSinAsignar(cantidad);
        dp.setDescuento(0);
        dp.setSubTotal(cantidad * p.getPrecio());
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

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
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
}
