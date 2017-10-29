package com.dp1wms.view;

import java.util.ResourceBundle;

public enum AlmacenView implements FxmlView{
    VISTA_ALMACEN{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.almacenes.editaralmacen.title");
        }
        @Override public String getFxmlFile() { return "/fxml/AlmacenFxml/Almacen.fxml"; }
        @Override public boolean isResizable() {
            return false;
        }
    };

    public String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    };
}
