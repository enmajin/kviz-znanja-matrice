package org.kviz.model;

public class ZadatakMatricaOdgovor extends Zadatak {
    Matrica ispravnoRjesenje;

    Matrica korisnikovoRjesenje;

    public ZadatakMatricaOdgovor() {
    }

    public ZadatakMatricaOdgovor(String pitanje, Matrica matrica1, Matrica matrica2, Matrica ispravnoRjesenje,
                                 Matrica korisnikovoRjesenje) {
        super(pitanje, matrica1, matrica2);
        this.ispravnoRjesenje = ispravnoRjesenje;
        this.korisnikovoRjesenje = korisnikovoRjesenje;
    }

    public Matrica getIspravnoRjesenje() {
        return ispravnoRjesenje;
    }

    public void setIspravnoRjesenje(Matrica ispravnoRjesenje) {
        this.ispravnoRjesenje = ispravnoRjesenje;
    }

    public Matrica getKorisnikovoRjesenje() {
        return korisnikovoRjesenje;
    }

    public void setKorisnikovoRjesenje(Matrica korisnikovoRjesenje) {
        this.korisnikovoRjesenje = korisnikovoRjesenje;
    }
}
