package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantCliente;
import com.dp1wms.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryMantClienteImpl implements RepositoryMantCliente {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente registrarCliente(Cliente cliente){
        String sql = "INSERT INTO cliente (numdoc, razonsocial, telefono, direccion, email) " +
                     "values (?, ?, ?, ?, ?) RETURNING idcliente";
        try {
            Cliente c = (Cliente) this.jdbcTemplate.queryForObject(sql, new Object[]{cliente.getNumDoc(),
            cliente.getRazonSocial(), cliente.getTelefono(), cliente.getDireccion(), cliente.getEmail()},
                    (rs, i)->{
                Cliente cl = new Cliente();
                cl.setIdCliente(rs.getLong("idcliente"));
                return cl;
            });
            cliente.setIdCliente(c.getIdCliente());
            return cliente;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Cliente> buscarCliente(String campo, String dato){

        String sql = "SELECT * FROM cliente WHERE lower(" + campo + ") LIKE ?";
        dato = "%" + dato + "%";
        try{
            List<Cliente> clientes = this.jdbcTemplate.query(sql, new Object[]{dato}, this::mapCliente);
            return clientes;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Cliente mapCliente(ResultSet rs, int rowNum) throws SQLException{
        Cliente c = new Cliente();
        c.setIdCliente(rs.getLong("idcliente"));
        c.setNumDoc(rs.getString("numdoc"));
        c.setRazonSocial(rs.getString("razonsocial"));
        c.setTelefono(rs.getString("telefono"));
        c.setDireccion(rs.getString("direccion"));
        c.setEmail(rs.getString("email"));
        return c;
    }
}
