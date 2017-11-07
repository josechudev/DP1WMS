package com.dp1wms.dao.mapper;

import com.dp1wms.model.Area;
import javafx.geometry.Point2D;
import org.postgresql.geometric.PGpoint;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaResultSetExtractor implements ResultSetExtractor{

    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Area area = new Area();
        area.setIdArea(resultSet.getInt(1));
        area.setIdAlmacen(resultSet.getInt(2));
        area.setPosicionInicial((PGpoint) resultSet.getObject(3));
        area.setPosicionFinal((PGpoint) resultSet.getObject(4));
        area.setCodigo(resultSet.getString(5));
        return area;
    }
}
