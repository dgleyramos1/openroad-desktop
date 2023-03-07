package com.openroad.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.user.controller.AdminController;
import com.openroad.api.user.controller.dtos.UserDTO;
import com.openroad.api.user.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("login.fxml")
public class Login {
    @FXML
    private TextField inputPassword;

    @FXML
    private TextField inputUsername;

    @Autowired
    private AdminController controller;

    @FXML
    void handleLogin(MouseEvent event) {
        System.out.println("Username: " + inputUsername.getText() + " Password: " + inputPassword.getText());
        User exists = controller.findByUsername(inputUsername.getText());
        if (exists != null) {
            System.out.println("Usuário existe");
        }

    }

    @FXML
    public void initialize() {
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
    }
}
