package com.dp1wms.dao.impl.CategoriaProducto;

import com.dp1wms.model.CategoriaProducto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryMantCategoria {
    List<CategoriaProducto> selectAllCategoria();

    int newIdUsuario();

    void createUsuario(CategoriaProducto auxUsuario);

    void updateUsuario(CategoriaProducto auxUsuario);

    void deleteUsuario(CategoriaProducto auxUsuario);
}
