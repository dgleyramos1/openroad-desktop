package com.openroad.controller;

import java.io.IOException;

import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("viewPrincipal.fxml")
public class PrincipalController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void handleAnchoPaneAtendentes(ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/com/openroad/controller/anchorPaneAtendentes.fxml"));
        anchorPane.getChildren().setAll(a);
    }

}
