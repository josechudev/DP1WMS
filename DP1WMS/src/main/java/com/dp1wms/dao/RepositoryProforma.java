package com.dp1wms.dao;

import com.dp1wms.model.DetalleProforma;
import com.dp1wms.model.Envio;
import com.dp1wms.model.Producto;
import com.dp1wms.model.Proforma;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface RepositoryProforma {

    List<Producto> buscarProductosParaVenta(String campo, String dato);

    boolean registrarProforma(Proforma profoma);

    List<Proforma> buscarProformas(String campoCliente, String datoCliente, String codigoProforma,
                                   Timestamp fechaDesde, Timestamp fechaHasta);

    ArrayList<DetalleProforma> obtenerDetallesDeProforma(int idProforma);
}
