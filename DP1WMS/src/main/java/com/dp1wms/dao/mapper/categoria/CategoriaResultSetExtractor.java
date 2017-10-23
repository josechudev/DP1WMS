package com.dp1wms.dao.mapper.categoria;

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
