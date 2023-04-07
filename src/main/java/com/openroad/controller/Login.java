package com.openroad.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.user.controller.AdminController;
import com.openroad.api.user.model.User;
import com.openroad.utils.AlertDialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private AlertDialog dialog = new AlertDialog();

    @FXML
    void handleLogin(MouseEvent event) throws IOException {
        User exists = controller.findByUsername(inputUsername.getText());
        if (exists == null) {
            dialog.alert(a, AlertType.ERROR, "Alerta de erro!", "Usuário não existe",
                    "Verifique se passou o usuário correto!");
            return;
        }
        var autenticado = encoder.matches(inputPassword.getText(), exists.getPassword());
        if (!autenticado) {
            dialog.alert(a, AlertType.ERROR, "Alerta de erro!", "Usuário e senha inválidos",
                    "Verifique se passou o usuário e senha corretos!");
            return;
        }
        s.close();
        PrincipalController.loadView();
    }

    @FXML
    public void initialize() {
        a = new Alert(AlertType.NONE);
        a.initStyle(StageStyle.UNIFIED);
    }

    public static void loadLogin(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Login.class.getResource("login.fxml"));
        loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("OpenRoad - Login");
        stage.setResizable(false);
        stage.show();
        s = stage;
    }

}
