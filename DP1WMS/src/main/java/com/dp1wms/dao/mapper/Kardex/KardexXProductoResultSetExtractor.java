package com.dp1wms.dao.mapper.Kardex;

import com.dp1wms.model.KardexXProducto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KardexXProductoResultSetExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        KardexXProducto kardexXProducto = new KardexXProducto();
        kardexXProducto.setNombre(resultSet.getString("nombreproducto"));
        kardexXProducto.setcIngresos(resultSet.getInt("ingresos"));
        kardexXProducto.setcSalidas(resultSet.getInt("salidas"));
        kardexXProducto.setDescripcion(resultSet.getString("descripcion"));
        kardexXProducto.setPrecioVenta(resultSet.getFloat("precio"));
        kardexXProducto.setPrecioCompra(resultSet.getFloat("preciocompra"));
        kardexXProducto.setValorTotalC(resultSet.getFloat("valortotalc"));
        kardexXProducto.setValorTotalV(resultSet.getFloat("valortotalv"));
        kardexXProducto.setIngreso(resultSet.getBoolean("esingreso"));
        return kardexXProducto;

    }
}
