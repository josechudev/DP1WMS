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
        String sql = "SELECT idUsuario, nombreUsuario, password FROM usuario ORDER BY idUsuario";
        return jdbcTemplate.query(sql,
                new UsuarioRowMapper());
    }

    public void createUsuario(Usuario auxUsuario){

        String sql = "INSERT INTO usuario (idUsuario, nombreUsuario, password)" +
                                "VALUES(default, ?, crypt(?, gen_salt('md5')) )";
        jdbcTemplate.update(sql,
                new Object[] { auxUsuario.getV_nombre(), auxUsuario.getV_password() });
    }

    public void updateUsuario(Usuario auxUsuario, boolean auxModificarPassword){
        if(auxModificarPassword){
            String sql = "UPDATE usuario SET nombreUsuario  = ?, password  = crypt(?, gen_salt('md5')) WHERE idUsuario = ?";
            jdbcTemplate.update(sql,
                    new Object[] { auxUsuario.getV_nombre(), auxUsuario.getV_password(), auxUsuario.getV_id()});
        }
        else{
            String sql = "UPDATE usuario SET nombreUsuario  = ? WHERE idUsuario = ?";
            jdbcTemplate.update(sql,
                    new Object[] { auxUsuario.getV_nombre(), auxUsuario.getV_id()});
        }
    }

    public void deleteUsuario(Usuario auxUsuario){
        //String sql = "DELETE FROM usuario WHERE idUsuario= ?";
        //String sql = "UPDATE usuario SET activo = false WHERE idUsuario= ?";
        //jdbcTemplate.update(sql,
        //        new Object[] { auxUsuario.getV_id() });
    }

    public Usuario findUsuariobyId(int auxIdUser){
        String sql= "SELECT idUsuario, nombreUsuario, password FROM usuario WHERE idUsuario = '"+ auxIdUser +"'";
        List<Usuario> auxUsuario = jdbcTemplate.query(sql, new UsuarioRowMapper() );

        return auxUsuario.get(0);
    }

}