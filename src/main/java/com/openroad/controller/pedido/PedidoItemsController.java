package com.openroad.controller.pedido;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.api.catalog.item.controller.dtos.ItemDTO;
import com.openroad.api.catalog.item.model.Item;
import com.openroad.api.catalog.order.controller.OrderController;
import com.openroad.api.catalog.order.controller.dtos.OrderDTO;
import com.openroad.api.catalog.product.controller.ProductController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    private Stage stage;

    private Boolean confirmedBoolean = false;

    private OrderDTO order;

    private List<Item> listItems;

    private Stage dialogStage;

    @FXML
    void initialize() {
        dialogStage = new Stage();
    }

    private void carregaItems() {
        listItems.forEach(item -> {
            GridPane grid = new GridPane();
            grid.setMaxWidth(350.0);
            Label product = new Label();
            Label description = new Label();
            Label qtd = new Label();
            Label price = new Label();
            description.setWrapText(true);
            product.getStyleClass().add("grid-label-product");
            description.getStyleClass().add("grid-label-description");
            qtd.getStyleClass().add("grid-label-description");
            price.getStyleClass().add("grid-label-description");
            grid.getStyleClass().add("grid-pane");
            product.setText(item.getProduct().getName());
            description.setText(item.getProduct().getDescription());
            qtd.setText("Quantidade: " + item.getAmount());
            price.setText("Valor: " + String.format("%.2f", item.getPrice()));
            grid.add(product, 0, 0);
            grid.add(description, 0, 1);
            grid.add(qtd, 0, 2);
            grid.add(price, 0, 3);

            listViewItems.getItems().add(grid);
        });
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
        carregaItems();
    }

    @FXML
    void handleCloseOrder(MouseEvent event) {
        try {
            Boolean confirmedCloseOrder = showFXMLCLoseOrderConfirmed(order);
            if (confirmedCloseOrder) {
                confirmedBoolean = true;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            stage.close();
        }

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

    public Boolean getConfirmedBoolean() {
        return confirmedBoolean;
    }

    public void setConfirmedBoolean(Boolean confirmedBoolean) {
        this.confirmedBoolean = confirmedBoolean;
    }

    private Boolean showFXMLCLoseOrderConfirmed(OrderDTO orderDTO) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ConfirmedCloseOrderController.class.getResource("confirme.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)

        dialogStage.setTitle("Comanda");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        // Setando o cliente no Controller.
        ConfirmedCloseOrderController controller = loader.getController();
        controller.setStage(dialogStage);
        controller.setOrder(orderDTO);
        dialogStage.showAndWait();

        return controller.getConfirmed();
    }
}
