package com.openroad.controller;

import java.io.IOException;
import org.springframework.stereotype.Component;

import com.openroad.ApplicationFX;
import com.openroad.controller.category.CategoryPaneController;
import com.openroad.controller.cozinha.CozinhaController;
import com.openroad.controller.pedido.PedidoController;
import com.openroad.controller.product.ProductPaneController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("principal.fxml")
public class PrincipalController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void handleAnchoPaneAtendente(ActionEvent event) throws IOException {
        // anchorPane.getChildren().clear();
        pane("atendente");
    }

    @FXML
    public void handleAnchoPaneCategoria(ActionEvent event) throws IOException {
        // anchorPane.getChildren().clear();
        pane("categoria");
    }

    @FXML
    public void handleAnchoPaneProduto(ActionEvent event) throws IOException {
        // anchorPane.getChildren().clear();
        pane("produto");
    }

    @FXML
    public void handleAnchoPaneFechar(ActionEvent event) throws IOException {
        System.exit(0);
    }

    @FXML
    public void handleAnchoPanePedido(ActionEvent event) throws IOException {
        pane("pedido");
    }

    @FXML
    public void handleAnchoPaneCozinha(ActionEvent event) throws IOException {
        pane("cozinha");
    }

    public static void loadView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(PrincipalController.class.getResource("principal.fxml"));
        loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("OpenRoad");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(800.0);
        stage.initStyle(StageStyle.UNIFIED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(PrincipalController.class.getResourceAsStream("../img/software.png")));
        stage.show();
    }

    public void pane(String pane) {
        AnchorPane a = new AnchorPane();

        switch (pane) {
            case "atendente":
                a = AtendenteController.setAnchorPane(anchorPane);
                break;
            case "categoria":
                a = CategoryPaneController.setAnchorPane(anchorPane);
                break;
            case "produto":
                a = ProductPaneController.setAnchorPane(anchorPane);
                break;
            case "pedido":
                a = PedidoController.setAnchorPane(anchorPane);
                break;
            case "cozinha":
                a = CozinhaController.setAnchorPane(anchorPane);
                break;
        }
        anchorPane.setTopAnchor(a, 0.0);
        anchorPane.setBottomAnchor(a, 0.0);
        anchorPane.setLeftAnchor(a, 0.0);
        anchorPane.setRightAnchor(a, 0.0);

        anchorPane.getChildren().addAll(a);
    }

}
