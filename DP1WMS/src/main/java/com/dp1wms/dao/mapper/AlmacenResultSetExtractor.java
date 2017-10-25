package com.dp1wms.dao.mapper;

import com.dp1wms.model.Almacen;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlmacenResultSetExtractor implements ResultSetExtractor{

    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Almacen almacen = new Almacen();
        almacen.setIdAlmacen(resultSet.getInt(1));
        almacen.setDireccion(resultSet.getString(2));
        almacen.setLargo(resultSet.getInt(3));
        almacen.setAncho(resultSet.getInt(4));
        almacen.setNombre(resultSet.getString(5));
        return almacen;
    }
}
