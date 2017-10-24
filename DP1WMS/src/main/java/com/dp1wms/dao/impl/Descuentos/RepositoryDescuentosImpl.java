package com.dp1wms.dao.impl.Descuentos;

import com.dp1wms.dao.RepositoryDescuento;
import com.dp1wms.model.Descuento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class RepositoryDescuentosImpl implements RepositoryDescuento {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Descuento> obtenerDescuentos() {
        //String SQL = "SELECT d.iddescuento,d.tipodescuento,d.idproductogenerador,d.idcategoriaprodgen,d.cantprodgen,d.idproductodescuento,d.idcategoriaproddesc,d.cantproddesc,d.valordescuento,d.fechainicio,d.fechafin,d.descripcion,c1.descripcion as categoriagenerador,c2.descripcion as categoriadescuento,p1.nombreproducto as productogenerador,p2.nombreproducto as productodescuento FROM public.descuento d,public.producto p1,public.producto p2 ,public.categoriaproducto c1,public.categoriaproducto c2 WHERE p1.idproducto = d.idproductogenerador and p2.idproducto = d.idproductodescuento and c1.idcategoria = d.idcategoriaprodgen and c2.idcategoria = d.idcategoriaproddesc";
        String SQL = "SELECT iddescuento,tipodescuento,idproductogenerador,idcategoriaprodgen,cantprodgen,idproductodescuento,idcategoriaproddesc,cantproddesc,valordescuento,fechainicio,fechafin,descripcion FROM public.descuento";

        List<Descuento> listaDescuentos = null;
        try {
            listaDescuentos = jdbcTemplate.query(SQL, new Object[]{}, this::mapParam);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        for(Descuento descuento:listaDescuentos){
            descuento.setNombreProductoGenerador(obtenerNombreProducto(descuento.getIdProductoGenerador()));
            descuento.setNombreProductoDescuento(obtenerNombreProducto(descuento.getIdProductoDescuento()));
            descuento.setCategoriaGenerador(obtenerDescripcionCategoria(descuento.getIdCategoriaProdGen()));
            descuento.setCategoriaDescuento(obtenerDescripcionCategoria(descuento.getIdCategoriaProdDesc()));
        }
        return listaDescuentos;
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

    public Descuento mapParam(ResultSet rs, int i) throws SQLException {
        Descuento descuento = new Descuento();

        descuento.setIdDescuento(rs.getInt("iddescuento"));
        descuento.setTipoDescuento(rs.getString("tipodescuento"));
        descuento.setIdProductoGenerador(rs.getInt("idproductogenerador"));
        descuento.setIdCategoriaProdGen(rs.getInt("idcategoriaprodgen"));
        descuento.setCantProdGen(rs.getInt("cantprodgen"));
        descuento.setIdProductoDescuento(rs.getInt("idproductodescuento"));
        descuento.setIdCategoriaProdDesc(rs.getInt("idcategoriaproddesc"));
        descuento.setCantProdDesc(rs.getInt("cantproddesc"));
        descuento.setValorDescuento(rs.getDouble("valordescuento"));
        descuento.setFechaInicio(rs.getTimestamp("fechainicio"));
        descuento.setFechaFin(rs.getTimestamp("fechafin"));
        descuento.setDescripcion(rs.getString("descripcion"));
        /*descuento.setCategoriaGenerador(rs.getString("categoriagenerador"));
        descuento.setCategoriaDescuento(rs.getString("categoriadescuento"));
        descuento.setNombreProductoGenerador(rs.getString("productogenerador"));
        descuento.setNombreProductoDescuento(rs.getString("productodescuento"));*/

        return descuento;
    }

    @Transactional(rollbackFor = Exception.class)
    public int registrarDescuento(Descuento descuento) {
        // el stock parcial no se debe insertar directamente en lote, es trabajo del trigger por eso se pone de valor cero
        String SQL = "INSERT INTO public.descuento (tipodescuento,idproductogenerador,idcategoriaprodgen,cantprodgen,idproductodescuento,idcategoriaproddesc,cantproddesc,valordescuento,fechainicio,fechafin,descripcion) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        Long idProdDescuento = new Long(descuento.getIdProductoDescuento());
        if(idProdDescuento == -1){
            idProdDescuento = null;
        }
        Long idProdGen = new Long(descuento.getIdProductoGenerador());
        if(idProdGen == -1){
            idProdGen = null;
        }
        Long idCategoriaDescuento = new Long(descuento.getIdCategoriaProdDesc());
        if(idCategoriaDescuento == -1){
            idCategoriaDescuento = null;
        }
        Long idCategoriaGen = new Long(descuento.getIdCategoriaProdGen());
        if(idCategoriaGen == -1){
            idCategoriaGen = null;
        }

        try {
            jdbcTemplate.update(SQL, new Object[]{ descuento.getTipoDescuento(),idProdGen, idCategoriaGen, descuento.getCantProdGen(), idProdDescuento, idCategoriaDescuento, descuento.getCantProdDesc(), descuento.getValorDescuento(), descuento.getFechaInicio(), descuento.getFechaFin(), descuento.getDescripcion()});
            System.out.println("Se ha insertado en la tabla descuento");
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int actualizarDescuento(Descuento descuento) {
        // el stock parcial no se debe insertar directamente en lote, es trabajo del trigger por eso se pone de valor cero
        String SQL = "UPDATE public.descuento SET tipodescuento = ?,idproductogenerador = ?,idcategoriaprodgen = ?,cantprodgen = ?,idproductodescuento = ?,idcategoriaproddesc = ?,cantproddesc = ?,valordescuento = ?,fechainicio = ?,fechafin = ?,descripcion = ? where iddescuento = ?";

        Long idProdDescuento = new Long(descuento.getIdProductoDescuento());
        if(idProdDescuento == -1){
            idProdDescuento = null;
        }
        Long idProdGen = new Long(descuento.getIdProductoGenerador());
        if(idProdGen == -1){
            idProdGen = null;
        }
        Long idCategoriaDescuento = new Long(descuento.getIdCategoriaProdDesc());
        if(idCategoriaDescuento == -1){
            idCategoriaDescuento = null;
        }
        Long idCategoriaGen = new Long(descuento.getIdCategoriaProdGen());
        if(idCategoriaGen == -1){
            idCategoriaGen = null;
        }

        try {
            int i = jdbcTemplate.update(SQL, new Object[]{ descuento.getTipoDescuento(),idProdGen, idCategoriaGen, descuento.getCantProdGen(), idProdDescuento, idCategoriaDescuento, descuento.getCantProdDesc(), descuento.getValorDescuento(), descuento.getFechaInicio(), descuento.getFechaFin(), descuento.getDescripcion(),descuento.getIdDescuento()});
            System.out.println("Filas afectadas -> "+ i);
            System.out.println("Se ha actualizo en la tabla descuento");
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
}
