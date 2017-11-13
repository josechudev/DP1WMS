package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryComprobantePago;
import com.dp1wms.dao.mapper.ComprobantePagoRowMapper;
import com.dp1wms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryComprobantePagoImpl implements RepositoryComprobantePago {

    private static final float IGV = (float) 0.18;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ComprobantePago> selectAllComprobantes(){
        String sql = "SELECT Cp.idComprobante, Cp.idTipoComprobante, Tc.descripcion, " +
                "Cp.idCliente, C.razonSocial, Cp.fechaCreacion, Cp.fechaModificacion, " +
                "Cp.subtotal, Cp.costoFlete, Cp.igv, Cp.total, Cp.idEnvio, Cp.activo " +
                "FROM ComprobantePago Cp INNER JOIN TipoComprobante Tc ON Cp.idTipoComprobante = Tc.idTipoComprobante " +
                "INNER JOIN Cliente C ON Cp.idCliente = C.idCliente " +
                //"WHERE Cp.activo = true " +
                "ORDER BY Cp.idComprobante";

        try{
            return jdbcTemplate.query(sql,
                    new ComprobantePagoRowMapper());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<ComprobantePago> selectAllComprobantesActivos(){
        String sql = "SELECT Cp.idComprobante, Cp.idTipoComprobante, Tc.descripcion, " +
                "Cp.idCliente, C.razonSocial, Cp.fechaCreacion, Cp.fechaModificacion, " +
                "Cp.subtotal, Cp.costoFlete, Cp.igv, Cp.total, Cp.idEnvio, Cp.activo " +
                "FROM ComprobantePago Cp INNER JOIN TipoComprobante Tc ON Cp.idTipoComprobante = Tc.idTipoComprobante " +
                "INNER JOIN Cliente C ON Cp.idCliente = C.idCliente " +
                "WHERE Cp.activo = true " +
                "ORDER BY Cp.idComprobante";

        try{
            return jdbcTemplate.query(sql,
                    new ComprobantePagoRowMapper());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void crearComprobantePago(Envio auxEnvio, TipoComprobantePago auxTipoComprobantePago, Usuario auxUsuario){
        /*String sql0 = "Delete from ComprobantePago";
        jdbcTemplate.update(sql0,
                new Object[]{  });*/

        String sql = "INSERT INTO ComprobantePago (idComprobante, idTipoComprobante, idCliente, " +
                            "fechaCreacion, fechaModificacion, costoFlete, " +
                            "idEnvio, idEmpleadoAuditado, " +
                            "total, igv, subtotal) " +
                            "VALUES(default, ?, ?, " +
                            "now(), now(), ?, " +
                            "?, ?, " +
                            "?, ?, ?) ";

        try {
            jdbcTemplate.update(sql,
                    new Object[]{ auxTipoComprobantePago.getV_id(), auxEnvio.getIdCliente(),
                                auxEnvio.getCostoFlete(),
                                auxEnvio.getIdEnvio(), auxUsuario.getIdusuario(),
                                0,0,0});
            this.llenarDetalleComprobantePago(auxEnvio);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void llenarDetalleComprobantePago(Envio auxEnvio){
        Long auxIdComprobantePago = this.getIdComprobantePago(auxEnvio);
        List<DetalleEnvio> auxListDetalleEnvio = auxEnvio.getDetalleEnvio();

        float auxSubtotal = 0;

        for(int i = 0; i < auxListDetalleEnvio.size(); i++){
            DetalleEnvio auxDetalleEnvio = auxListDetalleEnvio.get(i);
            DetallePedido auxDetallePedido = this.obtenerDetallePedido( auxEnvio.getIdPedido(), auxDetalleEnvio.getIdProducto() );

            String sql = "INSERT INTO DetalleComprobante (idDetalleComprobante, idComprobante, idProducto, " +
                    "cantidad, precioUnitario, " +
                    "descuento, " +
                    "subtotal ) " +
                    "VALUES(default, ?, ?, " +
                    "?, ?, " +
                    "?, " +
                    "? ) ";
            float auxDescuento = auxDetallePedido.getDescuento() * (auxDetalleEnvio.getCantidad()/auxDetallePedido.getCantidad());
            float auxSubTotalDetalle = (auxDetallePedido.getPrecioUnitario()*auxDetalleEnvio.getCantidad()) - auxDescuento;
            auxSubtotal += auxSubTotalDetalle;
            try {
                jdbcTemplate.update(sql,
                        new Object[]{ auxIdComprobantePago, auxDetalleEnvio.getIdProducto(),
                                auxDetalleEnvio.getCantidad(), auxDetallePedido.getPrecioUnitario(),
                                auxDescuento,
                                auxSubTotalDetalle });
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        String sql2 = "UPDATE ComprobantePago SET subtotal  = ?, " +
                "igv  = ?, total = ? " +
                "WHERE idComprobante = ?";
        //auxSubtotal += auxEnvio.getCostoFlete();
        try {
            jdbcTemplate.update(sql2,
                    new Object[]{ auxSubtotal,
                            ( ( auxSubtotal + auxEnvio.getCostoFlete() )/(1 + IGV) )*IGV , auxSubtotal + auxEnvio.getCostoFlete(),
                            auxIdComprobantePago});
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private DetallePedido obtenerDetallePedido(Long idPedido, int idProducto){
        String sql = "SELECT dp.*, p.nombreproducto, p.codigo " +
                "FROM detallepedido dp LEFT JOIN producto p ON dp.idproducto = p.idproducto " +
                "WHERE dp.idpedido = ? and dp.idproducto = ? " +
                "ORDER BY dp.iddetallepedido ASC";
        try{
            List<DetallePedido> detalles = this.jdbcTemplate.query(sql,
                    new Object[]{idPedido, idProducto},
                    (res,i)->{
                        DetallePedido dpAux = new DetallePedido();
                        dpAux.setIdDetallePedido(res.getInt("iddetallepedido"));
                        dpAux.setCantidad(res.getInt("cantidad"));
                        dpAux.setDescuento(res.getFloat("descuento"));
                        dpAux.setSubtotal(res.getFloat("subtotal"));

                        Producto p = new Producto();
                        p.setCodigo(res.getString("codigo"));
                        p.setNombreProducto(res.getString("nombreproducto"));
                        p.setIdProducto(res.getInt("idproducto"));
                        p.setPrecio(res.getFloat("preciounitario"));

                        dpAux.setProducto(p);
                        return dpAux;
                    });
            return detalles.get(0);
        } catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    private Long getIdComprobantePago(Envio auxEnvio){
        String sql = "SELECT idComprobante " +
                "FROM ComprobantePago " +
                "WHERE idEnvio = ? ";
        try {
            return (Long) jdbcTemplate.queryForObject(sql,
                    new Object[]{ auxEnvio.getIdEnvio() },
                    Long.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Long.valueOf(0);
    }

    public List<TipoComprobantePago> selectAllTipoComprobantePago(){
        String sql = "SELECT idTipoComprobante, descripcion " +
                "FROM tipoComprobante ";
        try{
            return jdbcTemplate.query(sql, new Object[]{}, this::mapParamTipoComprobante);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private TipoComprobantePago mapParamTipoComprobante(ResultSet rs, int i) throws SQLException {
        TipoComprobantePago u = new TipoComprobantePago();
        u.setV_id(rs.getLong(1));
        u.setV_descripcion(rs.getString(2));
        return u;
    }

    public TipoComprobantePago getIdTipoComprobantePago(String auxNombreTipoComprobante){
        String sql = "SELECT idTipoComprobante, descripcion " +
                "FROM tipoComprobante " +
                "WHERE descripcion = ? ";
        try{
            List<TipoComprobantePago> auxListaTipoComprobantePago = jdbcTemplate.query(sql, new Object[]{ auxNombreTipoComprobante }, this::mapParamTipoComprobante);
            return auxListaTipoComprobantePago.get(0);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public List<DetalleComprobantePago> getDetalleComprobantePago(Long auxIdComprobante){
        String sql = "SELECT Dc.idDetalleComprobante, Dc.idProducto, " +
                "p.codigo, p.nombreProducto, Dc.cantidad,  " +
                "Dc.precioUnitario, Dc.descuento, Dc.subtotal " +
                "FROM DetalleComprobante Dc INNER JOIN producto p ON Dc.idProducto = p.idProducto " +
                "WHERE Dc.idComprobante = ? ";
        try{
            List<DetalleComprobantePago> detalles = this.jdbcTemplate.query(sql,
                    new Object[]{auxIdComprobante},
                    (res,i)->{
                        DetalleComprobantePago dpAux = new DetalleComprobantePago();
                        dpAux.setV_id(res.getLong("idDetalleComprobante"));
                        dpAux.setV_idProducto(res.getLong("idProducto"));

                        dpAux.setV_codigoProducto(res.getString("codigo"));
                        dpAux.setV_nombreProducto(res.getString("nombreProducto"));
                        dpAux.setV_cant(res.getLong("cantidad"));

                        dpAux.setV_precioUnitario(res.getFloat("precioUnitario"));
                        dpAux.setV_descuento(res.getFloat("descuento"));
                        dpAux.setV_subtotal(res.getFloat("subtotal"));
                        return dpAux;
                    });
            return detalles;
        } catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    public void cambiarActivo(boolean auxActivo, Long auxIdComprobante, Usuario auxUsuario){
        String sql = "UPDATE ComprobantePago SET activo  = ?, fechaModificacion = now(), idEmpleadoAuditado = ?" +
                "WHERE idComprobante = ?";
        try {
            jdbcTemplate.update(sql,
                    new Object[]{ auxActivo, auxUsuario.getIdusuario(),
                            auxIdComprobante});
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean existeFacturaActiva(Long auxIdEnvio){
        String sql = "SELECT idComprobante " +
                "FROM ComprobantePago " +
                "WHERE idEnvio = ? and activo = true ";

        try{
            return ( ( jdbcTemplate.query(sql,
                    new Object[]{ auxIdEnvio },
                    (res,i)->{
                        ComprobantePago dpAux = new ComprobantePago();
                        dpAux.setV_id (res.getLong(1));
                        return dpAux;
                    }) ).size() != 0 );
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
