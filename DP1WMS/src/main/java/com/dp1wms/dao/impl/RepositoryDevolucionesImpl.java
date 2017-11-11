package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryDevoluciones;
import com.dp1wms.model.*;
import com.dp1wms.util.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryDevolucionesImpl implements RepositoryDevoluciones{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ComprobantePago> obtenerFacturas(Boolean activo){
        String SQL = "SELECT c.idcomprobante,c.idtipocomprobante,c.idcliente,c.igv,c.total,c.fechacreacion,c.activo,c.costoflete,c.idenvio,c.subtotal,t.descripcion,e.razonsocial,e.direccion,e.numdoc FROM public.comprobantepago c, public.tipocomprobante t,public.cliente e WHERE c.activo = ? and t.idtipocomprobante = c.idtipocomprobante and e.idcliente = c.idcliente";
        List<ComprobantePago> listaComprobantes = null;
        try{
            listaComprobantes = jdbcTemplate.query(SQL,new Object[] {activo}, this::mapParam);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        for(ComprobantePago comprobantePago: listaComprobantes){
            List<DetalleFactura> listaDetalle =  this.obtenerDetalleComprobante(comprobantePago.getV_id());
            if((listaDetalle == null) || listaDetalle.isEmpty()){
                comprobantePago.setListaDetalleComprobante(new ArrayList<DetalleFactura>());
            }else{
                comprobantePago.setListaDetalleComprobante(listaDetalle);
            }
        }
        return listaComprobantes;
    }

    public ComprobantePago mapParam(ResultSet rs, int i) throws SQLException {
        ComprobantePago comprobantePago = new ComprobantePago();

        comprobantePago.setV_id(rs.getLong("idcomprobante"));
        comprobantePago.setV_idTipoComprobante(rs.getLong("idtipocomprobante"));
        comprobantePago.setV_idCliente(rs.getLong("idcliente"));
        comprobantePago.setV_igv(rs.getFloat("igv"));
        comprobantePago.setV_total(rs.getFloat("total"));

        Timestamp fechacreacion = rs.getTimestamp("fechacreacion");
        if(fechacreacion == null){
            comprobantePago.setV_fechaCreacion("");
        }else{
            comprobantePago.setV_fechaCreacion(DateParser.timestampToString(fechacreacion));
        }
        //comprobantePago.setV_fechaCreacion(rs.getString("fechacreacion"));
        comprobantePago.setV_activo(rs.getBoolean("activo"));
        comprobantePago.setV_flete(rs.getFloat("costoflete"));
        comprobantePago.setV_idEnvio(rs.getLong("idenvio"));
        comprobantePago.setV_subtotal(rs.getFloat("subtotal"));
        comprobantePago.setV_tipoComprobante(rs.getString("descripcion"));
        comprobantePago.setV_cliente(rs.getString("razonsocial"));
        comprobantePago.setDireccionCliente(rs.getString("direccion"));
        comprobantePago.setNumeroDocumentoCliente(rs.getString("numdoc"));
        return comprobantePago;
    }

    public List<DetalleFactura> obtenerDetalleComprobante(Long idComprobante){
        String SQL = "SELECT d.iddetallecomprobante,d.idcomprobante,d.idproducto,d.cantidad,d.preciounitario,d.descuento,d.subtotal,p.codigo, p.nombreproducto FROM public.detallecomprobante d, public.producto p WHERE d.idproducto = p.idproducto and idcomprobante = ?";
        List<DetalleFactura> listaDetalleComprobante = null;
        try{
            listaDetalleComprobante = jdbcTemplate.query(SQL,new Object[] {idComprobante}, this::mapParam2);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return listaDetalleComprobante;
    }
    public DetalleFactura mapParam2(ResultSet rs, int i) throws SQLException {
        DetalleFactura detalleComprobantePago = new DetalleFactura();

        detalleComprobantePago.setIdDetalleComprobante(rs.getLong("iddetallecomprobante"));
        detalleComprobantePago.setIdComprobante(rs.getLong("idcomprobante"));
        detalleComprobantePago.setIdProducto(rs.getLong("idproducto"));
        detalleComprobantePago.setCantidad(rs.getInt("cantidad"));
        detalleComprobantePago.setPrecioUnitario(rs.getDouble("preciounitario"));
        detalleComprobantePago.setDescuento(rs.getDouble("descuento"));
        detalleComprobantePago.setSubtotal(rs.getDouble("subtotal"));
        detalleComprobantePago.setCodigoProducto(rs.getString("codigo"));
        detalleComprobantePago.setNombreProducto(rs.getString("nombreproducto"));

        return detalleComprobantePago;
    }

    @Transactional(rollbackFor = Exception.class)
    public int registrarMovimiento(Movimiento movimiento){

        String SQL = "INSERT INTO public.movimiento (totalproductos,observaciones,fechamovimiento,idtipomovimiento,idempleadoauditado) VALUES (?,?,?,?,?) RETURNING idmovimiento";

        int idMovimiento = 0;
        int idTipoMov = 10;// harcodeado movimiento devolucion
        try {
            Movimiento movimientoInsertado = (Movimiento) this.jdbcTemplate.queryForObject(SQL, new Object[]{movimiento.getTotalProductos(),movimiento.getObservaciones(),movimiento.getFechaMovimiento(),idTipoMov,movimiento.getIdEmpleadoAuditado()},
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


    private Movimiento obtenerMovimiento(Long idEnvio){
        String SQL = "select idmovimiento,totalproductos from public.movimiento where idenvio = ?";
        List<Movimiento> listaMovimientos = null;
        try{
            listaMovimientos = jdbcTemplate.query(SQL,new Object[] {idEnvio}, this::mapParam3);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        if((listaMovimientos == null) || (listaMovimientos.isEmpty()) || (listaMovimientos.size()>1)){
            System.out.println("No se obtuvo el movimiento esperado");
            return null;
        }

        Movimiento movimiento = listaMovimientos.get(0);// el unico movimiento
        SQL = "SELECT idmovimiento,idproducto,idlote,cantidad,idcajon FROM public.detallemovimiento WHERE idmovimiento = ?";
        List<DetalleMovimiento> listaDetalleMov = null;
        try{
            listaDetalleMov = jdbcTemplate.query(SQL,new Object[] {movimiento.getIdMovimiento()}, this::mapParam4);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        movimiento.setListaDetalleMovimiento(listaDetalleMov);

        return movimiento;
    }

    public Movimiento mapParam3(ResultSet rs, int i) throws SQLException {
        Movimiento movimiento = new Movimiento();

        movimiento.setIdMovimiento(rs.getInt("idmovimiento"));
        movimiento.setTotalProductos(rs.getInt("totalproductos"));
        return movimiento;
    }

    public DetalleMovimiento mapParam4(ResultSet rs, int i) throws SQLException {
        DetalleMovimiento detalleMovimiento = new DetalleMovimiento();

        detalleMovimiento.setIdMovimiento(rs.getInt("idmovimiento"));
        detalleMovimiento.setIdProducto(rs.getInt("idproducto"));
        detalleMovimiento.setIdLote(rs.getInt("idlote"));
        detalleMovimiento.setCantidad(rs.getInt("cantidad"));
        detalleMovimiento.setIdCajon(rs.getInt("idcajon"));
        return detalleMovimiento;
    }

    @Transactional(rollbackFor = Exception.class)
    public void registrarPedidoDevolucion(Long idEmpleadoAuditado,Long idComprobantePago, Long idEnvio){


        Movimiento movimiento = this.obtenerMovimiento(idEnvio);
        if(movimiento == null){
            System.out.println("Movimiento nulo");
            return;
        }
        movimiento.setIdEmpleadoAuditado(idEmpleadoAuditado);
        movimiento.setObservaciones("Pedido de devolucion");
        java.util.Date today = new java.util.Date();
        Timestamp fechahoraactual =new java.sql.Timestamp(today.getTime());
        movimiento.setFechaMovimiento(fechahoraactual);

        int idMov = this.registrarMovimiento(movimiento);
        System.out.println("Para el pedido de devolucion se inserto el movimiento con id "+idMov);

        String SQL = "UPDATE public.comprobantepago SET idempleadoauditado = ?, activo = false WHERE idcomprobante = ?";
        try{
            jdbcTemplate.update(SQL,new Object[] {idEmpleadoAuditado,idComprobantePago});
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void registrarNotaCredito(Long idEmpleadoAuditado,Long idComprobantePago, Long idCliente){
        String SQL = "INSERT INTO public.notacredito (idcomprobante,idcliente,idempleadoauditado) VALUES (?,?,?)";
        try{
            jdbcTemplate.update(SQL,new Object[] {idComprobantePago,idCliente,idEmpleadoAuditado});
            System.out.println("Se ha insertado en la tabla nota credito");
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<ComprobantePago> obtenerFacturasNotasCredito(){
        String SQL = "SELECT c.idcomprobante,c.idtipocomprobante,c.idcliente,c.igv,c.total,c.fechacreacion,c.activo,c.costoflete,c.idenvio,c.subtotal,t.descripcion,e.razonsocial,e.direccion,e.numdoc,n.idnotadecredito FROM public.comprobantepago c, public.tipocomprobante t,public.cliente e,public.notacredito n WHERE n.idcomprobante = c.idcomprobante and t.idtipocomprobante = c.idtipocomprobante and e.idcliente = c.idcliente";
        List<ComprobantePago> listaComprobantes = null;
        try{
            listaComprobantes = jdbcTemplate.query(SQL,new Object[] {}, this::mapParam5);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        for(ComprobantePago comprobantePago: listaComprobantes){
            List<DetalleFactura> listaDetalle =  this.obtenerDetalleComprobante(comprobantePago.getV_id());
            if((listaDetalle == null) || listaDetalle.isEmpty()){
                comprobantePago.setListaDetalleComprobante(new ArrayList<DetalleFactura>());
            }else{
                comprobantePago.setListaDetalleComprobante(listaDetalle);
            }
        }
        return listaComprobantes;
    }


    public ComprobantePago mapParam5(ResultSet rs, int i) throws SQLException {
        ComprobantePago comprobantePago = new ComprobantePago();

        comprobantePago.setV_id(rs.getLong("idcomprobante"));
        comprobantePago.setV_idTipoComprobante(rs.getLong("idtipocomprobante"));
        comprobantePago.setV_idCliente(rs.getLong("idcliente"));
        comprobantePago.setV_igv(rs.getFloat("igv"));
        comprobantePago.setV_total(rs.getFloat("total"));

        Timestamp fechacreacion = rs.getTimestamp("fechacreacion");
        if(fechacreacion == null){
            comprobantePago.setV_fechaCreacion("");
        }else{
            comprobantePago.setV_fechaCreacion(DateParser.timestampToString(fechacreacion));
        }
        //comprobantePago.setV_fechaCreacion(rs.getString("fechacreacion"));
        comprobantePago.setV_activo(rs.getBoolean("activo"));
        comprobantePago.setV_flete(rs.getFloat("costoflete"));
        comprobantePago.setV_idEnvio(rs.getLong("idenvio"));
        comprobantePago.setV_subtotal(rs.getFloat("subtotal"));
        comprobantePago.setV_tipoComprobante(rs.getString("descripcion"));
        comprobantePago.setV_cliente(rs.getString("razonsocial"));
        comprobantePago.setDireccionCliente(rs.getString("direccion"));
        comprobantePago.setNumeroDocumentoCliente(rs.getString("numdoc"));
        comprobantePago.setIdNotaCredito((rs.getLong("idnotadecredito")));
        return comprobantePago;
    }

}
