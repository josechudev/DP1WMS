package com.dp1wms.dao;

import com.dp1wms.model.Envio;

import java.util.List;

public interface RepositoryEnvio {

    List<Envio> obtenerEnviosNoDespachados();
    void actualizarEstadoEnvio(Long idPedido);
}
