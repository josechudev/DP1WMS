package com.dp1wms.dao.IReporteAlmacen;

import com.dp1wms.model.ReporteAlmacen;

import java.util.List;

public interface RepositoryReporteAlmacen {
    List<ReporteAlmacen> selectAllKardexFila(String fecInicio, String fecFin);
    List<ReporteAlmacen> selectAllKardexFila();
}
