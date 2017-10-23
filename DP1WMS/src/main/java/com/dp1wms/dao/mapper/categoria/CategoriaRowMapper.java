package com.dp1wms.dao.mapper.categoria;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        CategoriaResultSetExtractor extractor = new CategoriaResultSetExtractor();
        return extractor.extractData(resultSet);
    }
}
