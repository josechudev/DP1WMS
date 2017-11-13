package com.dp1wms.dao;

import com.dp1wms.model.Rack;

import java.util.ArrayList;
import java.util.List;

public interface RepositoryMantRack {

    List<Rack> getRacksByAlmacenId(int almacenId);
    List<Rack> getRacksByAreaId(int areaId);
    int crear(Rack rack);
    int[] crear(ArrayList<Rack> racks);
    int eliminar(Rack rack);
    int[] eliminar(ArrayList<Rack> racks);
    int editarRack(Rack rack);
}
