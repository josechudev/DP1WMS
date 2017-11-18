package com.dp1wms.dao.mapper;

import com.dp1wms.model.tabu.Almacen;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnvioProductosResultSetExtractor implements ResultSetExtractor{

    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        String nombre = resultSet.getString("nombreproducto");
        return nombre;
    }
}
