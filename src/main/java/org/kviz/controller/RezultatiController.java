package org.kviz.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.model.InfoZaRezultateDto;
import org.kviz.model.Zadatak;
import org.kviz.service.RezultatiService;
import org.kviz.util.Ekrani;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/rezultati.fxml")
public class RezultatiController implements Initializable {
    private static final int BROJ_ZADATAKA_U_KVIZU = 5;
    private final RezultatiService rezultatiService;
    @FXML
    AnchorPane rezultatiAnchorPane;
    @FXML
    Text testid;
    @FXML
    TableColumn korisnikoviOdgovori;
    @FXML
    TableColumn ispravniOdgovori;
    @FXML
    TableView odgovori;
    private ArrayList<Zadatak> zadaci;
    @FXML
    private Text bodovi;
    private int brojBodova;
    private Duration vrijeme;

    @Autowired
    public RezultatiController(RezultatiService rezultatiService) {
        this.rezultatiService = rezultatiService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setData(InfoZaRezultateDto data) {
        this.zadaci = data.getZadaci();
        this.vrijeme = data.getVrijeme(); //todo mozemo prikazati i vrijeme
        prikaziRezultate();
    }

    private void prikaziRezultate() {
        korisnikoviOdgovori.setCellValueFactory(new PropertyValueFactory<>("korisnikovoRjesenje"));
        ispravniOdgovori.setCellValueFactory(new PropertyValueFactory<>("ispravnoRjesenje"));
        brojBodova = 0;
        zadaci.forEach(zadatak -> {
                    odgovori.getItems().add(zadatak);
                    if (zadatak.getIsZadatakIspravnoRijesen()) {
                        brojBodova++;
                    }
                }
        );
        //todo: spremiti broj bodova u bazu ako je veci od osobnog rekorda (mozda i vrijeme?)

        bodovi.setText(brojBodova + "/" + BROJ_ZADATAKA_U_KVIZU);
    }

    public void onNoviKviz(MouseEvent mouseEvent) {
        rezultatiService.promijeniEkran(rezultatiAnchorPane, Ekrani.PITANJE);
    }

    public void onPokaziRangListu(MouseEvent mouseEvent) {
        rezultatiService.promijeniEkran(rezultatiAnchorPane, Ekrani.RANG);
    }
}
