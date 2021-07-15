package org.kviz.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.model.Korisnik;
import org.kviz.util.DataBaseUtil;
import org.kviz.util.Ekrani;
import org.kviz.util.User;
import org.springframework.stereotype.Component;

import org.kviz.service.LoginService;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;


@Component
@FxmlView("/view/login.fxml")
public class LoginController implements Initializable {
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    AnchorPane loginAnchorPane;

    @FXML
    private Button cancelButton;
    @FXML
    private Text loginMessage;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;
    private final LoginService loginService;

    /*public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }*/

    public void loginButtonOnAction(ActionEvent event){
        if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){
            String lozinka = enterPasswordField.getText();
            String ime = usernameTextField.getText();
            Korisnik korisnik = loginService.dohvatiKorisnika(lozinka, ime);
            if(korisnik.getId()==0)
                loginMessage.setText(" Ne postoji! ");
            else {
                User.ulogiraj(ime);
                loginService.promijeniEkran(loginAnchorPane, Ekrani.POCETNAKORISNIK);
                //korisnikMessage.setText(" Bok! ");
            }
        }
        else{
            loginMessage.setText(" Unesite ime i zaporku! ");
        }
    }

    public void oncancelButton(MouseEvent mouseEvent) {
        loginService.promijeniEkran(loginAnchorPane, Ekrani.POCETNA);
    }


        public Korisnik dohvatiKorisnika(String loz, String im) {
            return loginService.dohvatiKorisnika(loz, im);
        }
}
