package com.openroad.controller.product;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.catalog.product.controller.ProductController;
import com.openroad.api.catalog.product.controller.dtos.ProductDTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("product.fxml")
public class ProductPaneController {

    @FXML
    private TableColumn<ProductDTO, LocalDate> tableViewColumnCreatedAt;

    @FXML
    private TableColumn<ProductDTO, String> tableViewColumnDescription;

    @FXML
    private TableColumn<ProductDTO, String> tableViewColumnName;

    @FXML
    private TableColumn<ProductDTO, String> tableViewColumnPrice;

    @FXML
    private TableColumn<ProductDTO, LocalDate> tableViewColumnUpdatedAt;

    @FXML
    private TableView<ProductDTO> tableViewProducts;

    @Autowired
    private ProductController controller;

    private List<ProductDTO> list = new ArrayList<>();
    private ObservableList<ProductDTO> observableList;

    @FXML
    void handleAdicionarProduto(MouseEvent event) {

    }

    @FXML
    void handleDeletarProduto(MouseEvent event) {

    }

    @FXML
    void handleEditarProduto(MouseEvent event) {

    }

    private void carregarTableView() {
        tableViewColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableViewColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableViewColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableViewColumnCreatedAt.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        tableViewColumnUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updated_at"));

        list = controller.listProducts();
        observableList = FXCollections.observableArrayList(list);

        tableViewProducts.setItems(observableList);
    }

    @FXML
    void initialize() {
        carregarTableView();
    }

    public static AnchorPane setAnchorPane(AnchorPane pane) {

        try {
            FXMLLoader loader = new FXMLLoader(ProductPaneController.class.getResource("product.fxml"));
            loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
            AnchorPane a = loader.load();
            return a;
        } catch (IOException ex) {
            new IOException("Error " + ex.getMessage());
        }
        return pane;
    }
}
