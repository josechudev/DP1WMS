package com.dp1wms.dao;

import com.dp1wms.model.ComprobantePago;
import com.dp1wms.model.TipoComprobantePago;

import java.util.List;

public interface RepositoryComprobantePago {

    List<ComprobantePago> selectAllComprobantes();
    List<TipoComprobantePago> selectAllTipoComprobantePago();
}
