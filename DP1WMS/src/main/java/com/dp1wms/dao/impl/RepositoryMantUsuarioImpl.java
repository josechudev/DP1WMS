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
        String sql = "SELECT idUsuario, nombreUsuario, password " + "" +
                "FROM usuario ORDER BY idUsuario";
        try {
            return jdbcTemplate.query(sql,
                    new UsuarioRowMapper());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void createUsuario(Usuario auxUsuario){
        String sql = "INSERT INTO usuario (idUsuario, nombreUsuario, password)" +
                                "VALUES(default, ?, crypt(?, gen_salt('md5')) )";
        try {
            jdbcTemplate.update(sql,
                    new Object[]{auxUsuario.getV_nombre(), auxUsuario.getV_password()});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateUsuario(Usuario auxUsuario, boolean auxModificarPassword){
        if(auxModificarPassword){
            String sql = "UPDATE usuario SET nombreUsuario  = ?, " +
                        "password  = crypt(?, gen_salt('md5')) " +
                        "WHERE idUsuario = ?";
            try {
                jdbcTemplate.update(sql,
                        new Object[]{auxUsuario.getV_nombre(), auxUsuario.getV_password(), auxUsuario.getV_id()});
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            String sql = "UPDATE usuario SET nombreUsuario  = ? WHERE idUsuario = ?";
            try {
                jdbcTemplate.update(sql,
                        new Object[]{auxUsuario.getV_nombre(), auxUsuario.getV_id()});
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void deleteUsuario(Usuario auxUsuario){
        //No se elimina el usuario
    }

    public Usuario findUsuariobyId(int auxIdUser){
        String sql= "SELECT idUsuario, nombreUsuario, password " +
                "FROM usuario WHERE idUsuario = '"+ auxIdUser +"'";
        try {
            List<Usuario> auxUsuario = jdbcTemplate.query(sql, new UsuarioRowMapper());
            return auxUsuario.get(0);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean existeUsuario(String auxNameUser){
        String sql= "SELECT idUsuario, nombreUsuario, password " +
                "FROM usuario WHERE nombreUsuario = '"+ auxNameUser +"'";
        try {
            List<Usuario> auxUsuario = jdbcTemplate.query(sql, new UsuarioRowMapper());
            return (auxUsuario.size() != 0) ;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean coincideUsuarioId(String auxNameUser, int auxIdUser){
        Usuario auxUser = findUsuariobyId(auxIdUser);
        return (auxUser.getV_nombre()).equals(auxNameUser);
    }
}