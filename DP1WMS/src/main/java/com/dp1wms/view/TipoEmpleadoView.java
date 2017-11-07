package com.dp1wms.view;

import java.util.ResourceBundle;

public enum TipoEmpleadoView implements FxmlView {
    MAIN{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("tipo_empleado.main.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/MantenimientoTipoEmpleado.fxml";
        }
        @Override
        public boolean isResizable() {
            return false;
        }
    }, INFO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("tipo_empleado.info.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/CrearTipoEmpleado.fxml";
        }
        @Override
        public boolean isResizable() {
            return false;
        }
    };

    public String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
