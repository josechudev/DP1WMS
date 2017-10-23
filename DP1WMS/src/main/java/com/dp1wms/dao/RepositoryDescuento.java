package com.dp1wms.dao;


import com.dp1wms.model.Descuento;

import java.util.List;

public interface RepositoryDescuento {

    List<Descuento> obtenerDescuentos();
    int registrarDescuento(Descuento descuento);
    int eliminarDescuento(int id);
    int actualizarDescuento(Descuento descuento);
}