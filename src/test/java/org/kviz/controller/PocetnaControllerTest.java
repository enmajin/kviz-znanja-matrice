package org.kviz.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kviz.service.PocetnaService;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PocetnaControllerTest {
    private PocetnaController pocetnaController;

    @Mock
    private PocetnaService pocetnaService;

    @BeforeEach
    void setUp() {
        pocetnaController = new PocetnaController(pocetnaService);
    }

    @Test
    public void sanityTest() {
        assertThat(pocetnaController).isNotNull();
    }


}
