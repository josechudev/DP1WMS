package com.dp1wms.dao;

import com.dp1wms.model.Envio;
import com.dp1wms.model.Ruta;
import com.dp1wms.model.tabu.Nodo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public interface RepositoryRuta {

    boolean guardarRuta(ArrayList<Nodo> solucion, Envio envio);

    List<Ruta> obtenerRutasPorEnvio(Envio envio);

}
