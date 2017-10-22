package com.dp1wms.dao.mapper;

import com.dp1wms.model.Empleado;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EmpleadoResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {
        Empleado person = new Empleado();
        person.setIdempleado(rs.getLong(1));
        person.setIdusuario(rs.getLong(2));
        person.setNombre(rs.getString(3));
        person.setApellidos(rs.getString(4));
        person.setEmail(rs.getString(5));
        person.setIdtipoempleado(6);
        return person;
    }

}
