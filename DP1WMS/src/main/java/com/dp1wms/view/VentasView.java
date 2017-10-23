package com.dp1wms.view;

import java.util.ResourceBundle;

public enum VentasView implements FxmlView {
    GEN_PROFORMA{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("generarproforma.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Ventas/Proforma.fxml";
        }
        @Override
        public boolean isResizable(){
            return true;
        }
    }, VENTA_BUSCAR_PROD {
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
    }, PROFORMA_PREVIEW {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("ventas.proforma_preview.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Ventas/ProformaPreview.fxml";
        }
        @Override
        public boolean isResizable(){
            return true;
        }
    }, BUSCAR_CLIENTE{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("buscarcliente.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Ventas/BuscarCliente.fxml";
        }
        @Override
        public boolean isResizable(){
            return true;
        }
    }, REGISTRAR_CLIENTE{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("registrarcliente.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Ventas/RegistrarCliente.fxml";
        }
        @Override
        public boolean isResizable(){
            return true;
        }
    };

    public String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
