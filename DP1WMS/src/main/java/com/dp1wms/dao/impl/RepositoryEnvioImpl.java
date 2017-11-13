package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryEnvio;
import com.dp1wms.model.DetalleEnvio;
import com.dp1wms.model.Envio;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class RepositoryEnvioImpl implements RepositoryEnvio{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Envio> obtenerEnviosRealizados(Boolean realizado){

        String SQL = "SELECT e.idenvio,e.fechaenvio,e.destino,e.realizado,e.idpedido, p.idcliente,c.razonsocial FROM public.envio e ,public.pedido p,public.cliente c WHERE (e.realizado = ?) and e.idpedido = p.idpedido and (not p.esdevolucion) and (c.idcliente = p.idcliente) ";
        List<Envio> listaEnvios = null;
        try{
            listaEnvios = jdbcTemplate.query(SQL,new Object[] {realizado}, this::mapParam);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        for(Envio envio: listaEnvios){

            List<DetalleEnvio> listaDetalle = this.obtenerDetalleEnvio(envio.getIdEnvio());

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
        System.out.println("ID ENVIO Obtenido"+idEnvio);
        String SQL ="SELECT d.iddetalleenvio,d.idenvio,d.idproducto,d.cantidad,p.codigo, p.nombreproducto FROM public.detalleenvio d,public.producto p where (d.idenvio = ?) and (d.idproducto = p.idproducto)";

        List<DetalleEnvio> listaDetalleEnvio = new ArrayList<DetalleEnvio>();
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

    public List<Envio> obtenerEnvios(int idPedido){
        String sql = "SELECT * " +
                "FROM envio WHERE idpedido = ? ORDER BY idenvio ASC";
        try{
            List<Envio> envios = this.jdbcTemplate.query(sql, new Object[]{idPedido},
                    (res, i)->{
                        Envio e = new Envio();
                        e.setIdEnvio(res.getLong("idenvio"));
                        e.setCostoFlete(res.getFloat("costoflete"));
                        e.setDestino(res.getString("destino"));
                        e.setRealizado(res.getBoolean("realizado"));
                        e.setFechaEnvio(res.getTimestamp("fechaenvio"));
                        return e;
                    });
            return envios;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Envio> obtenerListaEnvio(){

        String sql = "SELECT e.idenvio, e.fechaenvio, e.destino, " +
                "e.realizado,e.idpedido, p.idcliente,c.razonsocial " +
                "FROM public.envio e INNER JOIN public.pedido p ON e.idpedido = p.idpedido " +
                "INNER JOIN public.cliente c ON  c.idcliente = p.idcliente " +
                "WHERE (not p.esdevolucion) ";
        String SQL = "SELECT e.idenvio,e.fechaenvio,e.destino,e.realizado,e.idpedido, p.idcliente,c.razonsocial " +
                "FROM public.envio e ,public.pedido p,public.cliente c " +
                "WHERE (e.realizado = ?) and e.idpedido = p.idpedido and (not p.esdevolucion) and (c.idcliente = p.idcliente) ";
        List<Envio> listaEnvios = null;
        try{
            listaEnvios = jdbcTemplate.query(sql,new Object[] {}, this::mapParam);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        for(Envio envio: listaEnvios){

            List<DetalleEnvio> listaDetalle = this.obtenerDetalleEnvio(envio.getIdEnvio());

            if(listaDetalle != null){
                envio.setDetalleEnvio(listaDetalle);
            }
        }


        return listaEnvios;

    }

    public List<Envio> obtenerListaEnvioRepoio(){

        String sql = "SELECT e.idenvio, e.fechaenvio, e.destino, " +
                "e.realizado,e.idpedido, p.idcliente,c.razonsocial, e.costoflete " +
                "FROM public.envio e INNER JOIN public.pedido p ON e.idpedido = p.idpedido " +
                "INNER JOIN public.cliente c ON  c.idcliente = p.idcliente " +
                "WHERE (not p.esdevolucion) ";
        String SQL = "SELECT e.idenvio,e.fechaenvio,e.destino,e.realizado,e.idpedido, p.idcliente,c.razonsocial " +
                "FROM public.envio e ,public.pedido p,public.cliente c " +
                "WHERE (e.realizado = ?) and e.idpedido = p.idpedido and (not p.esdevolucion) and (c.idcliente = p.idcliente) ";
        List<Envio> listaEnvios = null;
        try{
            listaEnvios = jdbcTemplate.query(sql,new Object[] {}, this::mapParamRepoio);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        for(Envio envio: listaEnvios){

            List<DetalleEnvio> listaDetalle = this.obtenerDetalleEnvio(envio.getIdEnvio());

            if(listaDetalle != null){
                envio.setDetalleEnvio(listaDetalle);
            }
        }


        return listaEnvios;

    }

    public Envio mapParamRepoio(ResultSet rs, int i) throws SQLException {
        Envio envio = new Envio();

        envio.setIdEnvio(rs.getLong("idenvio"));
        envio.setDestino(rs.getString("destino"));
        envio.setFechaEnvio(rs.getTimestamp("fechaenvio"));
        envio.setRealizado(rs.getBoolean("realizado"));
        envio.setIdPedido(rs.getLong("idpedido"));
        envio.setIdCliente(rs.getLong("idcliente"));
        envio.setRazonSocial(rs.getString("razonsocial"));
        envio.setCostoFlete(rs.getFloat("costoflete"));

        return envio;
    }
}
