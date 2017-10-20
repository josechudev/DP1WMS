package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class RepositoryMantMovImpl implements RepositoryMantMov{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Producto> obtenerProductos(){
        String SQL= "SELECT * FROM public.producto ";

        List<Producto> listaProductos = null;
        try{
            listaProductos = jdbcTemplate.query(SQL,new Object[] {}, this::mapParam);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Lista Productos -> " + listaProductos + "Tam -> " +listaProductos.size());
        return listaProductos;
    }

    public Producto mapParam(ResultSet rs, int i) throws SQLException {
        Producto producto = new Producto();
        producto.setNombreProducto(rs.getString("nombreproducto"));
        // aqui setean todas las columnas que quieran
        return producto;
    }

}
