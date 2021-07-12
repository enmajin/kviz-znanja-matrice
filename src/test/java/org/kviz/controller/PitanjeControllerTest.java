package org.kviz.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kviz.service.PitanjeService;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class PitanjeControllerTest {
    private PitanjeController pitanjeController;

    @Mock
    private PitanjeService pitanjeService;

    @BeforeEach
    void setUp() {
        pitanjeController = new PitanjeController(pitanjeService);
    }

    @Test
    public void sanityTest() {
        assertThat(pitanjeController).isNotNull();
    }
}
