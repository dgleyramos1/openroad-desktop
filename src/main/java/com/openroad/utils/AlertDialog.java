package com.openroad.utils;

import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public class AlertDialog {

    DialogPane dialogPane;

    public void alert(Alert a, AlertType type, String title, String header, String content) {
        dialogPane = a.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../styles/myDialog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        a.setAlertType(type);
        a.setTitle(title);
        a.setHeaderText(header);
        a.setContentText(content);
        a.show();
    }
}
