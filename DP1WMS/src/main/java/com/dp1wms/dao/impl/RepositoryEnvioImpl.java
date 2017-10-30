package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryEnvio;
import com.dp1wms.model.DetalleEnvio;
import com.dp1wms.model.Envio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class RepositoryEnvioImpl implements RepositoryEnvio{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Envio> obtenerEnviosNoDespachados(){

        String SQL = "SELECT e.idenvio,e.fechaenvio,e.destino,e.realizado,e.idpedido, p.idcliente,c.razonsocial FROM public.envio e ,public.pedido p,public.cliente c WHERE not realizado and e.idpedido = p.idpedido and (not p.esdevolucion) and (c.idcliente = p.idcliente) ";
        List<Envio> listaEnvios = null;
        try{
            listaEnvios = jdbcTemplate.query(SQL,new Object[] {}, this::mapParam);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        for(Envio envio: listaEnvios){
            List<DetalleEnvio> listaDetalle = this.obtenerDetalleEnvio(envio.getIdPedido());
            if(listaDetalle != null){
                envio.setDetalleEnvio(listaDetalle);
            }
        }


        return listaEnvios;
    }

    public void actualizarEstadoEnvio(Long idEnvio){

        String SQL = "UPDATE public.envio set realizado=true  WHERE idenvio = ? ";

        try{
            jdbcTemplate.update(SQL,new Object[] {idEnvio});
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

    }


    public Envio mapParam(ResultSet rs, int i) throws SQLException {
        Envio envio = new Envio();

        envio.setIdEnvio(rs.getLong("idenvio"));
        envio.setDestino(rs.getString("destino"));
        envio.setFechaEnvio(rs.getTimestamp("fechaenvio"));
        envio.setRealizado(rs.getBoolean("realizado"));
        envio.setIdPedido(rs.getLong("idpedido"));
        envio.setIdCliente(rs.getLong("idcliente"));
        envio.setRazonSocial(rs.getString("razonsocial"));

        return envio;
    }

    private List<DetalleEnvio> obtenerDetalleEnvio(Long idEnvio){
        String SQL ="SELECT d.iddetalleenvio,d.idenvio,d.idproducto,d.cantidad,p.codigo, p.nombreproducto FROM public.detalleenvio d,public.producto p where idenvio = ? and (d.idproducto = p.idproducto)";

        List<DetalleEnvio> listaDetalleEnvio = null;
        try{
            listaDetalleEnvio = jdbcTemplate.query(SQL,new Object[] {idEnvio}, this::mapParam2);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return listaDetalleEnvio;
    }

    public DetalleEnvio mapParam2(ResultSet rs, int i) throws SQLException {
        DetalleEnvio detalleEnvio = new DetalleEnvio();

        detalleEnvio.setCantidad(rs.getInt("cantidad"));
        detalleEnvio.setIdProducto(rs.getInt("idproducto"));
        detalleEnvio.setIdDetalleEnvio(rs.getLong("iddetalleenvio"));
        detalleEnvio.setIdEnvio(rs.getLong("idenvio"));
        detalleEnvio.setNombreProducto(rs.getString("nombreproducto"));
        detalleEnvio.setCodigoProducto(rs.getString("codigo"));

        return detalleEnvio;
    }

}
