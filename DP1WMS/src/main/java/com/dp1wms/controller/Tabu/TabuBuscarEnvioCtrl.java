package com.dp1wms.controller.Tabu;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.dao.RepositoryAlmacen;
import com.dp1wms.model.Envio;
import com.dp1wms.util.DateParser;
import com.dp1wms.view.StageManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TabuBuscarEnvioCtrl implements FxmlController{


    @FXML private TableView<Envio> enviosTable;
    @FXML private TableColumn<Envio, Long> codigoTC;
    @FXML private TableColumn<Envio, String> destinoTC;
    @FXML private TableColumn<Envio, String> fechaTC;
    @FXML private TableColumn<Envio, String> rucTC;
    @FXML private TableColumn<Envio, String> nombreTC;

    @Autowired
    private RepositoryAlmacen repositoryAlmacen;

    private Envio envio;

    private StageManager stageManager;

    @Override
    public void initialize() {
        this.envio = null;
        this.enviosTable.getItems().clear();
        this.codigoTC.setCellValueFactory(value->{
            return new SimpleObjectProperty<Long>(value.getValue().getIdEnvio());
        });
        this.destinoTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getDestino());
        });
        this.fechaTC.setCellValueFactory(value->{
            String fecha = DateParser.timestampToString(value.getValue().getFechaEnvio());
            return new SimpleStringProperty(fecha);
        });
        this.rucTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getCliente().getNumDoc());
        });
        this.nombreTC.setCellValueFactory(value->{
            return new SimpleStringProperty(value.getValue().getCliente().getRazonSocial());
        });
        List<Envio> envios = this.repositoryAlmacen.obtenerEnvios();
        if (envios != null) {
            this.enviosTable.getItems().addAll(envios);
        }
    }

    @FXML
    private void seleccionarEnvio(ActionEvent event){
        Envio envio = this.enviosTable.getSelectionModel().getSelectedItem();
        if(envio == null){
            this.stageManager.mostrarErrorDialog("Seleccionar Envio", null,
                    "No ha seleccionado ningun envio");
        } else {
            this.setEnvio(envio);
            this.stageManager.cerrarVentana(event);
        }
    }

    @Autowired @Lazy
    public TabuBuscarEnvioCtrl(StageManager stageManager){
        this.stageManager = stageManager;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
}
