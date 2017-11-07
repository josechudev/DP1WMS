package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryComprobantePago;
import com.dp1wms.dao.mapper.ComprobantePagoRowMapper;
import com.dp1wms.model.ComprobantePago;
import com.dp1wms.model.TipoComprobantePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryComprobantePagoImpl implements RepositoryComprobantePago {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ComprobantePago> selectAllComprobantes(){
        String sql = "SELECT Cp.idComprobante, Cp.idTipoComprobante, Tc.descripcion, " +
                "Cp.idCliente, C.razonSocial, Cp.fechaCreacion, Cp.fechaModificacion, " +
                "Cp.subtotal, Cp.costoFlete, Cp.igv, Cp.total, Cp.idEnvio, Cp.activo " +
                "FROM ComprobantePago Cp INNER JOIN TipoComprobante Tc ON Cp.idTipoComprobante = Tc.idTipoComprobante " +
                "INNER JOIN Cliente C ON Cp.idCliente = C.idCliente " +
                "WHERE Cp.activo = true " +
                "ORDER BY Cp.idComprobante";

        try{
            return jdbcTemplate.query(sql,
                    new ComprobantePagoRowMapper());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<TipoComprobantePago> selectAllTipoComprobantePago(){
        String sql = "SELECT idTipoComprobante, descripcion " +
                "FROM tipoComprobante ";
        try{
            return jdbcTemplate.query(sql, new Object[]{}, this::mapParamTipoComprobante);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private TipoComprobantePago mapParamTipoComprobante(ResultSet rs, int i) throws SQLException {
        TipoComprobantePago u = new TipoComprobantePago();
        u.setV_id(rs.getLong(1));
        u.setV_descripcion(rs.getString(2));
        return u;
    }
}
