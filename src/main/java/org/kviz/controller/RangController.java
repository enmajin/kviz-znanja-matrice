package org.kviz.controller;

import javafx.fxml.Initializable;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/rang.fxml")
public class RangController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
