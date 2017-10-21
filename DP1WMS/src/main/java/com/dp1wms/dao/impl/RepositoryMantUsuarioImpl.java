package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantUsuario;
import com.dp1wms.dao.mapper.UsuarioRowMapper;
import com.dp1wms.model.UsuarioModel.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryMantUsuarioImpl implements RepositoryMantUsuario {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Usuario> selectAllUsuario(){
        String sql = "SELECT * FROM usuario ORDER BY idUsuario";
        return jdbcTemplate.query(sql,
                new UsuarioRowMapper());
    }

    public int newIdUsuario(){
        String sql= "SELECT MAX(idUsuario) FROM usuario";

        return jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class) + 1;
    }

    public void createUsuario(Usuario auxUsuario){
        String sql = "INSERT INTO usuario (idUsuario, nombreUsuario, password) VALUES(?,?,?)";
        jdbcTemplate.update(sql,
                new Object[] { auxUsuario.getV_id(), auxUsuario.getV_nombre(), auxUsuario.getV_password() });
    }

    public void updateUsuario(Usuario auxUsuario){
        String sql = "UPDATE usuario SET nombreUsuario  = ?, password  = ? WHERE idUsuario = ?";
        jdbcTemplate.update(sql,
                new Object[] { auxUsuario.getV_nombre(), auxUsuario.getV_password(), auxUsuario.getV_id()});
    }

    public void deleteUsuario(Usuario auxUsuario){
        String sql = "DELETE FROM usuario WHERE idUsuario= ?";
        jdbcTemplate.update(sql,
                new Object[] { auxUsuario.getV_id() });
    }

}