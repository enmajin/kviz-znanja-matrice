package org.kviz.util;

import org.kviz.model.Korisnik;
import org.kviz.model.Matrica;
import org.kviz.model.Rezultat;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Duration;
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
                + " vrijeme varchar(60) \n"
                + ");";

        String matrica = " CREATE TABLE IF NOT EXISTS Matrica (\n"
                + " id Integer NOT NULL PRIMARY KEY ,\n"
                + " vrijednosti varchar(255) NOT NULL ,\n"
                + " dimenzija integer NOT NULL \n"
                + ");";

        kreiraj_tablicu(korisnik);
        kreiraj_tablicu(matrica);

        dodajKorisnika(1, "perinasifra", "Pero", 0);
        dodajKorisnika(2, "mirkovasifra", "Mirko", 2);
        dodajKorisnika(3, "slavkovasifra", "Slavko", 1);
        dodajKorisnika(4, "darkovasifra", "Darko", 3);
        dodajKorisnika(5, "ivinasifra", "Ivo", 4);
        dodajKorisnika(6, "aninaasifra", "Ana", 2);
        dodajKorisnika(7, "majinasifra", "Maja", 2);
        dodajKorisnika(8, "ružinasifra", "Ruža", 5);
        dodajKorisnika(9, "petrinasifra", "Petra", 4);
        dodajKorisnika(10, "teninasifra", "Tena", 4);

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

    public void dodajKorisnika(int i, String loz, String im, int naj_rez) {
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

                        Matrica matrica = new Matrica(vrijednosti, dimenzija);

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

    public Matrica dohvatiMatricuDimenzijeN(int n) {
        Matrica matrica = new Matrica();

        String upit = "SELECT vrijednosti, dimenzija FROM Matrica " +
                "WHERE dimenzija = ? " +
                "ORDER BY RANDOM() " +
                "LIMIT 1";

        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    PreparedStatement prep = conn.prepareStatement(upit);
                    prep.setInt(1, n);
                    ResultSet rezultat = prep.executeQuery();
                    matrica = new Matrica(
                            rezultat.getString("vrijednosti"),
                            rezultat.getInt("dimenzija")
                    );
                } catch (SQLException e) {
                    //todo dodati logiranje exceptiona
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return matrica;
    }

    public Korisnik dohvatiKorisnika(String loz, String im) {
        Korisnik korisnik = new Korisnik();

        String upit = "SELECT id, lozinka, ime, najbolji_rezultat FROM Korisnik " +
                "WHERE lozinka = ? AND ime = ? ";

        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    PreparedStatement prep = conn.prepareStatement(upit);
                    prep.setString(1, loz);
                    prep.setString(2, im);
                    ResultSet rezultat = prep.executeQuery();
                    korisnik = new Korisnik(
                            rezultat.getInt("id"),
                            rezultat.getString("lozinka"),
                            rezultat.getString("ime"),
                            rezultat.getInt("najbolji_rezultat")
                    );
                } catch (SQLException e) {
                    //todo dodati logiranje exceptiona
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return korisnik;
    }

    public int max_id() {
        String upit = "SELECT MAX(id) as id FROM Korisnik";
        int id = 0;
        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    Statement stm = conn.createStatement();
                    ResultSet rezultat = stm.executeQuery(upit);
                    id = rezultat.getInt("id");

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    public ArrayList<Rezultat> dohvatiNajboljeRezultate() {
        ArrayList<Rezultat> rezultati = new ArrayList<>();
        String upit = "SELECT ROW_NUMBER() OVER (" +
                "ORDER BY najbolji_rezultat DESC" +
                ") rang, id, lozinka, ime, najbolji_rezultat, vrijeme FROM Korisnik " +
                "ORDER BY najbolji_rezultat DESC LIMIT 10";
        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    Statement stm = conn.createStatement();
                    ResultSet rezultat = stm.executeQuery(upit);
                    while (rezultat.next()) {
                        String ime = rezultat.getString("ime");
                        int najboljiRezultat = rezultat.getInt("najbolji_rezultat");
                        int rang = rezultat.getInt("rang");
                        rezultati.add(new Rezultat(ime, najboljiRezultat, Duration.ofMillis(0), rang));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return rezultati;
    }

    public int dohvatiNajboljiRezultat(String username) {
        String upit = "SELECT najbolji_rezultat FROM Korisnik WHERE ime = ?";
        int najboljiRezultat = 0;
        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    PreparedStatement prep = conn.prepareStatement(upit);
                    prep.setString(1, username);
                    ResultSet rezultat = prep.executeQuery();
                    najboljiRezultat = rezultat.getInt("najbolji_rezultat");

                } catch (SQLException e) {
                    e.printStackTrace();
                    return -1;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return najboljiRezultat;
    }

    /*public int dohvatiVrijeme(String username){
        String upit = "SELECT rang, id, lozinka, ime, najbolji_rezultat, vrijeme FROM Korisnik WHERE name = ?";
        int najboljeVrijeme = -1;
        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    PreparedStatement prep = conn.prepareStatement(upit);
                    prep.setString(1,username);
                    ResultSet rezultat = prep.executeQuery(upit);
                    najboljeVrijeme = rezultat.getInt("vrijeme");

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    return -1;
                }
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return najboljeVrijeme;
    }*/

    public void ažurirajNajboljiRezultat(String username, int rezultat){
        String upit = "UPDATE Korisnik SET najbolji_rezultat = ? WHERE ime = ?";
        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    PreparedStatement prep = conn.prepareStatement(upit);
                    prep.setInt(1, rezultat);
                    prep.setString(2, username);
                    prep.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ažurirajVrijeme(String username, Duration vrijeme){
        String upit = "UPDATE table Korisnik SET vrijeme = ? WHERE ime = ?";
        try (Connection conn = connect()) {
            if (conn != null) {
                try {
                    PreparedStatement prep = conn.prepareStatement(upit);
                    prep.setString(1, String.valueOf(vrijeme));
                    prep.setString(2,username);
                    prep.executeUpdate();
                } catch (SQLException e) {
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
