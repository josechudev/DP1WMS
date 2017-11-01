package com.dp1wms.view;

import java.util.ResourceBundle;

public enum MainView implements FxmlView{
    MAIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.app.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Main.fxml";
        }
        @Override
        public boolean isResizable(){
            return true;
        }
    }, MANTENIMIENTO_USUARIO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.usuario.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/UsuarioFxml/MantenimientoUsuario.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    },MANTENIMIENTO_MOVIMIENTO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.movimiento.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/MantenimientoMov.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    } , MANTENIMIENTO_TIPOEMPLEADO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.roles.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/MantenimientoTipoEmpleado.fxml";
        }
        @Override
        public boolean isResizable(){return false;}
    }, NUEVO_TIPOEMPLEADO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.movimiento.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/CrearTipoEmpleado.fxml";
        }
        @Override
        public boolean isResizable(){return false;}
    
    },MANTENIMIENTO_CATEGORIA {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.categorias.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Categorias/MantenimientoCategoria.fxml";
        }

        @Override
        public boolean isResizable(){
            return false;
        }
    },MANTENIMIENTO_PRODUCTO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.productos.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Productos/MantenimientoProductos.fxml";
        }

        @Override
        public boolean isResizable(){
            return false;
        }
    }
    , INGRESO_PRODUCTO{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("ingreso.producto.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/IngresoProducto.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    }, CREAR_LOTE{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("crearlote.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/CrearLote.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    }, BUSQUEDA_PRODUCTO{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("busquedaproducto.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/BusquedaProducto.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    },
    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    }, MANTENIMIENTO_DESCUENTO{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.condicion.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Descuentos/MantenimientoDescuento.fxml";
        }

        @Override
        public boolean isResizable(){
            return false;
        }
    },DATOS_DESCUENTO{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("datos.condicion.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Descuentos/DatosDescuento.fxml";
        }

        @Override
        public boolean isResizable(){
            return false;
        }
    },BUSQUEDA_PRODUCTO_DESC{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("busquedaproducto.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Descuentos/BusquedaProductoDesc.fxml";
        }

        @Override
        public boolean isResizable(){
            return false;
        }
    }, MANTENIMIENTO_ALMACEN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.almacenes.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Almacen/MantenimientoAlmacenes.fxml";
        }

        @Override
        public boolean isResizable() {
            return false;
        }
    },
    ALMACEN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.almacenes.editaralmacen.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Almacen/AlmacenInfo.fxml";
        }

        @Override
        public boolean isResizable() {
            return true;
        }
    },
    NUEVO_ALMACEN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.almacenes.nuevo.titles");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Almacen/NuevoAlmacen.fxml";
        }

        @Override
        public boolean isResizable() {
            return false;
        }
    },BUSQUEDA_PRODUCTO_LOTE{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("busquedaproducto.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/BusquedaProductoLote.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    },LISTAR_ENVIOS{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("listaenvios.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Envios/ListaEnvios.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    },RETIRO_ENVIO{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("retiroenvio.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Envios/RetirarEnvio.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    },SELECCONAR_LOTE{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("seleccionarLote.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Envios/SeleccionarLote.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    },LISTAR_ENVIOS_GUIA{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("listaenviosguia.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Guia/ListaEnviosRealizados.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    },LISTAR_GUIA{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("listaguia.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Guia/ListaGuias.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    },CREAR_GUIA{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("crearguia.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Guia/CrearGuia.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    }, DEVOLVER_PEDIDO{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("devolucionPedido.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/DevolucionPedido/DevolucionPedido.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    }, CARGAR_KARDEX{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("cargarKardex.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Kardex/ListaKardex.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    }

    ;

    public String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
