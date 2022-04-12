package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.HtmlHandler;
import sample.HttpClientClass;
import sample.Main;
import sample.model.Note;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ContentController {
    Note note = new Note();

    @FXML
    private TextField title;

    @FXML
    private TextArea content;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private DatePicker noteDate;

    @FXML
    private Label noteDateText;

    @FXML
    public void editNote() throws IOException {
        if (edit.getText().equals("Edytuj")){
            title.setEditable(true);
            content.setEditable(true);
            edit.setText("Zapisz");
            delete.setText("Anuluj");
            noteDateText.setVisible(true);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(note.getNoteDate(), formatter);
            noteDate.setValue(localDate);
            noteDate.setVisible(true);
        } else if (edit.getText().equals("Zapisz")) {
            Alert alert;
            if (title.getText().equals("") || content.getText().equals("")) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Notatka nie została edytowana, uzupełnij tytuł, treść oraz datę!");
                alert.showAndWait();
            } else {
                note = new Note(note.getUser(), title.getText(), content.getText(),
                        noteDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), note.getId());
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacja");
                alert.setHeaderText("Notatka edytowana!");
                alert.showAndWait();
                if (!Main.username.equals("OFFLINE")){
                    HttpClientClass.httpClientPutNote(note);
                } else {
                    HtmlHandler.edit(note.getUser(), note.getId(), note);
                }
            }
            title.setEditable(false);
            content.setEditable(false);
            noteDateText.setVisible(false);
            noteDate.setVisible(false);
            edit.setText("Edytuj");
            delete.setText("Usuń");
        } else {
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText("Notatka usunięta!");
            alert.showAndWait();
            if (!Main.username.equals("OFFLINE")){
                HttpClientClass.httpClientDeleteNote (note.getUser(), note.getId());
            } else {
                HtmlHandler.deleteNote(note.getUser(), note.getId());
            }

            Main.setRoot("list");
        }

    }

    @FXML
    public void deleteNote() {
        if (delete.getText().equals("Usuń")) {
            edit.setText("Potwierdź");
            delete.setText("Anuluj");
        } else if (delete.getText().equals("Anuluj")) {
            title.setText(note.getTitle());
            content.setText(note.getContent());
            edit.setText("Edytuj");
            delete.setText("Usuń");
            title.setEditable(false);
            content.setEditable(false);
            noteDateText.setVisible(false);
            noteDate.setVisible(false);
        }
    }


    public void initData(Note note_) {
        this.note = note_;
        title.setText(note.getTitle());
        content.setText(note.getContent());
        title.setEditable(false);
        content.setEditable(false);
        noteDateText.setVisible(false);
        noteDate.setVisible(false);
    }

}
