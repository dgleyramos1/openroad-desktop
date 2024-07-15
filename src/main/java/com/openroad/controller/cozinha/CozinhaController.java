package com.openroad.controller.cozinha;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.catalog.item.controller.ItemController;
import com.openroad.api.catalog.item.model.Item;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("cozinhatela.fxml")
public class CozinhaController {

    @FXML
    private ListView<GridPane> listViewPedidos;

    @Autowired
    private ItemController controller;

    private List<Item> listItems;

    @FXML
    void initialize() {
        carregaDados();
    }

    @FXML
    void handleDados(MouseEvent event) {
        listViewPedidos.getItems().clear();
        carregaDados();
    }


    private void carregaDados() {
        listItems = controller.findAll();

        if (!listItems.isEmpty()) {
            for (int i = 0; i < listItems.size(); i++) {
                GridPane grid = new GridPane();
                Label product = new Label();
                Label description = new Label();
                Label mesa = new Label();
                Label qtd = new Label();
                Label observacao = new Label();
                description.setWrapText(true);
                product.getStyleClass().add("grid-label-product");
                description.getStyleClass().add("grid-label-description");
                mesa.getStyleClass().add("grid-label-description");
                qtd.getStyleClass().add("grid-label-description");
                observacao.getStyleClass().add("grid-label-description");
                grid.getStyleClass().add("grid-pane");
                product.setText((i + 1) + " - " + listItems.get(i).getProduct().getName());
                description.setText("Ingredientes: " +
                        listItems.get(i).getProduct().getDescription());
                mesa.setText("Mesa " + listItems.get(i).getOrder().getTable());
                qtd.setText("Quantidade: " + listItems.get(i).getAmount());
                observacao.setText(
                        listItems.get(i).getObservation() != null ? "Observação: " +
                                listItems.get(i).getObservation()
                                : "");
                grid.add(product, 0, 0);
                grid.add(mesa, 0, 1);
                grid.add(qtd, 0, 2);
                grid.add(description, 0, 3);
                grid.add(observacao, 0, 4);

                listViewPedidos.getItems().add(grid);
            }
        }

    }

    public static AnchorPane setAnchorPane(AnchorPane pane) {

        try {
            FXMLLoader loader = new FXMLLoader(CozinhaController.class.getResource("cozinhatela.fxml"));
            loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
            AnchorPane a = loader.load();
            return a;
        } catch (IOException ex) {
            new IOException("Error " + ex.getMessage());
        }
        return pane;
    }
}
