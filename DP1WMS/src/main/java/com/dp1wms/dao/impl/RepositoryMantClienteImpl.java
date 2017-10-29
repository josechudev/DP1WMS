package com.dp1wms.dao.impl;

import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryMantCliente;
import com.dp1wms.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryMantClienteImpl implements RepositoryMantCliente {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MainController mainController;

    public Cliente registrarCliente(Cliente cliente){
        String sql = "INSERT INTO cliente (numdoc, razonsocial, telefono, direccion, email, idempleadoauditado) " +
                     "values (?, ?, ?, ?, ?, ?) RETURNING idcliente";
        try {
            Cliente c = (Cliente) this.jdbcTemplate.queryForObject(sql, new Object[]{
                    cliente.getNumDoc(), cliente.getRazonSocial(),
                            cliente.getTelefono(), cliente.getDireccion(),
                            cliente.getEmail(), mainController.getEmpleado().getIdempleado()},
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

    public boolean modificarCliente(Cliente cliente){
        String sql = "UPDATE cliente set numdoc = ?, razonsocial = ?, telefono = ?, direccion = ?," +
                "email = ?, idempleadoauditado = ?, activo = ? WHERE idcliente = ?";
        try {
            int v = this.jdbcTemplate.update(sql, new Object[]{
                    cliente.getNumDoc(), cliente.getRazonSocial(), cliente.getTelefono(),
                    cliente.getDireccion(), cliente.getEmail(), mainController.getEmpleado().getIdempleado(),
                    new Boolean(cliente.isActivo()), cliente.getIdCliente()
            });
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> buscarCliente(String campo, String dato){

        String sql = "SELECT * FROM cliente WHERE activo AND lower(" + campo + ") LIKE ?";
        dato = "%" + dato.toLowerCase() + "%";
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
        c.setActivo(rs.getBoolean("activo"));
        return c;
    }

    @Transactional (rollbackFor = Exception.class)
    public void registrarVariosClientes(ArrayList<Cliente> clientes){
        String sql = "INSERT INTO cliente (numdoc, razonsocial, telefono, direccion, email, idempleadoauditado) " +
                "values (?, ?, ?, ?, ?, ?) RETURNING idcliente";
        for(Cliente cliente: clientes){
            try {
                Cliente c = (Cliente) this.jdbcTemplate.queryForObject(sql, new Object[]{
                                cliente.getNumDoc(), cliente.getRazonSocial(),
                                cliente.getTelefono(), cliente.getDireccion(),
                                cliente.getEmail(), mainController.getEmpleado().getIdempleado()},
                        (rs, i) -> {
                            Cliente cl = new Cliente();
                            cl.setIdCliente(rs.getLong("idcliente"));
                            return cl;
                        });
                cliente.setIdCliente(c.getIdCliente());
                cliente.setActivo(true);
            } catch (Exception e){
                e.printStackTrace();
                throw  e;
            }
        }
    }
}
