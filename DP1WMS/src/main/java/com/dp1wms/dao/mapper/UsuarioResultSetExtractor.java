package com.dp1wms.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dp1wms.model.UsuarioModel.Usuario;
import org.springframework.jdbc.core.ResultSetExtractor;


public class UsuarioResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {
        Usuario person = new Usuario();
        person.setV_id(rs.getInt(1));
        person.setV_nombre(rs.getString(2));
        person.setV_password(rs.getString(3));
        return person;
    }

}
