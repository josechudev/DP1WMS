package com.dp1wms.dao;

import com.dp1wms.model.*;

import java.util.List;

public interface RepositoryComprobantePago {

    List<ComprobantePago> selectAllComprobantes();
    public List<ComprobantePago> selectAllComprobantesActivos();

    void crearComprobantePago(Envio auxEnvio, TipoComprobantePago auxTipoComprobantePago, Usuario auxUsuario);

    List<TipoComprobantePago> selectAllTipoComprobantePago();

    TipoComprobantePago getIdTipoComprobantePago(String auxNombreTipoComprobante);

    List<DetalleComprobantePago> getDetalleComprobantePago(Long auxIdComprobante);

    void cambiarActivo(boolean auxActivo, Long auxIdComprobante, Usuario auxUsuario);

    boolean existeFacturaActiva(Long auxIdEnvio);
}
