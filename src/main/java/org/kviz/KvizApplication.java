package org.kviz;

import javafx.application.Application;
import org.kviz.service.TestService;
import org.kviz.util.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KvizApplication implements CommandLineRunner {

    private final TestService service;
    private final DataBaseUtil dataBaseUtil;

    @Autowired
    public KvizApplication(TestService service, DataBaseUtil dataBaseUtil) {
        this.service = service;
        this.dataBaseUtil = dataBaseUtil;
    }

    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args); //ovo je main metoda koja pokrece cijelu aplikaciju i prikazuje sucelje
    }


    @Override
    public void run(String... args) throws Exception {
        dataBaseUtil.napuni_bazu();
        service.printMessage(args); //ovo je samo test da se nesto ispise u konzolu, ovdje dodajemo stvari koje se trebaju izvrsiti prilikom pokretanja aplikacije
    }
}