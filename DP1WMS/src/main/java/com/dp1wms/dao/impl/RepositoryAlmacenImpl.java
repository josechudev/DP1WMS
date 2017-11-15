package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryAlmacen;
import com.dp1wms.dao.mapper.AlmacenRowMapper;
import com.dp1wms.dao.mapper.CajonesRowMapper;
import com.dp1wms.dao.mapper.EnvioRowMapper;
import com.dp1wms.dao.mapper.RackRowMapper;
import com.dp1wms.model.Cajon;
import com.dp1wms.model.Cliente;
import com.dp1wms.model.Envio;
import com.dp1wms.model.tabu.Almacen;
import com.dp1wms.model.tabu.Rack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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


}