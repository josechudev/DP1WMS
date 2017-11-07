package com.dp1wms.dao.mapper;

import com.dp1wms.model.Seccion;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeccionSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {

        Seccion seccion = new Seccion();
        seccion.setIdSeccion(rs.getInt(1));
        seccion.setDescripcion(rs.getString(2));
        return seccion;
    }

}
