package com.dp1wms.dao.mapper.Kardex;

import com.dp1wms.model.KardexXProducto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KardexXProductoRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        KardexXProductoResultSetExtractor extractor = new KardexXProductoResultSetExtractor();
        return extractor.extractData(resultSet);

    }
}
