package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantRack;
import com.dp1wms.dao.mapper.RackRowMapper;
import com.dp1wms.model.Rack;
import org.postgresql.geometric.PGpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryMantRackImpl implements RepositoryMantRack {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Rack> getRacksByAlmacenId(int almacenId) {
        String sql = "SELECT * FROM rack INNER JOIN area ON area.idarea=rack.idarea WHERE idalmacen = ?";
        return jdbcTemplate.query(sql, new Object[]{almacenId}, new RackRowMapper());
    }

    @Override
    public List<Rack> getRacksByAreaId(int areaId) {
        String sql = "SELECT * rack WHERE idarea = ?";
        return jdbcTemplate.query(sql, new Object[] {areaId}, new RackRowMapper());
    }

    @Override
    public int crearRack(Rack rack) {
        String sql = "INSERT INTO rack (idarea, posicioninicial, posicionfinal, altura, longitudcajon, codigo)" +
                " values (?,?,?,?,?,?)";
        return jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, rack.getIdRack());
                ps.setObject(2, new PGpoint(rack.getPosicionInicial().getX(), rack.getPosicionInicial().getY()));
                ps.setObject(3, new PGpoint(rack.getPosicionFinal().getX(), rack.getPosicionFinal().getY()));
                ps.setInt(4, rack.getAltura());
                ps.setInt(5, rack.getLongitudCajon());
                ps.setString(6, rack.getCodigo());
                return ps;
            }
        });
    }

    @Override
    public int eliminarRack(Rack rack) {
        String sql = "DELETE FROM rack WHERE idrack = ?";
        return jdbcTemplate.update(sql, rack.getIdRack());
    }

    @Override
    public int editarRack(Rack rack) {
        String sql = "UPDATE rack SET posicioninicial = ?, posicionfinal = ?, altura = ?, longitudcajon = ?, codigo = ?" +
                "where idrack = ?";
        return jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, rack.getIdRack());
                ps.setObject(2, new PGpoint(rack.getPosicionInicial().getX(), rack.getPosicionInicial().getY()));
                ps.setObject(3, new PGpoint(rack.getPosicionFinal().getX(), rack.getPosicionFinal().getY()));
                ps.setInt(4, rack.getAltura());
                ps.setInt(5, rack.getLongitudCajon());
                ps.setString(6, rack.getCodigo());
                return ps;
            }
        });
    }
}
