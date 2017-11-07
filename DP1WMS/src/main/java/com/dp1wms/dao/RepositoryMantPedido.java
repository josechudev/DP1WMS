package com.dp1wms.dao;

import com.dp1wms.model.DetallePedido;
import com.dp1wms.model.Envio;
import com.dp1wms.model.Pedido;
import com.dp1wms.model.Proforma;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface RepositoryMantPedido {

    boolean registrarPedido(Pedido pedido, ArrayList<Envio> envios);

    List<Pedido> buscarPedidos(String campoCliente, String datoCliente, String codigoPedido,
                               Timestamp fechaDesde, Timestamp fechaHasta);

    List<DetallePedido> obtenerDetallesPedido(int idPedido);

    boolean actualizarEstadoPedido(int idPedido, int idEstadoPedido);

}
