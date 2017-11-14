package com.dp1wms.dao.mapper.Auditoria;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventoRowMapper implements RowMapper{
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        EventoResultSetExtractor eventoResultSetExtractor = new EventoResultSetExtractor();
        return eventoResultSetExtractor.extractData(resultSet);
    }
}
