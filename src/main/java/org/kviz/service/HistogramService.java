package org.kviz.service;

import com.github.rcaller.graphics.BlackTheme;
import com.github.rcaller.graphics.SkyTheme;
import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kviz.util.Ekrani;
import org.kviz.view.SceneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class HistogramService {
    private SceneManager sceneManager;

    @Autowired
    public HistogramService(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void promijeniEkran(AnchorPane anchorPaneHistogram, Ekrani ekran) {
        sceneManager.promijeniEkran(getStage(anchorPaneHistogram), ekran, null);
    }

    public Image crtajHistogram() {
        try {

            RCaller caller = RCaller.create();
            caller.setGraphicsTheme(new SkyTheme());
            RCode code = RCode.create();

            double[] numbers = new double[]{1, 4, 3, 5, 6, 10};

            code.addDoubleArray("x", numbers);

            File file = code.startPlot();
            code.addRCode("hist(x)"); //ovom funkcijom se dodaju R naredbe koje ce se izvrsiti - moze ih se dodati puno
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

}
