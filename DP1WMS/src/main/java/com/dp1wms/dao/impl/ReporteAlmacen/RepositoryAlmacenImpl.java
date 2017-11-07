package com.dp1wms.dao.impl.ReporteAlmacen;

import com.dp1wms.dao.IReporteAlmacen.RepositoryReporteAlmacen;
import com.dp1wms.dao.mapper.ReporteAlmacen.ReporteAlmacenResultSetExtractor;
import com.dp1wms.dao.mapper.ReporteAlmacen.ReporteAlmacenRowMapper;
import com.dp1wms.model.ReporteAlmacen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RepositoryAlmacenImpl implements RepositoryReporteAlmacen {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<ReporteAlmacen> selectAllKardexFila(String fecInicio, String fecFin) {
        String sql = "select p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock stock_fisico, coalesce(p.preciocompra,0) preciocompra,\n" +
                "sum(coalesce(dp.cantidad,0)) cantidad_pedido, (p.stock-sum(coalesce(dp.cantidad,0))) stock_logico\n" +
                "from producto as p\n" +
                "left join detallepedido as dp on dp.idproducto = p.idproducto\n" +
                "left join pedido as pd on pd.idpedido = dp.idpedido\n" +
                "where (not pd.esdevolucion) and pd.idestadopedido = 1 and pd.fechacreacion >= ? and pd.fechacreacion<= ?\n" +
                "group by p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock, p.preciocompra;\n";
        return jdbcTemplate.query(sql,new Object[]{fecInicio,fecFin},new ReporteAlmacenRowMapper());
    }
}
