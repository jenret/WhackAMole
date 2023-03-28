package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    int i = 1;
    @FXML
    private Label number;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        number.setText(Integer.toString(i++));
        //ChangeScene.changeScene(event, "whackBoard.fxml");
    }
}