package org.kviz.service;

import javafx.scene.Node;
import javafx.stage.Stage;
import org.ejml.simple.SimpleMatrix;
import org.kviz.controller.RezultatiController;
import org.kviz.model.InfoZaRezultateDto;
import org.kviz.model.Matrica;
import org.kviz.model.Zadatak;
import org.kviz.model.ZadatakMatricaOdgovor;
import org.kviz.repository.PitanjeRepository;
import org.kviz.util.Ekrani;
import org.kviz.view.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



@Service
public class PitanjeService {
    private static final int MAX_DIMENZIJA = 3; //todo: dodati vise mogucih dimenzija u bazu
    private static final ArrayList<Operacije> LISTA_PITANJA = new ArrayList<>(List.of(Operacije.values()));
    private static final int BROJ_ZADATAKA_U_KVIZU = 5;

    private enum Operacije {
        ZBROJI("Zbroji")/*, ODUZMI("Oduzmi")*/, POMNOZI("Pomnoži"),
        INVERZ("Inverz");


        private final String value;

        Operacije(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private PitanjeRepository pitanjeRepository;
    private SceneManager sceneManager;

    public void promijeniEkran(Node pocetna, Ekrani ekran, InfoZaRezultateDto data) {
        sceneManager.promijeniEkran(getStage(pocetna), ekran, data);
    }

    private Stage getStage(Node pocetna) {
        return (Stage) pocetna.getScene().getWindow();
    }

    @Autowired
    public PitanjeService(PitanjeRepository pitanjeRepository, SceneManager sceneManager) {
        this.pitanjeRepository = pitanjeRepository;
        this.sceneManager = sceneManager;
    }

    public ArrayList<Zadatak> generirajZadatke() {
        ArrayList<Zadatak> zadaci = new ArrayList<>();

        for (int i=0; i<BROJ_ZADATAKA_U_KVIZU; i++) {
            Zadatak zadatak = null;
            while(zadatak == null) zadatak = generirajZadatakRjesenjeMatrica();
            zadaci.add(zadatak);  //todo: dodati i generiranje zadatka s ponudenim odgovorom
        }
        return zadaci;
    }

    private Zadatak generirajZadatakRjesenjeMatrica(){
        Random rnd = new Random();
        Operacije operacije = LISTA_PITANJA.get(rnd.nextInt(LISTA_PITANJA.size()));
        String pitanje =  operacije.getValue() + " matrice";

        int n = 1;
        while ( n <= 1 ) {
            n = rnd.nextInt(MAX_DIMENZIJA);
        }
        Matrica matrica1 = pitanjeRepository.dohvatiMatricuDimenzijeN(n);
        Matrica matrica2 = pitanjeRepository.dohvatiMatricuDimenzijeN(n);
        Matrica ispravnoRjesenje = izracunaj(matrica1, operacije, matrica2);
        if(ispravnoRjesenje == null) return null;
        else return new ZadatakMatricaOdgovor(pitanje, matrica1, matrica2, ispravnoRjesenje, new Matrica());
    }

    private Matrica izracunaj(Matrica matrica1, Operacije operacije, Matrica matrica2) {
        if(operacije == Operacije.ZBROJI)    return zbroji(matrica1, matrica2);
        //if(operacije == Operacije.ODUZMI)    return oduzmi(matrica1, matrica2);
        if(operacije == Operacije.POMNOZI)   return pomnozi(matrica1, matrica2);
        return inverz(matrica1);
    }

    private Matrica zbroji(Matrica matrica1, Matrica matrica2){
        Matrica zbroj = matrica1;
        SimpleMatrix M1 = new SimpleMatrix(matrica1.getDimenzija(),matrica1.getDimenzija(),true,matrica1.getVrijednosti());
        SimpleMatrix M2 = new SimpleMatrix(matrica2.getDimenzija(),matrica2.getDimenzija(),true,matrica2.getVrijednosti());
        SimpleMatrix M = M1.plus(M2);
        zbroj.setVrijednosti(konvertiraj(M));
        return zbroj;
    }

    /*private Matrica oduzmi(Matrica matrica1, Matrica matrica2){
        Matrica razlika = matrica1;
        SimpleMatrix M1 = new SimpleMatrix(matrica1.getDimenzija(),matrica1.getDimenzija(),true,matrica1.getVrijednosti());
        SimpleMatrix M2 = new SimpleMatrix(matrica1.getDimenzija(),matrica1.getDimenzija(),true,matrica2.getVrijednosti());
        SimpleMatrix M = M1.minus(M2);
        razlika.setVrijednosti(konvertiraj(M));
        return razlika;
    }*/

    private Matrica pomnozi(Matrica matrica1, Matrica matrica2){
        Matrica produkt = matrica1;
        SimpleMatrix M1 = new SimpleMatrix(matrica1.getDimenzija(),matrica1.getDimenzija(),true,matrica1.getVrijednosti());
        SimpleMatrix M2 = new SimpleMatrix(matrica2.getDimenzija(),matrica2.getDimenzija(),true,matrica2.getVrijednosti());
        SimpleMatrix M = M1.mult(M2);
        produkt.setVrijednosti(konvertiraj(M));
        return produkt;
    }

    private Matrica inverz(Matrica matrica1){
        Matrica inv = matrica1;
        SimpleMatrix M1 = new SimpleMatrix(matrica1.getDimenzija(),matrica1.getDimenzija(),true,matrica1.getVrijednosti());
        try{
            SimpleMatrix M = M1.invert();
            inv.setVrijednosti(konvertiraj(M));
        } catch(Exception e){
            System.out.println(e);
            return null;
        }

        return inv;
    }

    private double[] konvertiraj(SimpleMatrix M){
        int brojac = 0;
        double[] produktArr = new double[M.numRows()*M.numCols()];
        for(int i=0; i<M.numRows(); i++) {
            for(int j = 0; j < M.numCols(); j++) {
                produktArr[brojac] = M.get(i, j);
                brojac++;
            }
        }
        return produktArr;
    }
}
