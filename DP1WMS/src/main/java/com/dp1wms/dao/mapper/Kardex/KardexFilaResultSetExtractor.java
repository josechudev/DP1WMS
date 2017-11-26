package com.dp1wms.dao.mapper.Kardex;

import com.dp1wms.model.KardexFila;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KardexFilaResultSetExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        KardexFila kardexFila = new KardexFila();
        kardexFila.setIdMovimiento(resultSet.getInt(1));
        kardexFila.setIdTipoMovimiento(resultSet.getInt(2));
        kardexFila.setFechaMovimiento(resultSet.getString(3));
        kardexFila.setIdProducto(resultSet.getInt(4));
        kardexFila.setNombreProducto(resultSet.getString(5));
        kardexFila.setCantidad(resultSet.getInt(6));
        kardexFila.setDescripcion(resultSet.getString(7));
        kardexFila.setPrecioVenta(resultSet.getFloat(8));
        kardexFila.setPrecioCompra(resultSet.getFloat(9));
        kardexFila.setValorTotal(resultSet.getFloat(10));
        kardexFila.setEsIngreso(resultSet.getBoolean(11));
        kardexFila.setAuxIngreso(resultSet.getInt(12));
        return kardexFila;

    }
}
