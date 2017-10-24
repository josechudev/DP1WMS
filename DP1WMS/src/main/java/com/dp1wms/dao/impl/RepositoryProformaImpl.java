package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryProforma;
import com.dp1wms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryProformaImpl implements RepositoryProforma {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Producto> buscarProductosParaVenta(String campo, String dato){
        List<Producto> productos = null;

        dato = "%" + dato.toLowerCase()  + "%";
        String sql = "SELECT p.idproducto, p.codigo, p.nombreproducto, p.precio, p.idcategoria, cp.descripcion as categoria," +
                        " (p.stock - COALESCE(x.cantidad, 0)) as stock " +
                    "FROM producto p INNER JOIN categoriaproducto cp ON p.idcategoria = cp.idcategoria" +
                    " LEFT JOIN ( " +
                        "SELECT dp.idproducto, sum(dp.cantidad) as cantidad " +
                        "FROM detallepedido dp INNER JOIN pedido p ON dp.idpedido = p.idpedido " +
                        "WHERE p.idestadopedido = 1 GROUP BY dp.idproducto" +
                    ") x ON p.idproducto = x.idproducto WHERE p.activo AND ";
        if(campo != null){
            sql += "lower(p." + campo + ") LIKE ?";
        } else {
            sql += "lower(cp.descripcion) LIKE ?";
        }

        try{
            productos = this.jdbcTemplate.query(sql, new Object[]{dato}, this::mapProducto);
            return productos;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean registrarProformaYEnvios(Proforma proforma, ArrayList<Envio> envios){
        String sqlProforma = "INSERT INTO proforma (idempleado, idcliente, observaciones, totalsinflete, costoflete, total)" +
                " VALUES (?, ?, ?, ?, ?, ?) RETURNING idproforma";
        try{
            int idProforma = jdbcTemplate.queryForObject(sqlProforma, new Object[]{
                    proforma.getIdEmpleado(),
                    proforma.getIdCliente(),
                    proforma.getObservaciones(),
                    proforma.getTotalSinFlete(),
                    proforma.getCostoFlete(),
                    proforma.getTotal()}, (rs, i)->{
                return new Integer(rs.getInt("idproforma"));
            });
            proforma.setIdProforma(idProforma);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        String sqlDetalleProforma = "INSERT INTO detalleproforma (idproforma, idproducto, cantidad, preciounitario, descuento)" +
                " VALUES (" + String.valueOf(proforma.getIdProforma()) + ", ?, ?, ?, ?)";
        try{
            for(DetalleProforma dp: proforma.getDetallesProforma()){
                jdbcTemplate.update(sqlDetalleProforma, new Object[]{
                        dp.getProducto().getIdProducto(),
                        dp.getCantidad(),
                        dp.getProducto().getPrecio(),
                        dp.getDescuento()
                });
            }
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        String sqlEnvio = "INSERT INTO envio (idproforma, fechaenvio, destino, costoflete) " +
                "VALUES (" + String.valueOf(proforma.getIdProforma()) + ", to_timestamp(?, 'DD/MM/YYYY'), ?, ?) RETURNING idenvio";
        try{
            for(Envio e: envios){
                int idEnvio = jdbcTemplate.queryForObject(sqlEnvio, new Object[]{
                    e.getFechaEnvio(), e.getDestino(), e.getCostoFlete()
                }, (rs, i)->{
                    return new Integer(rs.getInt("idenvio"));
                });
                e.setIdEnvio(idEnvio);
                String sqlDetalleEnvio = "INSERT INTO detalleenvio (idenvio, cantidad, idproducto) " +
                        "VALUES (" + String.valueOf(e.getIdEnvio()) + ", ?, ?)";
                for(DetalleEnvio de: e.getDetalleEnvio()){
                    jdbcTemplate.update(sqlDetalleEnvio, new Object[]{
                        de.getCantidad(), de.getProducto().getIdProducto()
                    });
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        return true;
    }

    private Producto mapProducto(ResultSet rs, int i) throws SQLException{
        Producto p = new Producto();
        p.setIdProducto(rs.getInt("idproducto"));
        p.setCodigo(rs.getString("codigo"));
        p.setNombreProducto(rs.getString("nombreproducto"));
        p.setPrecio(rs.getFloat("precio"));
        p.setIdCategoria(rs.getInt("idcategoria"));
        p.setStock(rs.getInt("stock"));
        p.setCategoria(rs.getString("categoria"));
        return p;
    }
}
