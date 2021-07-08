package org.kviz.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/main-scene.fxml")
public class FXMLController implements Initializable {
    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label.setText("Hello, JavaFX.");
    }
}
