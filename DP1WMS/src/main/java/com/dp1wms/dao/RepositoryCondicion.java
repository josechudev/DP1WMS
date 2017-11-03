package com.dp1wms.dao;


import com.dp1wms.model.Condicion;

import java.util.List;

public interface RepositoryCondicion {

    List<Condicion> obtenerDescuentos();
    int registrarDescuento(Condicion condicion);
    int eliminarDescuento(int id,Long idEmpleadoAuditado);
    int actualizarDescuento(Condicion condicion);
    List<Condicion> obtenerDescuentosActivos();
}