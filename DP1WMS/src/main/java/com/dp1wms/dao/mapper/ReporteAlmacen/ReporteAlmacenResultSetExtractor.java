package com.dp1wms.dao.mapper.ReporteAlmacen;

import com.dp1wms.model.ReporteAlmacen;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteAlmacenResultSetExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        ReporteAlmacen reporteAlmacen = new ReporteAlmacen();
        reporteAlmacen.setCodigo(resultSet.getString("codigo"));
        reporteAlmacen.setNombreproducto(resultSet.getString("nombreproducto"));
        reporteAlmacen.setDescripcion(resultSet.getString("descripcion"));
        reporteAlmacen.setStockMinimo(resultSet.getInt("stockminimo"));
        reporteAlmacen.setStockFisico(resultSet.getInt("stock_fisico"));
        reporteAlmacen.setPrecioCompra(resultSet.getFloat("preciocompra"));
        reporteAlmacen.setCantidadPedido(resultSet.getInt("cantidad_pedido"));
        reporteAlmacen.setStockLogico(resultSet.getInt("stock_logico"));
        return reporteAlmacen;

    }
}
