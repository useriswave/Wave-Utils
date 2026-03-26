package com.wave.waveutils.apputils.fileorganizer.cards;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class Card extends VBox {

    private Label main;
    private Label description;
    private final String mainContent;
    private final String descriptionContent;
    private String id = "card";
    private final Group icon;

    public Card() {

        this.mainContent = "Main Text";
        this.descriptionContent = "Description Text";
        this.icon = new Group();

        initUI();
        registerElements();
        setIds();
    }

    public Card(String mainContent, String descriptionContent, String id) {
        this.mainContent = mainContent;
        this.descriptionContent = descriptionContent;
        this.id = id;
        this.icon = new Group();

        initUI();
        registerElements();
        setIds();
    }

    public Card(String mainContent, String descriptionContent) {
        this.mainContent = mainContent;
        this.descriptionContent = descriptionContent;
        this.icon = new Group();

        initUI();
        registerElements();
        setIds();
    }

    public Card(String mainContent, String descriptionContent, Group icon) {
        this.mainContent = mainContent;
        this.descriptionContent = descriptionContent;
        this.icon = icon;

        initUI();
        registerElements();
        setIds();
    }

    private void initUI() {
        this.setPrefSize(300, 200);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
//        this.setBorder(new Border(new BorderStroke(
//                Color.web("#303030"),
//                BorderStrokeStyle.SOLID,
//                CornerRadii.EMPTY,
//                new BorderWidths(1)
//        )));
        main = new Label(mainContent);
        description = new Label(descriptionContent);
    }

    private void setIds() {
        this.setId(id);
        main.setId("main-card-text");
        description.setId("description-card-text");
    }

    private void registerElements() {
        this.getChildren().addAll(icon, main, description);
    }
}
