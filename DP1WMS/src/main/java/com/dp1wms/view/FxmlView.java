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
    },MANTMOV {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("mantmov.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/MantenimientoMov.fxml";
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
