package org.kviz.service;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kviz.util.DataBaseUtil;
import org.kviz.util.Ekrani;
import org.kviz.view.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RezultatiService {
    private SceneManager sceneManager;
    private DataBaseUtil dataBaseUtil;

    @Autowired
    public RezultatiService(SceneManager sceneManager, DataBaseUtil dataBaseUtil)
    {
        this.sceneManager = sceneManager;
        this.dataBaseUtil = dataBaseUtil;
    }

    public void promijeniEkran(AnchorPane rezultatiAnchorPane, Ekrani ekran) {
        sceneManager.promijeniEkran(getStage(rezultatiAnchorPane), ekran, null);
    }

    private Stage getStage(Node pocetna) {
        return (Stage) pocetna.getScene().getWindow();
    }

    public int dohvatiNajboljiRezultat(String username){
        return dataBaseUtil.dohvatiNajboljiRezultat(username);
    }

    public void ažurirajNajboljiRezultat(String username, int rezultat){
        dataBaseUtil.ažurirajNajboljiRezultat(username,rezultat);
    }

    /*public int dohvatiVrijeme(String username){
       return dataBaseUtil.dohvatiVrijeme(username);
    }*/

    public void ažurirajVrijeme(String username, Duration vrijeme){
        dataBaseUtil.ažurirajVrijeme(username, vrijeme);
    }
}
