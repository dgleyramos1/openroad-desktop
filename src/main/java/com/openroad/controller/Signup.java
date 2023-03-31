package com.openroad.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.user.controller.AdminController;
import com.openroad.api.user.controller.dtos.UserCreateDTO;
import com.openroad.utils.AlertDialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("signup.fxml")
public class Signup {

    @FXML
    private TextField inputName;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;

    private static Stage s;

    @Autowired
    private AdminController controller;

    Alert a;
    private AlertDialog dialog = new AlertDialog();

    @FXML
    void initialize() {
        a = new Alert(AlertType.NONE);
        a.initStyle(StageStyle.UNIFIED);
    }

    @FXML
    void handleSignup(MouseEvent event) throws IOException {
        if (inputName.getText().isEmpty() || inputPassword.getText().isEmpty() || inputUsername.getText().isEmpty()) {
            dialog.alert(a, AlertType.ERROR, "Alerta de erro!", "Preencha os campos",
                    "Todos os campos são obrigatórios!");
            return;
        }
        UserCreateDTO user = new UserCreateDTO();
        user.setName(inputName.getText());
        user.setUsername(inputUsername.getText());
        user.setPassword(inputPassword.getText());
        controller.create(user);
        s.close();
        PrincipalController.loadView();
    }

    public static void signup(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Signup.class.getResource("signup.fxml"));
        loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Open Road - Sign-up");
        stage.setResizable(false);
        stage.show();
        s = stage;
    }
}
