package org.kviz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.model.Korisnik;
import org.kviz.service.RegistracijaService;
import org.kviz.util.Ekrani;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/registracija.fxml")
public class RegistracijaController implements Initializable {
    public RegistracijaController(RegistracijaService registracijaService) {
        this.registracijaService = registracijaService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    AnchorPane registracijaAnchorPane;

    @FXML
    private Button cancelButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private PasswordField enteragainPasswordField;
    @FXML
    private Text regMessage;
    private final RegistracijaService registracijaService;

    public void regButtonOnAction(ActionEvent event) {
        if (nameTextField.getText().isBlank() == false && lastnameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false && enteragainPasswordField.getText().isBlank() == false) {
            String lozinka = enterPasswordField.getText();
            String ponovilozinka = enteragainPasswordField.getText();
            if(enterPasswordField.getText().equals(enteragainPasswordField.getText())) {
                String ime = nameTextField.getText();
                String prezime = lastnameTextField.getText();
                int rez = 0;
                Korisnik korisnik = registracijaService.dohvatiKorisnika(lozinka, ime);
                if (korisnik.getId() == 0) {
                    int id = registracijaService.max_id();
                    id = id + 1;
                    registracijaService.dodajKorisnika(id, lozinka, ime, rez);
                    regMessage.setText(" Uspješna registracija! ");
                } else {
                    registracijaService.promijeniEkran(registracijaAnchorPane, Ekrani.LOGIN);
                }
            }
            else
                regMessage.setText(" Lozinke nisu iste! ");
        } else {
            regMessage.setText(" Unesite ime, prezime, email i zaporku! ");
        }
    }

        public Korisnik dohvatiKorisnika(String loz, String im) {
            return registracijaService.dohvatiKorisnika(loz, im);
        }

    public int max_id(){
        return registracijaService.max_id();
    }

    public void dodaj_korisnike(int id, String loz, String im, int rez) {
        registracijaService.dodajKorisnika(id, loz, im, rez);
    }


    public void oncancelButton(MouseEvent mouseEvent) {
        registracijaService.promijeniEkran(registracijaAnchorPane, Ekrani.POCETNA);
    }
}
