package org.kviz.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.kviz.util.StageReadyEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    private final String applicationTitle;
    private final FxWeaver fxWeaver;
    private Stage stage;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle, FxWeaver fxWeaver) {
        this.applicationTitle = applicationTitle;
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        stage = event.getStage();
        Class controllerClass = event.getViewEnum().getControllerClass();
        Parent root = (Parent) fxWeaver.loadView(controllerClass);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(applicationTitle);
        stage.show();
    }
}
