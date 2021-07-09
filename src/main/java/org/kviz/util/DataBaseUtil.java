package org.kviz.util;

import java.sql.*;
import java.lang.*;
import org.kviz.model.Korisnik;
import org.kviz.model.Matrica;
import java.util.*;


public class DataBaseUtil {
    public void kreiraj_tablicu(String str){
        String imeBaze = "test.db";
        String url = "jdbc:sqlite:" + imeBaze;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                //DatabaseMetaData meta = conn.getMetaData();
                //System.out.println("Ime biblioteke za rad s bazom podataka " + meta.getDriverName());
                System.out.println("Stvorena je nova baza.");
                Statement stm =  conn.createStatement();
                stm.execute(str);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void dodaj_korisnike(int i, String loz, String im, int naj_rez){
        String upit = "INSERT INTO Korisnik (id, lozinka, ime, najbolji_rezultat) VALUES (?, ?, ?, ?)";

        String imeBaze = "test.db";
        String url = "jdbc:sqlite:" + imeBaze;
        try (Connection conn = DriverManager.getConnection(url)) {
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
    public void dodaj_matrice(int i, String vr, int dim){
        String upit = "INSERT INTO Matrica (id, vrijednosti, dimenzija) VALUES (?, ?, ?)";

        String imeBaze = "test.db";
        String url = "jdbc:sqlite:" + imeBaze;
        try (Connection conn = DriverManager.getConnection(url)) {
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

        String imeBaze = "test.db";
        String url = "jdbc:sqlite:" + imeBaze;
        ArrayList<Matrica> matrice = new ArrayList<Matrica>();
        try (Connection conn = DriverManager.getConnection(url)) {
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

        String imeBaze = "test.db";
        String url = "jdbc:sqlite:" + imeBaze;
        ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
        try (Connection conn = DriverManager.getConnection(url)) {
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
