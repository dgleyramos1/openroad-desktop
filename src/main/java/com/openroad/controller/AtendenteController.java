package com.openroad.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.user.controller.AdminController;
import com.openroad.api.user.controller.dtos.UserDTO;
import com.openroad.api.user.model.User;
import com.openroad.api.user.service.UserService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("anchorPaneAtendentes.fxml")
public class AtendenteController {

    @FXML
    private TableColumn<UserDTO, String> tableColumnCreatedAt;
    @FXML
    private TableColumn<UserDTO, String> tableColumnName;
    @FXML
    private TableColumn<UserDTO, String> tableColumnUpdatedAt;
    @FXML
    private TableColumn<UserDTO, String> tableColumnUsername;
    @FXML
    private TableView<UserDTO> tableViewUsers;
    private List<UserDTO> userList;
    private ObservableList<UserDTO> observableList;

    @Autowired
    private AdminController adminController;

    @FXML
    void handleAdicionarNovoAtendente(ActionEvent event) {

    }

    private void carregarTabViewAtendentes() {
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableColumnCreatedAt.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        tableColumnUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updated_at"));

        userList = adminController.findAll();

        observableList = FXCollections.observableArrayList(userList);
        tableViewUsers.setItems(observableList);

    }

    @FXML
    public void initialize() {
        carregarTabViewAtendentes();
    }

    public static AnchorPane setAnchorPane(AnchorPane pane) {

        try {
            FXMLLoader loader = new FXMLLoader(AtendenteController.class.getResource("anchorPaneAtendentes.fxml"));
            loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
            AnchorPane a = loader.load();
            return a;
        } catch (IOException ex) {
            new IOException("Error " + ex.getMessage());
        }
        return pane;
    }

}
