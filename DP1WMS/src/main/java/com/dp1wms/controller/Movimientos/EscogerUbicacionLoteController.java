package com.dp1wms.controller.Movimientos;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.*;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EscogerUbicacionLoteController implements FxmlController {

    @FXML
    private ComboBox<Almacen> cb_almacen;
    @FXML
    private ComboBox<Area> cb_area;
    @FXML
    private ComboBox<Rack> cb_rack;
    @FXML
    private ComboBox<Cajon> cb_cajon;
    @FXML
    private Label labelCantidad;
    @FXML
    private TextField txb_cantidad;
    @FXML
    private Label labelTitulo;

    private List<Almacen> listaAlmacenes = new ArrayList<Almacen>();

    private int maxCantidad;

    private Boolean esAgregar;

    private Ubicacion ubicacionEscogida=null;

    private Lote loteEscogido=null;

    private Long idEmpleadoAuditado;

    @Autowired
    private RepositoryMantMov repositoryMantMov;

    private  UbicacionLoteController ubicacionLoteController;

    private final StageManager stageManager;

    @Autowired
    @Lazy
    public EscogerUbicacionLoteController(StageManager stageManager, UbicacionLoteController ubicacionLoteController){
        this.stageManager = stageManager;
        this.ubicacionLoteController = ubicacionLoteController;
    }

    public void guardarUbicacionLote(ActionEvent event){
        if(this.txb_cantidad.getText().equalsIgnoreCase("")){
            this.stageManager.mostrarErrorDialog("Error Lotes", null,
                    "Debe ingresar una cantidad para la ubicacion escogida");
            return;
        }

        if(this.cb_cajon.getValue() == null){
            this.stageManager.mostrarErrorDialog("Error Lotes", null,
                    "Debe escoger en que cajon se guardara el lote");
            return;
        }

        int cantidad=-1;
        try {
            cantidad = Integer.parseInt(this.txb_cantidad.getText());
        } catch (Exception e) {
            this.stageManager.mostrarErrorDialog("Error Lotes", null,
                    "Debe ingresar un numero valido");
            return;
        }

        if(cantidad>this.maxCantidad){
            this.stageManager.mostrarErrorDialog("Error Lotes", null,
                    "La cantidad ingresada supera el maximo que posee para asignar a esa ubicacion");
            return;
        }

        int idcajon = this.cb_cajon.getValue().getIdCajon();
        if(this.ubicacionLoteController.cajonExiste(idcajon)){
            this.stageManager.mostrarErrorDialog("Error Lotes", null,
                    "Ya existe esta ubicacion asignada para este lote, modifique la ubicacion existe si desea agregar mayor cantidad");
            return;
        }

        if(cantidad != -1){
            if(esAgregar){
                this.repositoryMantMov.insertarUbicacion(loteEscogido.getIdLote(),loteEscogido.getIdProducto(),idcajon,cantidad,this.idEmpleadoAuditado);
            }else{
                this.repositoryMantMov.actualizarUbicacion(loteEscogido.getIdLote(),loteEscogido.getIdProducto(),idcajon,cantidad,ubicacionEscogida.getIdCajon(),this.idEmpleadoAuditado);

            }
            this.ubicacionLoteController.refrescarTabla();
            this.stageManager.cerrarVentana(event);
        }else {
            return;
        }

    }

    public void cancelarOperacion(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }
    /////////
    public void actualizarListasPorRack(ActionEvent event){
       if(cb_rack.getValue() != null){
           int idRack= cb_rack.getValue().getIdRack();
           for(Almacen almacen : this.listaAlmacenes){
               List<Area> listaAreaBusqueda = almacen.getListaArea();
               for(Area area: listaAreaBusqueda){
                   List<Rack> listaRackBusqueda = area.getListaRack();
                   for(Rack rack : listaRackBusqueda){
                       if(idRack == rack.getIdRack()){
                           this.llenarComboBoxNoRack(rack);
                           return;
                       }
                   }
               }
           }
       }
    }

    private void llenarComboBoxNoRack(Rack rack){
        cb_cajon.getItems().clear();
        List<Cajon> cajones = rack.getListaCajones();
        for(Cajon cajon : cajones){
            cb_cajon.getItems().add(cajon);
        }
    }

    public void actualizarListasPorArea(ActionEvent event){
        if(cb_area.getValue() != null){
            int idArea = cb_area.getValue().getIdArea();
            for(Almacen almacen : this.listaAlmacenes){
                List<Area> listaAreaBusqueda = almacen.getListaArea();
                for(Area area: listaAreaBusqueda){
                    if(area.getIdArea() == idArea){
                        llenarComboBoxNoArea(area);
                        return;
                    }
                }
            }
        }
    }
    private void llenarComboBoxNoArea(Area area){
        cb_cajon.getItems().clear();
        cb_rack.getItems().clear();
        List<Rack> racks = area.getListaRack();
        for(Rack rack : racks){
            cb_rack.getItems().add(rack);
            List<Cajon> cajones = rack.getListaCajones();
            for(Cajon cajon : cajones){
                cb_cajon.getItems().add(cajon);
            }
        }
    }


    public void actualizarListasPorAlmacen(ActionEvent event){
       if(cb_almacen.getValue() != null){
           int idAlmacen = cb_almacen.getValue().getIdAlmacen();
           List<Almacen> listaAlmacenAux = new ArrayList<Almacen>();
           for(Almacen almacen : this.listaAlmacenes){
               if(almacen.getIdAlmacen() == idAlmacen){
                   listaAlmacenAux.add(almacen);
               }
           }
           this.llenarComboBoxNoAlmacen(listaAlmacenAux);
       }
    }

    private void llenarComboBoxNoAlmacen(List<Almacen> listaAlmacenesLlenar){
        cb_cajon.getItems().clear();
        cb_rack.getItems().clear();
        cb_area.getItems().clear();
        for(Almacen almacen:listaAlmacenesLlenar){
            List<Area> areas = almacen.getListaArea();
            for(Area area : areas){
                cb_area.getItems().add(area);
                List<Rack> racks = area.getListaRack();
                for(Rack rack : racks){
                    cb_rack.getItems().add(rack);
                    List<Cajon> cajones = rack.getListaCajones();
                    for(Cajon cajon : cajones){
                        cb_cajon.getItems().add(cajon);
                    }
                }
            }
        }
    }

    private void llenarComboBox(List<Almacen> listaAlmacenesLlenar){
        cb_almacen.getItems().clear();
        cb_area.getItems().clear();
        cb_rack.getItems().clear();
        cb_cajon.getItems().clear();
        for(Almacen almacen:listaAlmacenesLlenar){
            cb_almacen.getItems().add(almacen);
            List<Area> areas = almacen.getListaArea();
            for(Area area : areas){
                cb_area.getItems().add(area);
                List<Rack> racks = area.getListaRack();
                for(Rack rack : racks){
                    cb_rack.getItems().add(rack);
                    List<Cajon> cajones = rack.getListaCajones();
                    for(Cajon cajon : cajones){
                        cb_cajon.getItems().add(cajon);
                    }
                }
            }
        }
    }

    private void configurarComboBoxAlmacen(){

        Callback<ListView<Almacen>, ListCell<Almacen>> factory = lv -> new ListCell<Almacen>() {

            @Override
            protected void updateItem(Almacen item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNombre());
            }

        };

        cb_almacen.setCellFactory(factory);
        cb_almacen.setButtonCell(factory.call(null));
    }

    private void configurarComboBoxArea(){

        Callback<ListView<Area>, ListCell<Area>> factory = lv -> new ListCell<Area>() {

            @Override
            protected void updateItem(Area item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getCodigo());
            }

        };

        cb_area.setCellFactory(factory);
        cb_area.setButtonCell(factory.call(null));
    }

    private void configurarComboBoxRack(){

        Callback<ListView<Rack>, ListCell<Rack>> factory = lv -> new ListCell<Rack>() {

            @Override
            protected void updateItem(Rack item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getCodigo());
            }

        };

        cb_rack.setCellFactory(factory);
        cb_rack.setButtonCell(factory.call(null));
    }

    private void configurarComboBoxCajon(){

        Callback<ListView<Cajon>, ListCell<Cajon>> factory = lv -> new ListCell<Cajon>() {

            @Override
            protected void updateItem(Cajon item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : "X: "+item.getPosX() + " Nivel: "+item.getPosY());
            }

        };

        cb_cajon.setCellFactory(factory);
        cb_cajon.setButtonCell(factory.call(null));
    }

    private void llenarCombosPorModificacion(int idCajon){
        List<Almacen> listaAlmacenesLlenar = this.listaAlmacenes;
        for(Almacen almacen:listaAlmacenesLlenar){
            //cb_almacen.getItems().add(almacen);
            List<Area> areas = almacen.getListaArea();
            for(Area area : areas){
                //cb_area.getItems().add(area);
                List<Rack> racks = area.getListaRack();
                for(Rack rack : racks){
                    //cb_rack.getItems().add(rack);
                    List<Cajon> cajones = rack.getListaCajones();
                    for(Cajon cajon : cajones){
                        if(cajon.getIdCajon() == idCajon){
                            cb_cajon.setValue(cajon);
                            cb_rack.setValue(rack);
                            cb_area.setValue(area);
                            cb_almacen.setValue(almacen);
                            return;
                        }

                    }
                }
            }
        }
    }

    @Override
    public void initialize() {
        this.configurarComboBoxAlmacen();
        this.configurarComboBoxArea();
        this.configurarComboBoxRack();
        this.configurarComboBoxCajon();

        this.listaAlmacenes = this.repositoryMantMov.obtenerAlmacenes();
        this.llenarComboBox(this.listaAlmacenes);

        this.esAgregar = this.ubicacionLoteController.esAgregar();

        if(!esAgregar){
            this.ubicacionEscogida = this.ubicacionLoteController.obtenerUbicacionEscogida();
            this.loteEscogido = this.ubicacionLoteController.obtenerLoteEscogido();
            this.llenarCombosPorModificacion(ubicacionEscogida.getIdCajon());
        }else{
            this.labelTitulo.setText("Asignar Nueva Ubicacion Para el Lote");
            this.loteEscogido = this.ubicacionLoteController.obtenerLoteEscogido();
        }

        this.maxCantidad = this.ubicacionLoteController.obtenerMaxCantidad();
        String labelText = this.labelCantidad.getText();
        labelText += "" + this.maxCantidad+ ")";
        this.labelCantidad.setText(labelText);

        this.txb_cantidad.setText(""+maxCantidad);

        this.idEmpleadoAuditado = this.ubicacionLoteController.obtenerIdEmpleadoAuditado();
    }
}
