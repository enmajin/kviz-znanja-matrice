package org.kviz.service;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kviz.model.Rezultat;
import org.kviz.repository.RangRepository;
import org.kviz.util.Ekrani;
import org.kviz.view.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RangService {

    private RangRepository rangRepository;
    private SceneManager sceneManager;

    @Autowired
    public RangService(RangRepository rangRepository, SceneManager sceneManager) {
        this.rangRepository = rangRepository;
        this.sceneManager = sceneManager;
    }

    public ArrayList<Rezultat> dohvatiNajboljeRezultate() {
        return rangRepository.dohvatiNajboljeRezultate();
    }

    public void promijeniEkran(AnchorPane rangAnchorPane, Ekrani ekran) {
        sceneManager.promijeniEkran(getStage(rangAnchorPane), ekran,null);
    }

    private Stage getStage(Node pocetna) {
        return (Stage) pocetna.getScene().getWindow();
    }
}
