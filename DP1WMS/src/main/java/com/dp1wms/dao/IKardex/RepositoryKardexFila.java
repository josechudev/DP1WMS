package com.dp1wms.dao.IKardex;

import com.dp1wms.model.KardexFila;

import java.util.List;

public interface RepositoryKardexFila {
    List<KardexFila> selectAllKardexFila();
    List<KardexFila> selectAllKardexFila(String fecInicio,String fecFin);
}
