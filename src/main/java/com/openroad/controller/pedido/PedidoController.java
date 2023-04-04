package com.openroad.controller.pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.catalog.item.controller.ItemController;
import com.openroad.api.catalog.item.controller.dtos.ItemDTO;
import com.openroad.api.catalog.item.model.Item;
import com.openroad.api.catalog.order.controller.OrderController;
import com.openroad.api.catalog.order.controller.dtos.OrderDTO;
import com.openroad.utils.AlertDialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    @Autowired
    private ItemController itemController;

    Stage dialogStage;

    private List<Item> listItems;

    private AlertDialog dialog = new AlertDialog();
    Alert alert;

    private void selectedOrder(String order_id) {
        listItems = itemController.getItems(order_id);
        if (listItems.isEmpty()) {
            dialog.alert(alert, AlertType.INFORMATION, "Alerta", "", "A mesa ainda não pediu!");
            return;
        }
        OrderDTO order = controller.getOrder(order_id);
        try {
            showFXMLOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregaOrdens() {

        list = controller.listarOrdens();

        list.forEach(item -> {
            Label label = new Label();
            label.setMaxWidth(600);
            label.setText("Mesa " + item.getTable());
            label.setId(item.getId());
            label.getStyleClass().add("label-list");
            label.setOnMouseClicked(event -> selectedOrder(label.getId()));
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
        dialogStage = new Stage();
        alert = new Alert(AlertType.NONE);
        alert.initStyle(StageStyle.UNIFIED);
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

    private void showFXMLOrder(OrderDTO orderDTO) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PedidoItemsController.class.getResource("items.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)

        dialogStage.setTitle("Comanda");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        // Setando o cliente no Controller.
        PedidoItemsController controller = loader.getController();
        controller.setStage(dialogStage);
        controller.setOrder(orderDTO);
        controller.setListItems(listItems);
        dialogStage.showAndWait();
    }
}
