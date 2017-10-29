package com.dp1wms.dao.IProducto;

import com.dp1wms.model.Producto;

import java.util.List;

public interface RepositoryMantProducto {
    List<Producto> selectAllProducto();

    int newIdProducto();
    void createProducto(Producto producto);
    void updateProducto(Producto producto);
    void deleteProducto(Producto producto);
}
