package com.openroad.controller.product;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.catalog.category.controller.CategoryController;
import com.openroad.api.catalog.category.controller.dtos.CategoryDTO;
import com.openroad.api.catalog.product.controller.ProductController;
import com.openroad.api.catalog.product.controller.dtos.ProductCreateDTO;
import com.openroad.api.catalog.product.controller.dtos.ProductDTO;
import com.openroad.api.catalog.product.service.ProductService;
import com.openroad.utils.AlertDialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private ProductService service;

    private List<ProductDTO> list = new ArrayList<>();
    private ObservableList<ProductDTO> observableList;
    private List<CategoryDTO> listCategory;

    private CategoryDTO categoryDTOSelected;

    private CategoryDTO category;

    Stage dialogStage;

    Alert a;
    private AlertDialog dialog = new AlertDialog();

    @FXML
    void handleAdicionarProduto(MouseEvent event) {
        ProductCreateDTO productDTO = new ProductCreateDTO();
        try {
            Boolean confirmedButton = showFXMLNewProduct(productDTO);
            if (confirmedButton) {
                controller.create(categoryDTOSelected.getId(), productDTO);
                carregarTableView();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleDeletarProduto(MouseEvent event) {
        ProductDTO selectedProduct = tableViewProducts.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            dialog.alert(a, AlertType.ERROR, "Alerta de erro", "Seleicone um produto",
                    "Por favor, selecione um produto para poder deleta-lo!");
            return;
        }
        controller.delete(selectedProduct.getId());
        carregarTableView();
    }

    @FXML
    void handleEditarProduto(MouseEvent event) {
        ProductDTO selectedProduct = tableViewProducts.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            dialog.alert(a, AlertType.WARNING, "Cuidado!", "", "Selecione um produto da tabela");
            return;
        }
        category = categoryController
                .categoryById(service.seletedProduct(selectedProduct.getId()).getCategory().getId());

        System.out.println("ID da categoria do produto " + category.getId());

        CategoryDTO newCategoryDTOOrNo;
        try {
            Boolean confirmedButton = showFXMLEditeProduct(selectedProduct);
            if (confirmedButton) {

                if (categoryDTOSelected != null) {
                    newCategoryDTOOrNo = categoryDTOSelected;
                } else {
                    newCategoryDTOOrNo = category;
                }
                controller.update(selectedProduct.getId(), newCategoryDTOOrNo.getId(), selectedProduct);
                carregarTableView();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void carregarTableView() {
        tableViewColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableViewColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableViewColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableViewColumnCreatedAt.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        tableViewColumnUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updated_at"));

        list = controller.listProducts();
        listCategory = categoryController.listCategories();
        observableList = FXCollections.observableArrayList(list);

        tableViewProducts.setItems(observableList);
    }

    @FXML
    void initialize() {
        carregarTableView();
        a = new Alert(AlertType.NONE);
        a.initStyle(StageStyle.UNIFIED);
        dialogStage = new Stage();
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

    private Boolean showFXMLNewProduct(ProductCreateDTO productDTO) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProductNewController.class.getResource("newproduct.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)

        dialogStage.setTitle("Produto");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        // Setando o cliente no Controller.
        ProductNewController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProductDTO(productDTO);
        controller.setCategoryDTOList(listCategory);
        dialogStage.showAndWait();

        categoryDTOSelected = controller.getCategoryDTO();
        controller.setCategoryDTO(category);
        return controller.getIsButtonConfirmedClicked();
    }

    private Boolean showFXMLEditeProduct(ProductDTO productDTO) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProductEditeController.class.getResource("editeproduct.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)

        dialogStage.setTitle("Produto");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        // Setando o cliente no Controller.
        ProductEditeController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProductDTO(productDTO);
        controller.setCategoryDTOList(listCategory);
        controller.setCategoryDTO(category);
        categoryDTOSelected = controller.getCategorySelected();
        dialogStage.showAndWait();

        return controller.getIsButtonConfirmedClicked();
    }

}
