package com.dp1wms.dao;

import com.dp1wms.model.auditoria.Evento;

import java.util.List;

public interface RepositoryAuditoria {
    List<Evento> getAll();
}
