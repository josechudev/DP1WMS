package com.dp1wms.dao.mapper;

import com.dp1wms.model.Envio;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnvioResultSetExtractor implements ResultSetExtractor{

    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Envio envio = new Envio();
        envio.setIdEnvio(resultSet.getLong("idenvio"));
        envio.setDestino(resultSet.getString("destino"));
        return envio;
    }
}