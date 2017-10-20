package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.CategoriaProducto;
import com.dp1wms.model.Lote;
import com.dp1wms.model.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dp1wms.model.TipoMovimiento;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class RepositoryMantMovImpl implements RepositoryMantMov{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Producto> obtenerProductos(){
        String SQL= "SELECT p.idproducto,p.nombreproducto,p.idcategoria,c.descripcion FROM public.producto p,public.categoriaproducto c WHERE c.idcategoria = p.idcategoria ";

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
        producto.setIdProducto(rs.getInt("idproducto"));
        producto.setNombreProducto(rs.getString("nombreproducto"));
        producto.setIdCategoria(rs.getInt("idcategoria"));
        producto.setCategoria(rs.getString("descripcion"));
        // aqui setean todas las columnas que quieran
        return producto;
    }

    public List<CategoriaProducto> obtenerCategoriasProducto(){
        String SQL= "SELECT idcategoria,descripcion FROM public.categoriaproducto";

        List<CategoriaProducto> listaCategorias = null;
        try{
            listaCategorias = jdbcTemplate.query(SQL,new Object[] {}, this::mapParam2);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Lista Categorias Productos -> " + listaCategorias + "Tam -> " +listaCategorias.size());
        return listaCategorias;
    }

    public CategoriaProducto mapParam2(ResultSet rs, int i) throws SQLException {
        CategoriaProducto categoria = new CategoriaProducto();
        categoria.setIdCategoria(rs.getInt("idcategoria"));
        categoria.setDescripcion(rs.getString("descripcion"));
        // aqui setean todas las columnas que quieran
        return categoria;
    }

    public List<Lote> obtenerLotes(){
        String SQL= "SELECT p.idproducto,p.nombreproducto,l.stockparcial,l.fechaentrada FROM public.producto p,public.lote l WHERE l.idproducto = p.idproducto ";

        List<Lote> listaLotes = null;
        try{
            listaLotes = jdbcTemplate.query(SQL,new Object[] {}, this::mapParam3);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Lista Lotes -> " + listaLotes + "Tam -> " +listaLotes.size());
        return listaLotes;
    }

    public Lote mapParam3(ResultSet rs, int i) throws SQLException {
        Lote lote = new Lote();
        lote.setIdProducto(rs.getInt("idproducto"));
        lote.setNombreProducto(rs.getString("nombreproducto"));
        lote.setStockParcial(rs.getInt("stockparcial"));
        lote.setFechaEntrada(rs.getString("fechaentrada"));
        // aqui setean todas las columnas que quieran
        return lote;
    }

    public List<TipoMovimiento> obtenerTiposMovimiento(){
        String SQL= "SELECT idcategoria,descripcion FROM public.categoriaproducto";

        List<TipoMovimiento> listaTipoMovimiento = null;
        try{
            listaTipoMovimiento = jdbcTemplate.query(SQL,new Object[] {}, this::mapParam4);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Lista Categorias Tipo Movimientos -> " + listaTipoMovimiento + "Tam -> " +listaTipoMovimiento.size());
        return listaTipoMovimiento;
    }

    public TipoMovimiento mapParam4(ResultSet rs, int i) throws SQLException {
        TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setIdTipoMovimiento(rs.getInt("idtipomovimiento"));
        tipoMovimiento.setDescripcion(rs.getString("descripcion"));
        // aqui setean todas las columnas que quieran
        return tipoMovimiento;
    }

}
