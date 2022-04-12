package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.controllers.ContentController;
import sample.model.Note;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application {


    public static String username;
    private static Scene scene;

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/sample/resources/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setRootArg(String fxml, Note note) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/sample/resources/" + fxml + ".fxml"));
        scene.setRoot(fxmlLoader.load());
        ContentController controller = fxmlLoader.getController();
        controller.initData(note);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}