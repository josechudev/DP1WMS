package com.dp1wms.dao.mapper;

import com.dp1wms.model.ComprobantePago;
import com.dp1wms.model.UsuarioModel.Usuario;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ComprobantePagoSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {
        ComprobantePago person = new ComprobantePago();
        person.setV_id(rs.getLong(1));
        person.setV_tipoComprobante(rs.getString(2));
        //person.setV_idCliente(rs.getLong(3));
        person.setV_cliente(rs.getString(3));
        //person.setV_idEmpleado(rs.getLong(5));
        //person.setV_empleado(rs.getString(4));
        //person.setV_subtotal(rs.getFloat(4));
        person.setV_igv(rs.getFloat(4));
        person.setV_total(rs.getFloat(5));
        person.setV_estadoComprobante(rs.getString(6));
        //person.setV_activo(rs.getBoolean(11));
        return person;
    }

}
