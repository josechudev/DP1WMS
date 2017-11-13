package com.dp1wms.dao;

import com.dp1wms.model.Rack;

import java.util.ArrayList;

public interface RepositoryCajon {
    int[] crear(Rack rack);
    int[] crear(ArrayList<Rack> racks);
    int eliminar(int idRack);
    int[] eliminar(ArrayList<Rack> racks);
}
