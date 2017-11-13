package com.dp1wms.controller.Ubicaciones;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryMantMov;
import com.dp1wms.model.Almacen;
import com.dp1wms.model.Area;
import com.dp1wms.model.Cajon;
import com.dp1wms.model.Rack;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UbicacionesController implements FxmlController{

    @FXML
    private ComboBox<Almacen> cb_almacen;
    @FXML
    private ComboBox<Area> cb_area;
    @FXML
    private ComboBox<Rack> cb_rack;
    @FXML
    private ComboBox<Cajon> cb_cajon;
    @FXML
    private ComboBox<String> cb_escoger;
    @FXML
    private Label label_rack;
    @FXML
    private Label label_cajon;

    private List<Almacen> listaAlmacenes = new ArrayList<Almacen>();

    private Long idEmpleadoAuditado;

    private static final String textoArea = "Area";

    private static final String textoRack = "Rack";

    //private static final String textoCajon = "Cajon";

    @Autowired
    private RepositoryMantMov repositoryMantMov;

    private  MainController mainController;

    private final StageManager stageManager;

    @Autowired
    @Lazy
    public UbicacionesController(StageManager stageManager, MainController mainController){
        this.stageManager = stageManager;
        this.mainController = mainController;
    }

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

    public void deshabilitarUbicacion(ActionEvent event){
        if(cb_escoger.getValue() == null){
            this.stageManager.mostrarErrorDialog("Error en Ubicaciones", null,
                    "Debe seleccionar si deshabilitara un rack o area");
            return;
        }
        String valorCombo = cb_escoger.getValue();
        if(valorCombo.equalsIgnoreCase(textoArea)){

            if(cb_area.getValue() == null){
                this.stageManager.mostrarErrorDialog("Error en Ubicaciones", null,
                        "Debe seleccionar un valor para el area que deshabilitara");
                return;
            }

            this.repositoryMantMov.habilitarArea(false,cb_area.getValue().getIdArea(),idEmpleadoAuditado);

        }else if(valorCombo.equalsIgnoreCase(textoRack)){

            if(cb_rack.getValue() == null){

                this.stageManager.mostrarErrorDialog("Error en Ubicaciones", null,
                        "Debe seleccionar un valor para el rack que deshabilitara");
                return;
            }

            this.repositoryMantMov.habilitarRack(false,cb_rack.getValue().getIdRack(),idEmpleadoAuditado);

        }

        this.stageManager.mostrarErrorDialog("Alerta", null,
                "Se Deshabilito la ubicacion");

        //this.stageManager.cerrarVentana(event);
    }


    public void habilitarUbicacion(ActionEvent event){
        if(cb_escoger.getValue() == null){
            this.stageManager.mostrarErrorDialog("Error en Ubicaciones", null,
                    "Debe seleccionar si deshabilitara un rack o area");
            return;
        }
        String valorCombo = cb_escoger.getValue();
        if(valorCombo.equalsIgnoreCase(textoArea)){

            if(cb_area.getValue() == null){
                this.stageManager.mostrarErrorDialog("Error en Ubicaciones", null,
                        "Debe seleccionar un valor para el area que deshabilitara");
                return;
            }

            this.repositoryMantMov.habilitarArea(true,cb_area.getValue().getIdArea(),idEmpleadoAuditado);

        }else if(valorCombo.equalsIgnoreCase(textoRack)){

            if(cb_rack.getValue() == null){

                this.stageManager.mostrarErrorDialog("Error en Ubicaciones", null,
                        "Debe seleccionar un valor para el rack que deshabilitara");
                return;
            }

            this.repositoryMantMov.habilitarRack(true,cb_rack.getValue().getIdRack(),idEmpleadoAuditado);

        }

        this.stageManager.mostrarErrorDialog("Alerta", null,
                "Se Habilito la ubicacion");
       // this.stageManager.cerrarVentana(event);
    }

    public void cancelarOperacion(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }


    public void actualizarVista(ActionEvent event){
        String valorCombo = this.cb_escoger.getValue();
        if(valorCombo != null){
            switch (valorCombo){
                case textoArea:
                    this.label_rack.setVisible(false);
                    this.label_cajon.setVisible(false);
                    this.cb_rack.setVisible(false);
                    this.cb_cajon.setVisible(false);
                    break;
                case textoRack:
                    this.label_rack.setVisible(true);
                    this.label_cajon.setVisible(false);
                    this.cb_rack.setVisible(true);
                    this.cb_cajon.setVisible(false);
                    break;
            }
        }

    }

    @Override
    public void initialize() {
            this.configurarComboBoxAlmacen();
            this.configurarComboBoxArea();
            this.configurarComboBoxRack();
            this.configurarComboBoxCajon();

            this.idEmpleadoAuditado = this.mainController.getEmpleado().getIdempleado();

            this.listaAlmacenes = this.repositoryMantMov.obtenerTodosAlmacenes();
            this.llenarComboBox(this.listaAlmacenes);

            this.label_cajon.setVisible(false);
            this.cb_cajon.setVisible(false);

            this.cb_escoger.getItems().add(this.textoArea);
            this.cb_escoger.getItems().add(this.textoRack);
            //this.cb_escoger.getItems().add(this.textoCajon);
    }
}
