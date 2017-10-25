package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantAlmacen;
import com.dp1wms.dao.mapper.AlmacenRowMapper;
import com.dp1wms.model.Almacen;
import com.dp1wms.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositoryMantAlmacenImpl implements RepositoryMantAlmacen{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Almacen> obtenerAlmacenes() {
        String sql = "SELECT * FROM almacen ORDER BY idalmacen";
        return (List<Almacen>) jdbcTemplate.query(sql, new AlmacenRowMapper());
    }

    @Transactional (rollbackFor = Exception.class)
    public int crearAlmacen(Almacen auxAlmacen){
        String sql = "INSERT INTO almacen (nombre, direccion, largo, ancho) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, auxAlmacen.getNombre(), auxAlmacen.getDireccion(), auxAlmacen.getLargo(), auxAlmacen.getAncho());
    }

    @Transactional (rollbackFor = Exception.class)
    public int eliminarAlmacen(Almacen auxAlmacen){
        String sql= "DELETE FROM almacen WHERE idAlmacen = ?";
        return jdbcTemplate.update(sql, auxAlmacen.getIdAlmacen());
    }

    @Transactional (rollbackFor = Exception.class)
    public int editarAlmacen(Almacen auxAlmacen){
        String sql = "UPDATE almacen SET nombre = ?, direccion = ?, largo = ?, ancho = ? where idalmacen = ?";
        return jdbcTemplate.update(sql, auxAlmacen.getNombre(), auxAlmacen.getDireccion(), auxAlmacen.getLargo(),
                auxAlmacen.getAncho(), auxAlmacen.getIdAlmacen());
    }

    public Almacen obtenerAlmacenById() {
        return null;
    }
}
