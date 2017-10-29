package com.dp1wms.dao;

import com.dp1wms.model.CategoriaProducto;
import com.dp1wms.model.Lote;
import com.dp1wms.model.Producto;
import com.dp1wms.model.TipoMovimiento;

import java.sql.Timestamp;
import java.util.List;

public interface RepositoryMantMov{

    List<Producto> obtenerProductos();
    List<CategoriaProducto> obtenerCategoriasProducto();
    List<Lote> obtenerLotes();
    List<TipoMovimiento> obtenerTiposMovimiento();
    int registrarMovimiento(Integer totalProductos, String observaciones, Timestamp fecha, int idTipoMovimiento, int idProducto, Integer idLote, Integer cantidad,Long idEmpleadoAuditado);
    int registrarLote(int idProducto,Timestamp fechalote,Timestamp fechaEntrada,int stockParcial,Long idEmpleadoAuditado);
}