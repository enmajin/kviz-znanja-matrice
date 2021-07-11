package org.kviz.service;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.kviz.util.ViewEnum;
import org.kviz.view.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PocetnaService {
    private SceneManager sceneManager;

    @Autowired
    public PocetnaService(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void promijeniEkran(Node pocetna, ViewEnum registracija) {
        sceneManager.promijeniEkran(getStage(pocetna), registracija);
    }

    private Stage getStage(Node pocetna) {
        return (Stage) pocetna.getScene().getWindow();
    }

}
