package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryAlmacen;
import com.dp1wms.dao.mapper.AlmacenRowMapper;
import com.dp1wms.dao.mapper.CajonesRowMapper;
import com.dp1wms.dao.mapper.EnvioRowMapper;
import com.dp1wms.dao.mapper.RackRowMapper;
import com.dp1wms.model.Cajon;
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

        System.out.println("Lista de almacenes");
        System.out.println(almacenes.size());

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

        System.out.println("Numero de Racks");
        System.out.println(racks.size());

        return racks;
    }


    public List<Envio> obtenerEnvios(){

        String SQL = "SELECT idenvio, destino FROM envio";

        List<Envio> envios = null;
        try {
            envios = jdbcTemplate.query(SQL, new EnvioRowMapper());
        }catch(EmptyResultDataAccessException e ){
            e.printStackTrace();
        }

        System.out.println("Numero de Envios");
        System.out.println(envios.size());

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

        if (cajones != null) {
            System.out.println("Numero de Cajones: ");
            System.out.println(cajones.size());
        }
        else {
            System.out.println("No hay cajones rip");
        }
        return cajones;

    }


}