package com.dp1wms.util;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DBUtil {

    private static final String JDBC_DRIVER= "org.postgresql.Driver";

    private static Connection conn = null;

    private static final String connStr="jdbc:postgresql://200.16.7.71:1047";

    public static void dbConnect() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error postgres jdbc");
            e.printStackTrace();
            throw e;
        }
        System.out.printf("Postgresql registrado");
        try{
            conn= DriverManager.getConnection(connStr);
        }catch (SQLException e){
            System.out.println("Conexion fallida " + e);
            e.printStackTrace();
            throw e;
        }
    }
    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
            throw e;
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs =null;
        try{
            dbConnect();
            System.out.println("Statement "+ queryStmt +"\n");

            stmt = conn.createStatement();

            resultSet = stmt.executeQuery(queryStmt);

            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        }catch (SQLException e){
            System.out.println("Error de query");
            throw e;
        }finally {
            if(resultSet!=null){
                resultSet.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    public static void dbExecuteUpdate(String sqlstmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try{
            dbConnect();
            stmt=conn.createStatement();
            stmt.executeUpdate(sqlstmt);
        }catch (SQLException e){
            System.out.println("Error dequery");
        }finally {
            if(stmt!=null){
                stmt.close();
            }
            dbDisconnect();
        }
    }
}
