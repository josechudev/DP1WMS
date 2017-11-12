package com.dp1wms.dao.impl.CategoriaProducto;

import com.dp1wms.dao.ICategoriaProducto.RepositoryMantCategoria;
import com.dp1wms.dao.mapper.Categoria.CategoriaRowMapper;
import com.dp1wms.model.CategoriaProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryMantCategoriaImpl implements RepositoryMantCategoria{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CategoriaProducto> selectAllCategoria() {
        String sql = "SELECT * FROM categoriaproducto order by idcategoria";
        return jdbcTemplate.query(sql,new CategoriaRowMapper());
    }

    @Override
    public int newIdCategoria() {
        String sql = "SELECT MAX(idCategoria) FROM categoriaproducto";
        return jdbcTemplate.queryForObject(sql,new Object[]{},Integer.class)+1;
    }

    @Override
    public void createCategoria(CategoriaProducto categoria) {
        String sql = "INSERT INTO categoriaproducto(idCategoria,descripcion) VALUES(default, ?)";
        jdbcTemplate.update(sql,new Object[]{categoria.getDescripcion()});
    }

    @Override
    public void updateCategoria(CategoriaProducto categoriaProducto) {
        String sql = "UPDATE categoriaproducto SET descripcion = ? WHERE idcategoria = ?";
        jdbcTemplate.update(sql,new Object[]{categoriaProducto.getDescripcion(),categoriaProducto.getIdCategoria()});
    }

    @Override
    public void deleteCategoria(CategoriaProducto categoriaProducto) {
        String sql = "DELETE FROM categoriaproducto WHERE idcategoria = ?";
        jdbcTemplate.update(sql, new Object[]{categoriaProducto.getIdCategoria()});
    }
}
