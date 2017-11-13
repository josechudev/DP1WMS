package com.dp1wms.dao.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RackRowMapper implements RowMapper{
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        RackResultSetExtractor rackResultSetExtractor = new RackResultSetExtractor();
        return rackResultSetExtractor.extractData(resultSet);
    }
}
