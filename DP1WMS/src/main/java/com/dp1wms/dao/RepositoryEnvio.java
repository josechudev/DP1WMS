package com.dp1wms.dao;

import com.dp1wms.model.Envio;

import javax.swing.text.StyledEditorKit;
import java.util.List;

public interface RepositoryEnvio {

    List<Envio> obtenerEnviosRealizados(Boolean realizado);
    void actualizarEstadoEnvio(Long idPedido);
    List<Envio> obtenerEnvios(int idPedido);

    List<Envio> obtenerListaEnvio();
    List<Envio> obtenerListaEnvioRepoio();
}
