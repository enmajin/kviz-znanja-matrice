package org.kviz.util;

public class User {
    public static boolean ulogiran;
    public static String korisnickoIme;

    public static void ulogiraj(String korisnickoIme) {
        ulogiran = true;
        User.korisnickoIme = korisnickoIme;
    }

    public static void odjavi() {
        ulogiran = false;
        User.korisnickoIme = "";
    }

}
