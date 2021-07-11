package org.kviz.model;

public class Korisnik {
    int id;
    String ime;
    String lozinka;
    int najboljiRezultat;

    public Korisnik() {
    }

    public Korisnik(int id, String lozinka, String ime, int najboljiRezultat) {
        this.id = id;
        this.lozinka = lozinka;
        this.ime = ime;
        this.najboljiRezultat = najboljiRezultat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getNajboljiRezultat() {
        return najboljiRezultat;
    }

    public void setNajboljiRezultat(int najboljiRezultat) {
        this.najboljiRezultat = najboljiRezultat;
    }
}
