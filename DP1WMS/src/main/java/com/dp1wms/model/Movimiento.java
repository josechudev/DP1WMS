package com.dp1wms.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Movimiento {

    private int idMovimiento;

    private int totalProductos;

    private String observaciones;

    private Timestamp fechaMovimiento;

    private int idTipoMovimiento;

    private Long idEmpleadoAuditado;

    private List<DetalleMovimiento> listaDetalleMovimiento = new ArrayList<DetalleMovimiento>();

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(int totalProductos) {
        this.totalProductos = totalProductos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Timestamp getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Timestamp fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(int idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }


    public Long getIdEmpleadoAuditado() {
        return idEmpleadoAuditado;
    }

    public void setIdEmpleadoAuditado(Long idEmpleadoAuditado) {
        this.idEmpleadoAuditado = idEmpleadoAuditado;
    }

    public List<DetalleMovimiento> getListaDetalleMovimiento() {
        return listaDetalleMovimiento;
    }

    public void setListaDetalleMovimiento(List<DetalleMovimiento> listaDetalleMovimiento) {
        this.listaDetalleMovimiento = listaDetalleMovimiento;
    }
}
