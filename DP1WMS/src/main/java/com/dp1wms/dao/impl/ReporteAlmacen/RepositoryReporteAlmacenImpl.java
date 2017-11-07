package com.dp1wms.dao.impl.ReporteAlmacen;

import com.dp1wms.dao.IReporteAlmacen.RepositoryReporteAlmacen;
import com.dp1wms.dao.mapper.ReporteAlmacen.ReporteAlmacenRowMapper;
import com.dp1wms.model.ReporteAlmacen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryReporteAlmacenImpl implements RepositoryReporteAlmacen {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ReporteAlmacen> selectAllKardexFila(String fecInicio, String fecFin) {
        String sql = "select p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock stock_fisico, coalesce(p.preciocompra,0) preciocompra,\n" +
                "sum(coalesce(de.cantidad,0)) cantidad_pedido, (p.stock-sum(coalesce(de.cantidad,0))) stock_logico\n" +
                "from producto as p\n" +
                "left join detallepedido as dp on dp.idproducto = p.idproducto\n" +
                "left join pedido as pd on pd.idpedido = dp.idpedido\n" +
                "left join envio as e on e.idpedido = pd.idpedido\n"+
                "left join detalleenvio as de on de.idenvio = e.idenvio\n"+
                "where (not pd.esdevolucion) and pd.idestadopedido in (1,5) and not(e.realizado) and pd.idestadopedido = 1 and pd.fechacreacion >= ?::DATE and pd.fechacreacion<= ?::DATE \n" +
                "group by p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock, p.preciocompra;\n";
        return jdbcTemplate.query(sql, new Object[]{fecInicio, fecFin}, new ReporteAlmacenRowMapper());
    }

    @Override
    public List<ReporteAlmacen> selectAllKardexFila() {
        String sql = "select p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock stock_fisico, coalesce(p.preciocompra,0) preciocompra,\n" +
                "sum(coalesce(de.cantidad,0)) cantidad_pedido, (p.stock-sum(coalesce(de.cantidad,0))) stock_logico\n" +
                "from producto as p\n" +
                "left join detallepedido as dp on dp.idproducto = p.idproducto\n" +
                "left join pedido as pd on pd.idpedido = dp.idpedido\n" +
                "left join envio as e on e.idpedido = pd.idpedido\n"+
                "left join detalleenvio as de on de.idenvio = e.idenvio\n"+
                "where (not pd.esdevolucion) and pd.idestadopedido in (1,5) and not(e.realizado)\n" +
                "group by p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock, p.preciocompra;\n";
        return jdbcTemplate.query(sql, new ReporteAlmacenRowMapper());
    }
}
