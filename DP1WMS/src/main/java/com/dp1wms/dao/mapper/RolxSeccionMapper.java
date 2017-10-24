package com.dp1wms.dao.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolxSeccionMapper implements RowMapper{
    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        RolxSeccionSetExtractor extractor= new RolxSeccionSetExtractor();
        return extractor.extractData(rs);
    }
}
