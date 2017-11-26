package com.dp1wms.dao.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CajonesRowMapper implements RowMapper{
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        CajonesResultSetExtractor cajonesResultSetExtractor = new CajonesResultSetExtractor();
        return cajonesResultSetExtractor.extractData(resultSet);
    }

}

