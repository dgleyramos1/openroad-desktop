package com.openroad.controller.pedido;

import com.openroad.api.catalog.order.model.Order;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

@FxmlView("confirme.fxml")
public class ConfirmedCloseOrderController {

    @FXML
    private Label labelInfoOrder;

    @FXML
    private Label labelInfoPrice;

    private Boolean confirmed = false;

    private Stage stage;

    private Order order;

    @FXML
    void handleCloseOrderNo(MouseEvent event) {
        stage.close();
    }

    @FXML
    void handleCloseOrderYes(MouseEvent event) {
        confirmed = true;
        stage.close();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setOrder(Order order) {
        this.order = order;
        labelInfoOrder.setText("Mesa " + order.getTable());
        labelInfoPrice.setText(String.format("%.2f", order.getTotal()));
    }
}
