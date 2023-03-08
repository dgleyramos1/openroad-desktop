package com.openroad.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.user.controller.AdminController;
import com.openroad.api.user.controller.dtos.UserDTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("atendentes.fxml")
public class AtendenteController {

    @FXML
    private Label detailLabelCreatedAt;

    @FXML
    private Label detailLabelNome;

    @FXML
    private Label detailLabelUpdatedAt;

    @FXML
    private Label detailLabelUser;

    @FXML
    private TableColumn<UserDTO, String> tableViewColumnName;

    @FXML
    private TableColumn<UserDTO, String> tableViewColumnUser;
    @FXML
    private TableView<UserDTO> tableViewUsers;
    private List<UserDTO> userList;

    private ObservableList<UserDTO> observableList;

    @Autowired
    private AdminController adminController;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    void handleAdicionarDeletarAtendente(MouseEvent event) {

    }

    @FXML
    void handleAdicionarEditarAtendente(MouseEvent event) {

    }

    @FXML
    void handleAdicionarNovoAtendente(MouseEvent event) {

    }

    private void carregarTabViewAtendentes() {
        tableViewColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableViewColumnUser.setCellValueFactory(new PropertyValueFactory<>("username"));

        userList = adminController.findAll();

        observableList = FXCollections.observableArrayList(userList);
        tableViewUsers.setItems(observableList);
    }

    @FXML
    public void initialize() {
        carregarTabViewAtendentes();
        selecionarItemTableView(null);

        tableViewUsers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableView(newValue));

    }

    private void selecionarItemTableView(UserDTO userDTO) {
        if (userDTO != null) {
            detailLabelNome.setText(userDTO.getName());
            detailLabelUser.setText(userDTO.getUsername());
            detailLabelCreatedAt.setText(String.valueOf(userDTO.getCreated_at().format(dateTimeFormatter)));
            detailLabelUpdatedAt.setText(String.valueOf(userDTO.getUpdated_at().format(dateTimeFormatter)));
        } else {
            detailLabelNome.setText("");
            detailLabelUser.setText("");
            detailLabelCreatedAt.setText("");
            detailLabelUpdatedAt.setText("");
        }
    }

    public static AnchorPane setAnchorPane(AnchorPane pane) {

        try {
            FXMLLoader loader = new FXMLLoader(AtendenteController.class.getResource("atendentes.fxml"));
            loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
            AnchorPane a = loader.load();
            return a;
        } catch (IOException ex) {
            new IOException("Error " + ex.getMessage());
        }
        return pane;
    }

}
