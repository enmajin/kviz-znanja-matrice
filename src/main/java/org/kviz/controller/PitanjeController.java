package org.kviz.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.model.*;
import org.kviz.service.PitanjeService;
import org.kviz.util.Ekrani;
import org.kviz.util.User;
import org.kviz.util.VrstaZadatka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/pitanje.fxml")
public class PitanjeController implements Initializable {

    @FXML
    private AnchorPane pitanjeAnchorPane;
    @FXML
    private VBox mainVBox;
    @FXML
    private HBox visestrukiOdgovorHbox;
    @FXML
    private HBox matricaOdgovorHbox;
    @FXML
    private Label brojPitanjaLabel;
    @FXML
    private Label pitanjeLabel;
    @FXML
    private Label matrica1Label;
    @FXML
    private Label matrica2Label;
    @FXML
    private VBox unosMatriceVBox;
    @FXML
    private HBox gumbiHbox;
    @FXML
    private Text vrijemeText;
    @FXML
    private Text imekorisnika;


    private ArrayList<Zadatak> zadaci;
    private int brojOdgovorenihPitanja;
    private Zadatak trenutniZadatak;
    private VrstaZadatka vrstaZadatka;
    private int dimenzijaMatriceRjesenja;
    private Instant vrijemePocetak;
    private Instant vrijemeKraj;
    private Duration trajanje;
    private Timeline timeline;
    private Duration moguceTrajanje = Duration.ofMinutes(5);
    private Instant krajnjeVrijeme;

    private PitanjeService pitanjeService;

    @Autowired
    public PitanjeController(PitanjeService pitanjeService) {
        this.pitanjeService = pitanjeService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imekorisnika.setText(User.korisnickoIme);
        brojOdgovorenihPitanja = 0;
        zadaci = pitanjeService.generirajZadatke();
        trenutniZadatak = zadaci.get(0);
        timeline = new Timeline(new KeyFrame(
                javafx.util.Duration.millis(1000),
                ae -> {
                    Duration zaIspis = Duration.between(Instant.now(), krajnjeVrijeme);
                    vrijemeText.setText(zaIspis.toMinutesPart() + ":" + zaIspis.toSecondsPart());
                }));
        timeline.setCycleCount(Animation.INDEFINITE);

        postaviMjestoZaOdgovor();
        prikaziZadatak();
        pokreniStopericu();
    }

    private void pokreniStopericu() {
        vrijemePocetak = Instant.now();
        krajnjeVrijeme = vrijemePocetak.plusSeconds(5*60);
        timeline.play();
    }

    private void prikaziZadatak() {
        brojPitanjaLabel.setText("Pitanje " + (brojOdgovorenihPitanja + 1));
        pitanjeLabel.setText(trenutniZadatak.getPitanje());

        matrica1Label.setText(trenutniZadatak.getMatrica1().toString());
        matrica2Label.setText(trenutniZadatak.getMatrica2().toString());
    }

    private void postaviMjestoZaOdgovor() {
        if (trenutniZadatak instanceof ZadatakMatricaOdgovor) {
            vrstaZadatka = VrstaZadatka.ODGOVOR_MATRICA;
            mainVBox.getChildren().remove(visestrukiOdgovorHbox);

            dimenzijaMatriceRjesenja = ((ZadatakMatricaOdgovor) trenutniZadatak).getIspravnoRjesenje().getDimenzija();

            for (int i = 0; i < dimenzijaMatriceRjesenja; i++) {
                HBox red = new HBox(5);
                for (int j = 0; j < dimenzijaMatriceRjesenja; j++) {
                    TextField element = new TextField("0");
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
        if (vrstaZadatka == VrstaZadatka.ODGOVOR_MATRICA) {
            Matrica unesenaMatrica = procitajUnesenuMatricu();
            ((ZadatakMatricaOdgovor) trenutniZadatak).setKorisnikovoRjesenje(unesenaMatrica);
            zadaci.set(brojOdgovorenihPitanja, trenutniZadatak);
        }
        else if (vrstaZadatka == VrstaZadatka.VISESTRUKI_ODABIR) {
            //todo dodati zadatke s visestrukim odabirom (radio buttoni su vec pridruzeni toggle grupi "odgovori")
        }

        if (++brojOdgovorenihPitanja < zadaci.size()) {
            ucitajSljedeciZadatak();
        } else zavrsiKviz();
    }

    private void zavrsiKviz() {
        vrijemeKraj = Instant.now();
        timeline.stop();
        Duration trajanje = Duration.between(vrijemePocetak, vrijemeKraj);

        pitanjeService.promijeniEkran(pitanjeAnchorPane, Ekrani.REZULTATI, new InfoZaRezultateDto(zadaci, trajanje));
    }

    private void ucitajSljedeciZadatak() {
        trenutniZadatak = zadaci.get(brojOdgovorenihPitanja);
        resetirajVrijednosti();
        postaviMjestoZaOdgovor();
        prikaziZadatak();
    }

    private void resetirajVrijednosti() {
        unosMatriceVBox.getChildren().clear();
        mainVBox.getChildren().removeAll(gumbiHbox, visestrukiOdgovorHbox, matricaOdgovorHbox);
        mainVBox.getChildren().addAll(visestrukiOdgovorHbox, matricaOdgovorHbox, gumbiHbox);
    }

    private Matrica procitajUnesenuMatricu() {
        Matrica unesenaMatrica = new Matrica();
        int dimenzija = dimenzijaMatriceRjesenja;
        unesenaMatrica.setDimenzija(dimenzija);
        double[] arr = new double[dimenzija*dimenzija];
        int i = 0;
        for (Node red : unosMatriceVBox.getChildren()) {
            for (Node textField : ((HBox) red).getChildren()) {
                String element = ((TextField) textField).getText();
                if (element.equals("")) {
                    arr[i++] = 0;
                }
                else {
                    arr[i++] = Double.parseDouble(element);
                }
            }
        }
        unesenaMatrica.setVrijednosti(arr);
        return unesenaMatrica;
    }

    public void onOdustani(MouseEvent mouseEvent) {
        pitanjeService.promijeniEkran(pitanjeAnchorPane, Ekrani.POCETNA, null);
    }
}
