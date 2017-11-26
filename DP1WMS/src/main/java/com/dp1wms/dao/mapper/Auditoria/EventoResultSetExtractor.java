package com.dp1wms.dao.mapper.Auditoria;

import com.dp1wms.model.auditoria.Evento;
import com.dp1wms.util.DateParser;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventoResultSetExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Evento evento = new Evento();
        evento.setTabla(resultSet.getString(2));
        evento.setNombreEmpleado(resultSet.getString("nombre") + resultSet.getString("apellidos"));
        Timestamp ts = resultSet.getTimestamp(4);
        DateFormat df = new SimpleDateFormat("HH:mm");
        evento.setFechaAccion(DateParser.timestampToString(ts));
        evento.setHoraAccion(df.format(new Date(ts.getTime())));
        String idAccion = resultSet.getString(5);

        switch (idAccion){
            case "I":
                evento.setAccion("Insertar");
                break;
            case "D":
                evento.setAccion("Eliminar");
                break;
            case "U":
                evento.setAccion("Actualizar");
                break;
        }

        evento.setDataOriginal(resultSet.getString(6));
        evento.setDataNueva(resultSet.getString(7));
        return evento;
    }
}
