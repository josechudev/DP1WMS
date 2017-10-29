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
        String sql = "SELECT idtipoempleado, descripcion " +
                "FROM tipoempleado WHERE activo = true ORDER BY idtipoempleado";
        try{
            return jdbcTemplate.query(sql,
                    new TipoEmpleadoRowMapper());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void createTipoEmpleado(TipoEmpleado auxTipoEmpleado){
        String sql = "INSERT INTO tipoempleado(idtipoempleado, descripcion) VALUES(default,?)";
        try{
            jdbcTemplate.update(sql,
                    new Object[] { auxTipoEmpleado.getDescripcion()});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateTipoEmpleado(TipoEmpleado auxTipoEmpleado){
        String sql = "UPDATE tipoempleado SET descripcion = ? WHERE idtipoempleado = ?";
        try{
            jdbcTemplate.update(sql,
                    new Object[] { auxTipoEmpleado.getDescripcion(), auxTipoEmpleado.getIdtipoempleado() });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteTipoEmpleado(TipoEmpleado auxTipoEmpleado){
        String sql = "UPDATE tipoempleado SET activo = false WHERE idtipoempleado= ?";
        try{
            jdbcTemplate.update(sql,
                    new Object[] { auxTipoEmpleado.getIdtipoempleado() });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public TipoEmpleado obtenerTipoEmpleadoPorIdTipo(Long auxIdTipo){
        String sql= "SELECT idtipoempleado, descripcion FROM tipoempleado WHERE idtipoempleado = '"+ Long.toString(auxIdTipo) +"' and activo = true";
        try {
            List<TipoEmpleado> auxTipoEmpleado = jdbcTemplate.query(sql, new TipoEmpleadoRowMapper());
            return auxTipoEmpleado.get(0);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public TipoEmpleado obtenerTipoEmpleadoPorDescripcion(String auxDescripcion) {
        String sql = "SELECT idtipoempleado, descripcion FROM tipoempleado WHERE descripcion = '" + auxDescripcion + "' and activo = true";
        try{
            List<TipoEmpleado> auxTipoEmpleado = jdbcTemplate.query(sql, new TipoEmpleadoRowMapper());
            return auxTipoEmpleado.get(0);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    //Para Mantenimiento de Roles


    public List<Seccion> selectAllPermisos(){
        String sql = "SELECT * from Seccion";
        return jdbcTemplate.query(sql, new SeccionRowMapper());
    }

    public List<RolxSeccion> selectAllRolesxSeccion(){
        String sql = "SELECT * from TipoEmpleadoxSeccion";
        return jdbcTemplate.query(sql, new RolxSeccionMapper());
    }

    //obtener empleado por nombre
    public TipoEmpleado getTipoEmpleadoxnombre(String nombre){
        String sql= "SELECT idtipoempleado, descripcion FROM tipoempleado WHERE descripcion = '"+ nombre +"'";
        List<TipoEmpleado> tipos_empleado = jdbcTemplate.query(sql, new TipoEmpleadoRowMapper() );
        try {
            return tipos_empleado.get(0);
        }
        catch(Exception e){
            TipoEmpleado empleado = new TipoEmpleado();
            empleado.setDescripcion("Nulo");
            return empleado;
        }
    }

    //obtener seccion por nombre
    public Seccion getSeccionpornombre(String nombre){
        String sql = "SELECT idSeccion, descripcion FROM Seccion WHERE descripcion ='" + nombre + "'";
        List<Seccion> secciones = jdbcTemplate.query(sql, new SeccionRowMapper());
        return secciones.get(0);
    }

    //obtener permisos para tipo de Empleado
    public List<String> getPermisosTipoEmpleado(String nombre){
        TipoEmpleado empleado = this.getTipoEmpleadoxnombre(nombre);

        //get lista de ids secciones
        String sql = "SELECT * FROM TipoEmpleadoXSeccion WHERE idtipoempleado = " + Long.toString(empleado.getIdtipoempleado());
        List<RolxSeccion> idssecciones = jdbcTemplate.query(sql, new RolxSeccionMapper());
        List<Integer> ids = new ArrayList<Integer>();
        for(RolxSeccion r: idssecciones){
            ids.add(r.getIdSeccion());
        }

        List<String> secciones = new ArrayList<String>();
        for(Integer id: ids){
            String sql2 = "SELECT * FROM Seccion WHERE idSeccion = " + Integer.toString(id);
            List<Seccion> lista_secciones= jdbcTemplate.query(sql2, new SeccionRowMapper());
            secciones.add(lista_secciones.get(0).getDescripcion());
        }
        return secciones;
    }

    //insertar en la tabla de secciones (no usar luego de primer insert)
    public void insertarPermisos(String permiso){
        String sql = "INSERT INTO Seccion(idSeccion, descripcion) VALUES(default,?)";
        jdbcTemplate.update(sql,
                new Object[] {permiso});
    }

    //insertar tipo de empleado
    public void insertarTipoEmpleado(String nombre_rol){
        String sql = "INSERT INTO tipoempleado(idtipoempleado, descripcion) VALUES(default,?) ";
        jdbcTemplate.update(sql, new Object []{nombre_rol});
    }

    //asignar permiso a usuario
    public void asignarPermiso(String nombre_rol, String nombre_permiso){
        TipoEmpleado empleado = this.getTipoEmpleadoxnombre(nombre_rol);
        Seccion seccion = this.getSeccionpornombre(nombre_permiso);

        int id_empleado = ((int) empleado.getIdtipoempleado());
        int id_seccion = seccion.getIdSeccion();

        String sql = "INSERT INTO TipoEmpleadoXSeccion(idTipoEmpleado,idSeccion) VALUES(?,?)";
        jdbcTemplate.update(sql, new Object[] {id_empleado, id_seccion});
    }

    //borrar todos los permisos de usuario
    public void borrarTodosPermisos(String nombre_rol){
        TipoEmpleado empleado = this.getTipoEmpleadoxnombre(nombre_rol);
        String sql = "DELETE FROM TipoEmpleadoXSeccion WHERE idTipoEmpleado = " + Long.toString(empleado.getIdtipoempleado()) ;
        jdbcTemplate.update(sql);
    }

    //asignar permisos
    public void asignarTodosPermisos(String nombre_rol, List<String> permisos){
        TipoEmpleado e = this.getTipoEmpleadoxnombre(nombre_rol);
        if (e.getDescripcion() == "Nulo"){
            this.insertarTipoEmpleado(nombre_rol);
        }

        this.borrarTodosPermisos(nombre_rol);

        for(String permiso: permisos){
            this.asignarPermiso(nombre_rol,permiso);
        }
    }

    public void actualizarPermisos(String nombre_rol, List<String> permisos){
        this.borrarTodosPermisos(nombre_rol);
        this.asignarTodosPermisos(nombre_rol,permisos);
    }
}
