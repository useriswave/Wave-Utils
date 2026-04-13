package com.wave.waveutils.apputils.fileorganizer.layouts.children;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URI;

public class TopLayout extends StackPane {

    private VBox layout;
    private BorderPane bar;
    private Label name;
    private Label title;
    private Button discord;
    private Button github;
    private Separator sep;
    private HBox rightButtons;

    public TopLayout() {
        initUI();
        initStyles();
        buttonEvents();
        setIds();
        registerElements();
    }

    private void initUI() {
        name = new Label("@useriswave");
        sep = new Separator();
        github = new Button("GitHub");
        discord = new Button("Discord");
        bar = new BorderPane();
        layout = new VBox();
        title = new Label("File Organizer");
        rightButtons = new HBox(10, discord, github);
    }

    public void initStyles() {
        title.setAlignment(Pos.CENTER);
        discord.setPadding(new Insets(10, 20, 10, 20));
        discord.setFocusTraversable(false);
        github.setPadding(new Insets(10, 20, 10, 20));
        github.setFocusTraversable(false);
        rightButtons.setAlignment(Pos.CENTER_RIGHT);
        bar.setPadding(new Insets(10, 30, 10, 30));
        bar.setLeft(name);
        bar.setRight(rightButtons);
    }

    private void buttonEvents() {
        openUrlOnButtonClick(github, "https://github.com/useriswave");
        openUrlOnButtonClick(discord, "https://discord.com/users/971493987158482944");
        applyHover(discord);
        applyHover(github);
    }

    private void openUrlOnButtonClick(Button btn, String url) {
        btn.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void applyHover(Button btn) {
        btn.setOnMouseEntered( event-> {
            btn.setOpacity(0.5);
        });
        btn.setOnMouseExited(e -> {
            btn.setOpacity(1);
        });
    }


    private void setIds() {
        title.setId("top-title");
        name.setId("top-name");
        discord.setId("top-discord");
        github.setId("top-discord");
    }

    public void registerElements() {
        layout.getChildren().addAll(bar, sep);
        this.getChildren().addAll(layout, title);
    }
}