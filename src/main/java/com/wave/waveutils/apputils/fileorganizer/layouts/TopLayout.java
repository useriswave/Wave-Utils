package com.wave.waveutils.apputils.fileorganizer.layouts;

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

/*
 this class is re-used from my Wordle clone project, will continue to use this until further notice.
 https://github.com/useriswave/JavaFX-Wordle-Clone/tree/main
 */

public class TopLayout extends StackPane {

    public TopLayout() {

        VBox layout = new VBox();

        BorderPane bar = new BorderPane();
        bar.setPadding(new Insets(10, 30, 10, 30));

        Label name = new Label("@useriswave");
        Label title = new Label("File Organizer");
        title.setStyle("-fx-font-weight: bold;\n" +
                "-fx-font-size: 30;");
        title.setAlignment(Pos.CENTER);
        name.setStyle("-fx-font-weight: bold;\n" +
                "-fx-font-size: 15;");

        Button discord = new Button("Discord");
        discord.setStyle("-fx-background-color: #5865f2;" + "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" + "-fx-cursor: hand;" + "-fx-background-radius: 15px;");
        discord.setPadding(new Insets(10, 20, 10, 20));

        Button github = new Button("GitHub");
        github.setStyle("-fx-background-color: black;" + "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" + "-fx-cursor: hand;" + "-fx-background-radius: 15px;");
        github.setPadding(new Insets(10, 20, 10, 20));
        discord.setFocusTraversable(false);
        github.setFocusTraversable(false);

        hover(discord);
        hover(github);
        onDiscordButtonClick(discord);
        onGithubButtonClick(github);

        HBox rightButtons = new HBox(10, discord, github);
        rightButtons.setAlignment(Pos.CENTER_RIGHT);

        Separator sep = new Separator();

        bar.setLeft(name);
        bar.setRight(rightButtons);

        StackPane.setAlignment(title, Pos.CENTER);

        layout.getChildren().addAll(bar, sep);

        getChildren().addAll(layout, title);
    }

    private void hover(Button btn1) {
        btn1.setOnMouseEntered( event-> {
            btn1.setOpacity(0.5);
        });
        btn1.setOnMouseExited(e -> {
            btn1.setOpacity(1);
        });
    }

    private void onDiscordButtonClick(Button btn) {
        btn.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://discord.com/users/971493987158482944"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void onGithubButtonClick(Button btn) {
        btn.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/useriswave"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}



