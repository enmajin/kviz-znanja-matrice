package org.kviz.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.model.Zadatak;
import org.kviz.model.ZadatakMatricaOdgovor;
import org.kviz.model.ZadatakPonudeniOdgovori;
import org.kviz.service.PitanjeService;
import org.kviz.util.VrstaZadatka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/pitanje.fxml")
public class PitanjeController implements Initializable {

    @FXML
    HBox visestrukiOdgovorHbox;
    @FXML
    HBox matricaOdgovorHbox;
    @FXML
    Label pitanjeLabel;
    @FXML
    Label matrica1Label;
    @FXML
    Label matrica2Label;
    @FXML
    Label brojPitanjaLabel;
    @FXML
    VBox unosMatriceVBox;


    ArrayList<Zadatak> zadaci;
    int brojOdgovorenihPitanja;
    Zadatak trenutniZadatak;
    VrstaZadatka vrstaZadatka;
    private PitanjeService pitanjeService;

    @Autowired
    public PitanjeController(PitanjeService pitanjeService) {
        this.pitanjeService = pitanjeService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        brojOdgovorenihPitanja = 0;
        zadaci = pitanjeService.generirajZadatke();
        trenutniZadatak = zadaci.get(brojOdgovorenihPitanja);
        postaviMjestoZaOdgovor();
        prikaziZadatak();

    }

    private void prikaziZadatak() {
        brojPitanjaLabel.setText("Pitanje " + (brojOdgovorenihPitanja + 1));
        pitanjeLabel.setText(trenutniZadatak.getPitanje());

        matrica1Label.setText(trenutniZadatak.getMatrica1().toString());
        matrica2Label.setText(trenutniZadatak.getMatrica2().toString());
    }

    private void postaviMjestoZaOdgovor() {
        VBox mainVBox = (VBox) visestrukiOdgovorHbox.getParent();

        if (trenutniZadatak instanceof ZadatakMatricaOdgovor) {
            vrstaZadatka = VrstaZadatka.ODGOVOR_MATRICA;
            mainVBox.getChildren().remove(visestrukiOdgovorHbox);

            int n = ((ZadatakMatricaOdgovor) trenutniZadatak).getIspravnoRjesenje().getDimenzija();
            for (int i = 0; i < n; i++) {
                    HBox red = new HBox(5);
                    for (int j = 0; j < n; j++) {
                        TextField element = new TextField();
                        element.setId(i + "-" + j);
                        red.getChildren().add(element);
                    }
                    unosMatriceVBox.getChildren().add(red);
                }


            } else if (trenutniZadatak instanceof ZadatakPonudeniOdgovori) {
                vrstaZadatka = VrstaZadatka.VISESTRUKI_ODABIR;
                mainVBox.getChildren().remove(matricaOdgovorHbox);
            } else {
                new Alert(Alert.AlertType.ERROR, "Greska u dohvacanju zadatka").show(); //todo: maknuti nakon testiranja
            }
        }


    public void onPotvrdi(MouseEvent mouseEvent) {

    }
}
