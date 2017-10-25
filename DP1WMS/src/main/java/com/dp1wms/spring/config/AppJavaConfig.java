package com.dp1wms.spring.config;

import com.dp1wms.controller.MantenimientoTipoEmpleadoController;
import com.dp1wms.view.StageManager;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.util.ResourceBundle;

@Configuration
public class AppJavaConfig {
    @Autowired SpringFXMLLoader springFXMLLoader;

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean
    @Lazy(value = true) //Stage only created after Spring context bootstap
    public StageManager stageManager(Stage stage) throws IOException {
        return new StageManager(springFXMLLoader, stage);
    }

    @Bean @Lazy
    public MantenimientoTipoEmpleadoController mantenimientoTipoEmpleadocontroller(StageManager stageManager) {
        return new MantenimientoTipoEmpleadoController(stageManager);
    }

}
