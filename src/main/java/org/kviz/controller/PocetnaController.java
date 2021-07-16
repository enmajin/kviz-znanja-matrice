package org.kviz.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.service.PocetnaService;
import org.kviz.util.Ekrani;
import org.kviz.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/pocetna.fxml")
public class PocetnaController implements Initializable {

    private final PocetnaService pocetnaService;

    @FXML
    private AnchorPane pocetnaAnchorPane;

    @Autowired
    public PocetnaController(PocetnaService pocetnaService) {
        this.pocetnaService = pocetnaService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User.korisnickoIme = "";
    }

    public void onProbniKviz(MouseEvent mouseEvent) {
        pocetnaService.promijeniEkran(pocetnaAnchorPane, Ekrani.PITANJE, null);
    }

    public void onRegistrirajSe(MouseEvent mouseEvent) {
        pocetnaService.promijeniEkran(pocetnaAnchorPane, Ekrani.REGISTRACIJA, null);
    }

    public void onLogIn(MouseEvent mouseEvent) {
        pocetnaService.promijeniEkran(pocetnaAnchorPane, Ekrani.LOGIN, null);
    }
}
