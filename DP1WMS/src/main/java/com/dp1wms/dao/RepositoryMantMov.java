package com.dp1wms.dao;

import com.dp1wms.model.*;

import java.sql.Timestamp;
import java.util.List;

public interface RepositoryMantMov{

    List<Producto> obtenerProductos();
    List<CategoriaProducto> obtenerCategoriasProducto();
    List<Lote> obtenerLotes();
    List<TipoMovimiento> obtenerTiposMovimiento();
    int registrarMovimiento(Integer totalProductos, String observaciones, Timestamp fecha, int idTipoMovimiento, int idProducto, Integer idLote, Integer cantidad,Long idEmpleadoAuditado,int idCajon);
    int registrarLote(int idProducto,Timestamp fechalote,Timestamp fechaEntrada,int stockParcial,Long idEmpleadoAuditado,int idCajon);
    int registrarMovimiento(Movimiento movimiento);
    List<Almacen> obtenerAlmacenes();
    List<Ubicacion> obtenerUbicaciones(int idLote,int idProducto);
    void insertarUbicacion(int idLote,int idProducto,int idCajon,int cantidad,Long idEmpleadoAuditado);
    void actualizarUbicacion(int idLote,int idProducto,int idCajon,int cantidad,int idCajonAntiguo,Long idEmpleadoAuditado);
}