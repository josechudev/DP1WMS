package com.dp1wms.dao;

import com.dp1wms.model.ComprobantePago;

import java.util.List;

public interface RepositoryDevoluciones {
    List<ComprobantePago> obtenerFacturas();
    void registrarPedidoDevolucion(Long idEmpleadoAuditado,Long idComprobantePago, Long idEnvio);
}
