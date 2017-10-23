package com.dp1wms.view;

import java.util.ResourceBundle;

public enum VentasView implements FxmlView {
    VENTA_BUSCAR_PROD {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("ventas.buscar_producto.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Ventas/BuscarProducto.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    };

    public String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
