package org.kviz.view;

import javafx.stage.Stage;
import org.kviz.util.StageReadyEvent;
import org.kviz.util.ViewEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SceneManager{
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public SceneManager(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void promijeniEkran(Stage stage, ViewEnum viewEnum) {
        applicationEventPublisher.publishEvent(new StageReadyEvent(stage, viewEnum));
    }
}