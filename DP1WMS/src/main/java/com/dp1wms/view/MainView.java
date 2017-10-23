package com.dp1wms.view;

import java.util.ResourceBundle;

public enum MainView implements FxmlView{
    MAIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.app.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Main.fxml";
        }
        @Override
        public boolean isResizable(){
            return true;
        }
    }, MANTENIMIENTO_USUARIO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.usuario.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/UsuarioFxml/MantenimientoUsuario.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    },MANTENIMIENTO_MOVVIMIENTO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.movimiento.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/MantenimientoMov.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    } , MANTENIMIENTO_TIPOEMPLEADO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.movimiento.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/MantenimientoTipoEmpleado.fxml";
        }
        @Override
        public boolean isResizable(){return false;}
    }, NUEVO_TIPOEMPLEADO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("mantenimiento.movimiento.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/CrearTipoEmpleado.fxml";
        }
        @Override
        public boolean isResizable(){return false;}
    },

    INGRESO_PRODUCTO{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("ingreso.producto.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/IngresoProducto.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    }, CREAR_LOTE{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("crearlote.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/CrearLote.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    }, BUSQUEDA_PRODUCTO{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("busquedaproducto.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/BusquedaProducto.fxml";
        }
        @Override
        public boolean isResizable(){
            return false;
        }
    }, GEN_PROFORMA{
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
            return false;
        }
    },
    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }
        @Override
        public String getFxmlFile() {
            return "/fxml/Login.fxml";
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
