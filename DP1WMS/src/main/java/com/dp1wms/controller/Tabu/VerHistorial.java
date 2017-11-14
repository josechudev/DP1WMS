package com.dp1wms.controller.Tabu;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryRuta;
import com.dp1wms.model.Envio;
import com.dp1wms.model.Ruta;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VerHistorial implements FxmlController{

    @FXML private TableView<Ruta> rutasTable;
    @FXML private TableColumn<Ruta, Integer> codigoTC;
    @FXML private TableColumn<Ruta, Integer> recorridoTC;

    private List<Ruta> rutas;
    private Envio envio;

    private Ruta ruta;

    @Autowired
    private RepositoryRuta repositoryRuta;

    private StageManager stageManager;

    @FXML
    private void verRuta(ActionEvent event){
        Ruta ruta = this.rutasTable.getSelectionModel().getSelectedItem();
        if(ruta == null){
            this.stageManager.mostrarErrorDialog("Historial de Rutas", null,
                    "Debe seleccionar una ruta");
        } else {
            this.setRuta(ruta);
            this.stageManager.cerrarVentana(event);
        }
    }

    @Autowired @Lazy
    public VerHistorial(StageManager stageManager){
        this.stageManager = stageManager;
    }

    @Override
    public void initialize() {
        this.ruta = null;
        this.rutas = this.repositoryRuta.obtenerRutasPorEnvio(this.envio);
        if(this.rutas == null){
            this.stageManager.mostrarErrorDialog("Historial de Rutas", null,
                    "Hubo un error al cargar las rutas. Vuelve a intentarlo");
        } else {
            this.rutasTable.getItems().clear();
            this.codigoTC.setCellValueFactory(value->{
                return new SimpleObjectProperty<Integer>(value.getValue().getIdRuta());
            });
            this.recorridoTC.setCellValueFactory(value->{
                return new SimpleObjectProperty<Integer>(value.getValue().getCosto());
            });
            this.rutasTable.getItems().addAll(this.rutas);
        }

    }

    public void setEnvio(Envio envio){
        this.envio = envio;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
}
