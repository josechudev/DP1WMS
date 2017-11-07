package com.dp1wms.dao.impl.Kardex;

import com.dp1wms.dao.IKardex.RepositoryKardexXProducto;
import com.dp1wms.dao.mapper.Kardex.KardexXProductoRowMapper;
import com.dp1wms.model.KardexXProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositryKardexXProductoImpl implements RepositoryKardexXProducto {
    @Autowired
    private  JdbcTemplate jdbcTemplate;
    @Override
    public List<KardexXProducto> selectAllKardexXProducto() {
        String sql = "select\n" +
                "p.nombreproducto,\n" +
                "sum(dm.cantidad),\n" +
                "tm.descripcion,\n" +
                "p.precio,\n" +
                "p.preciocompra,\n" +
                "(p.precio * dm.cantidad) valortotal,\n" +
                "tm.esingreso\n" +
                "from movimiento m\n" +
                "left join detallemovimiento as dm on dm.iddetallemovimiento = m.idmovimiento\n" +
                "left join tipomovimiento as tm on tm.idtipomovimiento = m.idtipomovimiento\n" +
                "left join producto as p on p.idproducto = dm.idproducto\n" +
                "where m.idtipomovimiento is not null and p.idcategoria=1 and m.idtipomovimiento >1\n" +
                "group by m.idtipomovimiento,dm.idproducto,p.nombreproducto,dm.cantidad,tm.descripcion,p.preciocompra,p.precio,tm.esingreso\n;";
        return jdbcTemplate.query(sql,new KardexXProductoRowMapper());
    }

    @Override
    public List<KardexXProducto> selectAllKardexXProducto(String fecInicio, String fecFin) {
        return null;
    }
}
