package com.dp1wms.dao.ICategoriaProducto;

import com.dp1wms.model.CategoriaProducto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryMantCategoria {
    List<CategoriaProducto> selectAllCategoria();

    int newIdCategoria();

    void createCategoria(CategoriaProducto categoriaProducto);

    void updateCategoria(CategoriaProducto categoriaProducto);

    void deleteCategoria(CategoriaProducto categoriaProducto);
}
