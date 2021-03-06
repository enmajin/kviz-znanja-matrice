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
public class PocetnaService {
    private SceneManager sceneManager;

    @Autowired
    public PocetnaService(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void promijeniEkran(Node pocetna, Ekrani registracija, ArrayList<Zadatak> zadaci) {
        sceneManager.promijeniEkran(getStage(pocetna), registracija, zadaci);
    }

    private Stage getStage(Node pocetna) {
        return (Stage) pocetna.getScene().getWindow();
    }

}
