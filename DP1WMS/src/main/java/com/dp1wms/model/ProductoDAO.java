package com.dp1wms.model;

import com.dp1wms.com.dp1wms.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {
    //SELECT Producto

    public static Producto buscarProducto (String nombreProducto, int idProducto) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM producto WHERE idproducto=" + idProducto+" OR nombreproducto="+idProducto;
        try {
            ResultSet rsProd = DBUtil.dbExecuteQuery(selectStmt);
            Producto producto = getProductoFromResultSet(rsProd);
            return producto;
        }catch (SQLException e){
            System.out.println("No se encontró producto " +idProducto);
            throw e;
        }
    }
    private static Producto getProductoFromResultSet(ResultSet rs) throws SQLException {
        Producto prod = null;
        if(rs.next()){
            prod = new Producto();
            prod.setIdProducto(rs.getInt("idproducto"));
            prod.setNombreProducto(rs.getString("nombreproducto"));
            prod.setPeso(rs.getFloat("peso"));
            prod.setFechaVencimiento(rs.getDate("fechavencimiento").toString());
            prod.setDescripcion(rs.getString("descripcion"));
            prod.setPrecio(rs.getFloat("precio"));
            prod.setStock(rs.getInt("stock"));
            prod.setIdCategoria(rs.getInt("idcategoria"));
        }
        return prod;
    }
    public static ObservableList<Producto> buscarProductos () throws SQLException, ClassNotFoundException {
        String selectStmt ="SELECT * FROM producto";
        try{
            ResultSet rsProd = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Producto> prodLista = getProductoLista(rsProd);
            return prodLista;
        }catch (SQLException e){
            System.out.println("Error");
            throw e;
        }
    }
    private static ObservableList<Producto> getProductoLista(ResultSet rs) throws SQLException {
        ObservableList<Producto> prodLista = FXCollections.observableArrayList();
        while(rs.next()){
            Producto prod = new Producto();
            prod.setIdProducto(rs.getInt("idproducto"));
            prod.setNombreProducto(rs.getString("nombreproducto"));
            prod.setPeso(rs.getFloat("peso"));
            prod.setFechaVencimiento(rs.getDate("fechavencimiento").toString());
            prod.setDescripcion(rs.getString("descripcion"));
            prod.setPrecio(rs.getFloat("precio"));
            prod.setStock(rs.getInt("stock"));
            prod.setIdCategoria(rs.getInt("idcategoria"));
            prodLista.add(prod);
        }
        return prodLista;
    }

    public static void actualizarProducto(int idProd,String nombreProducto) throws ClassNotFoundException, SQLException {
        String updateStmt=" BEGIN\n "+
                "UPDATE producto \n" +
                " SET nombreproducto = " + nombreProducto +  "\n" +
                " WHERE idProd = " +idProd +  "\n" +
                " COMMIT \n" +
                "END";
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        }catch ( SQLException e) {
            throw e;
        }
    }

    public static void eliminarProducto(int idProd,String nombreProducto) throws ClassNotFoundException, SQLException {
        String updateStmt=" BEGIN\n "+
                "DELETE FROM  producto \n" +
                " WHERE idProd = " +idProd +  "\n" +
                " COMMIT \n" +
                "END";
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        }catch ( SQLException e){
            throw e;
        }

    }
}
