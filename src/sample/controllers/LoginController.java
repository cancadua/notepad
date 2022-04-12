package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.HttpClientClass;
import sample.Main;
import sample.model.User;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField loginText;
    @FXML
    public Button loginButton;
    @FXML
    public AnchorPane bar;
    @FXML
    public PasswordField passText;
    @FXML
    public Button offlineButton;
    @FXML
    public Button registerButton;


    @FXML
    private void offline() throws IOException {
        Main.username = "OFFLINE";
        Main.setRoot("note");
    }

    @FXML
    private void register() throws IOException {
        if (loginText.getText().equals("") || passText.getText().equals("")) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Uzupełnij nazwę użytkownika i hasło!");
            alert.showAndWait();
        } else if (HttpClientClass.httpClientRegister(new User(loginText.getText(), passText.getText())) == 200) {
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rejestracja");
            alert.setHeaderText("Zostałeś zarejestrowany!");
            alert.showAndWait();
        } else {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Błąd rejestracji, upewnij się, że nie masz już konta!");
            alert.showAndWait();
        }
    }

    @FXML
    private void login() throws IOException {
        if (loginText.getText().equals("") || passText.getText().equals("")) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Uzupełnij nazwę użytkownika i hasło!");
            alert.showAndWait();
        } else if (HttpClientClass.httpClientPostAuth(new User(loginText.getText(), passText.getText())) == 200) {
            Main.username = loginText.getText();
            Main.setRoot("note");
        } else {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Zła nazwa użytkownika lub hasło, aby utworzyć konto uzupełnij nazwę użytkownika " +
                    "oraz hasło i użyj przycisku Zarejestruj!");
            alert.showAndWait();
        }


    }
}
