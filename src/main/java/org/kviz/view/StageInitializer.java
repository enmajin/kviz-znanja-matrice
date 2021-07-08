package org.kviz.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.kviz.JavaFXApp;
import org.kviz.controller.FXMLController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<JavaFXApp.StageReadyEvent> {
    private final String applicationTitle;
    private final FxWeaver fxWeaver;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle, FxWeaver fxWeaver) {
        this.applicationTitle = applicationTitle;
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(JavaFXApp.StageReadyEvent event) {
        Stage stage = event.getStage();
        Parent root = fxWeaver.loadView(FXMLController.class);
        Scene scene = new Scene(root);
        stage.setTitle(applicationTitle);
        stage.setScene(scene);
        stage.show();
    }
}
