package com.wave.waveutils.apputils.fileorganizer.cards;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResultCard extends HBox {

    // labels will use real data

    private Label amount;
    private Label info;
    private Group cardIcon;
    private VBox labelsBox;
    private final double iconScaleValue = 1.2;
    private String amountText;

    public ResultCard(String amountText, Group cardIcon) {
        this.amountText = amountText;
        this.cardIcon = cardIcon;
        initUI();
        initStyles();
        setIds();
        registerElements();
    }

    private void initUI() {
        amount = new Label(amountText);

        info = new Label("Some info");

        labelsBox = new VBox(amount, info);

    }

    private void initStyles() {
        this.setSpacing(50);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefWidth(500);
        this.setMinHeight(100);
        this.setMaxHeight(100);
        this.setPadding(new Insets(30));
        cardIcon.setScaleX(iconScaleValue);
        cardIcon.setScaleY(iconScaleValue);
        labelsBox.setAlignment(Pos.CENTER_LEFT);

    }


    private void setIds() {
        this.setId("result-card");
        amount.setId("amount-result-card");
        info.setId("info-result-card");
        cardIcon.setId("icon-result-card");
    }

    private void registerElements() {
        this.getChildren().addAll(cardIcon, labelsBox);
    }

}
