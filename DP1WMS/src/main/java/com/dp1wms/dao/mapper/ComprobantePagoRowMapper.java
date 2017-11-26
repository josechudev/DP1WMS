package com.dp1wms.dao.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComprobantePagoRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        ComprobantePagoSetExtractor extractor = new ComprobantePagoSetExtractor();
        return extractor.extractData(rs);
    }

}