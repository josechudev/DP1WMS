package com.dp1wms.dao.impl;

import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryMantPedido;
import com.dp1wms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Pedido> buscarPedidos(String campoCliente, String datoCliente, String codigoPedido,
                                      Timestamp fechaDesde, Timestamp fechaHasta){
        String sql = "SELECT p.idpedido, c.idcliente, c.razonsocial, c.numdoc, c.telefono," +
                " c.direccion, c.email, to_char(p.fechacreacion, 'DD/MM/YYYY') as fechacreacion, " +
                " p.subtotal, p.costoflete, p.total, p.observaciones, count(e.idenvio) as num_envios, " +
                " ep.idestadopedido, ep.descripcion as pedido_desc " +
                "FROM pedido as p INNER JOIN cliente as c ON p.idcliente = c.idcliente " +
                "INNER JOIN envio as e ON e.idpedido = p.idpedido " +
                "LEFT JOIN estadopedido as ep ON p.idestadopedido = ep.idestadopedido " +
                "WHERE c." + campoCliente + " LIKE  ? AND p.idpedido::varchar LIKE ? " +
                "AND date_trunc('day',p.fechacreacion) >= ? AND date_trunc('day',p.fechacreacion) <= ? " +
                "GROUP BY p.idpedido, c.idcliente, ep.idestadopedido";
        datoCliente = "%" + datoCliente + "%";
        codigoPedido = "%" + codigoPedido + "%";

        try{
            List<Pedido> proformas = jdbcTemplate.query(sql,
                    new Object[]{datoCliente, codigoPedido,
                            fechaDesde, fechaHasta}, (res, i)->{
                        Pedido p = new Pedido();
                        p.setIdCliente(res.getLong("idcliente"));
                        p.setIdPedido(res.getInt("idpedido"));
                        Cliente c = new Cliente();
                        c.setIdCliente(res.getLong("idcliente"));
                        c.setRazonSocial(res.getString("razonsocial"));
                        c.setNumDoc(res.getString("numdoc"));
                        c.setTelefono(res.getString("telefono"));
                        c.setDireccion(res.getString("direccion"));
                        c.setEmail(res.getString("email"));
                        p.setCliente(c);
                        p.setFechaCreacion(res.getString("fechacreacion"));
                        p.setSubtotal(res.getFloat("subtotal"));
                        p.setCostoflete(res.getFloat("costoflete"));
                        p.setTotal(res.getFloat("total"));
                        p.setObservaciones(res.getString("observaciones"));
                        p.setNumEnvios(res.getInt("num_envios"));

                        EstadoPedido ep = new EstadoPedido();
                        ep.setIdEstadoPedido(res.getInt("idestadopedido"));
                        ep.setDescripcion(res.getString("pedido_desc"));
                        p.setEstadoPedido(ep);
                        return p;
                    });
            return proformas;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
