package org.kviz.model;

import java.util.ArrayList;
import java.util.List;

public class Matrica {
    int dimenzija;
    String vrijednosti;

    public Matrica() {
    }

    public Matrica(String vrijednosti, int dimenzija) {
        this.vrijednosti = vrijednosti;
        this.dimenzija = dimenzija;
    }

    public int getDimenzija() {
        return dimenzija;
    }

    public void setDimenzija(int dimenzija) {
        this.dimenzija = dimenzija;
    }

    public String getVrijednosti() {
        return vrijednosti;
    }

    public void setVrijednosti(String vrijednosti) {
        this.vrijednosti = vrijednosti;
    }

    @Override
    public String toString() {
        String stringValue = "";
        ArrayList<String> listaVrijednosti = new ArrayList<>(List.of(vrijednosti.split(",")));
        listaVrijednosti.remove("");
        listaVrijednosti.forEach(element -> element.trim());
        int i = 0;
        for (String element : listaVrijednosti) {
            i += 1;
            stringValue += element;
            if (i % dimenzija == 0) {
                stringValue += "\n";
            } else {
                stringValue += " ";
            }
        }
        return stringValue;
    }
}
