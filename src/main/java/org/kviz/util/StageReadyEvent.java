package org.kviz.util;

import javafx.stage.Stage;
import org.kviz.model.InfoZaRezultateDto;
import org.kviz.model.Zadatak;
import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;

public class StageReadyEvent extends ApplicationEvent {
    private Ekrani ekran;
    private InfoZaRezultateDto data;

    public StageReadyEvent(Stage stage, Ekrani ekrani) {
        super(stage);
        this.ekran = ekrani;
    }

    public Stage getStage() {
        return ((Stage) getSource());
    }

    public Ekrani getEkran() {
        return ekran;
    }

    public void setData(InfoZaRezultateDto data) {
        this.data = data;
    }

    public InfoZaRezultateDto getData() {
        return data;
    }

    public void setEkran(Ekrani ekran) {
        this.ekran = ekran;
    }

}
