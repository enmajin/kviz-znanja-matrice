package org.kviz.util;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class StageReadyEvent extends ApplicationEvent {
    private ViewEnum viewEnum;

    public StageReadyEvent(Stage stage, ViewEnum viewEnum) {
        super(stage);
        this.viewEnum = viewEnum;
    }

    public Stage getStage() {
        return ((Stage) getSource());
    }

    public ViewEnum getViewEnum() {
        return viewEnum;
    }

    public void setViewEnum(ViewEnum viewEnum) {
        this.viewEnum = viewEnum;
    }
}
