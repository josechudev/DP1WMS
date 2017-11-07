package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryGuia;
import com.dp1wms.model.DetalleGuia;
import com.dp1wms.model.Guia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryGuiaImpl implements RepositoryGuia {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    public int registrarGuia(Guia guia){

        String motivoTraslado = "Envio Productos al Cliente";

        String SQL = "INSERT INTO public.guiaremision (fechaemision,fechavencimiento,fechapartida,observaciones,puntopartida,puntollegada,transportista,numeroplaca,pesototal,idenvio,idempleadoauditado,numeroguia,motivotraslado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING idguiaremision";

        Long idGuia = null;
        try {
            Guia guiaInsertada = (Guia) this.jdbcTemplate.queryForObject(SQL, new Object[]{guia.getFechaEmision(),guia.getFechaVencimiento(),guia.getFechaPartida(),guia.getObservaciones(),guia.getPuntoPartida(),guia.getPuntoLlegada(),guia.getNombreTransportista(),guia.getNumeroPlaca(),guia.getPesoTotal(),guia.getIdEnvio(),guia.getIdEmpleadoAuditado(),guia.getNumeroGuia(),motivoTraslado},
                    (rs, i)->{
                        Guia guiaTemporal = new Guia();
                        guiaTemporal.setIdGuiaRemision(rs.getLong("idguiaremision"));
                        return guiaTemporal;
                    });
            idGuia = guiaInsertada.getIdGuiaRemision();
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        System.out.println("idGuia Insertado -> "+idGuia);
        List<DetalleGuia> listaDetalleGuia = guia.getListaDetalleGuia();
        int idEstadoEnviado = 1;// estado enviado
        for(DetalleGuia detalle : listaDetalleGuia){
            if(idGuia != null){
                SQL = "INSERT INTO public.detalleguia (idguiaremision,idproducto,cantidad,idestadodetalleremision,peso) VALUES (?,?,?,?,?)";
                try{
                    jdbcTemplate.update(SQL,new Object[] {idGuia,detalle.getIdProducto(),detalle.getCantidad(),idEstadoEnviado,detalle.getPeso()});
                    System.out.println("Se ha insertado en la tabla detalle guia");
                }catch (EmptyResultDataAccessException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }

        return 1;
    }

    public Float obtenerPesoProducto(int idProducto){
        String SQL = "SELECT peso FROM public.producto WHERE idproducto=?";
        Float peso=null;
        try{
            peso = jdbcTemplate.queryForObject(SQL,new Object[] {idProducto},Float.class);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return peso;
    }

    public String obtenerNombreCliente(Long idCliente){
        String SQL = "Select razonsocial from public.cliente where idcliente = ?";
        String razonSocial = null;
        try{
            razonSocial = jdbcTemplate.queryForObject(SQL,new Object[] {idCliente},String.class);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return razonSocial;
    }

    public List<Guia> obtenerGuias(){
        String SQL = "Select idguiaremision,fechaemision,fechavencimiento,fechapartida,observaciones,puntopartida,puntollegada,transportista,numeroplaca,pesototal,idenvio,idempleadoauditado,numeroguia,motivotraslado from public.guiaremision";

        List<Guia> listaGuias=null;
        try{
            listaGuias = jdbcTemplate.query(SQL,new Object[] {}, this::mapParam);
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        for(Guia guia:listaGuias){
            Long idEnvio = guia.getIdEnvio();
            String SQL2 = "Select idcliente from public.pedido p, public.envio e where p.idpedido = e.idpedido and idenvio = ?";
            Long idCliente=null;
            try{
                idCliente = jdbcTemplate.queryForObject(SQL2,new Object[] {idEnvio},Long.class);
            }catch (EmptyResultDataAccessException e) {
                e.printStackTrace();
            }
            if(idCliente!= null){
                guia.setRazonSocial(this.obtenerNombreCliente(idCliente));
                guia.setIdCliente(idCliente);
            }
        }

        return listaGuias;
    }

    public Guia mapParam(ResultSet rs, int i) throws SQLException {
        Guia guia = new Guia();

        guia.setIdGuiaRemision(rs.getLong("idguiaremision"));
        guia.setFechaEmision(rs.getTimestamp("fechaemision"));
        guia.setFechaVencimiento(rs.getTimestamp("fechavencimiento"));
        guia.setFechaPartida(rs.getTimestamp("fechapartida"));
        guia.setObservaciones(rs.getString("observaciones"));
        guia.setPuntoPartida(rs.getString("puntopartida"));
        guia.setPuntoLlegada(rs.getString("puntollegada"));
        guia.setNombreTransportista(rs.getString("transportista"));
        guia.setNumeroPlaca(rs.getString("numeroplaca"));
        guia.setPesoTotal(rs.getFloat("pesototal"));
        guia.setIdEnvio(rs.getLong("idenvio"));
        guia.setIdEmpleadoAuditado(rs.getLong("idempleadoauditado"));
        guia.setNumeroGuia(rs.getString("numeroguia"));
        guia.setMotivoTraslado(rs.getString("motivotraslado"));

        return guia;
    }
}
