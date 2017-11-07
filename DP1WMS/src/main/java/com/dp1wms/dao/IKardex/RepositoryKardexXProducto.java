package com.dp1wms.dao.IKardex;

import com.dp1wms.model.KardexXProducto;

import java.util.List;

public interface RepositoryKardexXProducto {
    List<KardexXProducto> selectAllKardexXProducto();
    List<KardexXProducto> selectAllKardexXProducto(String fecInicio,String fecFin);
}
