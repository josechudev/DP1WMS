package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositoryMantMovImpl implements RepositoryMantMov{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Producto> obtenerProductos(){
        String SQL= "SELECT p.stock,p.idproducto,p.nombreproducto,p.idcategoria,c.descripcion FROM public.producto p,public.categoriaproducto c WHERE c.idcategoria = p.idcategoria ";

        List<Producto> listaProductos = null;
        try{
            listaProductos = jdbcTemplate.query(SQL,new Object[] {}, this::mapParam);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return listaProductos;
    }

    private Producto mapParam(ResultSet rs, int i) throws SQLException {
        Producto producto = new Producto();
        producto.setIdProducto(rs.getInt("idproducto"));
        producto.setNombreProducto(rs.getString("nombreproducto"));
        producto.setIdCategoria(rs.getInt("idcategoria"));
        producto.setCategoria(rs.getString("descripcion"));
        producto.setStock(rs.getInt("stock"));
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
        return listaCategorias;
    }

    private CategoriaProducto mapParam2(ResultSet rs, int i) throws SQLException {
        CategoriaProducto categoria = new CategoriaProducto();
        categoria.setIdCategoria(rs.getInt("idcategoria"));
        categoria.setDescripcion(rs.getString("descripcion"));
        // aqui setean todas las columnas que quieran
        return categoria;
    }

    public List<Lote> obtenerLotes(){
        String SQL= "SELECT p.idproducto,p.nombreproducto,l.stockparcial,l.fechaentrada,l.idlote FROM public.producto p,public.lote l WHERE l.idproducto = p.idproducto ";

        List<Lote> listaLotes = null;
        try{
            listaLotes = jdbcTemplate.query(SQL,new Object[] {}, this::mapParam3);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return listaLotes;
    }

    private Lote mapParam3(ResultSet rs, int i) throws SQLException {
        Lote lote = new Lote();
        lote.setIdProducto(rs.getInt("idproducto"));
        lote.setNombreProducto(rs.getString("nombreproducto"));
        lote.setStockParcial(rs.getInt("stockparcial"));
        lote.setFechaEntrada(rs.getString("fechaentrada"));
        lote.setIdLote(rs.getInt("idlote"));
        // aqui setean todas las columnas que quieran
        return lote;
    }

    public List<TipoMovimiento> obtenerTiposMovimiento(){
        String SQL= "SELECT idtipomovimiento,descripcion FROM public.tipomovimiento";

        List<TipoMovimiento> listaTipoMovimiento = null;
        try{
            listaTipoMovimiento = jdbcTemplate.query(SQL,new Object[] {}, this::mapParam4);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return listaTipoMovimiento;
    }

    private TipoMovimiento mapParam4(ResultSet rs, int i) throws SQLException {
        TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setIdTipoMovimiento(rs.getInt("idtipomovimiento"));
        tipoMovimiento.setDescripcion(rs.getString("descripcion"));
        // aqui setean todas las columnas que quieran
        return tipoMovimiento;
    }

    @Transactional(rollbackFor = Exception.class)
    public int registrarMovimiento(Integer totalProductos,String observaciones,Timestamp fecha,int idTipoMovimiento,int idProducto,Integer idLote,Integer cantidad,Long idEmpleadoAuditado){



        String SQL = "INSERT INTO public.movimiento (totalproductos,observaciones,fechamovimiento,idtipomovimiento,idempleadoauditado) VALUES (?,?,?,?,?) RETURNING idmovimiento";

       int idMovimiento = 0;
        try {
            Movimiento movimientoInsertado = (Movimiento) this.jdbcTemplate.queryForObject(SQL, new Object[]{totalProductos,observaciones,fecha,idTipoMovimiento,idEmpleadoAuditado},
                    (rs, i)->{
                        Movimiento movimientoTemporal = new Movimiento();
                        movimientoTemporal.setIdMovimiento(rs.getInt("idmovimiento"));
                        return movimientoTemporal;
                    });
            idMovimiento = movimientoInsertado.getIdMovimiento();
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        System.out.println("IdMovimiento Insertado -> "+idMovimiento);
        if(idMovimiento != 0){
            SQL = "INSERT INTO public.detallemovimiento (idmovimiento,idproducto,idlote,cantidad) VALUES (?,?,?,?)";
            try{
                jdbcTemplate.update(SQL,new Object[] {idMovimiento,idProducto,idLote,cantidad});
                System.out.println("Se ha insertado en la tabla detalle movimiento");
            }catch (EmptyResultDataAccessException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return idMovimiento;
    }

    @Transactional(rollbackFor = Exception.class)
    public int registrarLote(int idProducto,Timestamp fechalote,Timestamp fechaEntrada,int stockParcial,Long idEmpleadoAuditado){
        // el stock parcial no se debe insertar directamente en lote, es trabajo del trigger por eso se pone de valor cero
        String SQL = "INSERT INTO public.lote (idproducto,fechalote,fechaentrada,idempleadoauditado,stockparcial) VALUES (?,?,?,?,0) RETURNING idlote";
        int idLote = 0;
        try {
            Lote loteInsertado = (Lote) this.jdbcTemplate.queryForObject(SQL, new Object[]{idProducto,fechalote,fechaEntrada,idEmpleadoAuditado},
                    (rs, i)->{
                        Lote loteTemporal = new Lote();
                        loteTemporal.setIdLote(rs.getInt("idlote"));
                        return loteTemporal;
                    });
            idLote = loteInsertado.getIdLote();
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        int idTipoMovimiento =1; // para ingreso de lote
        int totalProductos = 1;
        System.out.println("IdLoteInsertado -> "+idLote);
        if(idLote != 0){
            registrarMovimiento(totalProductos,"",fechaEntrada,idTipoMovimiento,idProducto,idLote,stockParcial,idEmpleadoAuditado);
        }

        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public int registrarMovimiento(Movimiento movimiento){

        String SQL = "INSERT INTO public.movimiento (totalproductos,observaciones,fechamovimiento,idtipomovimiento,idempleadoauditado) VALUES (?,?,?,?,?) RETURNING idmovimiento";

        int idMovimiento = 0;
        int idTipoMovSalida = 6;
        try {
            Movimiento movimientoInsertado = (Movimiento) this.jdbcTemplate.queryForObject(SQL, new Object[]{movimiento.getTotalProductos(),movimiento.getObservaciones(),movimiento.getFechaMovimiento(),idTipoMovSalida,movimiento.getIdEmpleadoAuditado()},
                    (rs, i)->{
                        Movimiento movimientoTemporal = new Movimiento();
                        movimientoTemporal.setIdMovimiento(rs.getInt("idmovimiento"));
                        return movimientoTemporal;
                    });
            idMovimiento = movimientoInsertado.getIdMovimiento();
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        System.out.println("IdMovimiento Insertado -> "+idMovimiento);
        List<DetalleMovimiento> listaDetalleMovimiento = movimiento.getListaDetalleMovimiento();
        for(DetalleMovimiento detalle : listaDetalleMovimiento){
            if(idMovimiento != 0){
                SQL = "INSERT INTO public.detallemovimiento (idmovimiento,idproducto,idlote,cantidad) VALUES (?,?,?,?)";
                try{
                    jdbcTemplate.update(SQL,new Object[] {idMovimiento,detalle.getIdProducto(),detalle.getIdLote(),detalle.getCantidad()});
                    System.out.println("Se ha insertado en la tabla detalle movimiento");
                }catch (EmptyResultDataAccessException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        return idMovimiento;
    }


}
