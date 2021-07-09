package org.kviz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kviz.model.Korisnik;
import org.kviz.model.Matrica;
import org.kviz.util.DataBaseUtil;

import java.util.ArrayList;
import java.util.Random;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        DataBaseUtil dbUtil = new DataBaseUtil();
        Random random = new Random();

        String korisnik = " CREATE TABLE IF NOT EXISTS Korisnik (\n"
                + " id Integer NOT NULL PRIMARY KEY ,\n"
                + " lozinka varchar(60) NOT NULL ,\n"
                + " ime varchar(60) NOT NULL ,\n"
                + " najbolji_rezultat integer \n"
                + ");";

        String matrica = " CREATE TABLE IF NOT EXISTS Matrica (\n"
                + " id Integer NOT NULL PRIMARY KEY ,\n"
                + " vrijednosti varchar(255) NOT NULL ,\n"
                + " dimenzija integer NOT NULL \n"
                + ");";

        dbUtil.kreiraj_tablicu(korisnik);
        dbUtil.kreiraj_tablicu(matrica);

        dbUtil.dodaj_korisnike(1,"perinasifra", "Pero", 0);
        dbUtil.dodaj_korisnike(2,"mirkovasifra", "Mirko", 6);
        dbUtil.dodaj_korisnike(3,"slavkovasifra", "Slavko", 2);
        dbUtil.dodaj_korisnike(4,"darkovasifra", "Darko", 1);
        dbUtil.dodaj_korisnike(5,"ivinasifra", "Ivo", 7);
        dbUtil.dodaj_korisnike(6,"aninaasifra", "Ana", 6);
        dbUtil.dodaj_korisnike(7,"majinasifra", "Maja", 5);
        dbUtil.dodaj_korisnike(8,"ružinasifra", "Ruža", 10);
        dbUtil.dodaj_korisnike(9,"petrinasifra", "Petra", 4);
        dbUtil.dodaj_korisnike(10,"teninasifra", "Tena", 9);

        for(int i=0; i<10; i++){ //napravi 10 matrica dimenzija 2x2
            int dimenzija = 2;
            String vrijednosti = "";
            for(int row=0; row<dimenzija; row++){
                for(int col=0; col<dimenzija; col++){
                    int x = random.nextInt(5 - 0); // od 0 do 5...elementi matrice
                    vrijednosti += x + ",";
                }
            }
            vrijednosti = vrijednosti.substring(0, vrijednosti.length() - 1); //makni zarez na kraju
            dbUtil.dodaj_matrice(i, vrijednosti, dimenzija);
        }

        for(int i=10; i<20; i++){ //napravi 10 matrica dimenzija 3x3
            int dimenzija = 3;
            String vrijednosti = "";
            for(int row=0; row<dimenzija; row++){
                for(int col=0; col<dimenzija; col++){
                    int x = random.nextInt(5 - 0); // od 0 do 5...elementi matrice
                    vrijednosti += x + ",";
                }
            }
            vrijednosti = vrijednosti.substring(0, vrijednosti.length() - 1); //makni zarez na kraju
            dbUtil.dodaj_matrice(i, vrijednosti, dimenzija);
        }

        ArrayList<Matrica> matrice = dbUtil.dohvati_sve_matrice();
        ArrayList<Korisnik> korisnici = dbUtil.dohvati_sve_korisnike();

        URL url = getClass().getClassLoader().getResource("view/scene.fxml");
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles.css").toExternalForm());

        stage.setTitle("Kviz znanja - matrice");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
