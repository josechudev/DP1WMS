package com.dp1wms.view;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public enum FxmlView {
    MAIN {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("main.app.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/Main.fxml";
        }

        @Override
        boolean isResizable(){
            return true;
        }
    }, MANTENIMIENTO_USUARIO {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("mantenimiento.usuario.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/UsuarioFxml/MantenimientoUsuario.fxml";
        }

        @Override
        boolean isResizable(){
            return false;
        }
    },MANTENIMIENTO_MOVIMIENTO {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("mantenimiento.movimiento.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/MantenimientoMov.fxml";
        }

        @Override
        boolean isResizable(){
            return false;
        }
     }, INGRESO_PRODUCTO{
         @Override
         String getTitle() {
                        return getStringFromResourceBundle("ingreso.producto.title");
                    }

                 @Override
         String getFxmlFile() {
                        return "/fxml/IngresoProducto.fxml";
                    }

                 @Override
         boolean isResizable(){
                        return false;
                    }
     }, BUSQUEDA_PRODUCTO_LOTE{
         @Override
         String getTitle() {
                        return getStringFromResourceBundle("busquedaproductolote.title");
                    }

                 @Override
         String getFxmlFile() {
                        return "/fxml/BusquedaProductoLote.fxml";
                    }

                 @Override
         boolean isResizable(){
                        return false;
                    }
    },BUSQUEDA_PRODUCTO{
        @Override
        String getTitle() {
            return getStringFromResourceBundle("busquedaproducto.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/BusquedaProducto.fxml";
        }

        @Override
        boolean isResizable(){
            return false;
        }
    }, CREAR_LOTE{
        @Override
        String getTitle() {
            return getStringFromResourceBundle("crearlote.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/CrearLote.fxml";
        }

        @Override
        boolean isResizable(){
            return false;
        }
    }, GEN_PROFORMA{
        @Override
        String getTitle() {
            return getStringFromResourceBundle("generarproforma.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/Ventas/Proforma.fxml";
        }

        @Override
        boolean isResizable(){
            return true;
        }
    },
    LOGIN {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/Login.fxml";
        }

        @Override
        boolean isResizable(){
            return false;
        }
    }, MANTENIMIENTO_DESCUENTO{
        @Override
        String getTitle() {
            return getStringFromResourceBundle("mantenimiento.descuento.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/Descuentos/MantenimientoDescuento.fxml";
        }

        @Override
        boolean isResizable(){
            return false;
        }
    },DATOS_DESCUENTO{
        @Override
        String getTitle() {
            return getStringFromResourceBundle("datos.descuento.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/Descuentos/DatosDescuento.fxml";
        }

        @Override
        boolean isResizable(){
            return false;
        }
    },BUSQUEDA_PRODUCTO_DESC{
        @Override
        String getTitle() {
            return getStringFromResourceBundle("busquedaproducto.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/Descuentos/BusquedaProductoDesc.fxml";
        }

        @Override
        boolean isResizable(){
            return false;
        }
    };

    abstract String getTitle();
    abstract String getFxmlFile();
    abstract boolean isResizable();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
