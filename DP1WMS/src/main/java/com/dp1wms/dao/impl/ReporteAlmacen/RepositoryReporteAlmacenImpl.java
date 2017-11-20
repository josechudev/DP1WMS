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
    public List<ReporteAlmacen> selectAllKardexFila(String fecInicio, String fecFin, String producto) {
        String sql = "select p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock stock_fisico, coalesce(p.preciocompra,0) preciocompra,\n" +
                "sum(coalesce(de.cantidad,0)) cantidad_pedido, (p.stock-sum(coalesce(de.cantidad,0))) stock_logico\n" +
                "from producto as p\n" +
                "left join detallepedido as dp on dp.idproducto = p.idproducto\n" +
                "left join (select * from pedido where (not esdevolucion) and idestadopedido in (1,5) and fechacreacion >= ?::DATE and fechacreacion<= ?::DATE ) as pd on pd.idpedido = dp.idpedido\n" +
                "left join (select * from envio where not realizado)as e on e.idpedido = pd.idpedido\n"+
                "left join detalleenvio as de on de.idenvio = e.idenvio and de.idproducto = p.idproducto\n" +
                "where lower(p.nombreproducto) like lower(?)"+
                "group by p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock, p.preciocompra\n" +
                "order by p.codigo";
        return jdbcTemplate.query(sql, new Object[]{fecInicio, fecFin,producto}, new ReporteAlmacenRowMapper());
    }

    @Override
    public List<ReporteAlmacen> selectAllKardexFila() {
        String sql = "select p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock stock_fisico, coalesce(p.preciocompra,0) preciocompra,\n" +
                "sum(coalesce(de.cantidad,0)) cantidad_pedido, (p.stock-sum(coalesce(de.cantidad,0))) stock_logico\n" +
                "from producto as p\n" +
                "left join detallepedido as dp on dp.idproducto = p.idproducto\n" +
                "left join (select * from pedido where (not esdevolucion) and idestadopedido in (1,5)) as pd on pd.idpedido = dp.idpedido\n" +
                "left join (select * from envio where not realizado) as e on e.idpedido = pd.idpedido\n"+
                "left join detalleenvio as de on de.idenvio = e.idenvio and de.idproducto = p.idproducto\n"+
                "group by p.codigo,p.nombreproducto, p.descripcion,p.stockminimo, p.stock, p.preciocompra \n" +
                "order by p.codigo";
        return jdbcTemplate.query(sql, new ReporteAlmacenRowMapper());
    }
}
