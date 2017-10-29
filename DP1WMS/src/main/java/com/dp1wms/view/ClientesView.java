package com.dp1wms.view;

import java.util.ResourceBundle;

public enum ClientesView implements  FxmlView{
    MAIN{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("clientes.main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Clientes/ClienteMain.fxml";
        }

        @Override
        public boolean isResizable() {
            return true;
        }
    }, INFO{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("clientes.info.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Clientes/ClienteInfo.fxml";
        }

        @Override
        public boolean isResizable() {
            return false;
        }
    };




    @Override
    public String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
