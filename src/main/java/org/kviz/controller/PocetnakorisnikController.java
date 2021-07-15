package org.kviz.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.service.PocetnakorisnikService;
import org.kviz.util.Ekrani;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/pocetnakorisnik.fxml")
public class PocetnakorisnikController implements Initializable {

    private final PocetnakorisnikService pocetnakorisnikService;

    @FXML
    private AnchorPane pocetnakorisnikAnchorPane;
    private Alert alertMetodaNijeImplementirana;

    @Autowired
    public PocetnakorisnikController(PocetnakorisnikService pocetnakorisnikService) {
        this.pocetnakorisnikService = pocetnakorisnikService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertMetodaNijeImplementirana = new Alert(Alert.AlertType.WARNING, "Metoda nije implementirana.");
    }

    public void onKviz(MouseEvent mouseEvent) {
        pocetnakorisnikService.promijeniEkran(pocetnakorisnikAnchorPane, Ekrani.PITANJE, null);
    }

    public void onLogOut(MouseEvent mouseEvent) {
        pocetnakorisnikService.promijeniEkran(pocetnakorisnikAnchorPane, Ekrani.POCETNA, null);
    }
}
