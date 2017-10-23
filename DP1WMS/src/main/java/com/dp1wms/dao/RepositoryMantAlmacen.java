package com.dp1wms.dao;

import com.dp1wms.model.Almacen;
import java.util.List;

public interface RepositoryMantAlmacen {

    List<Almacen> obtenerAlmacenes();
    Almacen obtenerAlmacenById();
    int crearAlmacen(Almacen auxAlmacen);
}
