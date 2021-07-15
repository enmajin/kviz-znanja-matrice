package org.kviz.repository;

import org.kviz.model.Korisnik;
import org.kviz.util.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class RegistracijaRepository {
    DataBaseUtil dataBaseUtil;

    @Autowired
    public RegistracijaRepository(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }

    public ArrayList<Korisnik> dohvatiSveKorisnike(){
        return dataBaseUtil.dohvati_sve_korisnike();
    }

    public Korisnik dohvatiKorisnika(String loz, String im) {
        return dataBaseUtil.dohvatiKorisnika(loz, im);
    }

    public int max_id(){
        return dataBaseUtil.max_id();
    }

    public void dodaj_korisnike(int id, String loz, String im, int rez) {
        dataBaseUtil.dodaj_korisnike(id ,loz, im, rez);
    }

}
