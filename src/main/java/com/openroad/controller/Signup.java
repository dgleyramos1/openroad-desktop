package com.openroad.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @FXML
    void handleSignup(MouseEvent event) {

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
