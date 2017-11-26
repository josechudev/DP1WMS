package com.dp1wms.util;

import com.dp1wms.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DescuentoAlgoritmo {

    public static void aplicarDescuento(List<Condicion> condiciones, Cabecera cabecera){
        for(int i = 0; i < cabecera.getCantidadDetalle(); i++){
            Detalle detalle = cabecera.getDetalle(i);
            detalle.setDescuento(0);
        }

        //Eliminar condicions no válidos
        ArrayList<Condicion> descValidos = descuentosValidos(condiciones, cabecera);

        //aplicar prioridades a los condicions
        orderDescuentos(descValidos);

        //aplicar condicions por porcentaje primero
        aplicarDescuentosPorPorcentaje(descValidos, cabecera);

        //aplicar condicions restantes
        for(Condicion desc: descValidos){
            if(desc.getPrioridad() < 7){
                for(int i = 0; i < cabecera.getCantidadDetalle(); i++){
                    Detalle detalle = cabecera.getDetalle(i);
                    if(detalle.getDescuento() <= 0){//aplica
                        Producto p = detalle.getProducto();
                        if(desc.getIdProductoDescuento() == p.getIdProducto() ||
                                desc.getIdCategoriaProdDesc() == p.getIdCategoria()){
                            int cantGen = 0;
                            if(desc.getIdProductoGenerador() > 0){
                                cantGen = obtenerCantidadPorIdProd(cabecera, desc.getIdProductoGenerador());
                            } else {
                                cantGen = obtenerCantidadPorIdCat(cabecera, desc.getIdCategoriaProdGen());
                            }
                            int cantMax = (int) Math.floor(cantGen / desc.getCantProdGen());

                            int cantDesc = 0;
                            if(desc.getIdProductoDescuento() > 0){
                                cantDesc = obtenerCantidadPorIdProd(cabecera, p.getIdProducto());
                            } else {
                                cantDesc = obtenerCantidadPorIdProd(cabecera, p.getIdCategoria());
                            }
                            int vecesDesc = (int) Math.floor(cantDesc / desc.getCantProdDesc());
                            if(vecesDesc > cantMax){
                                vecesDesc = cantMax;
                            }
                            float descuento = (float) (vecesDesc * desc.getCantProdDesc() * p.getPrecio() * desc.getValorDescuento());
                            detalle.setDescuento(descuento);
                        }
                    }
                }
            }
        }
    };

    private static ArrayList<Condicion> descuentosValidos(List<Condicion> condiciones, Cabecera cabecera){
        ArrayList<Condicion> descValidos = new ArrayList<>();
        for(Condicion desc: condiciones){
            boolean valido = false;
            switch(desc.getTipoCondicion()){
                case Condicion.DESC_P:{//por porcentaje
                    if(desc.getIdProductoDescuento() > 0){ //por producto
                        Detalle detalle = null;
                        for(int i = 0; i < cabecera.getCantidadDetalle(); i++){
                            Detalle d = cabecera.getDetalle(i);
                            if(d.getProducto().getIdProducto() == desc.getIdProductoGenerador() ||
                                    d.getProducto().getIdCategoria() == desc.getIdCategoriaProdGen()){
                                detalle = d;
                                break;
                            }
                        }
                        valido = (detalle != null);
                    }
                    else if(desc.getIdCategoriaProdDesc() > 0){
                        ArrayList<Detalle> variosDet = new ArrayList<>();
                        for(int i = 0; i < cabecera.getCantidadDetalle(); i++){
                            Detalle d = cabecera.getDetalle(i);
                            if(d.getProducto().getIdCategoria() == desc.getIdCategoriaProdDesc()){
                                variosDet.add(d);
                            }
                        }
                        valido = (variosDet.size() > 0);
                    }
                    break;
                }
                default:{//por cantidad
                    int cantidadGenerador = 0;
                    if(desc.getIdProductoGenerador() > 0){//por producto
                        cantidadGenerador = obtenerCantidadPorIdProd(cabecera, desc.getIdProductoGenerador());
                    } else {//por categoria
                        cantidadGenerador = obtenerCantidadPorIdCat(cabecera, desc.getIdCategoriaProdGen());
                    }
                    int cantidadTarget = 0;
                    if(desc.getIdProductoDescuento() > 0){
                        cantidadTarget = obtenerCantidadPorIdProd(cabecera, desc.getIdProductoDescuento());
                    } else {
                        cantidadTarget = obtenerCantidadPorIdCat(cabecera, desc.getIdCategoriaProdGen());
                    }
                    valido = (cantidadGenerador >= desc.getCantProdGen() && cantidadTarget >= desc.getCantProdDesc());
                    break;
                }
            }
            if(valido){
                descValidos.add(desc);
            }
        }
        return descValidos;
    }

    private static int obtenerCantidadPorIdProd(Cabecera cabecera, int idProducto){
        int cantidad = 0;
        for(int i = 0; i < cabecera.getCantidadDetalle(); i++){
            Detalle detalle = cabecera.getDetalle(i);
            if(detalle.getProducto().getIdProducto() == idProducto){
                cantidad = detalle.getCantidad();
                break;
            }
        }
        return cantidad;
    }

    private static int obtenerCantidadPorIdCat(Cabecera cabecera, int idCategoriaProd){
        int cantidad = 0;
        for(int i = 0; i < cabecera.getCantidadDetalle(); i++){
            Detalle d = cabecera.getDetalle(i);
            if(d.getProducto().getIdCategoria() == idCategoriaProd){
                cantidad += d.getCantidad();
            }
        }
        return cantidad;
    }

    private static  void orderDescuentos(ArrayList<Condicion> condiciones){
        //asignar prioridades
        for(Condicion desc: condiciones){
            switch (desc.getTipoCondicion()){
                case Condicion.DESC_P:{//por porcentaje
                    desc.setPrioridad(7);
                    break;
                }
                case Condicion.DESC_C:{//por cantidad (3x2)
                    if(desc.getIdProductoGenerador() > 0){ //categoria de producto
                        desc.setPrioridad(6);
                    } else { //producto
                        desc.setPrioridad(2);
                    }
                    break;
                }
                case Condicion.DESC_B:{//bonificacion por especie
                    if(desc.getIdProductoGenerador() > 0 && desc.getIdProductoDescuento() > 0){
                        desc.setPrioridad(5);
                    } else if (desc.getIdCategoriaProdGen() > 0 && desc.getIdProductoDescuento() > 0){
                        desc.setPrioridad(4);
                    } else if (desc.getIdProductoGenerador() > 0 && desc.getIdCategoriaProdDesc() > 0){
                        desc.setPrioridad(3);
                    } else {
                        desc.setPrioridad(1);
                    }
                    break;
                }
            }
        }
        //ordenar
        Collections.sort(condiciones, new Comparator<Condicion>() {
            @Override
            public int compare(Condicion o1, Condicion o2) {
                return o2.getPrioridad() - o1.getPrioridad();
            }
        });
    }

    private static void aplicarDescuentosPorPorcentaje(ArrayList<Condicion> condiciones, Cabecera cabecera){
        for(int i = 0; i < cabecera.getCantidadDetalle(); i++){
            Detalle d = cabecera.getDetalle(i);

            Producto p = d.getProducto();
            float descuento = 0;
            for(Condicion desc: condiciones){
                if(desc.getPrioridad() < 7){
                    break;
                }
                if(desc.getIdProductoGenerador() == p.getIdProducto() || desc.getIdCategoriaProdGen() == p.getIdCategoria()){
                    descuento += d.getCantidad() * p.getPrecio() * desc.getValorDescuento();
                }
            }
            if(descuento >= d.getCantidad() * p.getPrecio()){
                d.setDescuento(d.getCantidad() * p.getPrecio());
            } else {
                d.setDescuento(descuento);
            }
        }
    }
}