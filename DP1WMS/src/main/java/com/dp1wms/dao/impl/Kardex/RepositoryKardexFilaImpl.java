package com.dp1wms.dao.impl.Kardex;

import com.dp1wms.dao.IKardex.RepositoryKardexFila;
import com.dp1wms.dao.mapper.Kardex.KardexFilaRowMapper;
import com.dp1wms.model.KardexFila;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RepositoryKardexFilaImpl implements RepositoryKardexFila {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<KardexFila> selectAllKardexFila() {
        String sql = "select \n" +
                "m.idmovimiento,\n" +
                "m.idtipomovimiento,\n" +
                "to_char(m.fechamovimiento,'DD/MM/YYYY') fechamovimiento,\n" +
                "dm.idproducto,\n" +
                "p.nombreproducto,\n" +
                "dm.cantidad,\n" +
                "tm.descripcion,\n" +
                "p.precio,\n" +
                "p.preciocompra,\n" +
                "(p.precio*dm.cantidad) valortotal,\n" +
                "tm.esingreso,\n" +
                "(case when tm.esingreso is null then 3 WHEN tm.esingreso then 1 else 2 end)\n"+
                "from movimiento m\n" +
                "left join detallemovimiento as dm on dm.idmovimiento = m.idmovimiento\n" +
                "left join tipomovimiento as tm on tm.idtipomovimiento = m.idtipomovimiento\n" +
                "left join producto as p on p.idproducto = dm.idproducto\n" +
                "order by idmovimiento\n";
        return jdbcTemplate.query(sql, new KardexFilaRowMapper());
    }

    @Override
    public List<KardexFila> selectAllKardexFila(String fecInicio,String fecFin) {
        String sql = "select \n" +
                "m.idmovimiento,\n" +
                "m.idtipomovimiento,\n" +
                "to_char(m.fechamovimiento,'DD/MM/YYYY') fechamovimiento,\n" +
                "dm.idproducto,\n" +
                "p.nombreproducto,\n" +
                "dm.cantidad,\n" +
                "tm.descripcion,\n" +
                "p.precio,\n" +
                "p.preciocompra,\n" +
                "(p.precio*dm.cantidad) valortotal,\n" +
                "tm.esingreso,\n" +
                "(case when tm.esingreso is null then 3 WHEN tm.esingreso then 1 else 2 end)\n"+
                "from movimiento m\n" +
                "left join detallemovimiento as dm on dm.idmovimiento = m.idmovimiento\n" +
                "left join tipomovimiento as tm on tm.idtipomovimiento = m.idtipomovimiento\n" +
                "left join producto as p on p.idproducto = dm.idproducto\n" +
                "where m.fechamovimiento>= ?::date and m.fechamovimiento<=?::date\n" +
                "order by idmovimiento\n";
        return jdbcTemplate.query(sql, new Object[]{fecInicio,fecFin},new KardexFilaRowMapper());
    }
}
