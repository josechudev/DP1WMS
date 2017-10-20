package com.dp1wms.controller;

import com.dp1wms.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioCtrl {

    @Autowired
    private static JdbcTemplate jdbcTemplate;

    public boolean verificarCredenciales(String username, String pass){
        String sql = "SELECT * FROM usuario";

        List<Usuario> listUsuario = new ArrayList<Usuario>();
        try {
            listUsuario = jdbcTemplate.query(sql, new Object[] {}, this::mapParam);
        } catch (Exception e){
            e.printStackTrace();
        }
        for (Usuario u: listUsuario){
            u.imprimir();
        }

        return true;
    }

    private Usuario mapParam(ResultSet rs, int i) throws SQLException{
        Usuario usuario = new Usuario();

        usuario.setIdusuario(rs.getLong("idusuario"));
        usuario.setNombreusuario(rs.getString("nombreusuario"));
        usuario.setPassword(rs.getString("password"));
        return usuario;
    }
}
