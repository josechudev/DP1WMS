package com.dp1wms.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UsuarioRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        UsuarioResultSetExtractor extractor = new UsuarioResultSetExtractor();
        return extractor.extractData(rs);
    }

}