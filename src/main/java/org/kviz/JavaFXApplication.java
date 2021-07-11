package org.kviz;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.kviz.util.StageReadyEvent;
import org.kviz.util.ViewEnum;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFXApplication extends Application {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent(stage, ViewEnum.POCETNA));
    }

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        applicationContext = new SpringApplicationBuilder(KvizApplication.class).run(args);
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

}
