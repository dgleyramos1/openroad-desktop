package com.openroad.controller.pedido;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.openroad.api.catalog.item.controller.dtos.ItemDTO;
import com.openroad.api.catalog.order.controller.dtos.OrderDTO;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("items.fxml")
public class PedidoItemsController {

    @FXML
    private Label labelOrdenName;

    @FXML
    private Label labelOrdenTotal;

    @FXML
    private ListView<GridPane> listViewItems;

    private List<GridPane> grids;

    private Stage stage;

    private OrderDTO order;

    private List<ItemDTO> listItems;

    public void setListItems(List<ItemDTO> listItems) {
        this.listItems = listItems;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
        labelOrdenName.setText("Mesa " + order.getTable());
        labelOrdenTotal.setText(String.format("%.2f", order.getTotal()));
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
