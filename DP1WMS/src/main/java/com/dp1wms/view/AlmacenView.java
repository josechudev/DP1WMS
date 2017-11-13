package com.dp1wms.view;

import java.util.ResourceBundle;

public enum AlmacenView implements FxmlView{
    VISTA_ALMACEN{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.almacenes.editaralmacen.title");
        }

        @Override public String getFxmlFile() { return "/fxml/Almacen/AlmacenInfo.fxml"; }
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
    },
    VISTA_RACKS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.racks.title");

        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Racks/VistaRacks.fxml";
        }

        @Override
        public boolean isResizable() {
            return false;
        }

    },
    TABU{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("tabu.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Tabu/AlmacenRuta.fxml";
        }

        @Override
        public boolean isResizable() {
            return false;
        }
    },
    NUEVO_RACK {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.racks.nuevo");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Almacen/NuevoRack.fxml";
        }

        @Override
        public boolean isResizable() {
            return false;
        }
    };

    public String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    };
}
