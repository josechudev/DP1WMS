package com.dp1wms.dao.mapper;

import com.dp1wms.model.Rack;
import org.postgresql.geometric.PGpoint;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RackResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Rack rack = new Rack();
        rack.setIdRack(resultSet.getInt(1));
        rack.setIdArea(resultSet.getInt(2));
        rack.setPosicionInicial((PGpoint) resultSet.getObject(3));
        rack.setPosicionFinal((PGpoint) resultSet.getObject(4));
        rack.setAltura(resultSet.getInt(5));
        rack.setLongitudCajon(resultSet.getInt(6));
        rack.setCodigo(resultSet.getString(7));
        return rack;
    }
}
