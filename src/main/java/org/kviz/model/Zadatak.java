package org.kviz.model;

public class Zadatak {
    String pitanje;
    Matrica matrica1;
    Matrica matrica2;

    public Zadatak() {
    }

    public Zadatak(String pitanje, Matrica matrica1, Matrica matrica2) {
        this.pitanje = pitanje;
        this.matrica1 = matrica1;
        this.matrica2 = matrica2;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public Matrica getMatrica1() {
        return matrica1;
    }

    public void setMatrica1(Matrica matrica1) {
        this.matrica1 = matrica1;
    }

    public Matrica getMatrica2() {
        return matrica2;
    }

    public void setMatrica2(Matrica matrica2) {
        this.matrica2 = matrica2;
    }
}
