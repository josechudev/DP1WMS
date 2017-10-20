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
    },MANTENIMIENTO_MOVVIMIENTO {
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
    }, BUSQUEDA_PRODUCTO{
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
    },LOGIN {
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
    };

    abstract String getTitle();
    abstract String getFxmlFile();
    abstract boolean isResizable();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
