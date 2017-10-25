package com.dp1wms.dao.impl;

import com.dp1wms.dao.RepositoryCondicion;
import com.dp1wms.model.Condicion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryCondicionImpl implements RepositoryCondicion {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Condicion> obtenerDescuentos() {
        //String SQL = "SELECT d.iddescuento,d.tipodescuento,d.idproductogenerador,d.idcategoriaprodgen,d.cantprodgen,d.idproductodescuento,d.idcategoriaproddesc,d.cantproddesc,d.valordescuento,d.fechainicio,d.fechafin,d.descripcion,c1.descripcion as categoriagenerador,c2.descripcion as categoriadescuento,p1.nombreproducto as productogenerador,p2.nombreproducto as productodescuento FROM public.descuento d,public.producto p1,public.producto p2 ,public.categoriaproducto c1,public.categoriaproducto c2 WHERE p1.idproducto = d.idproductogenerador and p2.idproducto = d.idproductodescuento and c1.idcategoria = d.idcategoriaprodgen and c2.idcategoria = d.idcategoriaproddesc";
        String SQL = "SELECT iddescuento,tipodescuento,idproductogenerador,idcategoriaprodgen,cantprodgen,idproductodescuento,idcategoriaproddesc,cantproddesc,valordescuento,fechainicio,fechafin,descripcion FROM public.descuento";

        List<Condicion> listaCondicions = null;
        try {
            listaCondicions = jdbcTemplate.query(SQL, new Object[]{}, this::mapParam);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        for(Condicion condicion : listaCondicions){
            condicion.setNombreProductoGenerador(obtenerNombreProducto(condicion.getIdProductoGenerador()));
            condicion.setNombreProductoDescuento(obtenerNombreProducto(condicion.getIdProductoDescuento()));
            condicion.setCategoriaGenerador(obtenerDescripcionCategoria(condicion.getIdCategoriaProdGen()));
            condicion.setCategoriaDescuento(obtenerDescripcionCategoria(condicion.getIdCategoriaProdDesc()));
        }
        return listaCondicions;
    }

    public String obtenerNombreProducto(int idProducto){
        Long id = new Long(idProducto);
        if(idProducto != 0) {
            String SQL = "SELECT nombreproducto FROM public.producto WHERE idproducto = ?";

            String nombreProducto = null;
            try {
                nombreProducto = jdbcTemplate.queryForObject(SQL, new Object[]{id}, String.class);
            } catch (EmptyResultDataAccessException e) {
                e.printStackTrace();
            }
            return nombreProducto;
        }else{
            return null;
        }
    }

    private String obtenerDescripcionCategoria(int idCategoria){
        Long id = new Long(idCategoria);
        if(idCategoria != 0){
            String SQL = "SELECT descripcion FROM public.categoriaproducto WHERE idcategoria = ?";

            String descripcion = null;
            try {
                descripcion = jdbcTemplate.queryForObject(SQL,new Object[]{id}, String.class);
            } catch (EmptyResultDataAccessException e) {
                e.printStackTrace();
            }
            return descripcion;
        }else{
            return null;
        }

    }

    public Condicion mapParam(ResultSet rs, int i) throws SQLException {
        Condicion condicion = new Condicion();

        condicion.setIdDescuento(rs.getInt("iddescuento"));
        condicion.setTipoDescuento(rs.getString("tipodescuento"));
        condicion.setIdProductoGenerador(rs.getInt("idproductogenerador"));
        condicion.setIdCategoriaProdGen(rs.getInt("idcategoriaprodgen"));
        condicion.setCantProdGen(rs.getInt("cantprodgen"));
        condicion.setIdProductoDescuento(rs.getInt("idproductodescuento"));
        condicion.setIdCategoriaProdDesc(rs.getInt("idcategoriaproddesc"));
        condicion.setCantProdDesc(rs.getInt("cantproddesc"));
        condicion.setValorDescuento(rs.getDouble("valordescuento"));
        condicion.setFechaInicio(rs.getTimestamp("fechainicio"));
        condicion.setFechaFin(rs.getTimestamp("fechafin"));
        condicion.setDescripcion(rs.getString("descripcion"));
        /*condicion.setCategoriaGenerador(rs.getString("categoriagenerador"));
        condicion.setCategoriaDescuento(rs.getString("categoriadescuento"));
        condicion.setNombreProductoGenerador(rs.getString("productogenerador"));
        condicion.setNombreProductoDescuento(rs.getString("productodescuento"));*/

        return condicion;
    }

    @Transactional(rollbackFor = Exception.class)
    public int registrarDescuento(Condicion condicion) {
        // el stock parcial no se debe insertar directamente en lote, es trabajo del trigger por eso se pone de valor cero
        String SQL = "INSERT INTO public.condicion (tipodescuento,idproductogenerador,idcategoriaprodgen,cantprodgen,idproductodescuento,idcategoriaproddesc,cantproddesc,valordescuento,fechainicio,fechafin,descripcion) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        Long idProdDescuento = new Long(condicion.getIdProductoDescuento());
        if(idProdDescuento == -1){
            idProdDescuento = null;
        }
        Long idProdGen = new Long(condicion.getIdProductoGenerador());
        if(idProdGen == -1){
            idProdGen = null;
        }
        Long idCategoriaDescuento = new Long(condicion.getIdCategoriaProdDesc());
        if(idCategoriaDescuento == -1){
            idCategoriaDescuento = null;
        }
        Long idCategoriaGen = new Long(condicion.getIdCategoriaProdGen());
        if(idCategoriaGen == -1){
            idCategoriaGen = null;
        }

        try {
            jdbcTemplate.update(SQL, new Object[]{ condicion.getTipoDescuento(),idProdGen, idCategoriaGen, condicion.getCantProdGen(), idProdDescuento, idCategoriaDescuento, condicion.getCantProdDesc(), condicion.getValorDescuento(), condicion.getFechaInicio(), condicion.getFechaFin(), condicion.getDescripcion()});
            System.out.println("Se ha insertado en la tabla condicion");
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int actualizarDescuento(Condicion condicion) {
        // el stock parcial no se debe insertar directamente en lote, es trabajo del trigger por eso se pone de valor cero
        String SQL = "UPDATE public.condicion SET tipodescuento = ?,idproductogenerador = ?,idcategoriaprodgen = ?,cantprodgen = ?,idproductodescuento = ?,idcategoriaproddesc = ?,cantproddesc = ?,valordescuento = ?,fechainicio = ?,fechafin = ?,descripcion = ? where iddescuento = ?";

        Long idProdDescuento = new Long(condicion.getIdProductoDescuento());
        if(idProdDescuento == -1){
            idProdDescuento = null;
        }
        Long idProdGen = new Long(condicion.getIdProductoGenerador());
        if(idProdGen == -1){
            idProdGen = null;
        }
        Long idCategoriaDescuento = new Long(condicion.getIdCategoriaProdDesc());
        if(idCategoriaDescuento == -1){
            idCategoriaDescuento = null;
        }
        Long idCategoriaGen = new Long(condicion.getIdCategoriaProdGen());
        if(idCategoriaGen == -1){
            idCategoriaGen = null;
        }

        try {
            int i = jdbcTemplate.update(SQL, new Object[]{ condicion.getTipoDescuento(),idProdGen, idCategoriaGen, condicion.getCantProdGen(), idProdDescuento, idCategoriaDescuento, condicion.getCantProdDesc(), condicion.getValorDescuento(), condicion.getFechaInicio(), condicion.getFechaFin(), condicion.getDescripcion(), condicion.getIdDescuento()});
            System.out.println("Filas afectadas -> "+ i);
            System.out.println("Se ha actualizo en la tabla condicion");
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }
        return 1;
    }


    @Transactional(rollbackFor = Exception.class)
    public int eliminarDescuento(int id) {
        // el stock parcial no se debe insertar directamente en lote, es trabajo del trigger por eso se pone de valor cero
        String SQL = "DELETE FROM public.descuento WHERE iddescuento = ?";

        try {
            jdbcTemplate.update(SQL, new Object[]{id});
            System.out.println("Se ha eliminado en la tabla descuento");
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }
        return 1;
    }

    public List<Condicion> obtenerCondicionesActivos(){
        String sql = "SELECT * from condicion where fechainicio <= now() " +
                    "AND fechafin >= now() and activo";
        try{
            List<Condicion> condicions = jdbcTemplate.query(sql, new Object[]{}, this::mapParam);
            return condicions;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
