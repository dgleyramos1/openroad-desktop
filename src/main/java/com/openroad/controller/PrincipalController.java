package com.openroad.controller;

import org.springframework.stereotype.Service;

import com.openroad.api.user.controller.dtos.UserDTO;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.rgielen.fxweaver.core.FxmlView;

@Service
@FxmlView("viewPrincipal.fxml")
public class PrincipalController {
    @FXML
    private TableColumn<UserDTO, String> tableColumnUserCreatedAt;

    @FXML
    private TableColumn<UserDTO, String> tableColumnUserName;

    @FXML
    private TableColumn<UserDTO, String> tableColumnUserUpdatedAt;

    @FXML
    private TableColumn<UserDTO, String> tableColumnUserUsername;

    @FXML
    private TableView<UserDTO> tableViewUsers;

    public PrincipalController() {
    }

}
