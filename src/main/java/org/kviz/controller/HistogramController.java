package org.kviz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.service.HistogramService;
import org.kviz.util.Ekrani;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/histogram.fxml")
public class HistogramController implements Initializable {
    @FXML
    ImageView histogramImageView;
    @FXML
    AnchorPane anchorPaneHistogram;

    private HistogramService histogramService;

    @Autowired
    public HistogramController(org.kviz.service.HistogramService histogramService) {
        this.histogramService = histogramService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = histogramService.crtajHistogram();
        histogramImageView.setImage(image);
    }

    public void onPovratak(ActionEvent actionEvent) {
        histogramService.promijeniEkran(anchorPaneHistogram, Ekrani.POCETNAKORISNIK);
    }
}
