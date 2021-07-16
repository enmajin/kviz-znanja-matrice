package org.kviz.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.service.PocetnaKorisnikService;
import org.kviz.util.Ekrani;
import org.kviz.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/pocetnakorisnik.fxml")
public class PocetnaKorisnikController implements Initializable {

    private final PocetnaKorisnikService pocetnakorisnikService;

    @FXML
    private AnchorPane pocetnakorisnikAnchorPane;
    @FXML
    private Text korisnikMessage;

    @Autowired
    public PocetnaKorisnikController(PocetnaKorisnikService pocetnakorisnikService) {
        this.pocetnakorisnikService = pocetnakorisnikService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        korisnikMessage.setText(User.korisnickoIme);
    }

    public void onKviz(MouseEvent mouseEvent) {
        pocetnakorisnikService.promijeniEkran(pocetnakorisnikAnchorPane, Ekrani.PITANJE, null);
    }

    public void onLogOut(MouseEvent mouseEvent) {
        User.odjavi();
        pocetnakorisnikService.promijeniEkran(pocetnakorisnikAnchorPane, Ekrani.POCETNA, null);
    }

    public void onStupcastiGraf(MouseEvent mouseEvent) {
        pocetnakorisnikService.promijeniEkran(pocetnakorisnikAnchorPane, Ekrani.HISTOGRAM, null);
    }
}
