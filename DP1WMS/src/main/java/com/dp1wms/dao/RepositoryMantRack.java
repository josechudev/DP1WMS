package com.dp1wms.dao;

import com.dp1wms.model.Rack;

import java.util.List;

public interface RepositoryMantRack {

    List<Rack> getRacksByAlmacenId(int almacenId);
    List<Rack> getRacksByAreaId(int areaId);
    int crearRack(Rack rack);
    int eliminarRack(Rack rack);
    int editarRack(Rack rack);
}
