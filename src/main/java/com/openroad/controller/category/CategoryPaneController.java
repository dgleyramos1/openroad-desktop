package com.openroad.controller.category;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.catalog.category.controller.CategoryController;
import com.openroad.api.catalog.category.controller.dtos.CategoryDTO;
import com.openroad.utils.AlertDialog;

import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
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

    private List<CategoryDTO> list = new ArrayList<>();
    private ObservableList<CategoryDTO> observableList;
    Stage dialogStage;

    Alert a;
    private AlertDialog dialog = new AlertDialog();

    @FXML
    void handleNewCategory(MouseEvent event) {
        if (inputCategoryName.getText().isEmpty()) {
            dialog.alert(a, AlertType.ERROR, "Alerta de erro!", "Campo obrigatório",
                    "Por favor, preencha todos os campos!");
            return;
        }
        controller.createCategory(inputCategoryName.getText());
        carregarTabViewCategories();
        inputCategoryName.setText("");
    }

    @FXML
    void initialize() {
        carregarTabViewCategories();
        a = new Alert(AlertType.NONE);
        a.initStyle(StageStyle.UNIFIED);
        dialogStage = new Stage();

        tableViewCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selecionarCategoria(observable.getValue());
        });

        dialogStage.setOnCloseRequest(event -> {
            if (dialogStage.isShowing()) {
                event.consume();
                dialogStage.hide();
            }
        });

    }

    private void selecionarCategoria(CategoryDTO dto) {
        try {
            CategoryPopupController controllerCategory = showFXMLCategory(dto);
            if (controllerCategory.getIsButtonConfirmedClicked()) {
                if (controllerCategory.getIsDelete()) {
                    controller.delete(dto.getId());
                    carregarTabViewCategories();
                    return;
                }
                controller.update(dto.getId(), dto);
                return;
            }
            carregarTabViewCategories();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void carregarTabViewCategories() {
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnCreatedAt.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        tableColumnUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updated_at"));

        list = controller.listCategories();
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

    private CategoryPopupController showFXMLCategory(CategoryDTO category) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CategoryPopupController.class.getResource("categoryPopup.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)

        dialogStage.setTitle("Categoria");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        // Setando o cliente no Controller.
        CategoryPopupController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCategoryDTO(category);
        dialogStage.showAndWait();

        return controller;
    }
}
