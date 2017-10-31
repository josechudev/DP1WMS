package com.dp1wms.dao.impl;

import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryMantPedido;
import com.dp1wms.model.DetallePedido;
import com.dp1wms.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositoryMantPedidoImpl implements RepositoryMantPedido{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MainController mainController;

    @Transactional (rollbackFor = Exception.class)
    public boolean registrarPedido(Pedido pedido){

        String sql = "INSERT INTO pedido (idestadopedido, esdevolucion, observaciones, idempleadoauditado, idcliente, total) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING idpedido";
        try{
            Pedido p = this.jdbcTemplate.queryForObject(sql,
                    new Object[]{1, false, pedido.getObservaciones(), this.mainController.getEmpleado().getIdempleado(),
                    pedido.getIdCliente(), pedido.getTotal()}, (res, i)->{
                        Pedido pAux = new Pedido();
                        pAux.setIdPedido(res.getInt("idpedido"));
                        return pAux;
                    });
            pedido.setIdPedido(p.getIdPedido());
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        String sql2 = "INSERT INTO detallepedido (idproducto, idpedido, cantidad, preciounitario, descuento, subtotal) " +
                "VALUES (?,?,?,?,?,?) RETURNING iddetallepedido";
        try{
            for(DetallePedido dp: pedido.getDetalles()){
                DetallePedido dpRes = this.jdbcTemplate.queryForObject(sql2,
                        new Object[]{dp.getProducto().getIdProducto(), pedido.getIdPedido(), dp.getCantidad(),
                        dp.getPrecioUnitario(), dp.getDescuento(), dp.getSubtotal()}, (res, i)->{
                            DetallePedido dpAux = new DetallePedido();
                            dpAux.setIdDetallePedido(res.getInt("iddetallepedido"));
                            return dpAux;
                        });
                dp.setIdDetallePedido(dpRes.getIdDetallePedido());
            }
        } catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
        return true;
    }

}
