package org.kviz.service;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.kviz.model.Zadatak;
import org.kviz.util.Ekrani;
import org.kviz.view.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PocetnakorisnikService {
    private SceneManager sceneManager;

    @Autowired
    public PocetnakorisnikService(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void promijeniEkran(Node pocetnakorisnik, Ekrani registracija, ArrayList<Zadatak> zadaci) {
        sceneManager.promijeniEkran(getStage(pocetnakorisnik), registracija, zadaci);
    }

    private Stage getStage(Node pocetnakorisnik) {
        return (Stage) pocetnakorisnik.getScene().getWindow();
    }

}
