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
        person.setV_idTipoComprobante(rs.getLong(2));
        person.setV_tipoComprobante(rs.getString(3));
        person.setV_idCliente(rs.getLong(4));
        person.setV_cliente(rs.getString(5));

        person.setV_fechaCreacion(rs.getString(6));
        person.setV_fechaModificacion(rs.getString(7));

        person.setV_subtotal(rs.getFloat(8));
        person.setV_flete(rs.getFloat(9));
        person.setV_igv(rs.getFloat(10));
        person.setV_total(rs.getFloat(11));
        person.setV_idEnvio(rs.getLong(12));
        person.setV_activo(rs.getBoolean(13));

        return person;

    }

}
