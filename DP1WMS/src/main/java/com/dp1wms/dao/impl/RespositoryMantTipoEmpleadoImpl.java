package com.dp1wms.dao.impl;

import com.dp1wms.controller.MainController;
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
import org.springframework.transaction.annotation.Transactional;
import sun.applet.Main;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RespositoryMantTipoEmpleadoImpl implements RepositoryMantTipoEmpleado {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MainController mainController;

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
                    "AND tes.idtipoempleado = ? " +
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

    public int obtenerNumEmpleadosDeTipoEmp(long idTipoEmpleado){
        try{
            String sql = "SELECT count(idempleado) FROM empleado WHERE idtipoempleado = ?";
            Object[] params = {idTipoEmpleado};
            return jdbcTemplate.queryForObject(sql, params, Integer.class);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Transactional (rollbackFor = Exception.class)
    public boolean actualizarPermisos(TipoEmpleado tipoEmpleado,
                               List<Seccion> secciones){
        try{
            String sql = "UPDATE  tipoempleado set descripcion = ?, activo = ?, idempleadoauditado = ? " +
                    "WHERE idtipoempleado = ?";
            Object[] params = {
                    tipoEmpleado.getDescripcion(),
                    tipoEmpleado.getActivo(),
                    this.mainController.getEmpleado().getIdempleado(),
                    tipoEmpleado.getIdtipoempleado()
            };
            jdbcTemplate.update(sql, params);

            String sql2 = "UPDATE tipoempleadoxseccion set idempleadoauditado = ? " +
                    "WHERE idtipoempleado = ?";
            Object[] params2 = {this.mainController.getEmpleado().getIdempleado(),
                                tipoEmpleado.getIdtipoempleado()};
            jdbcTemplate.update(sql2, params2);

            String sql3 = "DELETE FROM tipoempleadoxseccion WHERE idtipoempleado = ?";
            Object[] params3 = {tipoEmpleado.getIdtipoempleado()};
            int[] types = {Types.BIGINT};
            jdbcTemplate.update(sql3, params3, types);

            String sql4 = "INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, " +
                    "idempleadoauditado) VALUES (?,?,?)";
            for(Seccion seccion: secciones){
                if(seccion.isSeleccionado()){
                    Object[] params4 = {
                            tipoEmpleado.getIdtipoempleado(),
                            seccion.getIdSeccion(),
                            this.mainController.getEmpleado().getIdempleado()
                    };
                    jdbcTemplate.update(sql4, params4);
                }
            }

            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional (rollbackFor = Exception.class)
    public TipoEmpleado crearTipoEmpleado(TipoEmpleado tipoEmpleado, List<Seccion> secciones){
        try{
            String sql = "INSERT INTO tipoempleado (descripcion, idempleadoauditado) " +
                    "VALUES (?, ?) RETURNING idtipoempleado";
            TipoEmpleado te = jdbcTemplate.queryForObject(sql, new Object[]{
                    tipoEmpleado.getDescripcion(),
                    this.mainController.getEmpleado().getIdempleado()
                    },
                    (res, i)->{
                        TipoEmpleado teAux = new TipoEmpleado();
                        teAux.setIdtipoempleado(res.getInt("idtipoempleado"));
                        return teAux;
                    });
            tipoEmpleado.setIdtipoempleado(te.getIdtipoempleado());
            tipoEmpleado.setActivo(true);

            String sql2 = "INSERT INTO tipoempleadoxseccion (idtipoempleado, idseccion, " +
                    "idempleadoauditado) VALUES (?,?,?)";
            for(Seccion seccion: secciones){
                if(seccion.isSeleccionado()){
                    jdbcTemplate.update(sql2, new Object[]{
                        tipoEmpleado.getIdtipoempleado(),
                        seccion.getIdSeccion(),
                        this.mainController.getEmpleado().getIdempleado()
                    });
                }
            }

            return tipoEmpleado;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
