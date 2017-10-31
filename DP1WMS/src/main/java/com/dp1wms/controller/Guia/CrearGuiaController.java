package com.dp1wms.controller.Guia;


import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryGuia;
import com.dp1wms.model.*;
import com.dp1wms.view.StageManager;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CrearGuiaController implements FxmlController {

    @FXML
    private TextField txb_numeroguia;
    @FXML
    private DatePicker dp_fechaEmision;
    @FXML
    private DatePicker dp_fechaInicio;
    @FXML
    private DatePicker dp_fechaFin;
    @FXML
    private TextField txb_puntoPartida;
    @FXML
    private TextField txb_puntoLlegada;
    @FXML
    private TextField txb_nombreTransportista;
    @FXML
    private TextField txb_numeroPlaca;
    @FXML
    private TextField txb_pesoTotal;
    @FXML
    private TextField txb_nombreCliente;
    @FXML
    private TableView<DetalleEnvio> tablaDetalleEnvio = new TableView<DetalleEnvio>();
    @FXML
    private TableColumn<DetalleEnvio,String> c_nombre;
    @FXML
    private TableColumn<DetalleEnvio,String> c_codigo;
    @FXML
    private TableColumn<DetalleEnvio,Integer> c_cantidad;
    @FXML
    private TableColumn<DetalleEnvio,Double> c_peso;
    @FXML
    private TableColumn<DetalleEnvio,Integer> c_indice;
    @FXML
    private TextField txb_observaciones;

    private Envio envioEscogido;

    private Double pesoTotal;

    private List<DetalleEnvio> listaDetalleEnvio;

    Long idEmpleadoAuditado = null;

    @Autowired
    private RepositoryGuia repositoryGuia;

    private final StageManager stageManager;

    private ListaEnviosRealizadosController listaEnviosRealizadosController;

    @Autowired
    @Lazy
    public CrearGuiaController(StageManager stageManager, ListaEnviosRealizadosController listaEnviosRealizadosController) {
        this.stageManager = stageManager;
        this.listaEnviosRealizadosController = listaEnviosRealizadosController;
    }

    public void registrarGuia(ActionEvent event){
        Guia guia = new Guia();
        List<DetalleGuia> listaDetalleGuia = new ArrayList<DetalleGuia>();
        Double pesoTotal = 0.0;
        for(DetalleEnvio detalleEnvio: this.listaDetalleEnvio){
            DetalleGuia detalleGuia = new DetalleGuia();
            Double pesoProducto = detalleEnvio.getPeso();
            if(pesoProducto != null){
                detalleGuia.setPeso(pesoProducto);
                pesoTotal += pesoProducto;
            }

            detalleGuia.setCantidad(detalleEnvio.getCantidad());
            detalleGuia.setIdProducto(detalleEnvio.getIdProducto());
            detalleGuia.setPeso(pesoTotal);
            listaDetalleGuia.add(detalleGuia);
        }
        guia.setListaDetalleGuia(listaDetalleGuia);

        Timestamp fechaEmision = null;
        Timestamp fechaPartida = null;
        Timestamp fechaVencimiento = null;
        if((this.dp_fechaEmision.getValue() != null) & (this.dp_fechaInicio.getValue() != null) & (this.dp_fechaFin.getValue() != null)){
            try {
                fechaEmision = this.obtenerFecha(this.dp_fechaEmision.getValue().toString());
                fechaPartida = this.obtenerFecha(this.dp_fechaInicio.getValue().toString());
                fechaVencimiento = this.obtenerFecha(this.dp_fechaFin.getValue().toString());
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Error al ingresar las fecha");
                return;
            }
        }
        guia.setFechaEmision(fechaEmision);
        guia.setFechaPartida(fechaPartida);
        guia.setFechaVencimiento(fechaVencimiento);

        String numeroGuia = this.txb_numeroguia.getText();
        if(numeroGuia.equalsIgnoreCase("")){
            System.out.println("Numero de guia es obligatorio");
            return;
        }
        guia.setNumeroGuia(numeroGuia);

        String puntoPartida = this.txb_puntoPartida.getText();
        if(puntoPartida.equalsIgnoreCase("")){
            System.out.println("Punto partida es obligatorio");
            return;
        }
        guia.setPuntoPartida(puntoPartida);

        String puntoLlegada = this.txb_puntoLlegada.getText();
        if(puntoLlegada.equalsIgnoreCase("")){
            System.out.println("Punto llegada es obligatorio");
            return;
        }
        guia.setPuntoLlegada(puntoLlegada);

        String numeroPlaca = this.txb_numeroPlaca.getText();
        if(numeroPlaca.equalsIgnoreCase("")){
            System.out.println("Numero placa es obligatorio");
            return;
        }

        guia.setNumeroPlaca(numeroPlaca);

        String pesoTotalTxb = this.txb_pesoTotal.getText();
        if(pesoTotalTxb.equalsIgnoreCase("")){
            System.out.println("Error al llenar el peso");
            return;
        }

        guia.setPesoTotal(this.pesoTotal);

        this.envioEscogido.getIdCliente();

        String observaciones  = txb_observaciones.getText();
        guia.setObservaciones(observaciones);

        guia.setNombreTransportista(txb_nombreTransportista.getText());
        guia.setIdEmpleadoAuditado(this.listaEnviosRealizadosController.obtenerIdEmpleadoAuditado());
        guia.setIdEnvio(this.envioEscogido.getIdEnvio());
        repositoryGuia.registrarGuia(guia);
        this. listaEnviosRealizadosController.actualizarListaGuia();
        this.stageManager.cerrarVentana(event);

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


    public void cancelarRegistroGuia(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    private void limpiarTabla(){
        tablaDetalleEnvio.getItems().clear();
        c_nombre.setCellValueFactory(new PropertyValueFactory<DetalleEnvio, String>("nombreProducto"));
        c_codigo.setCellValueFactory(new PropertyValueFactory<DetalleEnvio, String>("codigoProducto"));
        c_cantidad.setCellValueFactory(new PropertyValueFactory<DetalleEnvio, Integer>("cantidad"));
        c_peso.setCellValueFactory(new PropertyValueFactory<DetalleEnvio, Double>("peso"));
        c_indice.setCellValueFactory(new PropertyValueFactory<DetalleEnvio, Integer>("indiceTabla"));
        tablaDetalleEnvio.setEditable(true);
    }

    private void llenarTabla(List<DetalleEnvio> listaBusqueda){
        Integer indice = 1;
        for(DetalleEnvio detalleEnvio : listaBusqueda){
                detalleEnvio.setIndiceTabla(indice);
                indice++;
                tablaDetalleEnvio.getItems().add(detalleEnvio);
        }
    }

    private Double calcularPesoTotal() {
        Double pesoTotal = 0.0;
        for (DetalleEnvio detalleEnvio : this.listaDetalleEnvio) {
            Double pesoProducto = repositoryGuia.obtenerPesoProducto(detalleEnvio.getIdProducto());
            detalleEnvio.setPeso(pesoProducto);
            if (pesoProducto != null)
                pesoTotal += pesoProducto;
        }
        return pesoTotal;
    }


    public Double round(Double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(value));
    }


    @Override
    public void initialize() {
        this.envioEscogido =  this.listaEnviosRealizadosController.obtenerEnvio();
        this.listaDetalleEnvio = envioEscogido.getDetalleEnvio();


        Double pesoTotal = calcularPesoTotal();
        //pesoTotal = this.round(pesoTotal);

        this.txb_nombreCliente.setText(repositoryGuia.obtenerNombreCliente(this.envioEscogido.getIdCliente()));

        this.txb_pesoTotal.setText(""+ pesoTotal);
        this.pesoTotal = pesoTotal;
        this.txb_nombreCliente.setDisable(true);
        this.txb_pesoTotal.setDisable(true);
        //this.txb_nombreTransportista.setDisable(true);

        this.limpiarTabla();
        this.llenarTabla(this.listaDetalleEnvio);
    }
}
