package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.HtmlHandler;
import sample.HttpClientClass;
import sample.Main;
import sample.model.Note;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class NoteController implements Initializable {
    @FXML
    private Label date;

    @FXML
    private TableView<Note> list;

    private void init() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        date.setText(dtf.format(now));

        String[] cols = {"Nazwa", "Data wydarzenia"};
        ObservableList<Note> items = FXCollections.observableArrayList();

        if(Main.username.equals("OFFLINE")) {
            Note[] noteArr = HtmlHandler.getList("OFFLINE");
            if (noteArr != null){
                for (Note note: noteArr) {
                    if(note.getNoteDate().equals(dtf.format(now))){
                        items.add(note);
                    }
                }
            }
        } else {

            List<Note> noteList = HttpClientClass.httpClientGetAll(Main.username);
            if (noteList != null) {
                for (Note note : noteList) {
                    if (note.getNoteDate().equals(dtf.format(now))) {
                        items.add(note);
                    }
                }
            }
        }



        TableColumn<Note, String> column1 = new TableColumn<>(cols[0]);
        list.getColumns().add(column1);
        column1.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Note, String> column2 = new TableColumn<>(cols[1]);
        list.getColumns().add(column2);
        column2.setCellValueFactory(new PropertyValueFactory<>("noteDate"));


        list.getItems().addAll(items);
    }

    @FXML
    public void clickItem(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2)
        {
            Main.setRootArg("content", list.getSelectionModel().getSelectedItem());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle r) {
        init();
    }

}