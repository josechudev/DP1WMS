package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryCargaMasiva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryCargaMasivaController implements RepositoryCargaMasiva{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void storeProcedure_cargarLotes(){
        System.out.println("ejecutanto store procedure...");
        jdbcTemplate.update("DO $$ BEGIN  PERFORM INSERTAR_LOTES(); END $$;");
        System.out.println("store procedure ok?...");
    }

    public void storeProcedure_cargarCondicionesComerciales(){
        System.out.println("ejecutanto store procedure...");
        jdbcTemplate.update("DO $$ BEGIN  PERFORM INSERTAR_CONDICIONESCOMERCIALES(); END $$;");
        System.out.println("store procedure ok?...");
    }

    public void storeProcedure_cargarRoles(){
        System.out.println("ejecutanto store procedure...");
        jdbcTemplate.update("DO $$ BEGIN  PERFORM INSERTAR_ROLES(); END $$;");
        System.out.println("store procedure ok?...");
    }

    public void storeProcedure_cargarUsuariosEmpleados(){
        System.out.println("ejecutanto store procedure...");
        jdbcTemplate.update("DO $$ BEGIN  PERFORM INSERTAR_USUARIOS_EMPLEADOS(); END $$;");
        System.out.println("store procedure ok?...");
    }

    public void storeProcedure_cargarClientes(){
        System.out.println("ejecutanto store procedure...");
        jdbcTemplate.update("DO $$ BEGIN  PERFORM INSERTAR_CLIENTES(); END $$;");
        System.out.println("store procedure ok?...");
    }

    public void storeProcedure_cargarAlmacen(){
        System.out.println("ejecutanto store procedure...");
        jdbcTemplate.update("DO $$ BEGIN  PERFORM INSERTAR_ALMACEN(); END $$;");
        System.out.println("store procedure ok?...");
    }

    public void storeProcedure_cargarCategoriaProductos(){
        System.out.println("ejecutanto store procedure...");
        jdbcTemplate.update("DO $$ BEGIN  PERFORM INSERTAR_CATEGORIAPRODUCTOS(); END $$;");
        System.out.println("store procedure ok?...");
    }

    public void storeProcedure_cargarProductos(){
        System.out.println("ejecutanto store procedure...");
        jdbcTemplate.update("DO $$ BEGIN  PERFORM INSERTAR_PRODUCTOS(); END $$;");
        System.out.println("store procedure ok?...");
    }


    public void storeProcedure_cargarLimpiarCargaMasiva(){
        System.out.println("ejecutanto store procedure...");
        jdbcTemplate.update("DO $$ BEGIN  PERFORM LIMPIAR_DATA(); END $$;");
        System.out.println("store procedure ok?...");
    }
    public void storeProcedure_cargarPedido(){
        System.out.println("ejecutanto store procedure...");
        jdbcTemplate.update("DO $$ BEGIN  PERFORM INSERTAR_PEDIDOS(); END $$;");
        System.out.println("store procedure ok?...");
    }
}
