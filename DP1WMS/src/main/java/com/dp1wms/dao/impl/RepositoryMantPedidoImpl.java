package com.dp1wms.dao.impl;

import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryMantPedido;
import com.dp1wms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public class RepositoryMantPedidoImpl implements RepositoryMantPedido{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MainController mainController;

    @Transactional (rollbackFor = Exception.class)
    public boolean registrarPedido(Pedido pedido, ArrayList<Envio> envios){

        String sql = "INSERT INTO pedido (idestadopedido, esdevolucion, observaciones, " +
                " idempleadoauditado, idcliente," +
                " total, subtotal, costoflete) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING idpedido";
        try{
            Pedido p = this.jdbcTemplate.queryForObject(sql,
                    new Object[]{1, false, pedido.getObservaciones(), this.mainController.getEmpleado().getIdempleado(),
                    pedido.getIdCliente(), pedido.getTotal(), pedido.getSubtotal(),
                    pedido.getCostoflete()}, (res, i)->{
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

        String sql3 = "INSERT INTO envio (fechaenvio, destino, costoflete, idpedido, realizado, distancia) " +
                "VALUES (?,?,?,?,?, ?) RETURNING idenvio";
        String sql4 = "INSERT INTO detalleenvio (idenvio, idproducto, cantidad) " +
                "VALUES (?,?,?) RETURNING iddetalleenvio";
        try{
            for(Envio envio: envios){
                Envio e = this.jdbcTemplate.queryForObject(sql3, new Object[]{
                        envio.getFechaEnvio(), envio.getDestino(), envio.getCostoFlete(), pedido.getIdPedido(),
                        false, envio.getDistancia()
                }, (res, i)->{
                   Envio eAux = new Envio();
                   eAux.setIdEnvio(res.getLong("idenvio"));
                   return eAux;
                });
                envio.setIdEnvio(e.getIdEnvio());
                for(DetalleEnvio detalleEnvio: envio.getDetalleEnvio()){
                    DetalleEnvio de = this.jdbcTemplate.queryForObject(sql4, new Object[]{
                            envio.getIdEnvio(), detalleEnvio.getProducto().getIdProducto(),
                            detalleEnvio.getCantidad()
                    }, (res,i)->{
                       DetalleEnvio deAux = new DetalleEnvio();
                       deAux.setIdDetalleEnvio(res.getLong("iddetalleenvio"));
                       return deAux;
                    });
                    detalleEnvio.setIdDetalleEnvio(de.getIdDetalleEnvio());
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return true;
    }

}
