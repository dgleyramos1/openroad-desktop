package com.openroad.controller;

import java.io.IOException;
import org.springframework.stereotype.Component;

import com.openroad.ApplicationFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("principal.fxml")
public class PrincipalController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void handleAnchoPaneAtendentes(ActionEvent event) throws IOException {
        // anchorPane.getChildren().clear();
        AnchorPane a = AtendenteController.setAnchorPane(anchorPane);
        anchorPane.setTopAnchor(a, 0.0);
        anchorPane.setBottomAnchor(a, 0.0);
        anchorPane.setLeftAnchor(a, 0.0);
        anchorPane.setRightAnchor(a, 0.0);
        anchorPane.getChildren().add(a);
    }

    public static void loadView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(PrincipalController.class.getResource("principal.fxml"));
        loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Open Road");
        stage.setMinHeight(600.0);
        stage.setMinWidth(800.0);
        stage.initStyle(StageStyle.UNIFIED);
        stage.show();
    }

}
