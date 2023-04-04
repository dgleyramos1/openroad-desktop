package com.openroad.controller.pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.api.catalog.item.controller.dtos.ItemDTO;
import com.openroad.api.catalog.item.model.Item;
import com.openroad.api.catalog.order.controller.dtos.OrderDTO;
import com.openroad.api.catalog.product.controller.ProductController;

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

    private List<Item> listItems;

    @Autowired
    private ProductController controller;

    private void carregaItems() {
        listItems.forEach(item -> {
            GridPane grid = new GridPane();
            Label product = new Label();
            Label description = new Label();
            product.getStyleClass().add("grid-label-product");
            description.getStyleClass().add("grid-label-description");
            grid.getStyleClass().add("grid-pane");
            product.setText(item.getProduct().getName());
            description.setText(item.getProduct().getDescription());
            grid.add(product, 0, 0);
            grid.add(description, 0, 1);
            listViewItems.getItems().add(grid);
        });
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
        carregaItems();
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
