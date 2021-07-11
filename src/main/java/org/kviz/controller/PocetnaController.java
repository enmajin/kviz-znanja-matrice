package org.kviz.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.util.ViewEnum;
import org.kviz.service.PocetnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/pocetna.fxml")
public class PocetnaController implements Initializable {

    private final PocetnaService pocetnaService;

    @FXML private AnchorPane pocetna;
    private Alert alertMetodaNijeImplementirana;

    @Autowired
    public PocetnaController(PocetnaService pocetnaService, ApplicationEventPublisher applicationEventPublisher) {
        this.pocetnaService = pocetnaService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertMetodaNijeImplementirana = new Alert(Alert.AlertType.WARNING, "Metoda nije implementirana.");
    }

    public void onProbniKviz(MouseEvent mouseEvent) {
        pocetnaService.promijeniEkran(pocetna, ViewEnum.PITANJE);
    }

    public void onRegistrirajSe(MouseEvent mouseEvent) {
        pocetnaService.promijeniEkran(pocetna, ViewEnum.REGISTRACIJA);
    }

    public void onLogIn(MouseEvent mouseEvent) {
        alertMetodaNijeImplementirana.show(); // jos nemamo fxml za ovo
//        pocetnaService.promijeniEkran(pocetna, ViewEnum.LOGIN);
    }
}
