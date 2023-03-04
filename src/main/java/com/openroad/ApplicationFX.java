package com.openroad;

import java.io.IOException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.openroad.controller.PrincipalController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

public class ApplicationFX extends Application {

    private ConfigurableApplicationContext contextoSpring;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.contextoSpring = new SpringApplicationBuilder()
                .sources(OpenRoadApplication.class)
                .run(args);
    }

    @Override
    public void stop() {
        this.contextoSpring.close();
        Platform.exit();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FxWeaver fxWeaver = contextoSpring.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(PrincipalController.class);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Open Road");
        stage.setMinHeight(600.0);
        stage.setMinWidth(800.0);
        stage.show();
    }

}
