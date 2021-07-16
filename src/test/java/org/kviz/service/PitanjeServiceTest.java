package org.kviz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kviz.model.Matrica;
import org.kviz.model.Zadatak;
import org.kviz.repository.PitanjeRepository;
import org.kviz.view.SceneManager;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PitanjeServiceTest {
    @Mock
    private SceneManager sceneManager;

    private PitanjeService pitanjeService;

    @Mock
    private PitanjeRepository pitanjeRepository;

    @BeforeEach
    void setUp() {
        pitanjeService = new PitanjeService(pitanjeRepository, sceneManager);
    }

    @Test
    public void generirajZadatke() {
        Matrica expectedMatrica = new Matrica("2,3,4,5", 2);
        when(pitanjeRepository.dohvatiMatricuDimenzijeN(Mockito.anyInt()))
                .thenReturn(expectedMatrica);

        ArrayList<Zadatak> zadaci = pitanjeService.generirajZadatke();

        verify(pitanjeRepository, atLeast(10)).dohvatiMatricuDimenzijeN(anyInt());
        assertThat(zadaci.size()).isEqualTo(5);
        for (Zadatak zadatak : zadaci) {
            assertThat(zadatak.getMatrica1()).isEqualTo(expectedMatrica);
        }
    }
}
