package com.dp1wms.dao.mapper.Producto;

import com.dp1wms.model.Producto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
* package com.dp1wms.dao.mapper.Categoria;

import com.dp1wms.model.CategoriaProducto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaResultSetExtractor implements ResultSetExtractor{

    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        CategoriaProducto categoria = new CategoriaProducto();
        categoria.setIdCategoria(resultSet.getInt(1));
        categoria.setDescripcion(resultSet.getString(2));
        return  categoria;
    }
}
*/

    /*
    idproducto	-
    nombreproducto	-
    peso	-
    fechavencimiento	-
    descripcion	-
    precio	-
    stock	-
    idcategoria	-
    codigo	-
    fechacreacion -
    activo -*/

public class ProductoResultSetExtractor implements ResultSetExtractor{
    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Producto producto = new Producto();
        producto.setIdProducto(resultSet.getInt(1));
        producto.setNombreProducto(resultSet.getString(2));
        producto.setPeso(resultSet.getFloat(3));
        producto.setFechaVencimiento(resultSet.getString(4));
        producto.setDescripcion(resultSet.getString(5));
        producto.setPrecio(resultSet.getFloat(6));
        producto.setStock(resultSet.getInt(7));
        producto.setIdCategoria(resultSet.getInt(8));
        producto.setCategoria(resultSet.getString(9));
        producto.setCodigo(resultSet.getString(10));
        producto.setFechaCreacion(resultSet.getString(11));
        producto.setActivo(resultSet.getBoolean(12));
        producto.setPrecioCompra(resultSet.getFloat(13));
        producto.setUnidades(resultSet.getString(14));
        producto.setStockMinimo(resultSet.getInt(15));

        return producto;

    }
}
