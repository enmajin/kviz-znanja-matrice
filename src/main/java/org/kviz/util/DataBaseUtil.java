package org.kviz.util;

import org.kviz.model.Korisnik;
import org.kviz.model.Matrica;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

@Repository
public class DataBaseUtil {

    private Connection connect() throws SQLException {
        String imeBaze = "test.db";
        String url = "jdbc:sqlite:" + imeBaze;
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }

    public void napuni_bazu() {
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

        kreiraj_tablicu(korisnik);
        kreiraj_tablicu(matrica);

        dodaj_korisnike(1, "perinasifra", "Pero", 0);
        dodaj_korisnike(2, "mirkovasifra", "Mirko", 6);
        dodaj_korisnike(3, "slavkovasifra", "Slavko", 2);
        dodaj_korisnike(4, "darkovasifra", "Darko", 1);
        dodaj_korisnike(5, "ivinasifra", "Ivo", 7);
        dodaj_korisnike(6, "aninaasifra", "Ana", 6);
        dodaj_korisnike(7, "majinasifra", "Maja", 5);
        dodaj_korisnike(8, "ružinasifra", "Ruža", 10);
        dodaj_korisnike(9, "petrinasifra", "Petra", 4);
        dodaj_korisnike(10, "teninasifra", "Tena", 9);

        for (int i = 0; i < 10; i++) { //napravi 10 matrica dimenzija 2x2
            int dimenzija = 2;
            String vrijednosti = "";
            for (int row = 0; row < dimenzija; row++) {
                for (int col = 0; col < dimenzija; col++) {
                    int x = random.nextInt(5 - 0); // od 0 do 5...elementi matrice
                    vrijednosti += x + ",";
                }
            }
            vrijednosti = vrijednosti.substring(0, vrijednosti.length() - 1); //makni zarez na kraju
            dodaj_matrice(i, vrijednosti, dimenzija);
        }

        for (int i = 10; i < 20; i++) { //napravi 10 matrica dimenzija 3x3
            int dimenzija = 3;
            String vrijednosti = "";
            for (int row = 0; row < dimenzija; row++) {
                for (int col = 0; col < dimenzija; col++) {
                    int x = random.nextInt(5 - 0); // od 0 do 5...elementi matrice
                    vrijednosti += x + ",";
                }
            }
            vrijednosti = vrijednosti.substring(0, vrijednosti.length() - 1); //makni zarez na kraju
            dodaj_matrice(i, vrijednosti, dimenzija);
        }

        ArrayList<Matrica> matrice = dohvati_sve_matrice();
        ArrayList<Korisnik> korisnici = dohvati_sve_korisnike();
    }

    public void kreiraj_tablicu(String str) {
        try (Connection conn = connect()) {
            if (conn != null) {
                Statement stm = conn.createStatement();
                stm.execute(str);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void dodaj_korisnike(int i, String loz, String im, int naj_rez) {
        String upit = "INSERT INTO Korisnik (id, lozinka, ime, najbolji_rezultat) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    PreparedStatement prep = conn.prepareStatement(upit);
                    prep.setInt(1, i);
                    prep.setString(2, loz);
                    prep.setString(3, im);
                    prep.setInt(4, naj_rez);
                    prep.executeUpdate();
                } catch (SQLException e) {
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dodaj_matrice(int i, String vr, int dim) {
        String upit = "INSERT INTO Matrica (id, vrijednosti, dimenzija) VALUES (?, ?, ?)";

        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    PreparedStatement prep = conn.prepareStatement(upit);
                    prep.setInt(1, i);
                    prep.setString(2, vr);
                    prep.setInt(3, dim);
                    prep.executeUpdate();
                } catch (SQLException e) {
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Matrica> dohvati_sve_matrice() {
        String upit = " SELECT id, vrijednosti, dimenzija FROM Matrica ";

        ArrayList<Matrica> matrice = new ArrayList<Matrica>();
        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    Statement stm = conn.createStatement();
                    ResultSet rezultat = stm.executeQuery(upit);
                    while (rezultat.next()) {
                        int id = rezultat.getInt("id");
                        String vrijednosti = rezultat.getString("vrijednosti");
                        int dimenzija = rezultat.getInt("dimenzija");

                        Matrica matrica = new Matrica(id, vrijednosti, dimenzija);

                        matrice.add(matrica);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return matrice;
    }

    public ArrayList<Korisnik> dohvati_sve_korisnike() {
        String upit = " SELECT id, lozinka, ime, najbolji_rezultat FROM Korisnik ";

        ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    Statement stm = conn.createStatement();
                    ResultSet rezultat = stm.executeQuery(upit);
                    while (rezultat.next()) {
                        int id = rezultat.getInt("id");
                        String lozinka = rezultat.getString("lozinka");
                        String ime = rezultat.getString("ime");
                        int najbolji_rezultat = rezultat.getInt("najbolji_rezultat");

                        Korisnik korisnik = new Korisnik(id, lozinka, ime, najbolji_rezultat);

                        korisnici.add(korisnik);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return korisnici;
    }
}
