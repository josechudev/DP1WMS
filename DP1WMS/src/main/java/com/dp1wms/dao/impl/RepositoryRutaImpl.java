package com.dp1wms.dao.impl;

import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryRuta;
import com.dp1wms.model.Envio;
import com.dp1wms.model.Ruta;
import com.dp1wms.model.tabu.Nodo;
import com.dp1wms.tabu.Segmento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryRutaImpl implements RepositoryRuta{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MainController mainController;

    public boolean guardarRuta(ArrayList<Nodo> solucion, Envio envio){
        String sql = "INSERT INTO" +
                " ruta (idenvio, camino_x, camino_y, idempleadoauditado, costo) " +
                "VALUES (?,?,?,?, ?)";
        Segmento segmento = new Segmento();
        int costo = 0;
        for(int i = 0; i < solucion.size() - 1; i++){
            Nodo nodoA = solucion.get(i);
            Nodo nodoB = solucion.get(i+1);
            costo += segmento.distanciaEntreNodos(nodoA, nodoB);
        }

        try{
            Integer[] camino_x = new Integer[solucion.size()];
            Integer[] camino_y = new Integer[solucion.size()];
            for (int i = 0; i < solucion.size(); i++) {
                Nodo nodo = solucion.get(i);
                camino_x[i] = nodo.x;
                camino_y[i] = nodo.y;
            }

            this.jdbcTemplate.update(sql, new Object[]{
                    envio.getIdEnvio(),
                    this.createSqlArray(camino_x),
                    this.createSqlArray(camino_y),
                    this.mainController.getEmpleado().getIdempleado(),
                    costo
            });
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Ruta> obtenerRutasPorEnvio(Envio envio){
        String sql = "SELECT * FROM ruta WHERE idenvio = ?";
        try{
            List<Ruta> rutas = jdbcTemplate.query(sql, new Object[]{
                        envio.getIdEnvio()
                    },
                    (res, i)->{
                        Ruta ruta = new Ruta();
                        ruta.setIdRuta(res.getInt("idruta"));
                        ruta.setIdEnvio(res.getInt("idenvio"));
                        Array camino_x = res.getArray("camino_x");
                        Array camino_y = res.getArray("camino_y");
                        ruta.setCamino_x((Integer[]) camino_x.getArray());
                        ruta.setCamino_y((Integer[]) camino_y.getArray());
                        ruta.setCosto(res.getInt("costo"));
                        return ruta;
                    });
            return rutas;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private java.sql.Array createSqlArray(Integer[] list){
        java.sql.Array intArray = null;
        try {
            intArray = jdbcTemplate.getDataSource().getConnection().createArrayOf("integer", list);
        } catch (SQLException ignore) {
        }
        return intArray;
    }
}
