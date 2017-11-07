package com.dp1wms.dao.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioXEmpleadoRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        UsuarioXEmpleadoResultSetExtractor extractor = new UsuarioXEmpleadoResultSetExtractor();
        return extractor.extractData(rs);
    }

}