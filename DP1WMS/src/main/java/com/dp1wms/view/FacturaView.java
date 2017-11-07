package com.dp1wms.view;

import java.util.ResourceBundle;

public enum FacturaView implements FxmlView{
    MANTENIMIENTO_FACTURA{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("factura.mantenimiento.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/FacturaFxml/MantenimientoFactura.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    }, DEVOLVER_PEDIDO{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("devolucionPedido.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/FacturaFxml/MantenimientoFactura.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    };

    public String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    };
}
