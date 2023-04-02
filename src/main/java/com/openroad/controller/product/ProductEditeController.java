package com.openroad.controller.product;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.openroad.api.catalog.category.controller.dtos.CategoryDTO;
import com.openroad.api.catalog.product.controller.dtos.ProductDTO;
import com.openroad.utils.AlertDialog;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("editeproduct.fxml")
public class ProductEditeController {

    @FXML
    private TextArea inputDescriptionProduct;

    @FXML
    private TextField inputNameProduct;

    @FXML
    private TextField inputPriceProduct;

    private ProductDTO productDTO;

    private Boolean isButtonConfirmedClicked = false;

    private Stage dialogStage;

    private CategoryDTO categoryDTO;

    private List<CategoryDTO> categoryDTOList;

    @FXML
    private ComboBox<String> selectCategory;

    Alert a;
    private AlertDialog dialog = new AlertDialog();

    private CategoryDTO categorySelected;

    @FXML
    void initialize() {
        a = new Alert(AlertType.NONE);
        a.initStyle(StageStyle.UNIFIED);
        selectCategory.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            handleCategorySelected(categoryDTOList.get((int) newValue));
        });

    }

    private void carregaCategorias() {

        for (int i = 0; i < categoryDTOList.size(); i++) {
            selectCategory.getItems().add(i, categoryDTOList.get(i).getName());
        }

    }

    private void handleCategorySelected(CategoryDTO category) {
        setCategorySelected(category);
    }

    @FXML
    void handleCanceleProduct(MouseEvent event) {
        dialogStage.close();
    }

    @FXML
    void handleUpdateProduct(MouseEvent event) {
        if (inputNameProduct.getText().isEmpty() || inputDescriptionProduct.getText().isEmpty()
                || inputPriceProduct.getText().isEmpty()) {
            dialog.alert(a, AlertType.WARNING, "Cuidado", "",
                    "Todos os campos são obrigatórios!");
            return;
        }
        if (inputPriceProduct.getText().matches("^[a-zA-Z]*")) {
            dialog.alert(a, AlertType.WARNING, "Cuidado!", "", "Não informe letras ou carateres especiais!");
            return;
        }

        var valor = Double.parseDouble(inputPriceProduct.getText().replace(",", "."));
        productDTO.setDescription(inputDescriptionProduct.getText());
        productDTO.setName(inputNameProduct.getText());
        productDTO.setPrice(valor);
        isButtonConfirmedClicked = true;
        dialogStage.close();
    }

    public ProductDTO getProductDTO() {
        return this.productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
        if (productDTO != null) {
            inputNameProduct.setText(this.productDTO.getName());
            inputDescriptionProduct.setText(this.productDTO.getDescription());
            inputPriceProduct.setText(String.valueOf(this.productDTO.getPrice()));
        }
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
        return this.categoryDTO;
    }

    public void setCategoryDTOList(List<CategoryDTO> list) {
        this.categoryDTOList = list;
        carregaCategorias();
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
        selectCategory.getSelectionModel().select(categoryDTO.getName());
    }

    public CategoryDTO getCategorySelected() {
        return categorySelected;
    }

    public void setCategorySelected(CategoryDTO categorySelected) {
        this.categorySelected = categorySelected;
    }

}
