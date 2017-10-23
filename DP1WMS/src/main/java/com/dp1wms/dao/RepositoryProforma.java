package com.dp1wms.dao;

import com.dp1wms.model.Producto;

import java.util.List;

public interface RepositoryProforma {

    List<Producto> buscarProductosParaVenta(String campo, String dato);
}
