package com.dp1wms.model;

import java.sql.Timestamp;

public class Condicion {

    public static final String DESC_C = "Cantidad";
    public static final String DESC_B = "Bonificacion Por Especie";
    public static final String DESC_P = "Porcentaje";
    public static final String DESC_FP = "Flete por Peso";
    public static final String DESC_FD = "Flete por Distancia";

    private int idCondicion;

    private String tipoCondicion;

    private int idProductoGenerador;

    private int idCategoriaProdGen;

    private int cantProdGen;

    private int idProductoDescuento;

    private int idCategoriaProdDesc;

    private int cantProdDesc;

    private float valorDescuento;

    private Timestamp fechaInicio;

    private Timestamp fechaFin;

    private String descripcion;

    private int indiceTabla;

    private String nombreProductoGenerador;

    private String nombreProductoDescuento;

    private String categoriaGenerador;

    private String categoriaDescuento;

    private int prioridad;

    private Long idEmpleadoAuditado;

    private float factorFlete;

    public int getPrioridad(){
        return this.prioridad;
    }

    public void setPrioridad(int prioridad){
        this.prioridad = prioridad;
    }


    public int getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(int idCondicion) {
        this.idCondicion = idCondicion;
    }

    public String getTipoCondicion() {
        return tipoCondicion;
    }

    public void setTipoCondicion(String tipoCondicion) {
        this.tipoCondicion = tipoCondicion;
    }

    public int getIdProductoGenerador() {
        return idProductoGenerador;
    }

    public void setIdProductoGenerador(int idProductoGenerador) {
        this.idProductoGenerador = idProductoGenerador;
    }

    public int getIdCategoriaProdGen() {
        return idCategoriaProdGen;
    }

    public void setIdCategoriaProdGen(int idCategoriaProdGen) {
        this.idCategoriaProdGen = idCategoriaProdGen;
    }

    public int getCantProdGen() {
        return cantProdGen;
    }

    public void setCantProdGen(int cantProdGen) {
        this.cantProdGen = cantProdGen;
    }

    public int getIdProductoDescuento() {
        return idProductoDescuento;
    }

    public void setIdProductoDescuento(int idProductoDescuento) {
        this.idProductoDescuento = idProductoDescuento;
    }

    public int getIdCategoriaProdDesc() {
        return idCategoriaProdDesc;
    }

    public void setIdCategoriaProdDesc(int idCategoriaProdDesc) {
        this.idCategoriaProdDesc = idCategoriaProdDesc;
    }

    public int getCantProdDesc() {
        return cantProdDesc;
    }

    public void setCantProdDesc(int cantProdDesc) {
        this.cantProdDesc = cantProdDesc;
    }

    public float getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(float valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIndiceTabla() {
        return indiceTabla;
    }

    public void setIndiceTabla(int indiceTabla) {
        this.indiceTabla = indiceTabla;
    }

    public String getNombreProductoGenerador() {
        return nombreProductoGenerador;
    }

    public void setNombreProductoGenerador(String nombreProductoGenerador) {
        this.nombreProductoGenerador = nombreProductoGenerador;
    }

    public String getNombreProductoDescuento() {
        return nombreProductoDescuento;
    }

    public void setNombreProductoDescuento(String nombreProductoDescuento) {
        this.nombreProductoDescuento = nombreProductoDescuento;
    }

    public String getCategoriaGenerador() {
        return categoriaGenerador;
    }

    public void setCategoriaGenerador(String categoriaGenerador) {
        this.categoriaGenerador = categoriaGenerador;
    }

    public String getCategoriaDescuento() {
        return categoriaDescuento;
    }

    public void setCategoriaDescuento(String categoriaDescuento) {
        this.categoriaDescuento = categoriaDescuento;
    }

    public Long getIdEmpleadoAuditado() {
        return idEmpleadoAuditado;
    }

    public void setIdEmpleadoAuditado(Long idEmpleadoAuditado) {
        this.idEmpleadoAuditado = idEmpleadoAuditado;
    }

    public float getFactorFlete() {
        return factorFlete;
    }

    public void setFactorFlete(float factorFlete) {
        this.factorFlete = factorFlete;
    }
}
