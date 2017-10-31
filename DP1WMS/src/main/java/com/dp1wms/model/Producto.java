package com.dp1wms.model;


public class Producto {


    private int idProducto;
    private String nombreProducto;
    private int idCategoria;
    private String categoria;
    private float peso;
    private String fechaVencimiento;
    private String descripcion;
    private int stock;
    private String codigo;
    private float precio;
    private boolean activo;
    private String fechaCreacion;
    private float precioCompra;
    private String unidades;

    public Producto(int idProducto, String nombreProducto, int idCategoria, String categoria, float peso, String fechaVencimiento, String descripcion, int stock, String codigo, float precio, boolean activo, String fechaCreacion, float precioCompra, String unidades) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.peso = peso;
        this.fechaVencimiento = fechaVencimiento;
        this.descripcion = descripcion;
        this.stock = stock;
        this.codigo = codigo;
        this.precio = precio;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.precioCompra = precioCompra;
        this.unidades = unidades;
    }


    public Producto(String unidades) {
        this.unidades = unidades;
    }

    public Producto() {
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }



    public Producto(int idProducto, String nombreProducto, int idCategoria, float peso, String fechaVencimiento, String descripcion, int stock) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.idCategoria = idCategoria;
        this.peso = peso;
        this.fechaVencimiento = fechaVencimiento;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Producto(Producto producto) {
        this.idProducto = producto.getIdProducto();
        this.nombreProducto = producto.getNombreProducto();
        this.idCategoria = producto.getIdCategoria();
        this.categoria = producto.getCategoria();
        this.peso = producto.getPeso();
        this.fechaVencimiento = producto.getFechaVencimiento();
        this.descripcion = producto.getDescripcion();
        this.stock = producto.stock;
        this.codigo = producto.getCodigo();
        this.precio = producto.getPrecio();
        this.activo = (producto.isActivo() == "Activo") ? true : false;
        this.fechaCreacion = producto.getFechaCreacion();
        this.precioCompra = producto.precioCompra;
        this.unidades=producto.unidades;
    }

    public String isActivo() {
        if (activo) return "Activo";
        else return "Inactivo";
    }

    public boolean esActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
