package com.dp1wms.dao.mapper.Kardex;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KardexFilaRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        KardexFilaResultSetExtractor extractor = new KardexFilaResultSetExtractor();
        return extractor.extractData(resultSet);

    }
}
