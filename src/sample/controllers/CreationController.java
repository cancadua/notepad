package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.HtmlHandler;
import sample.HttpClientClass;
import sample.Main;
import sample.model.Note;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreationController implements Initializable {
    @FXML
    private TextField title;
    @FXML
    private TextArea content;
    @FXML
    private DatePicker noteDate;
    @FXML
    private Button saveLocal;
    @FXML
    private Button saveToServer;

    @FXML
    public void saveLocal() {
        Alert alert;
        System.out.println(noteDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if (title.getText().equals("") || content.getText().equals("") ||
                noteDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals("")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Notatka nie została zapisana, uzupełnij tytuł, treść oraz datę!");
        } else {
            Note note = new Note(Main.username, title.getText(), content.getText(),
                    noteDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            HtmlHandler.save(note);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText("Notatka zapisana!");
        }
        alert.showAndWait();
    }

    @FXML
    public void saveToCloud() {
        Alert alert;
        if (title.getText().equals("") || content.getText().equals("")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Notatka nie została zapisana, uzupełnij tytuł, treść oraz datę!");
            alert.showAndWait();
        } else {
            Note note = new Note(Main.username, title.getText(), content.getText(),
                    noteDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText("Notatka zapisana!");
            alert.showAndWait();
            HttpClientClass.httpClientPostNote(note);
        }
    }

    @FXML
    public void initialize (URL url, ResourceBundle rb) {
        if (Main.username.equals("OFFLINE")){
            saveToServer.setDisable(true);
        } else {
            saveLocal.setDisable(true);
        }
        noteDate.setValue(LocalDate.now());
    }

}
