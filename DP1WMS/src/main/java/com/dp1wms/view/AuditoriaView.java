package com.dp1wms.view;

import java.util.ResourceBundle;

public enum AuditoriaView implements FxmlView {
    DETALLE_EVENTO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("auditoria.detalleevento.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Auditoria/DetalleEvento.fxml";
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
