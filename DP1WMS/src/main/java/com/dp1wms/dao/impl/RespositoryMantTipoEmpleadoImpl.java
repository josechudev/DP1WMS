package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantEmpleado;
import com.dp1wms.dao.RepositoryMantTipoEmpleado;
import com.dp1wms.dao.mapper.EmpleadoRowMapper;
import com.dp1wms.dao.mapper.RolxSeccionMapper;
import com.dp1wms.dao.mapper.SeccionRowMapper;
import com.dp1wms.dao.mapper.TipoEmpleadoRowMapper;
import com.dp1wms.model.Empleado;
import com.dp1wms.model.RolxSeccion;
import com.dp1wms.model.Seccion;
import com.dp1wms.model.TipoEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RespositoryMantTipoEmpleadoImpl implements RepositoryMantTipoEmpleado {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TipoEmpleado> selectAllTipoEmpleado(){
        String sql = "SELECT idtipoempleado, descripcion FROM tipoempleado WHERE activo = true ORDER BY idtipoempleado";
        return jdbcTemplate.query(sql,
                new TipoEmpleadoRowMapper());
    }

    public void createTipoEmpleado(TipoEmpleado auxTipoEmpleado){
        String sql = "INSERT INTO tipoempleado(idtipoempleado, descripcion) VALUES(default,?)";
        jdbcTemplate.update(sql,
                new Object[] { auxTipoEmpleado.getDescripcion()});
    }

    public void updateTipoEmpleado(TipoEmpleado auxTipoEmpleado){
        String sql = "UPDATE tipoempleado SET descripcion = ? WHERE idtipoempleado = ?";
        jdbcTemplate.update(sql,
                new Object[] { auxTipoEmpleado.getDescripcion(), auxTipoEmpleado.getIdtipoempleado() });
    }

    public void deleteTipoEmpleado(TipoEmpleado auxTipoEmpleado){
        String sql = "UPDATE tipoempleado SET activo = false WHERE idtipoempleado= ?";
        //String sql = "DELETE FROM tipoempleado WHERE idtipoempleado= ?";
        jdbcTemplate.update(sql,
                new Object[] { auxTipoEmpleado.getIdtipoempleado() });
    }

    public TipoEmpleado obtenerTipoEmpleadoPorIdTipo(Long auxIdTipo){
        String sql= "SELECT idtipoempleado, descripcion FROM tipoempleado WHERE idtipoempleado = '"+ Long.toString(auxIdTipo) +"' and activo = true";
        List<TipoEmpleado> auxTipoEmpleado = jdbcTemplate.query(sql, new TipoEmpleadoRowMapper() );

        return auxTipoEmpleado.get(0);
    }

    public TipoEmpleado obtenerTipoEmpleadoPorDescripcion(String auxDescripcion){
        String sql= "SELECT idtipoempleado, descripcion FROM tipoempleado WHERE descripcion = '"+ auxDescripcion +"' and activo = true";
        List<TipoEmpleado> auxTipoEmpleado = jdbcTemplate.query(sql, new TipoEmpleadoRowMapper() );

        return auxTipoEmpleado.get(0);
    }



    //Para Mantenimiento de Roles
    public List<TipoEmpleado> buscarTipoEmpleado(String descripcion){
        try{
            String sql = "SELECT idtipoempleado, descripcion, activo FROM tipoempleado " +
                    "WHERE lower(descripcion) LIKE ? ORDER BY idtipoempleado";
            descripcion = "%" + descripcion.toLowerCase() + "%";
            List<TipoEmpleado> tiposEmpleado = jdbcTemplate.query(sql, new Object[]{
                    descripcion
            }, (res,i)->{
                TipoEmpleado te = new TipoEmpleado();
                te.setIdtipoempleado(res.getLong("idtipoempleado"));
                te.setDescripcion(res.getString("descripcion"));
                te.setActivo(res.getBoolean("activo"));
                return te;
            });
            return tiposEmpleado;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Seccion> obtenerTodasLasSecciones(){
        try{
            String sql = "SELECT * FROM seccion ORDER BY idseccion";
            List<Seccion> seccions = jdbcTemplate.query(sql, new SeccionRowMapper());
            return seccions;
        } catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    public List<Seccion> obtenerSeccionesDeTipoEmpleado(long idTipoEmpleado){
        try{
            String sql = "SELECT s.idseccion, s.descripcion, " +
                    "(tes.idtipoempleado is not null) as seleccionado " +
                    "FROM seccion s LEFT JOIN tipoempleadoxseccion tes " +
                    "ON tes.idseccion = s.idseccion " +
                    "WHERE tes.idtipoempleado = ? OR tes.idtipoempleado is null " +
                    "ORDER BY s.idseccion";
            List<Seccion> seccions = jdbcTemplate.query(sql, new Object[]{idTipoEmpleado},
                    (res, i)->{
                        Seccion s = new Seccion();
                        s.setIdSeccion(res.getInt("idseccion"));
                        s.setDescripcion(res.getString("descripcion"));
                        s.setSeleccionado(res.getBoolean("seleccionado"));
                        return s;
                    });
            return seccions;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean actualizarPermisos(long idTipoEmpleado,
                               ArrayList<Seccion> inserts,
                               ArrayList<Seccion> updates,
                               ArrayList<Seccion> deletes){
        return false;
    }

    public TipoEmpleado crearTipoEmpleado(TipoEmpleado tipoEmpleado){
        try{
            String sql = "INSERT INTO tipoempleado (descripcion) " +
                    "VALUES (?) RETURNING idtipoempleado";
            TipoEmpleado te = jdbcTemplate.queryForObject(sql, new Object[]{
                    tipoEmpleado.getDescripcion()},
                    (res, i)->{
                        TipoEmpleado teAux = new TipoEmpleado();
                        teAux.setIdtipoempleado(res.getInt("idtipoempleado"));
                        return teAux;
                    });
            tipoEmpleado.setIdtipoempleado(te.getIdtipoempleado());
            tipoEmpleado.setActivo(true);
            return tipoEmpleado;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
