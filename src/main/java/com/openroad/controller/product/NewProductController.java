package com.openroad.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.api.catalog.category.controller.CategoryController;
import com.openroad.api.catalog.category.controller.dtos.CategoryDTO;
import com.openroad.api.catalog.product.controller.dtos.ProductDTO;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("newProduct.fxml")
public class NewProductController {

    @FXML
    private TextField inputDescriptionProduct;

    @FXML
    private TextField inputNameProduct;

    @FXML
    private TextField inputPriceProduct;

    @FXML
    private SplitMenuButton selectCategory;

    private ProductDTO productDTO;

    private Boolean isButtonConfirmedClicked = false;

    private Stage dialogStage;

    private CategoryDTO categoryDTO;

    private List<CategoryDTO> categoryDTOList;
    private ObservableList<CategoryDTO> observableList;

    @Autowired
    private CategoryController controller;

    @FXML
    void handleCanceleProduct(MouseEvent event) {

    }

    @FXML
    void handleNewProduct(MouseEvent event) {

    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public Boolean getIsButtonConfirmedClicked() {
        return isButtonConfirmedClicked;
    }

    public void setIsButtonConfirmedClicked(Boolean isButtonConfirmedClicked) {
        this.isButtonConfirmedClicked = isButtonConfirmedClicked;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
