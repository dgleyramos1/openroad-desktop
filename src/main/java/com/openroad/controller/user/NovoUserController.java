package com.openroad.controller.user;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

@Controller
public class NovoUserController {
    @FXML
    private TextField inputNovaAtedenteNome;

    @FXML
    private PasswordField inputNovaAtedenteSenha;

    @FXML
    private TextField inputNovaAtedenteUsuario;

    @FXML
    void handleNovoUsuarioCancelar(MouseEvent event) {

    }

    @FXML
    void handleNovoUsuarioSalvar(MouseEvent event) {

    }
}
