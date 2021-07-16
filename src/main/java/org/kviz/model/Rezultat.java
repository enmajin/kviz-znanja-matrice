package org.kviz.model;

import java.time.Duration;

public class Rezultat {
    int rang;
    String ime;
    int bodovi;
    String vrijeme;

    public Rezultat() {
    }

    public Rezultat(String ime, int bodovi, String vrijeme, int rang) {
        this.ime = ime;
        this.bodovi = bodovi;
        this.vrijeme = vrijeme;
        this.rang = rang;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        this.vrijeme = vrijeme;
    }
}
