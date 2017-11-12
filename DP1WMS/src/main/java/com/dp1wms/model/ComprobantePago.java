package com.dp1wms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComprobantePago {
    private Long v_id;

    private Long v_idTipoComprobante;
    private String v_tipoComprobante;

    private Long v_idCliente;
    private String v_cliente;

    private String v_fechaCreacion;
    private String v_fechaModificacion;

    private float v_subtotal;
    private float v_flete;
    private float v_igv;
    private float v_total;

    private Long v_idEnvio;

    private boolean v_activo;

    private String v_mostrarActivo;

    private List<DetalleFactura> listaDetalleComprobante;
    private Integer indiceTabla;
    private String direccionCliente;
    private String numeroDocumentoCliente;
    private Long idNotaCredito;

    //private List<DetalleComprobantePago> v_listaDetalleComprobante;

    public ComprobantePago(){
        //v_listaDetalleComprobante = new ArrayList<DetalleComprobantePago>();
    }

    public ComprobantePago( Long v_id,
                            Long v_idTipoComprobante,
                            String v_tipoComprobante,
                            Long v_idCliente,
                            String v_cliente,
                            String v_fechaCreacion,
                            String v_fechaModificacion,
                            float v_subtotal,
                            float v_flete,
                            float v_igv,
                            float v_total,
                            Long v_idEnvio,
                            boolean v_activo  ){

        this.setV_id(v_id);
        this.setV_idTipoComprobante(v_idTipoComprobante);
        this.setV_tipoComprobante(v_tipoComprobante);
        this.setV_idCliente(v_idCliente);
        this.setV_cliente(v_cliente);

        this.setV_fechaCreacion(v_fechaCreacion);
        this.setV_fechaModificacion(v_fechaModificacion);

        this.setV_subtotal(v_subtotal);
        this.setV_flete(v_flete);
        this.setV_igv(v_igv);
        this.setV_total(v_total);
        this.setV_idEnvio(v_idEnvio);
        this.setV_activo(v_activo);

        //v_listaDetalleComprobante = new ArrayList<DetalleComprobantePago>();
    }


    public Long getV_id() {
        return v_id;
    }

    public void setV_id(Long v_id) {
        this.v_id = v_id;
    }

    public Long getV_idTipoComprobante() {
        return v_idTipoComprobante;
    }

    public void setV_idTipoComprobante(Long v_idTipoComprobante) {
        this.v_idTipoComprobante = v_idTipoComprobante;
    }

    public String getV_tipoComprobante() {
        return v_tipoComprobante;
    }

    public void setV_tipoComprobante(String v_tipoComprobante) {
        if(v_tipoComprobante == null) v_tipoComprobante = "";
        this.v_tipoComprobante = v_tipoComprobante;
    }

    public Long getV_idCliente() {
        return v_idCliente;
    }

    public void setV_idCliente(Long v_idCliente) {
        this.v_idCliente = v_idCliente;
    }

    public String getV_cliente() {
        return v_cliente;
    }

    public void setV_cliente(String v_cliente) {
        if(v_cliente == null) v_cliente = "";
        this.v_cliente = v_cliente;
    }

    public float getV_subtotal() {
        return v_subtotal;
    }

    public void setV_subtotal(float v_subtotal) {
        this.v_subtotal = v_subtotal;
    }

    public float getV_flete() {
        return v_flete;
    }

    public void setV_flete(float v_flete) {
        this.v_flete = v_flete;
    }

    public float getV_igv() {
        return v_igv;
    }

    public void setV_igv(float v_igv) {
        this.v_igv = v_igv;
    }

    public float getV_total() {
        return v_total;
    }

    public void setV_total(float v_total) {
        this.v_total = v_total;
    }

    public Long getV_idEnvio() {
        return v_idEnvio;
    }

    public void setV_idEnvio(Long v_idEnvio) {
        this.v_idEnvio = v_idEnvio;
    }

    public boolean isV_activo() {
        return v_activo;
    }

    public void setV_activo(boolean v_activo) {
        this.v_activo = v_activo;
        if(this.v_activo)
            this.setV_mostrarActivo("Activo");
        else
            this.setV_mostrarActivo("Cancelado");
    }

    public String getV_mostrarActivo() {
        return v_mostrarActivo;
    }

    public void setV_mostrarActivo(String v_mostrarActivo) {
        if(v_mostrarActivo == null) v_mostrarActivo = "";
        this.v_mostrarActivo = v_mostrarActivo;
    }

    public String getV_fechaCreacion() {
        return v_fechaCreacion;
    }

    public void setV_fechaCreacion(String v_fechaCreacion) {
        if(v_fechaCreacion == null) v_fechaCreacion = "";
        this.v_fechaCreacion = v_fechaCreacion;
    }

    public String getV_fechaModificacion() {
        return v_fechaModificacion;
    }

    public void setV_fechaModificacion(String v_fechaModificacion) {
        if(v_fechaModificacion == null) v_fechaModificacion = "";
        this.v_fechaModificacion = v_fechaModificacion;
    }

    public List<DetalleFactura> getListaDetalleComprobante() {
        return listaDetalleComprobante;
    }

    public void setListaDetalleComprobante(List<DetalleFactura> listaDetalleComprobante) {
        this.listaDetalleComprobante = listaDetalleComprobante;
    }


    public Integer getIndiceTabla() {
        return indiceTabla;
    }

    public void setIndiceTabla(Integer indiceTabla) {
        this.indiceTabla = indiceTabla;
    }


    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getNumeroDocumentoCliente() {
        return numeroDocumentoCliente;
    }

    public void setNumeroDocumentoCliente(String numeroDocumentoCliente) {
        this.numeroDocumentoCliente = numeroDocumentoCliente;
    }

    public Long getIdNotaCredito() {
        return idNotaCredito;
    }

    public void setIdNotaCredito(Long idNotaCredito) {
        this.idNotaCredito = idNotaCredito;
    }
}
