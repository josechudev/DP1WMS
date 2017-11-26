package com.dp1wms.dao.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlmacenRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        AlmacenResultSetExtractor almacenResultSetExtractor = new AlmacenResultSetExtractor();
        return almacenResultSetExtractor.extractData(resultSet);
    }
}