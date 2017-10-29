package com.dp1wms.dao.impl;

import com.dp1wms.controller.MainController;
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

    @Autowired
    private MainController mainController;

    @Override
    public List<Producto> buscarProductosParaVenta(String campo, String dato){
        List<Producto> productos = null;

        dato = "%" + dato.toLowerCase()  + "%";
        String sql = "SELECT p.idproducto, p.codigo, p.nombreproducto, p.precio, p.idcategoria, cp.descripcion as categoria "  +
                    "FROM producto p  INNER JOIN categoriaproducto cp ON p.idcategoria = cp.idcategoria WHERE p.activo AND ";
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
    public boolean registrarProforma(Proforma proforma){
        String sqlProforma = "INSERT INTO proforma (idempleado, idcliente, observaciones, total, idempleadoauditado)" +
                " VALUES (?, ?, ?, ?, ?) RETURNING idproforma";
        try{
            int idProforma = jdbcTemplate.queryForObject(sqlProforma, new Object[]{
                    proforma.getIdEmpleado(),
                    proforma.getIdCliente(),
                    proforma.getObservaciones(),
                    proforma.getTotal(),
                    mainController.getEmpleado().getIdempleado()}, (rs, i)->{
                return new Integer(rs.getInt("idproforma"));
            });
            proforma.setIdProforma(idProforma);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        String sqlDetalleProforma = "INSERT INTO detalleproforma (idproforma, idproducto, cantidad, preciounitario, descuento, subtotal)" +
                " VALUES (" + String.valueOf(proforma.getIdProforma()) + ", ?, ?, ?, ?, ?)";
        try{
            for(DetalleProforma dp: proforma.getDetallesProforma()){
                jdbcTemplate.update(sqlDetalleProforma, new Object[]{
                        dp.getProducto().getIdProducto(),
                        dp.getCantidad(),
                        dp.getProducto().getPrecio(),
                        dp.getDescuento(),
                        dp.getSubtotal()
                });
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
        p.setCategoria(rs.getString("categoria"));
        return p;
    }
}
