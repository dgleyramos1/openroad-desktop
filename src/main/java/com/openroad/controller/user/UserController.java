package com.openroad.controller.user;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import com.openroad.api.user.controller.dtos.UserCreateDTO;
import com.openroad.utils.AlertDialog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("user.fxml")
public class UserController implements Initializable {
    @FXML
    private TextField inputAtedenteNome;

    @FXML
    private PasswordField inputAtedenteSenha;

    @FXML
    private TextField inputAtedenteUsuario;

    private UserCreateDTO userCreateDTO;
    private Boolean isButtonConfirmedClicked = false;
    private Stage dialogStage;

    Alert a;
    private AlertDialog dialog = new AlertDialog();

    private static Stage s;

    @FXML
    void handleUsuarioCancelar(MouseEvent event) {
        dialogStage.close();
    }

    @FXML
    void handleUsuarioSalvar(MouseEvent event) {
        if (inputAtedenteNome.getText().isEmpty() || inputAtedenteUsuario.getText().isEmpty()
                || inputAtedenteSenha.getText().isEmpty()) {
            dialog.alert(a, AlertType.ERROR, "Alerta de erro", "Campos obrigatórios",
                    "Por favor, preencha todos os campos!");
            return;
        }
        userCreateDTO.setName(inputAtedenteNome.getText());
        userCreateDTO.setUsername(inputAtedenteUsuario.getText());
        userCreateDTO.setPassword(inputAtedenteSenha.getText());

        dialogStage.close();
        isButtonConfirmedClicked = true;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        a = new Alert(AlertType.NONE);
        a.initStyle(StageStyle.UTILITY);
    }

    public UserCreateDTO getUserCreateDTO() {
        return userCreateDTO;
    }

    public void setUserCreateDTO(UserCreateDTO userCreateDTO) {
        this.userCreateDTO = userCreateDTO;
        if (userCreateDTO != null) {
            inputAtedenteNome.setText(userCreateDTO.getName());
            inputAtedenteUsuario.setText(userCreateDTO.getUsername());
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
