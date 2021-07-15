package org.kviz.service;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kviz.model.Korisnik;
import org.kviz.repository.LoginRepository;
import org.kviz.repository.RegistracijaRepository;
import org.kviz.util.Ekrani;
import org.kviz.view.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RegistracijaService {
    private RegistracijaRepository registracijaRepository;
    private SceneManager sceneManager;

    @Autowired
    public RegistracijaService(RegistracijaRepository registracijaRepository, SceneManager sceneManager) {
        this.registracijaRepository = registracijaRepository;
        this.sceneManager = sceneManager;
    }

    public void promijeniEkran(AnchorPane registracijaAnchorPane, Ekrani ekran) {
        sceneManager.promijeniEkran(getStage(registracijaAnchorPane), ekran,null);
    }

    private Stage getStage(Node pocetna) {
        return (Stage) pocetna.getScene().getWindow();
    }

    public Korisnik dohvatiKorisnika(String loz, String im) {
        return registracijaRepository.dohvatiKorisnika(loz, im);
    }

    public int max_id(){
        return registracijaRepository.max_id();
    }

    public void dodaj_korisnike(int id, String loz, String im, int rez) {
        registracijaRepository.dodaj_korisnike(id, loz, im, rez);
    }

}
