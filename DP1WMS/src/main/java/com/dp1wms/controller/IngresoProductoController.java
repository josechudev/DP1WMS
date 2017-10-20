package com.dp1wms.controller;

import com.dp1wms.view.FxmlView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
@Component
public class IngresoProductoController implements Initializable {

    @FXML
    private TextField txb_nombreProd;
    @FXML
    private TextField txb_cantidad;
    @FXML
    private DatePicker dp_fecha;
    @FXML
    private ComboBox cb_motivo;
    @FXML
    private TextArea txa_observaciones;
    @FXML
    private Button btn_registrar;

    private Integer idProducto=null;

    private final StageManager stageManager;

    @Autowired @Lazy
    public IngresoProductoController(StageManager stageManager){
        this.stageManager = stageManager;
    }

    public void buscarProducto(ActionEvent event){
        System.out.println("Buscar Producto");
        this.stageManager.mostarModal(FxmlView.BUSQUEDA_PRODUCTO);
    }

    public void actualizarDataProducto(String nombreProducto,int idProducto){
        this.txb_nombreProd.setText(nombreProducto);

    }

    public void registrarIngresoProducto(ActionEvent event){
        String motivoMovimiento = cb_motivo.getValue().toString();
        if(motivoMovimiento != null){
            //obtener id motivo
            int idMotivoMovimiento;
            if((this.idProducto != null)){
                //añadirMovimiento(this.idProducto,idMotivoMovimiento,this.txb_cantidad,this.dp_fecha.getValue().toString(),this.txa_observaciones.getText());
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txb_nombreProd.setDisable(true);
    }
}
