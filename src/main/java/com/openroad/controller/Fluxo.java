package com.openroad.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openroad.ApplicationFX;
import com.openroad.api.user.controller.AdminController;
import com.openroad.api.user.controller.dtos.UserDTO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("fluxo.fxml")
public class Fluxo {

    @Autowired
    private AdminController controller;

    private static List<UserDTO> list;

    private static Stage s;

    @FXML
    void initialize() throws IOException {
        list = controller.findAll();
    }

    public static void load(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Fluxo.class.getResource("fluxo.fxml"));
        loader.setControllerFactory(ApplicationFX.getContextSpring()::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Open Road");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNIFIED);
        stage.getIcons().add(new Image(Fluxo.class.getResourceAsStream("../img/software.png")));

        stage.show();
        s = stage;
        s.close();
        loginOrSignup(stage);
    }

    private static void loginOrSignup(Stage stage) throws IOException {
        if (list.isEmpty()) {
            Signup.signup(stage);
            return;
        }
        Login.loadLogin(stage);
    }
}
