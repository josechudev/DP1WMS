package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryProforma;
import com.dp1wms.model.CategoriaProducto;
import com.dp1wms.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryProformaImpl implements RepositoryProforma {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Producto> buscarProductosParaVenta(String campo, String dato){
        List<Producto> productos = null;

        dato = "%" + dato  + "%";
        String sql = "SELECT p.idproducto, p.codigo, p.nombreproducto, p.precio, p.idcategoria, cp.descripcion as categoria," +
                        " (p.stock - COALESCE(x.cantidad, 0)) as stock " +
                    "FROM producto p INNER JOIN categoriaproducto cp ON p.idcategoria = cp.idcategoria" +
                    " LEFT JOIN ( " +
                        "SELECT dp.idproducto, sum(dp.cantidad) as cantidad " +
                        "FROM detallepedido dp INNER JOIN pedido p ON dp.idpedido = p.idpedido " +
                        "WHERE p.idestadopedido = 1 GROUP BY dp.idproducto" +
                    ") x ON p.idproducto = x.idproducto WHERE p.activo AND ";
        if(campo != null){
            sql += "p." + campo + " LIKE ?";
        } else {
            sql += "cp.descripcion LIKE ?";
        }

        try{
            productos = this.jdbcTemplate.query(sql, new Object[]{dato}, this::mapProducto);
            return productos;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Producto mapProducto(ResultSet rs, int i) throws SQLException{
        Producto p = new Producto();
        p.setIdProducto(rs.getInt("idproducto"));
        p.setCodigo(rs.getString("codigo"));
        p.setNombreProducto(rs.getString("nombreproducto"));
        p.setIdCategoria(rs.getInt("idcategoria"));
        p.setStock(rs.getInt("stock"));
        p.setCategoria(rs.getString("categoria"));
        return p;
    }
}
