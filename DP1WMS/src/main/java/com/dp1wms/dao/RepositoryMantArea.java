package com.dp1wms.dao;

import com.dp1wms.model.Area;

import java.util.List;

public interface RepositoryMantArea {

    Area getAreaById(int idArea);
    List<Area> getAreasByIdAlmacen(int idAlmacen);
    int crearArea(Area auxArea);
    int editarArea(Area auxArea);
    int eliminarArea(Area auxArea);
}
