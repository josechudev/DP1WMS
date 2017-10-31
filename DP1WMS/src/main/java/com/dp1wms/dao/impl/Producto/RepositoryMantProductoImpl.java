package com.dp1wms.dao.impl.Producto;

import com.dp1wms.dao.IProducto.RepositoryMantProducto;
import com.dp1wms.dao.mapper.Producto.ProductoRowMapper;
import com.dp1wms.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class RepositoryMantProductoImpl implements RepositoryMantProducto {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(
            "dd/MM/yyyy");

    @Override
    public List<Producto> selectAllProducto() {
        String sql = "select idproducto, nombreproducto, peso, fechavencimiento, p.descripcion, precio, stock, p.idcategoria,cp.descripcion, codigo, fechacreacion, activo, idempleadoauditado\n" +
                "from producto p\n" +
                "left join categoriaproducto as cp on cp.idcategoria = p.idcategoria\n" +
                "order by idproducto;";
        return jdbcTemplate.query(sql, new ProductoRowMapper());
    }

    @Override
    public int newIdProducto() {
        String sql = "SELECT MAX(idproducto) FROM producto";
        return jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class) + 1;
    }

    @Override
    public void createProducto(Producto producto)  {

        String sql = "INSERT INTO producto (idproducto," +
                "nombreproducto," +
                "peso," +
                "fechavencimiento," +
                "descripcion," +
                "precio," +
                "stock," +
                "idcategoria," +
                "codigo," +
                "fechacreacion," +
                "activo) VALUES(default, ?,?,?,?,?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql,
                    new Object[]{producto.getNombreProducto(),
                            producto.getPeso(),
                            datetimeFormatter1.parse(producto.getFechaVencimiento()),
                            producto.getDescripcion(),
                            producto.getPrecio(),
                            producto.getStock(),
                            producto.getIdCategoria(),
                            producto.getCodigo(),
                            datetimeFormatter1.parse(producto.getFechaCreacion()),
                            producto.esActivo()});
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProducto(Producto producto) {
        String sql = "UPDATE producto SET nombreproducto = ?,peso=?,fechavencimiento=?," +
                "descripcion=?,precio=?,stock=?,idcategoria=?,fechacreacion=?,activo=? WHERE idproducto=?";
        jdbcTemplate.update(sql, new Object[]{producto.getNombreProducto(), producto.getPeso(),
                Timestamp.valueOf(producto.getFechaVencimiento()), producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getIdCategoria(),
                Timestamp.valueOf(producto.getFechaCreacion()),
                producto.esActivo(),
                producto.getIdProducto()
        });
    }

    @Override
    public void deleteProducto(Producto producto) {
        String sql = "UPDATE producto SET activo = false Where idproducto =?";
        jdbcTemplate.update(sql, new Object[]{producto.getIdProducto()});

    }

    @Override
    public List<Producto> buscarProductos(String campo, String dato){
        List<Producto> productos = null;

        dato = "%" + dato.toLowerCase()  + "%";
        String sql = "SELECT p.idproducto, p.codigo, p.nombreproducto, p.precio, p.idcategoria, " +
                "cp.descripcion as categoria "  +
                "FROM producto p  INNER JOIN categoriaproducto cp " +
                "ON p.idcategoria = cp.idcategoria WHERE p.activo AND ";
        if(campo != null){
            sql += "lower(p." + campo + ") LIKE ?";
        } else {
            sql += "lower(cp.descripcion) LIKE ?";
        }

        try{
            productos = this.jdbcTemplate.query(sql, new Object[]{dato}, this::mapProducto);
            return productos;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Producto mapProducto(ResultSet rs, int i) throws SQLException {
        Producto p = new Producto();
        p.setIdProducto(rs.getInt("idproducto"));
        p.setCodigo(rs.getString("codigo"));
        p.setNombreProducto(rs.getString("nombreproducto"));
        p.setPrecio(rs.getFloat("precio"));
        p.setIdCategoria(rs.getInt("idcategoria"));
        p.setCategoria(rs.getString("categoria"));
        p.setStock(0);
        //p.setStock(rs.getInt("stock"));
        return p;
    }
}
