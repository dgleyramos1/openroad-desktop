package com.openroad.controller.pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.catalog.order.controller.OrderController;
import com.openroad.api.catalog.order.controller.dtos.OrderDTO;

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

    private List<OrderDTO> list;

    private List<Label> listLabel = new ArrayList<>();

    private ObservableList<Label> observableList;

    @Autowired
    private OrderController controller;

    private void carregaOrdens() {

        list = controller.listarOrdens();

        list.forEach(item -> {
            Label label = new Label();
            label.setMaxWidth(600);
            label.setText("Mesa " + item.getTable());
            label.getStyleClass().add("label-list");
            listLabel.add(label);
        });

        observableList = FXCollections.observableArrayList(listLabel);
        listView.setItems(observableList);

    }

    @FXML
    private void handleListView() {
        listView.getItems().removeAll(observableList);
        observableList.clear();
        listLabel.clear();
        carregaOrdens();
    }

    @FXML
    void initialize() {
        carregaOrdens();

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
