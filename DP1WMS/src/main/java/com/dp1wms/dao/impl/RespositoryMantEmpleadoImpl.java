package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantEmpleado;
import com.dp1wms.dao.mapper.EmpleadoRowMapper;
import com.dp1wms.dao.mapper.UsuarioRowMapper;
import com.dp1wms.dao.mapper.UsuarioXEmpleadoRowMapper;
import com.dp1wms.model.Empleado;
import com.dp1wms.model.TipoEmpleado;
import com.dp1wms.model.UsuarioModel.Usuario;
import com.dp1wms.model.UsuarioModel.UsuarioXEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RespositoryMantEmpleadoImpl implements RepositoryMantEmpleado {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Empleado obtenerEmpleadoPorIdUsuario(long idUsuario){

        String sql = "SELECT e.*, tp.descripcion as tipoempleado " +
                    "FROM empleado e INNER JOIN tipoempleado tp " +
                        "ON e.idtipoempleado = tp.idtipoempleado " +
                    "WHERE idusuario = ?";
        try{
            Empleado empleado = (Empleado) jdbcTemplate.queryForObject(sql,
                    new Object[]{idUsuario}, (rs, id)->{
                        Empleado e = new Empleado();
                        TipoEmpleado te  = new TipoEmpleado();
                        e.setIdempleado(rs.getLong("idempleado"));
                        e.setIdusuario(rs.getLong("idusuario"));
                        e.setNumDoc(rs.getString("numDoc"));
                        e.setNombre(rs.getString("nombre"));
                        e.setApellidos(rs.getString("apellidos"));
                        e.setEmail(rs.getString("email"));
                        e.setIdtipoempleado(rs.getLong("idtipoempleado"));
                        te.setIdtipoempleado(rs.getLong("idtipoempleado"));
                        te.setDescripcion(rs.getString("tipoempleado"));
                        e.setTipoEmpleado(te);
                        return e;
                    });
            return empleado;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public List<UsuarioXEmpleado> obtenerUsuarioXEmpleadoPorIdUsuario(){

        String sql = "SELECT u.idUsuario, u.nombreUsuario, " +
                "e.numDoc, e.nombre, e.apellidos, " +
                "tp.descripcion " +
                "FROM usuario u INNER JOIN empleado e ON u.idusuario = e.idusuario " +
                "INNER JOIN tipoempleado tp ON e.idtipoempleado = tp.idtipoempleado";
        return jdbcTemplate.query(sql,
                new UsuarioXEmpleadoRowMapper());

    }

    public List<Empleado> selectAllEmpleado(){
        String sql = "SELECT idempleado, idusuario, numDoc, nombre, apellidos, email, idtipoempleado FROM empleado ORDER BY idempleado";
        return jdbcTemplate.query(sql,
                new EmpleadoRowMapper());
    }

    public void createEmpleado(Usuario auxUsuario, Empleado auxEmpleado, TipoEmpleado auxTipoEmpleado){
        String sql = "INSERT INTO empleado(idempleado, idusuario, numDoc, nombre, apellidos, email, idtipoempleado) " +
                                    "VALUES(default,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                new Object[] { this.findIdUsuario(auxUsuario), auxEmpleado.getNumDoc(), auxEmpleado.getNombre(), auxEmpleado.getApellidos(),
                        auxEmpleado.getEmail(), auxTipoEmpleado.getIdtipoempleado()});
    }

    public Usuario findUsuariobyName(String auxName){
        String sql= "SELECT idUsuario, nombreUsuario, password FROM usuario WHERE nombreUsuario = '"+ auxName +"'";
        List<Usuario> auxUsuario = jdbcTemplate.query(sql, new UsuarioRowMapper() );

        return auxUsuario.get(0);
    }
    private int findIdUsuario(Usuario auxUsuario){
        String sql= "SELECT idUsuario FROM usuario WHERE nombreUsuario = '"+ auxUsuario.getV_nombre() +"'";

        return jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
    }

    public void updateEmpleado(Usuario auxUsuario, Empleado auxEmpleado, TipoEmpleado auxTipoEmpleado){
        String sql = "UPDATE empleado SET numDoc = ?, nombre = ?, apellidos = ?, email = ?, idtipoempleado = ? WHERE idempleado= ? and idusuario = ?";
        jdbcTemplate.update(sql,
                new Object[] { auxEmpleado.getNumDoc(), auxEmpleado.getNombre(), auxEmpleado.getApellidos(), auxEmpleado.getEmail(), auxTipoEmpleado.getIdtipoempleado(),
                        auxEmpleado.getIdempleado(), auxUsuario.getV_id()});
    }

    public void deleteEmpleado(Usuario auxUsuario, Empleado auxEmpleado){
        String sql = "DELETE FROM empleado WHERE idempleado= ? and idusuario = ?";
        jdbcTemplate.update(sql,
                new Object[] { auxEmpleado.getIdempleado(), auxUsuario.getV_id() });
    }

}
