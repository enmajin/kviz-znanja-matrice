package org.kviz.model;

import java.util.ArrayList;
import java.util.List;

public class Matrica {
    int dimenzija;
    double[] vrijednosti;

    public Matrica() {
    }

    public Matrica(String vrijednosti, int dimenzija) {
        this.dimenzija = dimenzija;
        double[] arr = new double[dimenzija*dimenzija];
        String[] bezZareza = vrijednosti.split(",");

        for(int i=0; i<dimenzija*dimenzija; i++)    arr[i] = Double.parseDouble(bezZareza[i]);

        this.vrijednosti = arr;

    }

    public int getDimenzija() {
        return dimenzija;
    }

    public void setDimenzija(int dimenzija) {
        this.dimenzija = dimenzija;
    }

    public double[] getVrijednosti() {
        return vrijednosti;
    }

    public void setVrijednosti(double[] vrijednosti) {
        this.vrijednosti = vrijednosti;
    }

    @Override
    public String toString() {

        String stringValue = "";
        int i = 0;
        for (double element : vrijednosti) {
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
