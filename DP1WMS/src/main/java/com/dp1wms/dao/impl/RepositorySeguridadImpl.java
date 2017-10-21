package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositorySeguridad;
import com.dp1wms.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositorySeguridadImpl implements RepositorySeguridad {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Usuario validarCredenciales(Usuario usuario){
        String SQL = "SELECT * FROM usuario WHERE nombreusuario = ? AND password = crypt(?, password)";

        List<Usuario> usuarios = null;

        try{
            usuarios = jdbcTemplate.query(SQL, new Object[]{usuario.getNombreusuario(),
            usuario.getPassword()}, this::mapParamUsuario);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        if(usuarios.size() == 1){
            return usuarios.get(0);
        } else {
            return null;
        }
    }

    private Usuario mapParamUsuario(ResultSet rs, int i) throws SQLException{
        Usuario u = new Usuario();
        u.setIdusuario(rs.getLong("idusuario"));
        u.setNombreusuario(rs.getString("nombreusuario"));
        u.setPassword(rs.getString("password"));
        return u;
    }
}
