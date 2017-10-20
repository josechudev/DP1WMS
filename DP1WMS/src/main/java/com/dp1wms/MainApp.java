package com.dp1wms;

import com.dp1wms.view.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;
import com.dp1wms.view.FxmlView;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class MainApp extends Application {

    protected ConfigurableApplicationContext springContext;
    protected StageManager stageManager;

    @Override
    public void init(){
        springContext = bootstrapSpringApplicationContext();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stageManager = springContext.getBean(StageManager.class, stage);
        mostrarLogin();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    protected void mostrarLogin() {
        stageManager.cambiarScene(FxmlView.LOGIN);
    }

    private ConfigurableApplicationContext bootstrapSpringApplicationContext(){
        SpringApplicationBuilder builder = new SpringApplicationBuilder(MainApp.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        builder.headless(false); //needed for TestFX integration testing or eles will get a java.awt.HeadlessException during tests
        return builder.run(args);
    }

}
