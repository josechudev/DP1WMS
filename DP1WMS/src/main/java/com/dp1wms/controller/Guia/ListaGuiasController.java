package com.dp1wms.controller.Guia;

import com.dp1wms.controller.FxmlController;
import com.dp1wms.controller.MainController;
import com.dp1wms.dao.RepositoryGuia;
import com.dp1wms.model.Guia;
import com.dp1wms.view.MainView;
import com.dp1wms.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class ListaGuiasController implements FxmlController {


    @FXML
    private TableView<Guia> tablaGuias = new TableView<Guia>();
    @FXML
    private TableColumn<Guia,Integer> c_indice;
    @FXML
    private TableColumn<Guia,String> c_cliente;
    @FXML
    private TableColumn<Guia,String> c_numeroGuia;
    @FXML
    private TableColumn<Guia,Double> c_pesoTotal;

    private List<Guia> listaGuias;

    private MainController mainController;

    @Autowired
    private RepositoryGuia repositoryGuia;

    private final StageManager stageManager;

    @Autowired
    @Lazy
    public ListaGuiasController(StageManager stageManager,MainController mainController) {
        this.stageManager = stageManager;
        this.mainController = mainController;
    }

    public void crearGuiaRemision(ActionEvent event){
        this.stageManager.mostrarModal(MainView.LISTAR_ENVIOS_GUIA);
    }

    public void limpiarTabla(){
        tablaGuias.getItems().clear();
        c_indice.setCellValueFactory(new PropertyValueFactory<Guia,Integer>("indiceTabla"));
        c_cliente.setCellValueFactory(new PropertyValueFactory<Guia,String>("razonSocial"));
        c_numeroGuia.setCellValueFactory(new PropertyValueFactory<Guia,String>("numeroGuia"));
        c_pesoTotal.setCellValueFactory(new PropertyValueFactory<Guia,Double>("pesoTotal"));
        tablaGuias.setEditable(true);
    }

    public void cancelarOperacion(ActionEvent event){
        this.stageManager.cerrarVentana(event);
    }

    public List<Guia> obtenerListaGuias(){
        return this.listaGuias;
    }


    public void llenarTabla(List<Guia> lista){
        Integer indice = 1;
        for(Guia guia : lista){
            guia.setIndiceTabla(indice);
            indice++;
            this.tablaGuias.getItems().add(guia);
        }
    }

    public Long obtenerIdEmpleadoAuditado(){
        return mainController.getEmpleado().getIdempleado();
    }

    public void actualizarListaGuias(){
        this.actualizarTabla();
    }


    private void actualizarTabla(){
        this.listaGuias = this.repositoryGuia.obtenerGuias();
        this.limpiarTabla();
        this.llenarTabla(this.listaGuias);
    }

    @Override
    public void initialize() {
        this.actualizarTabla();
    }
}
