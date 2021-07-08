package org.kviz;

import javafx.application.Application;
import org.kviz.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KvizApplication implements CommandLineRunner {

    @Autowired
    TestService service;

    public static void main(String[] args) {
        Application.launch(JavaFXApp.class, args); //ovo je main metoda koja pokrece cijelu aplikaciju i prikazuje sucelje
    }


    @Override
    public void run(String... args) throws Exception {
        service.printMessage(args); //ovo je samo test da se nesto ispise u konzolu, ovdje dodajemo stvari koje se trebaju iyvrsiti prilikom pokretanja aplikacije
    }
}