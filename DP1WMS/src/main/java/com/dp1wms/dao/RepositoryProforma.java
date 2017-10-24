package com.dp1wms.dao;

import com.dp1wms.model.Envio;
import com.dp1wms.model.Producto;
import com.dp1wms.model.Proforma;

import java.util.ArrayList;
import java.util.List;

public interface RepositoryProforma {

    List<Producto> buscarProductosParaVenta(String campo, String dato);

    boolean registrarProformaYEnvios(Proforma profoma, ArrayList<Envio> envios);
}
