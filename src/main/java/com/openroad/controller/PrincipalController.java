package com.openroad.controller;

import java.io.IOException;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
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

}
