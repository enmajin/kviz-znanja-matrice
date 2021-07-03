package org.kviz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        URL url = getClass().getClassLoader().getResource("view/scene.fxml");
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles.css").toExternalForm());

        stage.setTitle("Kviz znanja - matrice");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
