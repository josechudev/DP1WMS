package com.dp1wms.dao.mapper;


import com.dp1wms.model.RolxSeccion;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolxSeccionSetExtractor implements ResultSetExtractor{
    @Override
    public Object extractData(ResultSet rs) throws SQLException {

        RolxSeccion seccion = new RolxSeccion();
        seccion.setIdTipoEmpleado(rs.getInt(1));
        seccion.setIdSeccion(rs.getInt(2));
        return seccion;
    }

}
