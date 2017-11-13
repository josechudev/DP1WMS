package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryCajon;
import com.dp1wms.model.Cajon;
import com.dp1wms.model.Rack;
import com.dp1wms.util.RackUtil;
import javafx.geometry.Point2D;
import org.postgresql.geometric.PGpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class RepositoryCajonImpl implements RepositoryCajon {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int[] crear(Rack rack) {
        int altura, largo, idRack;
        ArrayList<Cajon> cajones = new ArrayList<>();

        altura = rack.getAltura();
        idRack = rack.getIdRack();

        if (RackUtil.tieneOrientacionHorizontal(rack)){
            largo = (int) rack.getPosicionFinal().getX() - (int) rack.getPosicionInicial().getX() + 1;
        } else {
            largo = (int) rack.getPosicionFinal().getY() - (int) rack.getPosicionInicial().getY() + 1;
        }

        for(int i = 0; i < largo; i++){
            for (int j = 0; j < altura; j ++){
                Cajon cajon = new Cajon();
                cajon.setIdRack(idRack);
                cajon.setPosicion(new Point2D(i, j));
                cajones.add(cajon);
            }
        }

        String sql = "INSERT INTO cajon (idrack, posicion) values (?,?)";
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Cajon cajon = cajones.get(i);
                ps.setInt(1, cajon.getIdRack());
                ps.setObject(2, new PGpoint(cajon.getPosicion().getX(), cajon.getPosicion().getY()));
            }

            @Override
            public int getBatchSize() {
                return cajones.size();
            }
        });
    }

    @Override
    public int[] crear(ArrayList<Rack> racks) {
        int altura, largo, idRack;
        ArrayList<Cajon> cajones = new ArrayList<>();

        for(Rack rack: racks){
            altura = rack.getAltura();
            idRack = rack.getIdRack();

            if (RackUtil.tieneOrientacionHorizontal(rack)){
                largo = (int) rack.getPosicionFinal().getX() - (int) rack.getPosicionInicial().getX() + 1;
            } else {
                largo = (int) rack.getPosicionFinal().getY() - (int) rack.getPosicionInicial().getY() + 1;
            }

            for(int i = 0; i < largo; i++){
                for (int j = 0; j < altura; j ++){
                    Cajon cajon = new Cajon();
                    cajon.setIdRack(idRack);
                    cajon.setPosicion(new Point2D(i, j));
                    cajones.add(cajon);
                }
            }
        }

        String sql = "INSERT INTO cajon (idrack, posicion) values (?,?)";
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Cajon cajon = cajones.get(i);
                ps.setInt(1, cajon.getIdRack());
                ps.setObject(2, new PGpoint(cajon.getPosicion().getX(), cajon.getPosicion().getY()));
            }

            @Override
            public int getBatchSize() {
                return cajones.size();
            }
        });
    }

    @Override
    public int eliminar(int idRack) {
        String sql = "DELETE FROM cajon WHERE idrack = ?";
        return jdbcTemplate.update(sql, idRack);
    }

    @Override
    public int[] eliminar(ArrayList<Rack> racks) {
        return new int[0];
    }

}
