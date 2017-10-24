package com.dp1wms.dao.mapper;

import com.dp1wms.model.Empleado;
import com.dp1wms.model.TipoEmpleado;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TipoEmpleadoResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {

        TipoEmpleado auxTipoEmpleado = new TipoEmpleado();
        auxTipoEmpleado.setIdtipoempleado(rs.getLong(1));
        auxTipoEmpleado.setDescripcion(rs.getString(2));
        return auxTipoEmpleado;
    }

}
