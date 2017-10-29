package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantArea;
import com.dp1wms.dao.mapper.AreaRowMapper;
import com.dp1wms.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RepositoryMantAreaImpl implements RepositoryMantArea {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public RepositoryMantAreaImpl(){}

    public Area getAreaById(int idArea) {
        String sql = "SELECT TOP 1 FROM area WHERE idarea = ?";
        return (Area) jdbcTemplate.queryForObject(sql, new Object[] {idArea}, new AreaRowMapper());
    }

    public List<Area> getAreasByIdAlmacen(int idAlmacen) {
        String sql = "select * from area where idalmacen = ?";
        return jdbcTemplate.query(sql, new Object[] {idAlmacen}, new AreaRowMapper());
    }

    @Transactional (rollbackFor = Exception.class)
    public int crearArea(Area auxArea) {
        String sql = "INSERT INTO area (idalmacen, posicioninicial, posicionfinal, codigo)" +
                     "VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, auxArea.getIdAlmacen(), auxArea.getPosicionInicial(), auxArea.getPosicionFinal(), auxArea.getCodigo());
    }

    @Transactional (rollbackFor = Exception.class)
    public int editarArea(Area auxArea) {
        return 0;
    }

    @Transactional (rollbackFor = Exception.class)
    public int eliminarArea(Area auxArea) {
        return 0;
    }
}
