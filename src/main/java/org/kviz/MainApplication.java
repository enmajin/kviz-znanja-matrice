package org.kviz;

import javafx.application.Application;
import org.kviz.util.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    private final DataBaseUtil dataBaseUtil;

    @Autowired
    public MainApplication(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }

    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args); //ovo je main metoda koja pokrece cijelu aplikaciju i prikazuje sucelje
    }


    @Override
    public void run(String... args) {
        dataBaseUtil.napuni_bazu();
    }
}