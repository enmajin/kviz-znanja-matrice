package org.kviz.model;

public class Korisnik {
    int id, najbolji_rezultat;
    String lozinka, ime;

    public Korisnik(int i, String loz, String im, int naj_rez){
        id = i;
        lozinka = loz;
        ime = im;
        najbolji_rezultat = naj_rez;
    }
}
