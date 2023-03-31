package com.openroad.controller.category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.catalog.category.controller.CategoryController;
import com.openroad.api.catalog.category.controller.dtos.CategoryDTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("category.fxml")
public class CategoryPaneController {

    @FXML
    private TextField inputCategoryName;

    @FXML
    private TableColumn<CategoryDTO, String> tableColumnCreatedAt;

    @FXML
    private TableColumn<CategoryDTO, String> tableColumnName;

    @FXML
    private TableColumn<CategoryDTO, Integer> tableColumnProducts;

    @FXML
    private TableColumn<CategoryDTO, String> tableColumnUpdatedAt;

    @FXML
    private TableView<CategoryDTO> tableViewCategory;

    @Autowired
    private CategoryController controller;

    private List<CategoryDTO> list;
    private ObservableList<CategoryDTO> observableList;

    @FXML
    void handleNewCategory(MouseEvent event) {
        if (inputCategoryName.getText().isEmpty()) {
            return;
        }

        controller.createCategory(inputCategoryName.getText());
        carregarTabViewCategories();
        inputCategoryName.setText("");
    }

    @FXML
    void initialize() {
        carregarTabViewCategories();
    }

    private void carregarTabViewCategories() {
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnCreatedAt.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        tableColumnUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updated_at"));
        tableColumnProducts.setCellValueFactory(new PropertyValueFactory<>("qtdProducts"));

        list = controller.listCategories();

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setQtdProducts(list.get(i).getProducts().size());
        }
        observableList = FXCollections.observableArrayList(list);

        tableViewCategory.setItems(observableList);
    }

    public static AnchorPane setAnchorPane(AnchorPane pane) {

        try {
            FXMLLoader loader = new FXMLLoader(CategoryPaneController.class.getResource("category.fxml"));
            loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
            AnchorPane a = loader.load();
            return a;
        } catch (IOException ex) {
            new IOException("Error " + ex.getMessage());
        }
        return pane;
    }
}
