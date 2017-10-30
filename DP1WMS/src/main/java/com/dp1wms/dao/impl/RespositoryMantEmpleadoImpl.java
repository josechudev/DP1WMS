package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantEmpleado;
import com.dp1wms.dao.mapper.EmpleadoRowMapper;
import com.dp1wms.dao.mapper.TipoEmpleadoRowMapper;
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
                "INNER JOIN tipoempleado tp ON e.idtipoempleado = tp.idtipoempleado " +
                "WHERE e.activo = true " +
                "ORDER BY u.idUsuario";
        return jdbcTemplate.query(sql,
                new UsuarioXEmpleadoRowMapper());

    }

    public List<Empleado> selectAllEmpleado(){
        String sql = "SELECT idempleado, idusuario, numDoc, nombre, apellidos, email, idtipoempleado FROM empleado WHERE activo = true ORDER BY idempleado";
        return jdbcTemplate.query(sql,
                new EmpleadoRowMapper());
    }

    public void createEmpleado(Usuario auxUsuario, Empleado auxEmpleado, TipoEmpleado auxTipoEmpleado, Long auxIdEmpleadoAuditado){
        String sql = "INSERT INTO empleado(idempleado, idusuario, numDoc, nombre, apellidos, email, idtipoempleado, idEmpleadoAuditado) " +
                                    "VALUES(default,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                new Object[] { this.findIdUsuario(auxUsuario), auxEmpleado.getNumDoc(), auxEmpleado.getNombre(), auxEmpleado.getApellidos(),
                        auxEmpleado.getEmail(), auxTipoEmpleado.getIdtipoempleado(), auxIdEmpleadoAuditado});
    }

    public Usuario findUsuariobyName(String auxName){
        String sql= "SELECT u.idUsuario, u.nombreUsuario, u.password " +
                "FROM usuario u INNER JOIN empleado e ON u.idusuario = e.idusuario " +
                "WHERE u.nombreUsuario = '"+ auxName +"' and e.activo = true";
        List<Usuario> auxUsuario = jdbcTemplate.query(sql, new UsuarioRowMapper() );

        return auxUsuario.get(0);
    }

    private int findIdUsuario(Usuario auxUsuario){
        String sql= "SELECT u.idUsuario " +
                "FROM usuario u " +
                "WHERE u.nombreUsuario = '"+ auxUsuario.getV_nombre() +"' " +
                "ORDER BY u.idUsuario DESC";

        return jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
    }

    public void updateEmpleado(Usuario auxUsuario, Empleado auxEmpleado, TipoEmpleado auxTipoEmpleado, Long auxIdEmpleadoAuditado){
        String sql = "UPDATE empleado SET numDoc = ?, nombre = ?, apellidos = ?, email = ?, idtipoempleado = ? , idEmpleadoAuditado = ? " +
                "WHERE idempleado= ? and idusuario = ?";
        jdbcTemplate.update(sql,
                new Object[] { auxEmpleado.getNumDoc(), auxEmpleado.getNombre(), auxEmpleado.getApellidos(), auxEmpleado.getEmail(), auxTipoEmpleado.getIdtipoempleado(),
                        auxIdEmpleadoAuditado,
                        auxEmpleado.getIdempleado(), auxUsuario.getV_id()});
    }

    public void deleteEmpleado(Usuario auxUsuario, Empleado auxEmpleado, Long auxIdEmpleadoAuditado){
        //String sql = "DELETE FROM empleado WHERE idempleado= ? and idusuario = ?";
        String sql = "UPDATE empleado SET activo = false , idEmpleadoAuditado = ? " +
                "WHERE idempleado= ? and idusuario = ?";
        jdbcTemplate.update(sql,
                new Object[] { auxIdEmpleadoAuditado,
                        auxEmpleado.getIdempleado(), auxUsuario.getV_id() });
    }

    public void cargaMasivaDatos(List<Empleado> auxListaEmpleado, List<String> auxNombreUsuario, List<String> auxNombreTipoEmpleado){
        //Delete all empleados

        for(int i = 0; i < auxListaEmpleado.size(); i++){
            Usuario auxUsuario = findUsuariobyName(auxNombreUsuario.get(i));
            TipoEmpleado auxTipoEmepleado = getTipoEmpleadoPorDescripcion(auxNombreTipoEmpleado.get(i));

            String sql = "INSERT INTO empleado(idempleado, idusuario, numDoc, nombre, apellidos, email, idtipoempleado) " +
                    "VALUES(default,?,?,?,?,?,?)";
            jdbcTemplate.update(sql,
                    new Object[] { this.findIdUsuario(auxUsuario), auxListaEmpleado.get(i).getNumDoc(), auxListaEmpleado.get(i).getNombre(),
                            auxListaEmpleado.get(i).getApellidos(), auxListaEmpleado.get(i).getEmail(), auxTipoEmepleado.getIdtipoempleado()});
        }
    }

    private TipoEmpleado getTipoEmpleadoPorDescripcion(String auxDescripcion){
        String sql= "SELECT idtipoempleado, descripcion FROM tipoempleado WHERE descripcion = '"+ auxDescripcion +"' and activo = true";
        List<TipoEmpleado> auxTipoEmpleado = jdbcTemplate.query(sql, new TipoEmpleadoRowMapper() );

        return auxTipoEmpleado.get(0);
    }

}
