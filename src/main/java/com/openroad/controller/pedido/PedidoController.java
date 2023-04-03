package com.openroad.controller.pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("pedido.fxml")
public class PedidoController {

    @FXML
    private ListView<Label> listView;

    @FXML
    private ImageView carregador;

    private List<String> list = new ArrayList<>();

    @FXML
    void initialize() {
        list.add("Mesa 1");
        list.add("Mesa 2");
        list.add("Mesa 3");
        list.add("Mesa 4");

        list.forEach(item -> {
            Label label = new Label();
            label.setMaxWidth(600);
            label.setText(item);
            label.getStyleClass().add("label-list");
            listView.getItems().add(label);
        });

        listView.getStylesheets().add(getClass().getResource("../../styles/list.css").toExternalForm());
    }

    public static AnchorPane setAnchorPane(AnchorPane pane) {

        try {
            FXMLLoader loader = new FXMLLoader(PedidoController.class.getResource("pedido.fxml"));
            loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
            AnchorPane a = loader.load();
            return a;
        } catch (IOException ex) {
            new IOException("Error " + ex.getMessage());
        }
        return pane;
    }
}
