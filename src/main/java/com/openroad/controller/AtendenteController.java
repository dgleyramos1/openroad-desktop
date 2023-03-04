package com.openroad.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openroad.api.user.controller.AdminController;
import com.openroad.api.user.controller.dtos.UserDTO;
import com.openroad.api.user.service.UserService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;

@Component
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
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory<>("Usuário"));
        tableColumnCreatedAt.setCellValueFactory(new PropertyValueFactory<>("Data criação"));
        tableColumnUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("Data atualização"));

        // userList = adminController.findAll();

        // observableList = FXCollections.observableArrayList(userList);
        // tableViewUsers.setItems(observableList);

    }

    @FXML
    public void initialize() {
        System.out.println(adminController.findAll());
        carregarTabViewAtendentes();
    }

}
