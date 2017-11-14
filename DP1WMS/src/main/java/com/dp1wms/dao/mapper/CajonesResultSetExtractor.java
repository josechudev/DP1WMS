package com.dp1wms.dao.mapper;

import com.dp1wms.model.Cajon;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CajonesResultSetExtractor implements ResultSetExtractor{

    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        Cajon cajon = new Cajon();
        cajon.setIdRack(resultSet.getInt("idrack"));
        cajon.setPosX(resultSet.getInt("posx"));

        System.out.println("query");
        System.out.println(cajon.getIdRack());
        System.out.println(cajon.getPosX());
        return cajon;
    }


}
