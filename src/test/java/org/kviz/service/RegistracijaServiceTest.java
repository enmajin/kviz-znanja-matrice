package org.kviz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kviz.repository.RegistracijaRepository;
import org.kviz.view.SceneManager;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

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
        registracijaService.dodajKorisnika(Mockito.anyInt(),"", "", 0);
    }

}
