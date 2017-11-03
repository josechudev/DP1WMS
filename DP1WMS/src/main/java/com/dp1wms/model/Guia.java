package com.dp1wms.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Guia {

    private Long idGuiaRemision;
    private String motivoTraslado;
    private Timestamp fechaEmision;
    private Timestamp fechaVencimiento;
    private Timestamp fechaPartida;
    private String observaciones;
    private String puntoPartida;
    private String puntoLlegada;
    private String nombreTransportista;
    private String numeroPlaca;
    private Float pesoTotal;
    private Long idEnvio;
    private Long idEmpleadoAuditado;
    private String numeroGuia;
    private Integer indiceTabla;
    private String razonSocial;
    private Long idCliente;

    private List<DetalleGuia> listaDetalleGuia = new ArrayList<DetalleGuia>();

    public Long getIdGuiaRemision() {
        return idGuiaRemision;
    }

    public void setIdGuiaRemision(Long idGuiaRemision) {
        this.idGuiaRemision = idGuiaRemision;
    }

    public String getMotivoTraslado() {
        return motivoTraslado;
    }

    public void setMotivoTraslado(String motivoTraslado) {
        this.motivoTraslado = motivoTraslado;
    }

    public Timestamp getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Timestamp fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Timestamp getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Timestamp fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Timestamp getFechaPartida() {
        return fechaPartida;
    }

    public void setFechaPartida(Timestamp fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPuntoPartida() {
        return puntoPartida;
    }

    public void setPuntoPartida(String puntoPartida) {
        this.puntoPartida = puntoPartida;
    }

    public String getPuntoLlegada() {
        return puntoLlegada;
    }

    public void setPuntoLlegada(String puntoLlegada) {
        this.puntoLlegada = puntoLlegada;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public Float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(Float pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public Long getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Long idEnvio) {
        this.idEnvio = idEnvio;
    }


    public List<DetalleGuia> getListaDetalleGuia() {
        return listaDetalleGuia;
    }

    public void setListaDetalleGuia(List<DetalleGuia> listaDetalleGuia) {
        this.listaDetalleGuia = listaDetalleGuia;
    }


    public Long getIdEmpleadoAuditado() {
        return idEmpleadoAuditado;
    }

    public void setIdEmpleadoAuditado(Long idEmpleadoAuditado) {
        this.idEmpleadoAuditado = idEmpleadoAuditado;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }


    public String getNombreTransportista() {
        return nombreTransportista;
    }

    public void setNombreTransportista(String nombreTransportista) {
        this.nombreTransportista = nombreTransportista;
    }


    public Integer getIndiceTabla() {
        return indiceTabla;
    }

    public void setIndiceTabla(Integer indiceTabla) {
        this.indiceTabla = indiceTabla;
    }


    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
