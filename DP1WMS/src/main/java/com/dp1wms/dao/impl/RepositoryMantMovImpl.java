package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dp1wms.util.DateParser;
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
        String SQL= "SELECT p.idproducto,p.nombreproducto,l.stockparcial,l.fechaentrada,l.idlote,p.codigo FROM public.producto p,public.lote l WHERE l.idproducto = p.idproducto ";

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
        Timestamp t_fechaentrada = rs.getTimestamp("fechaentrada");
        String fecha = DateParser.timestampToString(t_fechaentrada);
        if(fecha == null){
            lote.setFechaEntrada("");
        }else{
            lote.setFechaEntrada(fecha);
        }
        //lote.setFechaEntrada(rs.getString("fechaentrada"));
        lote.setIdLote(rs.getInt("idlote"));
        lote.setCodigoProducto(rs.getString("codigo"));
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
    public int registrarMovimiento(Integer totalProductos,String observaciones,Timestamp fecha,int idTipoMovimiento,int idProducto,Integer idLote,Integer cantidad,Long idEmpleadoAuditado,int idCajon){



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


        Integer idCajonInt = new Integer(idCajon);
        if(idCajon == -1){
            idCajonInt = null;
        }

        System.out.println("IdMovimiento Insertado -> "+idMovimiento);
        if(idMovimiento != 0){
            SQL = "INSERT INTO public.detallemovimiento (idmovimiento,idproducto,idlote,cantidad,idcajon) VALUES (?,?,?,?,?)";
            try{
                jdbcTemplate.update(SQL,new Object[] {idMovimiento,idProducto,idLote,cantidad,idCajonInt});
                System.out.println("Se ha insertado en la tabla detalle movimiento");
            }catch (EmptyResultDataAccessException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return idMovimiento;
    }

    @Transactional(rollbackFor = Exception.class)
    public int registrarLote(int idProducto,Timestamp fechalote,Timestamp fechaEntrada,int stockParcial,Long idEmpleadoAuditado,int idCajon){
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

        if(idCajon != -1){
            SQL = "INSERT INTO public.ubicacion (idlote,idproducto,idcajon,cantidad) VALUES (?,?,?,?)";
            try{
                jdbcTemplate.update(SQL,new Object[] {idLote,idProducto,idCajon,stockParcial});
                System.out.println("Se ha insertado en la tabla ubicacion");
            }catch (EmptyResultDataAccessException e) {
                e.printStackTrace();
                throw e;
            }
        }

        int idTipoMovimiento =1; // para ingreso de lote
        int totalProductos = 1;
        System.out.println("IdLoteInsertado -> "+idLote);
        if(idLote != 0){
            registrarMovimiento(totalProductos,"",fechaEntrada,idTipoMovimiento,idProducto,idLote,stockParcial,idEmpleadoAuditado,idCajon);
        }

        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public int registrarMovimiento(Movimiento movimiento){

        String SQL = "INSERT INTO public.movimiento (totalproductos,observaciones,fechamovimiento,idtipomovimiento,idempleadoauditado,idenvio) VALUES (?,?,?,?,?,?) RETURNING idmovimiento";

        int idMovimiento = 0;
        int idTipoMovSalida = 6;
        try {
            Movimiento movimientoInsertado = (Movimiento) this.jdbcTemplate.queryForObject(SQL, new Object[]{movimiento.getTotalProductos(),movimiento.getObservaciones(),movimiento.getFechaMovimiento(),idTipoMovSalida,movimiento.getIdEmpleadoAuditado(),movimiento.getIdEnvio()},
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

    public List<Almacen> obtenerAlmacenes() {
        String SQL = "SELECT * FROM almacen WHERE activo";
        List<Almacen> listaAlmacenes = null;
        try{
            listaAlmacenes = jdbcTemplate.query(SQL,new Object[] {}, this::mapParamAlmacen);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        for(Almacen almacen: listaAlmacenes){
            List<Area> listaAreas = this.obtenerAreas(almacen.getIdAlmacen());
            if(listaAreas.isEmpty()){
                almacen.setListaArea(new ArrayList<Area>());
            }else{
                almacen.setListaArea(listaAreas);
            }

        }

        return listaAlmacenes;
    }

    private Almacen mapParamAlmacen(ResultSet rs, int i) throws SQLException {
        Almacen almacen = new Almacen();
        almacen.setIdAlmacen(rs.getInt("idalmacen"));
        almacen.setNombre(rs.getString("nombre"));
        return almacen;
    }


    private List<Area> obtenerAreas(int idAlmacen){
        String SQL = "SELECT * FROM area WHERE activo and idalmacen = ?";
        List<Area> listaAreas = null;
        try{
            listaAreas = jdbcTemplate.query(SQL,new Object[] {idAlmacen}, this::mapParamArea);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        for(Area area: listaAreas){
            List<Rack> listaRacks  =this.obtenerRack(area.getIdArea());
            if(listaRacks.isEmpty()){
                area.setListaRack(new ArrayList<Rack>());
            }else{
                area.setListaRack(listaRacks);
            }

        }

        return listaAreas;
    }

    private Area mapParamArea(ResultSet rs, int i) throws SQLException {
        Area area = new Area();
        area.setCodigo(rs.getString("codigo"));
        area.setIdArea(rs.getInt("idarea"));
        area.setIdAlmacen(rs.getInt("idalmacen"));
        return area;
    }


    private List<Rack> obtenerRack(int idArea){
        String SQL = "SELECT * FROM rack WHERE activo and idarea = ?";
        List<Rack> listaRack = null;
        try{
            listaRack = jdbcTemplate.query(SQL,new Object[] {idArea}, this::mapParamRack);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        for(Rack rack: listaRack){
            List<Cajon> listaCajones = this.obtenerCajones(rack.getIdRack());
            //System.out.println("DAO tam lista cajones: "+listaCajones.size());
            if(listaCajones.isEmpty()){
                rack.setListaCajones(new ArrayList<Cajon>());
            }else{
                rack.setListaCajones(listaCajones);
            }

        }

        return listaRack;
    }

    private Rack mapParamRack(ResultSet rs, int i) throws SQLException {
        Rack rack = new Rack();
        rack.setCodigo(rs.getString("codigo"));
        rack.setIdRack(rs.getInt("idrack"));
        rack.setIdArea(rs.getInt("idarea"));
        return rack;
    }

    private List<Cajon> obtenerCajones(int idRack){
        String SQL = "SELECT idcajon,idrack,posicion[0] as posX,posicion[1] as posY FROM cajon WHERE idrack = ?";
        List<Cajon> listaCajones = null;
        try{
            listaCajones = jdbcTemplate.query(SQL,new Object[] {idRack}, this::mapParamCajon);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return listaCajones;
    }


    private Cajon mapParamCajon(ResultSet rs, int i) throws SQLException {
        Cajon cajon = new Cajon();
        cajon.setIdCajon(rs.getInt("idcajon"));
        cajon.setIdRack(rs.getInt("idrack"));
        cajon.setPosX(rs.getInt("posX"));
        cajon.setPosY(rs.getInt("posY"));
        return cajon;
    }

    public List<Ubicacion> obtenerUbicaciones(int idLote,int idProducto) {
        String SQL = "SELECT al.idalmacen, al.nombre as nombreAlmacen,ar.idarea,ar.codigo as codigoArea, r.idrack,r.codigo as codigoRack,c.idcajon,c.posicion[0] as cajonX,c.posicion[1] as cajonY, u.cantidad FROM almacen al,area ar,rack r,cajon c,ubicacion u WHERE u.idlote = ? and u.idproducto = ? and u.idcajon = c.idcajon and c.idrack = r.idrack and r.idarea = ar.idarea and ar.idalmacen = al.idalmacen";
        List<Ubicacion> listaUbicaciones = null;
        try{
            listaUbicaciones = jdbcTemplate.query(SQL,new Object[] {idLote,idProducto}, this::mapParamUbicacion);
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
        return ubicacion;
    }


    public void insertarUbicacion(int idLote,int idProducto,int idCajon,int cantidad,Long idEmpleadoAuditado){
        String SQL = "INSERT INTO public.ubicacion (idlote,idproducto,idcajon,cantidad) VALUES (?,?,?,?)";
        try{
            jdbcTemplate.update(SQL,new Object[] {idLote,idProducto,idCajon,cantidad});
            System.out.println("Se ha insertado en la tabla ubicacion");
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }

        java.util.Date today = new java.util.Date();
        Timestamp fechahoraactual =new java.sql.Timestamp(today.getTime());
        int idTipoMovimiento = 8;
        registrarMovimiento(1,"Asignacion de un lote a una ubicacion",fechahoraactual,idTipoMovimiento,idProducto,idLote,cantidad,idEmpleadoAuditado,idCajon);
    }

    public void actualizarUbicacion(int idLote,int idProducto,int idCajon,int cantidad,int idCajonAntiguo,Long idEmpleadoAuditado){
        //System.out.println(idLote+"|"+idProducto+"|"+idCajon+"|"+cantidad+"|"+idCajonAntiguo);
        String SQL = "UPDATE public.ubicacion SET idcajon=?,cantidad=? WHERE idlote = ? and idproducto = ? and idcajon = ?";
        try{
            jdbcTemplate.update(SQL,new Object[] {idCajon,cantidad,idLote,idProducto,idCajonAntiguo});
            System.out.println("Se ha actualizado en la tabla ubicacion");
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }
        java.util.Date today = new java.util.Date();
        Timestamp fechahoraactual =new java.sql.Timestamp(today.getTime());
        int idTipoMovimiento = 8;
        registrarMovimiento(1,"Asignacion de un lote a una ubicacion",fechahoraactual,idTipoMovimiento,idProducto,idLote,cantidad,idEmpleadoAuditado,idCajon);
    }
}
