package org.kviz.model;

import java.time.Duration;
import java.util.ArrayList;

public class InfoZaRezultateDto {
    ArrayList<Zadatak> zadaci;
    Duration vrijeme;

    public InfoZaRezultateDto() {
    }

    public InfoZaRezultateDto(ArrayList<Zadatak> zadaci, Duration vrijeme) {
        this.zadaci = zadaci;
        this.vrijeme = vrijeme;
    }

    public ArrayList<Zadatak> getZadaci() {
        return zadaci;
    }

    public void setZadaci(ArrayList<Zadatak> zadaci) {
        this.zadaci = zadaci;
    }

    public Duration getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(Duration vrijeme) {
        this.vrijeme = vrijeme;
    }
}
