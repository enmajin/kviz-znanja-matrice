package org.kviz.repository;

import org.kviz.model.Matrica;
import org.kviz.util.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class PitanjeRepository {
    DataBaseUtil dataBaseUtil;

    @Autowired
    public PitanjeRepository(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }

    public ArrayList<Matrica> dohvatiSveMatrice(){
        return dataBaseUtil.dohvati_sve_matrice();
    }

    public Matrica dohvatiMatricuDimenzijeN(int n) {
        return dataBaseUtil.dohvatiMatricuDimenzijeN(n);
    }
}
