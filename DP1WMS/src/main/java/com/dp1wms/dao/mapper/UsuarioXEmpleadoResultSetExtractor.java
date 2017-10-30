package com.dp1wms.dao.mapper;

import com.dp1wms.model.UsuarioModel.Usuario;
import com.dp1wms.model.UsuarioModel.UsuarioXEmpleado;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioXEmpleadoResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {
        UsuarioXEmpleado person = new UsuarioXEmpleado();

        person.setV_id_user(rs.getInt(1));
        person.setV_user(rs.getString(2));
        person.setV_numDoc(rs.getString(3));
        person.setV_nombre(rs.getString(4));
        person.setV_apellido(rs.getString(5));
        person.setV_descripcion(rs.getString(6));

        person.setV_activo(rs.getBoolean(7));

        return person;
    }

}
