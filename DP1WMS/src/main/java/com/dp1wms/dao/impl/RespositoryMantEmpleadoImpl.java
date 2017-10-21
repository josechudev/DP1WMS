package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantEmpleado;
import com.dp1wms.model.Empleado;
import com.dp1wms.model.TipoEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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
}
