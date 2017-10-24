package com.dp1wms.controller.MantLote;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.Producto;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

@Component
public class CrearLoteController implements FxmlController {
    @FXML
    private TextField txb_nombreProducto;

    @FXML
    private TextField txb_cantidad;

    @FXML
    private TextField txb_stock;

    @FXML
    private DatePicker dp_fechaLote;

    @FXML
    private DatePicker dp_fechaEntrada;

    @FXML
    private AnchorPane crearLoteAnchorPane;

    private Integer idProducto=null;


    private final StageManager stageManager;

    @Autowired
    private RepositoryMantMov repositoryMantMov;

    @Autowired
    @Lazy
    public CrearLoteController(StageManager stageManager){
        this.stageManager = stageManager;
    }



    public void buscarProducto(ActionEvent event) {
        System.out.println("Buscar Producto");
        this.stageManager.mostrarModal(MainView.BUSQUEDA_PRODUCTO);


    }

    public void actualizarDataProducto(Producto producto){
        this.txb_nombreProducto.setText(producto.getNombreProducto());
        this.txb_stock.setText(""+producto.getStock());
        this.idProducto = producto.getIdProducto();
    }

    public void registrarLote(ActionEvent event){
        System.out.println("Registrar MantLote");
       if((this.dp_fechaLote.getValue() != null) && (this.dp_fechaEntrada.getValue() != null) && (this.txb_cantidad != null) &&(this.txb_nombreProducto != null)){
           Timestamp fechaLote = null;
           Timestamp fechaEntrada = null;
           try {
               fechaLote = this.obtenerFecha(this.dp_fechaLote.getValue().toString());
               fechaEntrada = this.obtenerFecha(this.dp_fechaEntrada.getValue().toString());
           } catch (ParseException e) {
               e.printStackTrace();
               System.out.println("Error al ingresar la fecha");
               return;
           }

           System.out.println("Cantidad text box-> "+this.txb_cantidad.getText());
           int cantidad;
           try{
               cantidad = Integer.parseInt(this.txb_cantidad.getText());
           }catch(Exception e){
               System.out.println("Error al ingresar la cantidad");
               return;
           }
           System.out.println("Cantidad parseo->"+cantidad);

           repositoryMantMov.registrarLote(this.idProducto,fechaLote,fechaEntrada,cantidad);
           crearLoteAnchorPane.getScene().getWindow().hide();
       }


    }

    private Timestamp obtenerFecha(String fecha) throws ParseException {
        if(fecha != null)
            return convertirFecha(fecha);
        else
            return null;

    }

    private Timestamp convertirFecha(String fecha) throws ParseException  {

        Date utiltime = null;
        utiltime = ISO8601Utils.parse(fecha, new ParsePosition(0));
        return new Timestamp(utiltime.getTime());

    }

    public void cancelarRegistrarLote(ActionEvent event){
        System.out.println("Se cancelo el registro del MantLote");
        crearLoteAnchorPane.getScene().getWindow().hide();
    }

    @Override
    public void initialize() {
        this.txb_stock.setDisable(true);
        this.txb_nombreProducto.setDisable(true);
    }
}
