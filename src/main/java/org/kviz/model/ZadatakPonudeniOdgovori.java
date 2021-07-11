package org.kviz.model;

import org.kviz.util.SlovoUzOdgovor;

import java.util.Map;

public class ZadatakPonudeniOdgovori extends Zadatak {
    Map<SlovoUzOdgovor, Matrica> ponudeniOdgovori;

    SlovoUzOdgovor ispravnoRjesenje;
    SlovoUzOdgovor korisnikovoRjesenje;

    public ZadatakPonudeniOdgovori(
            String pitanje, Matrica matrica1, Matrica matrica2, Map<SlovoUzOdgovor,
            Matrica> ponudeniOdgovori, SlovoUzOdgovor ispravnoRjesenje, SlovoUzOdgovor korisnikovoRjesenje
    ) {
        super(pitanje, matrica1, matrica2);
        this.ponudeniOdgovori = ponudeniOdgovori;
        this.ispravnoRjesenje = ispravnoRjesenje;
        this.korisnikovoRjesenje = korisnikovoRjesenje;
    }

    public ZadatakPonudeniOdgovori() {
    }

    public Map<SlovoUzOdgovor, Matrica> getPonudeniOdgovori() {
        return ponudeniOdgovori;
    }

    public void setPonudeniOdgovori(Map<SlovoUzOdgovor, Matrica> ponudeniOdgovori) {
        this.ponudeniOdgovori = ponudeniOdgovori;
    }

    public SlovoUzOdgovor getIspravnoRjesenje() {
        return ispravnoRjesenje;
    }

    public void setIspravnoRjesenje(SlovoUzOdgovor ispravnoRjesenje) {
        this.ispravnoRjesenje = ispravnoRjesenje;
    }

    public SlovoUzOdgovor getKorisnikovoRjesenje() {
        return korisnikovoRjesenje;
    }

    @Override
    public boolean getIsZadatakIspravnoRijesen() {
        return korisnikovoRjesenje == ispravnoRjesenje;
    }

    public void setKorisnikovoRjesenje(SlovoUzOdgovor korisnikovoRjesenje) {
        this.korisnikovoRjesenje = korisnikovoRjesenje;
    }
}
