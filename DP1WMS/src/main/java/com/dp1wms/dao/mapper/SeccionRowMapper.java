package com.dp1wms.dao.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeccionRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        SeccionSetExtractor extractor= new SeccionSetExtractor();
        return extractor.extractData(rs);
    }

}
