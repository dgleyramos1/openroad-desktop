package com.openroad.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.user.controller.AdminController;
import com.openroad.api.user.exception.UserNotFoundException;
import com.openroad.api.user.exception.UsernameNotFoundException;
import com.openroad.api.user.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.bytebuddy.asm.Advice.Thrown;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("login.fxml")
public class Login {
    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AdminController controller;

    private static Stage s;

    Alert a;
    DialogPane dialogPane;

    @FXML
    void handleLogin(MouseEvent event) throws IOException {
        User exists = controller.findByUsername(inputUsername.getText());
        if (exists == null) {
            a.setAlertType(AlertType.ERROR);
            a.setTitle("Alerta de erro!");
            a.setHeaderText("Usuário não existe");
            a.setContentText("Verifique se passou o usuário correto!");
            a.show();
            return;
        }
        var autenticado = encoder.matches(inputPassword.getText(), exists.getPassword());
        if (!autenticado) {
            a.setAlertType(AlertType.ERROR);
            a.setTitle("Alerta de erro!");
            a.setHeaderText("Usuário e senha inválidos");
            a.setContentText("Verifique se passou o usuário e senha corretos!");

            a.show();
            return;
        }
        s.close();
        PrincipalController.loadView();
    }

    @FXML
    public void initialize() {
        a = new Alert(AlertType.NONE);
        a.initStyle(StageStyle.UNIFIED);
        dialogPane = a.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("../styles/myDialog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
    }

    public static void loadLogin(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Login.class.getResource("login.fxml"));
        loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Open Road - Login");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNIFIED);
        stage.show();
        s = stage;
    }
}
