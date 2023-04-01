package com.openroad.controller.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.api.catalog.category.controller.CategoryController;
import com.openroad.api.catalog.category.controller.dtos.CategoryDTO;
import com.openroad.api.catalog.product.controller.dtos.ProductCreateDTO;
import com.openroad.utils.AlertDialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("newproduct.fxml")
public class ProductNewController {

    @FXML
    private TextField inputDescriptionProduct;

    @FXML
    private TextField inputNameProduct;

    @FXML
    private TextField inputPriceProduct;

    private ProductCreateDTO productDTO;

    private Boolean isButtonConfirmedClicked = false;

    private Stage dialogStage;

    private CategoryDTO categoryDTO;

    private List<CategoryDTO> categoryDTOList;

    @FXML
    private ComboBox<String> selectCategory;

    Alert a;
    private AlertDialog dialog = new AlertDialog();

    @FXML
    void initialize() {
        a = new Alert(AlertType.NONE);
        a.initStyle(StageStyle.UNIFIED);
        selectCategory.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            handleCategorySelected(categoryDTOList.get((int) newValue));
        });
    }

    private void carregaCategorias() {
        categoryDTOList.forEach((category) -> {
            selectCategory.getItems().add(category.getName());
        });
    }

    private void handleCategorySelected(CategoryDTO category) {
        categoryDTO = category;
    }

    @FXML
    void handleCanceleProduct(MouseEvent event) {
        dialogStage.close();
    }

    @FXML
    void handleNewProduct(MouseEvent event) {
        if (inputNameProduct.getText().isEmpty() || inputDescriptionProduct.getText().isEmpty()
                || inputPriceProduct.getText().isEmpty() || selectCategory.getPromptText().equals("Categoria")) {
            dialog.alert(a, AlertType.ERROR, "Alerta de erro", "Campos obrigatórios",
                    "Todos os campos são obrigatórios, por favor preencha-os!");
            return;
        }
        if (inputPriceProduct.getText().matches("^[a-zA-Z]*\\[@!#$%^&*()/\\]")) {
            dialog.alert(a, AlertType.WARNING, "Cuidado!", "", "Não informe letras ou carateres especiais!");
            return;
        }
        productDTO.setDescription(inputDescriptionProduct.getText());
        productDTO.setName(inputNameProduct.getText());
        productDTO.setPrice(Double.parseDouble(inputPriceProduct.getText()));
        isButtonConfirmedClicked = true;
        dialogStage.close();
    }

    public ProductCreateDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductCreateDTO productDTO) {
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

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTOList(List<CategoryDTO> list) {
        this.categoryDTOList = list;
        carregaCategorias();
    }
}
