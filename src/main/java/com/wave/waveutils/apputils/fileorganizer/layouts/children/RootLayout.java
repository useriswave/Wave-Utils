package com.wave.waveutils.apputils.fileorganizer.layouts.children;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RootLayout extends BorderPane {

    private final Stage stage;
    private CenterLayout centerLayout;
    private TopLayout topLayout;
    private ScrollPane scrollPane;

    public RootLayout(Stage stage) {
        this.stage = stage;
        initChildren();
        initStyles();
        registerChildren();
    }

    private void initChildren() {
        centerLayout = new CenterLayout(stage);
        topLayout = new TopLayout();
        scrollPane = new ScrollPane(centerLayout.getRoot());
    }

    private void initStyles() {
        scrollPane.setId("background");
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    private void registerChildren() {
        this.setTop(topLayout.getRoot());
        this.setCenter(scrollPane);
    }

}
