package org.kviz.repository;

import org.kviz.model.Rezultat;
import org.kviz.util.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class RangRepository {
    private DataBaseUtil dataBaseUtil;

    @Autowired
    public RangRepository(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }

    public ArrayList<Rezultat> dohvatiNajboljeRezultate() {
        return dataBaseUtil.dohvatiNajboljeRezultate();
    }
}
