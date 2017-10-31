package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryComprobantePago;
import com.dp1wms.dao.mapper.ComprobantePagoRowMapper;
import com.dp1wms.model.ComprobantePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryComprobantePagoImpl implements RepositoryComprobantePago {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ComprobantePago> selectAllComprobantes(){
        String sql = "SELECT Cp.idComprobante, Tc.descripcion, " +
                "c.razonSocial, " +
                //"e.nombre, " +
                //"Cp.subtotal, " +
                "Cp.igv, Cp.total, " +
                "Ec.descripcion " +
                "FROM ComprobantePago Cp INNER JOIN TipoComprobante Tc ON Cp.idTipoComprobante = Tc.idTipoComprobante " +
                "INNER JOIN Cliente c ON Cp.idCliente = c.idCliente " +
                //"INNER JOIN Empleado e ON Cp.idEmpleado = e.idEmpleado " +
                "INNER JOIN EstadoComprobante Ec ON Cp.idEstadoComprobante = Ec.idEstadoComprobante " +
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
}
