package com.dp1wms.dao;

import com.dp1wms.model.Cajon;
import com.dp1wms.model.Envio;
import com.dp1wms.model.Producto;
import com.dp1wms.model.Ubicacion;
import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Rack;

import java.util.List;


public interface RepositoryAlmacen {
    Almacen obtenerAlmacen();
    List<Rack> obtenerRacks();
    List<Envio> obtenerEnvios();
    List<Cajon> obtenerCajones(Long idenvio);
    List<String> obtenerNombresProductos(Long idenvio);
    //List<Ubicacion> obtenerUbicaciones(Long idenvio);
    List<Cajon> obtenerCajonesConLote(Long id_envio);
}