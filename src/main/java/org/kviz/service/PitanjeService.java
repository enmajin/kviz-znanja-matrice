package org.kviz.service;

import javafx.scene.Node;
import javafx.stage.Stage;
import org.ejml.simple.SimpleMatrix;
import org.kviz.controller.RezultatiController;
import org.kviz.model.*;
import org.kviz.repository.PitanjeRepository;
import org.kviz.util.Ekrani;
import org.kviz.util.SlovoUzOdgovor;
import org.kviz.view.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PitanjeService {
    private static final int MAX_DIMENZIJA = 3; //todo: dodati vise mogucih dimenzija u bazu
    private static final ArrayList<Operacije> LISTA_PITANJA = new ArrayList<>(List.of(Operacije.values()));
    private static final int BROJ_ZADATAKA_U_KVIZU = 5;

    private enum Operacije {
        ZBROJI("Zbroji"), POMNOZI("Pomnoži"),
        TRANSPONIRAJ("Transponiraj"), INVERZ("Inverz"),
        DETERMINANTA("Determinanta");


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
            while(zadatak == null) zadatak = generirajZadatakRjesenjeMatrica(i);
            zadaci.add(zadatak);
        }
        return zadaci;
    }

    private Zadatak generirajZadatakRjesenjeMatrica(int i){
        Random rnd = new Random();
        Operacije operacije = LISTA_PITANJA.get(i);
        String pitanje =  operacije.getValue() + " matrice";

        int n = 1;
        while ( n <= 1 ) {
            n = rnd.nextInt(MAX_DIMENZIJA);
        }
        Matrica matrica1 = pitanjeRepository.dohvatiMatricuDimenzijeN(n);
        Matrica matrica2 = pitanjeRepository.dohvatiMatricuDimenzijeN(n);
        Matrica ispravnoRjesenjeMatrica;
        double ispravnoRjesenjeDouble;
        if(operacije == Operacije.ZBROJI){
            ispravnoRjesenjeMatrica = zbroji(matrica1, matrica2);
            return new ZadatakMatricaOdgovor(pitanje, matrica1, matrica2, ispravnoRjesenjeMatrica, new Matrica());
        }
        else if(operacije == Operacije.POMNOZI){
            ispravnoRjesenjeMatrica = pomnozi(matrica1, matrica2);
            return new ZadatakMatricaOdgovor(pitanje, matrica1, matrica2, ispravnoRjesenjeMatrica, new Matrica());
        }
        else if(operacije == Operacije.TRANSPONIRAJ){
            ispravnoRjesenjeMatrica = transponiraj(matrica1);
            return new ZadatakMatricaOdgovor(pitanje, matrica1, null, ispravnoRjesenjeMatrica, new Matrica());
        }
        else if(operacije == Operacije.INVERZ){
            ispravnoRjesenjeMatrica = inverz(matrica1);
            if(ispravnoRjesenjeMatrica == null) return null;
            return new ZadatakMatricaOdgovor(pitanje, matrica1, null, ispravnoRjesenjeMatrica, new Matrica());
        }
        else ispravnoRjesenjeDouble = determinanta(matrica1);
        SlovoUzOdgovor randomSlovo = slucajnoSlovo();
        Map<SlovoUzOdgovor, Double> ponudeniOdgovori = new HashMap<>();
        ponudeniOdgovori.put(SlovoUzOdgovor.a, slucajanOdgovor(ispravnoRjesenjeDouble));
        ponudeniOdgovori.put(SlovoUzOdgovor.b, slucajanOdgovor(ispravnoRjesenjeDouble));
        ponudeniOdgovori.put(SlovoUzOdgovor.c, slucajanOdgovor(ispravnoRjesenjeDouble));
        ponudeniOdgovori.put(SlovoUzOdgovor.d, slucajanOdgovor(ispravnoRjesenjeDouble));
        ponudeniOdgovori.put(randomSlovo, ispravnoRjesenjeDouble); //updateaj mapu sa točnim rješenjem
        return new ZadatakPonudeniOdgovori(pitanje, matrica1, null, ponudeniOdgovori, randomSlovo, null);
    }

    private Matrica zbroji(Matrica matrica1, Matrica matrica2){
        Matrica zbroj = new Matrica();
        zbroj.setDimenzija(matrica1.getDimenzija());
        SimpleMatrix M1 = new SimpleMatrix(matrica1.getDimenzija(),matrica1.getDimenzija(),true,matrica1.getVrijednosti());
        SimpleMatrix M2 = new SimpleMatrix(matrica2.getDimenzija(),matrica2.getDimenzija(),true,matrica2.getVrijednosti());
        SimpleMatrix M = M1.plus(M2);
        zbroj.setVrijednosti(konvertiraj(M));
        return zbroj;
    }

    private Matrica pomnozi(Matrica matrica1, Matrica matrica2){
        Matrica produkt = new Matrica();
        produkt.setDimenzija(matrica1.getDimenzija());
        SimpleMatrix M1 = new SimpleMatrix(matrica1.getDimenzija(),matrica1.getDimenzija(),true,matrica1.getVrijednosti());
        SimpleMatrix M2 = new SimpleMatrix(matrica2.getDimenzija(),matrica2.getDimenzija(),true,matrica2.getVrijednosti());
        SimpleMatrix M = M1.mult(M2);
        produkt.setVrijednosti(konvertiraj(M));
        return produkt;
    }

    private Matrica transponiraj(Matrica matrica1){
        Matrica transponirana = new Matrica();
        transponirana.setDimenzija(matrica1.getDimenzija());
        SimpleMatrix M1 = new SimpleMatrix(matrica1.getDimenzija(),matrica1.getDimenzija(),true,matrica1.getVrijednosti());
        SimpleMatrix M = M1.transpose();
        transponirana.setVrijednosti(konvertiraj(M));
        return transponirana;
    }

    private double determinanta(Matrica matrica1){
        Matrica deter = new Matrica();
        deter.setDimenzija(matrica1.getDimenzija());
        SimpleMatrix M1 = new SimpleMatrix(matrica1.getDimenzija(),matrica1.getDimenzija(),true,matrica1.getVrijednosti());
        double d = M1.determinant();
        return d;
    }

    private Matrica inverz(Matrica matrica1){
        Matrica inv = new Matrica();
        inv.setDimenzija(matrica1.getDimenzija());
        SimpleMatrix M1 = new SimpleMatrix(matrica1.getDimenzija(),matrica1.getDimenzija(),true,matrica1.getVrijednosti());
        try{
            SimpleMatrix M = M1.invert();
            inv.setVrijednosti(konvertiraj(M));
        } catch(Exception e){
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

    private SlovoUzOdgovor slucajnoSlovo(){
        Random r = new Random();
        int index = r.nextInt(4 - 0) + 0;
        if(index == 0)  return SlovoUzOdgovor.a;
        if(index == 1)  return SlovoUzOdgovor.b;
        if(index == 2)  return SlovoUzOdgovor.c;
        else    return SlovoUzOdgovor.d;
    }

    private double slucajanOdgovor(double tocanOdgovor){
        Random r = new Random();
        int min = (int)tocanOdgovor - 10;
        int max = (int)tocanOdgovor + 10;
        int odg = r.nextInt(max - min) + min;
        return odg;
    }
}
