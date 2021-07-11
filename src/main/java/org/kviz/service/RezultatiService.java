package org.kviz.service;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kviz.util.Ekrani;
import org.kviz.view.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RezultatiService {
    private SceneManager sceneManager;

    @Autowired
    public RezultatiService(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void promijeniEkran(AnchorPane rezultatiAnchorPane, Ekrani ekran) {
        sceneManager.promijeniEkran(getStage(rezultatiAnchorPane), ekran, null);
    }

    private Stage getStage(Node pocetna) {
        return (Stage) pocetna.getScene().getWindow();
    }

}
