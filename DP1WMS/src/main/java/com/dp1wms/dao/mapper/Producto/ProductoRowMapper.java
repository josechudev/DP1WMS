package com.dp1wms.dao.mapper.Producto;

import com.dp1wms.dao.mapper.Categoria.CategoriaResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductoResultSetExtractor  extractor = new ProductoResultSetExtractor();
        return extractor.extractData(resultSet);
    }

}
