package org.kviz.view;

import javafx.stage.Stage;
import org.kviz.model.InfoZaRezultateDto;
import org.kviz.util.Ekrani;
import org.kviz.util.StageReadyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SceneManager {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public SceneManager(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void promijeniEkran(Stage stage, Ekrani ekran, Object data) {
        StageReadyEvent stageReadyEvent = new StageReadyEvent(stage, ekran);
        if (ekran == Ekrani.REZULTATI) {
            stageReadyEvent.setData((InfoZaRezultateDto) data);
        }
        applicationEventPublisher.publishEvent(stageReadyEvent);
    }
}