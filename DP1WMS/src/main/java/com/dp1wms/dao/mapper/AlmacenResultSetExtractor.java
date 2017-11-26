package com.dp1wms.dao.mapper;

import com.dp1wms.model.tabu.Almacen;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlmacenResultSetExtractor implements ResultSetExtractor{

    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        int alto = resultSet.getInt("largo");
        int ancho = resultSet.getInt("ancho");

        Almacen almacen = new Almacen(ancho,alto);
        return almacen;
    }
}