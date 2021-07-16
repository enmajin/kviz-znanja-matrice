package org.kviz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kviz.repository.RegistracijaRepository;
import org.kviz.view.SceneManager;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
public class RegistracijaServiceTest {
    @Mock
    private SceneManager sceneManager;

    private RegistracijaService registracijaService;

    @Mock
    private RegistracijaRepository registracijaRepository;

    @BeforeEach
    void setUp() {
        registracijaService = new RegistracijaService(registracijaRepository, sceneManager);
    }

    @Test
    public void dodaj_korisnike() {
        registracijaService.dodajKorisnika(100,"", "", 0);
        verify(registracijaRepository, times(1)).dodajKorisnika(100,"", "", 0);
    }

}
