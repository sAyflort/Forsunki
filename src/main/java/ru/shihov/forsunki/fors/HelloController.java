package ru.shihov.forsunki.fors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField textField;

    @FXML
    private TextField epsilon;

    @FXML
    private TextArea textArea;

    @FXML
    protected void onStartResult() {
        welcomeText.setText("Ввод границ области (x1,y1,x1,y2)\nПример (1,4,2,7.4), (6,2.12,4,7.4)...");
    }
}