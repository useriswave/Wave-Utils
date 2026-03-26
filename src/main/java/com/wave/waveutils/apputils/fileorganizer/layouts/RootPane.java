package com.wave.waveutils.apputils.fileorganizer.layouts;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RootPane extends BorderPane {

    private Stage stage;
    private CenterLayout center;
    private ScrollPane scrollPane;

    public RootPane(Stage stage) {
        this.stage = stage;
        initChildren();
        initStyles();
        registerChildren();
    }

    private void initChildren() {
        center = new CenterLayout(stage);
        scrollPane = new ScrollPane(center);
    }

    private void initStyles() {
        scrollPane.setId("background");
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    private void registerChildren() {
        this.setCenter(scrollPane);
    }

}
