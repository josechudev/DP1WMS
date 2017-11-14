package com.dp1wms.dao.mapper;

import com.dp1wms.model.tabu.Rack;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RackResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException{

        int ini_posx = resultSet.getInt("ini_posx");
        int ini_posy = resultSet.getInt("ini_posy");
        Point inicio = new Point(ini_posx, ini_posy);

        int fin_posx = resultSet.getInt("fin_posx");
        int fin_posy = resultSet.getInt("fin_posy");
        Point fin = new Point(fin_posx,fin_posy);

        Rack rack = new Rack(inicio,fin);
        rack.setNiveles(resultSet.getInt("altura"));
        rack.setId_rack(resultSet.getInt("idrack"));

        return rack;
    }
}