package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryAuditoria;
import com.dp1wms.dao.mapper.Auditoria.EventoRowMapper;
import com.dp1wms.model.auditoria.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryAuditoriaImpl implements RepositoryAuditoria {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Evento> getAll() {
        String sql = "select a.*, e.nombre, e.apellidos from auditoria a inner join empleado e on a.idempleado = e.idempleado";
        return (List<Evento>) jdbcTemplate.query(sql, new EventoRowMapper());
    }
}
