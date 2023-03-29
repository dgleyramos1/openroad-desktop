package com.openroad.controller.user;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import com.openroad.api.user.controller.dtos.UserCreateDTO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("criarNovoUser.fxml")
public class NovoUserController implements Initializable {
    @FXML
    private TextField inputNovaAtedenteNome;

    @FXML
    private PasswordField inputNovaAtedenteSenha;

    @FXML
    private TextField inputNovaAtedenteUsuario;

    private UserCreateDTO userCreateDTO;
    private Boolean isButtonConfirmedClicked = false;
    private Stage dialogStage;

    Alert a;
    DialogPane dialogPane;

    private static Stage s;

    @FXML
    void handleNovoUsuarioCancelar(MouseEvent event) {
        dialogStage.close();
    }

    @FXML
    void handleNovoUsuarioSalvar(MouseEvent event) {
        userCreateDTO.setName(inputNovaAtedenteNome.getText());
        userCreateDTO.setUsername(inputNovaAtedenteUsuario.getText());
        userCreateDTO.setPassword(inputNovaAtedenteSenha.getText());

        dialogStage.close();
        isButtonConfirmedClicked = true;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        a = new Alert(AlertType.NONE);
        a.initStyle(StageStyle.UNIFIED);
        dialogPane = a.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("../../styles/myDialog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
    }

    public UserCreateDTO getUserCreateDTO() {
        return userCreateDTO;
    }

    public void setUserCreateDTO(UserCreateDTO userCreateDTO) {
        this.userCreateDTO = userCreateDTO;
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
