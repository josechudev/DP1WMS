package com.dp1wms.dao;

import com.dp1wms.model.Guia;

import java.util.List;

public interface RepositoryGuia {

    int registrarGuia(Guia guia);
    Double obtenerPesoProducto(int idProducto);
    String obtenerNombreCliente(Long idCliente);
    List<Guia> obtenerGuias();
}
