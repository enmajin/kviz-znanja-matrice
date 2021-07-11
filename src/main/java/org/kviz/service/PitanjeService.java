package org.kviz.service;

import org.kviz.model.Matrica;
import org.kviz.model.Zadatak;
import org.kviz.model.ZadatakMatricaOdgovor;
import org.kviz.repository.PitanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PitanjeService {
    private static final int MAX_DIMENZIJA = 3; //todo: dodati vise mogucih dimenzija u bazu
    private static final ArrayList<Operacije> LISTA_PITANJA = new ArrayList<>(List.of(Operacije.values()));

    private enum Operacije {
        ZBROJI("Zbroji"), ODUZMI("Oduzmi"), POMNOZI("Pomnoži");


        private final String value;

        Operacije(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private PitanjeRepository pitanjeRepository;

    @Autowired
    public PitanjeService(PitanjeRepository pitanjeRepository) {
        this.pitanjeRepository = pitanjeRepository;
    }

    public ArrayList<Zadatak> generirajZadatke() {
        ArrayList<Zadatak> zadaci = new ArrayList<>();
        for (int i=0; i<5; i++) {
            zadaci.add(generirajZadatakRjesenjeMatrica());
        }
        return zadaci;
    }

    private Zadatak generirajZadatakRjesenjeMatrica(){ //todo: dodati i generiranje zadatka s ponudenim odgovorom
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
        return new ZadatakMatricaOdgovor(pitanje, matrica1, matrica2, ispravnoRjesenje, new Matrica());
    }

    private Matrica izracunaj(Matrica matrica1, Operacije operacije, Matrica matrica2) {
        //todo: dodati racunanje
        return matrica1; //mock
    }
}
