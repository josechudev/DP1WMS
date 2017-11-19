package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryAlmacen;
import com.dp1wms.dao.mapper.*;
import com.dp1wms.model.*;
import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Rack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryAlmacenImpl implements RepositoryAlmacen {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Almacen obtenerAlmacen(){

        List<Almacen> almacenes = new ArrayList<>();
        String SQL = "SELECT * from Almacen";

        try{
            almacenes = jdbcTemplate.query(SQL, new AlmacenRowMapper());
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return almacenes.get(0);
    }


    public List<String> obtenerNombresProductos(Long idenvio){
        String SQL = "select nombreproducto from (select idproducto from detalleenvio where idenvio = " +
                idenvio.toString() + ") a, producto where a.idproducto = producto.idproducto";

        List<String> nombres_productos = new ArrayList<>();

        try{
            nombres_productos = jdbcTemplate.query(SQL,new EnvioProductosRowMapper());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return nombres_productos;
    }

    public List<Rack> obtenerRacks(){

        String SQL = "SELECT posicioninicial[0] as ini_posx, posicioninicial[1] as ini_posy," +
                        "posicionfinal[0] as fin_posx, posicionfinal[1] as fin_posy, altura, idrack FROM rack";

        List<Rack> racks = new ArrayList<Rack>();
        try {
            racks = jdbcTemplate.query(SQL, new RackRowMapper());
        }catch(EmptyResultDataAccessException e ){
            e.printStackTrace();
        }

        return racks;
    }


    public List<Envio> obtenerEnvios(){

        String SQL = "SELECT e.idenvio, e.destino, e.fechaenvio, c.idcliente, c.numdoc, c.razonsocial " +
                "FROM envio e INNER JOIN pedido p ON e.idpedido = p.idpedido " +
                "INNER JOIN cliente c ON p.idcliente = c.idcliente " +
                "WHERE NOT p.esdevolucion";

        List<Envio> envios = null;
        try {
            envios = jdbcTemplate.query(SQL, (res,i)->{
                Envio envio = new Envio();
                envio.setIdEnvio(res.getLong("idenvio"));
                envio.setDestino(res.getString("destino"));
                envio.setFechaEnvio(res.getTimestamp("fechaenvio"));
                Cliente c = new Cliente();
                c.setIdCliente(res.getInt("idcliente"));
                c.setNumDoc(res.getString("numdoc"));
                c.setRazonSocial(res.getString("razonsocial"));
                envio.setCliente(c);
                return envio;
            });
        }catch(EmptyResultDataAccessException e ){
            e.printStackTrace();
        }

        return envios;
    }

    public List<Cajon> obtenerCajones(Long id_envio){


        String SQL = "select c.idrack, c.posicion[0] as posx from cajon as c, " +
                "(select * from detalleenvio as d, ubicacion as u where d.idproducto = u.idproducto) as b  " +
                "where idenvio = " + id_envio.toString() + " and c.idcajon = b.idcajon;";

        List<Cajon> cajones = null;
        try {
            cajones = jdbcTemplate.query(SQL, new CajonesRowMapper());
        }catch(EmptyResultDataAccessException e ){
            e.printStackTrace();
        }

        return cajones;

    }

    public List<Cajon> obtenerCajonesConLote(Long id_envio){


        String SQL = "SELECT idproducto,cantidad FROM detalleenvio WHERE idenvio = ?";

        List<DetalleEnvio> listaDetalle = null;
        try{
            listaDetalle = jdbcTemplate.query(SQL,new Object[]{id_envio},this::mapParamDetalleEnvio);
        }catch(EmptyResultDataAccessException e ){
            e.printStackTrace();
        }
        List<Cajon> cajones = new ArrayList<Cajon>();
        for(DetalleEnvio detalleEnvio : listaDetalle){
            //obtener lote
            int idlote = -1;
            SQL = "select u.idlote from ubicacion u,lote l where u.idproducto = ? and u.idproducto = l.idproducto and u.idlote = l.idlote order by l.fechalote asc limit 1";
            try{
                idlote = jdbcTemplate.queryForObject(SQL,new Object[]{detalleEnvio.getIdProducto()},Integer.class);
            }catch(EmptyResultDataAccessException e ) {
                e.printStackTrace();
            }
            /*Cajon cajon = new Cajon();
            cajon.setIdProducto(idproducto);
            cajon.setIdLote(idlote);*/

            //obtener datos cajon
            //SQL = "select c.idrack, c.posicion[0] as posx from cajon c,ubicacion u where u.idcajon = c.idcajon and u.idlote = ?";
            SQL = "select p.nombreproducto,r.codigo,c.idrack, c.posicion[0] as posx,c.posicion[1] as posy from cajon c,ubicacion u,rack r,producto p where p.idproducto = u.idproducto and r.idrack = c.idrack and u.idcajon = c.idcajon and u.idlote = ?";
            List<Cajon> listacajones = null;
            try {
                listacajones = jdbcTemplate.query(SQL,new Object[]{idlote}, this::mapParamCajon);
            }catch(EmptyResultDataAccessException e ){
                e.printStackTrace();
            }

            if((listacajones != null) && (listacajones.size()>0)){
                Cajon cajonAux = listacajones.get(0);
                cajonAux.setIdLote(idlote);
                cajonAux.setIdProducto(detalleEnvio.getIdProducto());
                cajonAux.setCantidad(detalleEnvio.getCantidad());
                cajones.add(cajonAux);
            }
        }

        return cajones;

    }

    private DetalleEnvio mapParamDetalleEnvio(ResultSet rs, int i) throws SQLException {
        DetalleEnvio detalleEnvio = new DetalleEnvio();

        detalleEnvio.setIdProducto(rs.getInt("idproducto"));
        detalleEnvio.setCantidad(rs.getInt("cantidad"));

        return detalleEnvio;
    }

    private Cajon mapParamCajon(ResultSet rs, int i) throws SQLException {
        Cajon cajon = new Cajon();
        cajon.setPosX(rs.getInt("posx"));
        cajon.setIdRack(rs.getInt("idrack"));
        cajon.setCodigoRack(rs.getString("codigo"));
        cajon.setPosY(rs.getInt("posy"));
        cajon.setNombreProducto(rs.getString("nombreProducto"));
        //cajon.setIdLote(rs.getInt("idlote"));
        //cajon.setIdProducto(rs.getInt("idproducto"));
        return cajon;
    }

/*
    public List<Ubicacion> obtenerUbicaciones(Long idenvio) {
        String SQL = "SELECT p.nombreproducto,u.idlote,u.idproducto,al.idalmacen, al.nombre as nombreAlmacen,ar.idarea,ar.codigo as codigoArea, r.idrack,r.codigo as codigoRack,c.idcajon,c.posicion[0] as cajonX,c.posicion[1] as cajonY, d.cantidad FROM almacen al,area ar,rack r,cajon c,ubicacion u,detalleenvio d,producto p WHERE p.idproducto = d.idproducto and idenvio = ? and u.idproducto = d.idproducto and u.idcajon = c.idcajon and c.idrack = r.idrack and r.idarea = ar.idarea and ar.idalmacen = al.idalmacen";
        List<Ubicacion> listaUbicaciones = null;
        try{
            listaUbicaciones = jdbcTemplate.query(SQL,new Object[] {idenvio}, this::mapParamUbicacion);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return listaUbicaciones;
    }

    private Ubicacion mapParamUbicacion(ResultSet rs, int i) throws SQLException {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setIdAlmacen(rs.getInt("idalmacen"));
        ubicacion.setNombreAlmacen(rs.getString("nombreAlmacen"));
        ubicacion.setIdArea(rs.getInt("idarea"));
        ubicacion.setCodigoArea(rs.getString("codigoArea"));
        ubicacion.setIdRack(rs.getInt("idrack"));
        ubicacion.setCodigoRack(rs.getString("codigoRack"));
        ubicacion.setCajonPosicionX(rs.getInt("cajonX"));
        ubicacion.setCajonPosicionY(rs.getInt("cajonY"));
        ubicacion.setIdCajon(rs.getInt("idcajon"));
        ubicacion.setCantidad(rs.getInt("cantidad"));
        ubicacion.setIdLote(rs.getInt("idlote"));
        ubicacion.setIdProducto(rs.getInt("idproducto"));
        ubicacion.setNombreProducto(rs.getString("nombreproducto"));
        return ubicacion;
    }
*/
}