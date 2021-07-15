package org.kviz.service;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kviz.model.Korisnik;
import org.kviz.repository.LoginRepository;
import org.kviz.repository.PitanjeRepository;
import org.kviz.util.Ekrani;
import org.kviz.view.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private LoginRepository loginRepository;
    private SceneManager sceneManager;

    @Autowired
    public LoginService(LoginRepository loginRepository, SceneManager sceneManager) {
        this.loginRepository = loginRepository;
        this.sceneManager = sceneManager;
    }

    public void promijeniEkran(AnchorPane loginAnchorPane, Ekrani ekran) {
        sceneManager.promijeniEkran(getStage(loginAnchorPane), ekran,null);
    }

    private Stage getStage(Node pocetna) {
        return (Stage) pocetna.getScene().getWindow();
    }

    public Korisnik dohvatiKorisnika(String loz, String im) {
        return loginRepository.dohvatiKorisnika(loz, im);
    }

}
