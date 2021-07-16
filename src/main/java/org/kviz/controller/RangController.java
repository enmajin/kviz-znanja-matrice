package org.kviz.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.model.Rezultat;
import org.kviz.service.RangService;
import org.kviz.util.Ekrani;
import org.kviz.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/rang.fxml")
public class RangController implements Initializable {

    private RangService rangService;
    private ArrayList<Rezultat> rezultati;

    @FXML
    AnchorPane rangAnchorPane;
    @FXML
    TableColumn rang;
    @FXML
    TableColumn username;
    @FXML
    TableColumn brojBodova;
    @FXML
    TableColumn vrijemeRjesavanja;
    @FXML
    TableView rangTableView;

    @Autowired
    public RangController(RangService rangService) {
        this.rangService = rangService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rezultati = rangService.dohvatiNajboljeRezultate();
        vrijemeRjesavanja.setCellValueFactory(new PropertyValueFactory<>("vrijeme"));
        username.setCellValueFactory(new PropertyValueFactory<>("ime"));
        brojBodova.setCellValueFactory(new PropertyValueFactory<>("bodovi"));
        rang.setCellValueFactory(new PropertyValueFactory<>("rang"));
        ObservableList tableViewItems = rangTableView.getItems();
        rezultati.forEach(rezultat -> {
                    tableViewItems.add(rezultat);
                }
        );
//        TableRow tableRow = (TableRow) tableViewItems.get(0);
//        tableRow.setStyle("-fx-font-weight: bold");

//        int i = 0;
//        for (Node n: rangTableView.lookupAll("TableRow")) {
//            if (n instanceof TableRow) {
//                TableRow row = (TableRow) n;
//                if (User.ulogiran && ((Rezultat) rangTableView.getItems().get(i)).getIme().equals(User.korisnickoIme)) {
//                    row.getStyleClass().add("willPayRow");
//                    row.setDisable(false);
//                } else {
//                    row.getStyleClass().add("wontPayRow");
//                    row.setDisable(true);
//                }
//                i++;
//                if (i == table.getItems().size())
//                    break;
//            }
//        }
    }

    public void pocetnaButtonOnAction(ActionEvent actionEvent) {
        if(User.korisnickoIme == "")
            rangService.promijeniEkran(rangAnchorPane, Ekrani.POCETNA);
        else
            rangService.promijeniEkran(rangAnchorPane, Ekrani.POCETNAKORISNIK);
    }
}
