package com.wave.waveutils.apputils.fileorganizer.layouts;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RootPane extends BorderPane {

    private Stage stage;
    private CenterLayout centerLayout;
    private TopLayout topLayout;
    private ScrollPane scrollPane;

    public RootPane(Stage stage) {
        this.stage = stage;
        initChildren();
        initStyles();
        registerChildren();
    }

    private void initChildren() {
        centerLayout = new CenterLayout(stage);
        topLayout = new TopLayout();
        scrollPane = new ScrollPane(centerLayout);
    }

    private void initStyles() {
        scrollPane.setId("background");
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    private void registerChildren() {
        this.setTop(topLayout);
        this.setCenter(scrollPane);
    }

}
