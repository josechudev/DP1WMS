package com.dp1wms.dao;

import com.dp1wms.model.CategoriaProducto;
import com.dp1wms.model.Lote;
import com.dp1wms.model.Producto;
import com.dp1wms.model.TipoMovimiento;

import java.util.List;

public interface RepositoryMantMov{

    List<Producto> obtenerProductos();
    List<CategoriaProducto> obtenerCategoriasProducto();
    List<Lote> obtenerLotes();
    List<TipoMovimiento> obtenerTiposMovimiento();
}