package org.kviz.service;

import com.github.rcaller.graphics.BlackTheme;
import com.github.rcaller.graphics.SkyTheme;
import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kviz.model.Rezultat;
import org.kviz.util.Ekrani;
import org.kviz.view.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.kviz.util.DataBaseUtil;

@Service
public class HistogramService {
    private SceneManager sceneManager;
    DataBaseUtil dataBaseUtil;

    @Autowired
    public HistogramService(SceneManager sceneManager, DataBaseUtil dataBaseUtil) {
        this.sceneManager = sceneManager;
        this.dataBaseUtil = dataBaseUtil;
    }

    public void promijeniEkran(AnchorPane anchorPaneHistogram, Ekrani ekran) {
        sceneManager.promijeniEkran(getStage(anchorPaneHistogram), ekran, null);
    }

    public Image crtajHistogram() {
        try {

            RCaller caller = RCaller.create();
            caller.setGraphicsTheme(new SkyTheme());
            RCode code = RCode.create();

            ArrayList<Rezultat> rezult = dohvatiNajboljeRezultate();
            int koliko = rezult.size();
            double[] rezultati = dohvatiRezultate(koliko);

            code.addDoubleArray("Rezultati", rezultati);

            File file = code.startPlot();
            code.addRCode("hist(Rezultati)"); //ovom funkcijom se dodaju R naredbe koje ce se izvrsiti - moze ih se dodati puno
            code.endPlot();

            caller.setRCode(code);

            caller.runOnly();

            FileInputStream fileInputStream = new FileInputStream(file);
            Image image = new Image(fileInputStream);
            System.out.println("success");
            return image;
        } catch (Exception e) {
            Logger.getLogger(HistogramService.class.getName()).log(Level.SEVERE, e.getMessage());
            return null;
        }

    }

    private Stage getStage(Node pocetna) {
        return (Stage) pocetna.getScene().getWindow();
    }

    public double[] dohvatiRezultate(int koliko){
        return dataBaseUtil.dohvatiRezultate(koliko);
    }

    public ArrayList<Rezultat> dohvatiNajboljeRezultate(){return dataBaseUtil.dohvatiNajboljeRezultate();}

}
