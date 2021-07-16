package org.kviz.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.kviz.model.Rezultat;
import org.kviz.service.RangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
@FxmlView("/view/rang.fxml")
public class RangController implements Initializable {

    @FXML
    TableColumn rang;
    @FXML
    TableColumn username;
    @FXML
    TableColumn brojBodova;
    @FXML
    TableColumn vrijemeRjesavanja;
    @FXML
    TableView<Rezultat> rangTableView;
    private RangService rangService;
    private ArrayList<Rezultat> rezultati;

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
    }
}