package com.dp1wms.dao;

import com.dp1wms.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public interface RepositoryMantCliente {

    Cliente registrarCliente(Cliente cliente);

    List<Cliente> buscarCliente(String campo, String dato);

    boolean modificarCliente(Cliente cliente);

    void registrarVariosClientes(ArrayList<Cliente> clientes);
}
