package org.kviz.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Testni service. Obrisati nakon sto kreiramo nesto korisnije.
 */
@Service
public class TestService {
    public void printMessage(String[] arguments) {
        System.out.println("Inside TestService Class. Received below arguments");
        Arrays.stream(arguments).forEach(System.out::println);
    }
}
