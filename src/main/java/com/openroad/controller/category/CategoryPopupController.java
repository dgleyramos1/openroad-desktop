package com.openroad.controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.api.catalog.category.controller.CategoryController;
import com.openroad.api.catalog.category.controller.dtos.CategoryDTO;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("categoryPopup.fxml")
public class CategoryPopupController {

    @FXML
    private TextField inputCategoryName;

    private CategoryDTO categoryDTO;

    private Boolean isButtonConfirmedClicked = false;
    private Stage dialogStage;

    @Autowired
    private CategoryController controller;

    public void show() {
        dialogStage.showAndWait();
    }

    @FXML
    void handleCategoryDelete(MouseEvent event) {
        controller.delete(categoryDTO.getId());
        dialogStage.close();
    }

    @FXML
    void handleCategorySave(MouseEvent event) {
        categoryDTO.setName(inputCategoryName.getText());
        dialogStage.close();
        isButtonConfirmedClicked = true;
    }

    public CategoryDTO getCategoryCreateDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
        if (categoryDTO != null) {
            inputCategoryName.setText(categoryDTO.getName());
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
}
