package org.kviz.util;

import com.sun.marlin.stats.Histogram;
import org.kviz.controller.*;

public enum Ekrani {
    POCETNA(PocetnaController.class),
    POCETNAKORISNIK(PocetnakorisnikController.class),
    PITANJE(PitanjeController.class),
    RANG(RangController.class),
    REGISTRACIJA(RegistracijaController.class),
    LOGIN(LoginController.class),
    REZULTATI(RezultatiController.class),
    HISTOGRAM(HistogramController.class);

    private final Class controllerClass;

    Ekrani(Class controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Class getControllerClass() {
        return controllerClass;
    }
}
