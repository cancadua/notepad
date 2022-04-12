package sample.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import sample.Main;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class NaviController implements Initializable {

    @FXML
    public Label dateTime;

    @FXML
    public Label username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClock();
        username.setText(Main.username);
    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    @FXML
    private void list() throws IOException {
        Main.setRoot("list");
    }

    @FXML
    private void home() throws IOException {
        Main.setRoot("note");
    }

    @FXML
    private void createNote() throws IOException {
        Main.setRoot("creation");
    }

    @FXML
    private void logout() throws IOException {
        Main.setRoot("login");
    }
}
