package com.dp1wms.view;

import com.dp1wms.spring.config.SpringFXMLLoader;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


public class StageManager {

    private final Stage stagePrincipal;
    private final SpringFXMLLoader springFXMLLoader;

    public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage){
        this.springFXMLLoader = springFXMLLoader;
        this.stagePrincipal = stage;
    }

    public void cambiarScene(final FxmlView view){
        Parent viewRootNode = loadFromFxmlFilePath(view.getFxmlFile());
        show(viewRootNode, view.getTitle(), view.isResizable());
    }

    public void mostarModal(final FxmlView view){
        Parent viewRootNode = loadFromFxmlFilePath(view.getFxmlFile());
        Scene scene = new Scene(viewRootNode);

                Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle(view.getTitle());
        stage.setResizable(false);
        stage.show();
    }
    private void show(final Parent rootnode, String title, boolean resizable) {
        Scene scene = prepararScene(rootnode);

        stagePrincipal.setTitle(title);
        stagePrincipal.setScene(scene);
        stagePrincipal.sizeToScene();
        stagePrincipal.centerOnScreen();
        stagePrincipal.setResizable(resizable);

        try {
            stagePrincipal.show();
        } catch (Exception exception) {
            logAndExit ("No se pudo cambiar scene " + title,  exception);
        }
    }

    private Scene prepararScene(Parent rootnode){
        Scene scene = stagePrincipal.getScene();
        if (scene == null) {
            scene = new Scene(rootnode);
        }
        scene.setRoot(rootnode);
        return scene;
    }

    private Parent loadFromFxmlFilePath(String fxmlFilePath){
        Parent rootNode = null;
        try {
            rootNode = springFXMLLoader.load(fxmlFilePath);
        } catch (Exception exception) {
            logAndExit("No se pudo cargar FXML view " + fxmlFilePath, exception);
        }
        return rootNode;
    }

    private void logAndExit(String errorMsg, Exception exception) {
        System.err.println(errorMsg);
        exception.printStackTrace();
        Platform.exit();
    }
}
