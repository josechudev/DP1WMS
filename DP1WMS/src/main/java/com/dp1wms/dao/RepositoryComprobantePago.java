package com.dp1wms.dao;

import com.dp1wms.model.*;

import java.util.List;

public interface RepositoryComprobantePago {

    List<ComprobantePago> selectAllComprobantes();
    void crearComprobantePago(Envio auxEnvio, TipoComprobantePago auxTipoComprobantePago, Usuario auxUsuario);
    List<TipoComprobantePago> selectAllTipoComprobantePago();

    TipoComprobantePago getIdTipoComprobantePago(String auxNombreTipoComprobante);

    List<DetalleComprobantePago> getDetalleComprobantePago(Long auxIdComprobante);
}
