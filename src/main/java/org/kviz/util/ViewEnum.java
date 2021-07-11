package org.kviz.util;

import org.kviz.controller.*;

public enum ViewEnum {
    POCETNA(PocetnaController.class),
    PITANJE(PitanjeController.class),
    RANG(RangController.class),
    REGISTRACIJA(RegistracijaController.class),
    LOGIN(LoginController.class),
    REZULTATI(RezultatiController.class);

    private final Class controllerClass;

    ViewEnum(Class controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Class getControllerClass() {
        return controllerClass;
    }
}
