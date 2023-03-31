package com.openroad.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.user.controller.AdminController;
import com.openroad.api.user.controller.dtos.UserCreateDTO;
import com.openroad.api.user.controller.dtos.UserDTO;
import com.openroad.controller.user.UserController;
import com.openroad.utils.AlertDialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    Alert alert;
    private AlertDialog dialog = new AlertDialog();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    void handleDeletarAtendente(MouseEvent event) {
        var user = tableViewUsers.getSelectionModel().getSelectedItem();
        if (user == null) {
            dialog.alert(alert, AlertType.ERROR, "Alerta de erro!", "Selecione usuário",
                    "Por favor, você precisa selecionar um usuário!");
            return;
        }
        if (adminController.findAll().size() == 1) {
            dialog.alert(alert, AlertType.WARNING, "Alerta de perigo", "VOcê não pode excluir",
                    "É necessario ter pelo menos um usuário");
            return;
        }
        adminController.delete(user.getId());
        carregarTabViewAtendentes();
    }

    @FXML
    void handleEditarAtendente(MouseEvent event) throws IOException {
        UserDTO userDTO = tableViewUsers.getSelectionModel().getSelectedItem();
        if (userDTO == null) {
            dialog.alert(alert, AlertType.ERROR, "Alerta de erro!", "Selecione usuário",
                    "Você precisa selecionar um usuário na tabela");
            return;
        }
        UserCreateDTO user = new UserCreateDTO();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        Boolean confirmedButton = showFXMLUsuario(user);
        if (confirmedButton) {
            adminController.update(userDTO.getId(), user);
            carregarTabViewAtendentes();
        }
    }

    @FXML
    void handleAdicionarAtendente(MouseEvent event) throws IOException {
        UserCreateDTO user = new UserCreateDTO();
        Boolean confirmedButton = showFXMLUsuario(user);
        if (confirmedButton) {
            adminController.create(user);
            carregarTabViewAtendentes();
        }
    }

    private Boolean showFXMLUsuario(UserCreateDTO user) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UserController.class.getResource("user.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de usuários");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        // Setando o cliente no Controller.
        UserController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setUserCreateDTO(user);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.getIsButtonConfirmedClicked();

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

        alert = new Alert(AlertType.NONE);
        alert.initStyle(StageStyle.UNIFIED);
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
