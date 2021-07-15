package org.kviz.service;

import org.kviz.model.Rezultat;
import org.kviz.repository.RangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RangService {

    private RangRepository rangRepository;

    @Autowired
    public RangService(RangRepository rangRepository) {
        this.rangRepository = rangRepository;
    }

    public ArrayList<Rezultat> dohvatiNajboljeRezultate() {
        return rangRepository.dohvatiNajboljeRezultate();
    }
}
