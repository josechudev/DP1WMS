package com.dp1wms.view;

import java.util.ResourceBundle;

public enum AlmacenView implements FxmlView{
    VISTA_ALMACEN{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.almacenes.editaralmacen.title");
        }
        @Override public String getFxmlFile() { return "/fxml/Almacen/Almacen.fxml"; }
        @Override public boolean isResizable() {
            return true;
        }
    },
    VISTA_AREAS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.almacenes.areas.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Almacen/MantenimientoAreas.fxml";
        }

        @Override
        public boolean isResizable() {
            return true;
        }
    };

    public String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    };
}
