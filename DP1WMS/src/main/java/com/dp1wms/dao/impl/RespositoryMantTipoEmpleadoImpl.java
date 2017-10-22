package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantEmpleado;
import com.dp1wms.dao.RepositoryMantTipoEmpleado;
import com.dp1wms.dao.mapper.EmpleadoRowMapper;
import com.dp1wms.dao.mapper.TipoEmpleadoRowMapper;
import com.dp1wms.model.Empleado;
import com.dp1wms.model.TipoEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RespositoryMantTipoEmpleadoImpl implements RepositoryMantTipoEmpleado {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TipoEmpleado> selectAllTipoEmpleado(){
        String sql = "SELECT idtipoempleado, descripcion FROM tipoempleado ORDER BY idtipoempleado";
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
        String sql = "DELETE FROM tipoempleado WHERE idtipoempleado= ?";
        jdbcTemplate.update(sql,
                new Object[] { auxTipoEmpleado.getIdtipoempleado() });
    }

}
