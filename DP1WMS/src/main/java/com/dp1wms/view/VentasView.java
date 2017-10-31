package com.dp1wms.view;

import java.util.ResourceBundle;

public enum VentasView implements FxmlView {
    GEN_PROFORMA{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("ventas.proforma.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Ventas/Proforma.fxml";
        }
        @Override
        public boolean isResizable(){
            return true;
        }
    }, BUSCAR_PROD {
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
            return true;
        }
    }, BUSCAR_CLIENTE{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("ventas.buscar_cliente.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Ventas/BuscarCliente.fxml";
        }
        @Override
        public boolean isResizable(){
            return true;
        }
    }, GEN_PEDIDO{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("ventas.pedido.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Ventas/Pedido.fxml";
        }
        @Override
        public boolean isResizable() {
            return true;
        }
    }, BUSCAR_PROFORMA{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("ventas.buscar_proforma.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Ventas/BuscarProforma.fxml";
        }
        @Override
        public boolean isResizable() {
            return true;
        }
    };



    public String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
