package com.wave.waveutils.apputils.fileorganizer.layouts.children;

import com.wave.waveutils.apputils.fileorganizer.constants.Constants;
import com.wave.waveutils.apputils.fileorganizer.layouts.base.BaseLayout;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

public class TopLayout extends BaseLayout {
    private StackPane root;
    private VBox layout;
    private BorderPane bar;
    private Label name;
    private Label title;
    private Button discord;
    private Button github;
    private Separator sep;
    private HBox rightButtons;

    public TopLayout() {
        build();
    }

    @Override
    protected void initRoot() {
         this.root = new StackPane();
    }

    @Override
    protected void initUI() {
        name = new Label(Constants.CREATOR);
        sep = new Separator();
        github = new Button("GitHub");
        discord = new Button("Discord");
        bar = new BorderPane();
        layout = new VBox();
        title = new Label("File Organizer");
        rightButtons = new HBox(10, discord, github);
    }

    @Override
    protected void initStyles() {
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

     protected void handleEvents() {
        openUrlOnButtonClick(github, "https://github.com/useriswave");
        openUrlOnButtonClick(discord, "https://discord.com/users/971493987158482944");
        applyHover(discord);
        applyHover(github);
    }

    private void openUrlOnButtonClick(Button btn, String url) {
        btn.setOnAction(event -> {
                if(Desktop.isDesktopSupported()) {
                    Desktop desktop= Desktop.getDesktop();
                    URI uri = null;
                    try {
                        uri = new URI(url);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                    if(desktop.isSupported(Desktop.Action.BROWSE)) {
                        try {
                            desktop.browse(uri);
                        } catch (Exception e) {
                            System.out.println("Unable to open URL");
                        }
                    }
                    else {
                        // fallback for linux systems
                        try {
                            Runtime.getRuntime().exec(new String[]{"xdg-open", uri.toString()});
                        }
                        catch(Exception e) {
                            System.out.println("ERROR: Your system cannot open the URL.");
                        }
                    }
                }
        });
    }

    @Override
    protected void setIds() {
        title.setId("top-title");
        name.setId("top-name");
        discord.setId("top-discord");
        github.setId("top-github");
    }

    @Override
    protected void registerElements() {
        layout.getChildren().addAll(bar, sep);
        root.getChildren().addAll(layout, title);
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}