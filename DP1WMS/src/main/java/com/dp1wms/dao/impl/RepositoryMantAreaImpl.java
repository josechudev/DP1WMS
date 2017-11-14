package com.dp1wms.dao.impl;

import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryMantArea;
import com.dp1wms.dao.mapper.AreaRowMapper;
import com.dp1wms.model.Area;
import com.dp1wms.util.PosicionFormatter;
import org.postgresql.geometric.PGpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryMantAreaImpl implements RepositoryMantArea {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MainController mainController;

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
        String sql = "INSERT INTO area (idalmacen, posicioninicial, posicionfinal, codigo, idempleadoauditado)" +
                     "VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, auxArea.getIdAlmacen());
                ps.setObject(2, new PGpoint(auxArea.getPosicionInicial().getX(), auxArea.getPosicionInicial().getY()));
                ps.setObject(3, new PGpoint(auxArea.getPosicionFinal().getX(), auxArea.getPosicionFinal().getY()));
                ps.setString(4, auxArea.getCodigo());
                ps.setLong(5, mainController.getEmpleado().getIdempleado());
                return ps;
            }
        });
    }

    @Transactional (rollbackFor = Exception.class)
    public int editarArea(Area auxArea) {
        String sql = "UPDATE area SET posicioninicial = (?), posicionfinal = (?), codigo = ?, idempleadoauditado = ? where idarea = ?";
        return jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setObject(1, new PGpoint(auxArea.getPosicionInicial().getX(), auxArea.getPosicionInicial().getY()));
                ps.setObject(2, new PGpoint(auxArea.getPosicionFinal().getX(), auxArea.getPosicionFinal().getY()));
                ps.setString(3, auxArea.getCodigo());
                ps.setLong(4, mainController.getEmpleado().getIdempleado());
                ps.setInt(5, auxArea.getIdArea());
                return ps;
            }
        });
    }

    @Transactional (rollbackFor = Exception.class)
    public int eliminarArea(Area auxArea) {
        String sql = "DELETE FROM area WHERE idarea = ?";
        return jdbcTemplate.update(sql, auxArea.getIdArea());
    }
}
