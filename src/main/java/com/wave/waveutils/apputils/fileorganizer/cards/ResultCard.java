package com.wave.waveutils.apputils.fileorganizer.cards;

import com.wave.waveutils.apputils.fileorganizer.enums.ResultCardType;
import com.wave.waveutils.apputils.fileorganizer.icons.Icon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResultCard extends HBox {

    private Label amount;
    private Label info;
    private Group cardIcon;
    private VBox labelsBox;

    private static final double ICON_SCALE_VALUE = 1.2;

    public ResultCard(String amountText, ResultCardType type) {
        initUI(amountText);
        configureCard(type, amountText);
        initStyles();
        setIds();
        registerElements();
    }

    private void initUI(String amountText) {
        amount = new Label(amountText);
        info = new Label();
        labelsBox = new VBox(amount, info);
    }

    private void configureCard(ResultCardType type, String amountText) {
        switch (type) {
            case TOTAL_FILES -> {
                info.setText("TOTAL FILES");
                cardIcon = Icon.loadFilesIcon();
            }

            case FOLDERS_CREATED -> {
                info.setText("FOLDERS CREATED");
                cardIcon = Icon.loadFolderOpenIcon();
            }

            case TOTAL_FILE_SIZES -> {
                amount.setText(amountText + " GB");
                info.setText("TOTAL FILE SIZES");
                cardIcon = Icon.loadSparklesIcon();
            }
        }
    }

    private void initStyles() {
        setSpacing(50);
        setAlignment(Pos.CENTER_LEFT);
        setPrefWidth(500);
        setMinHeight(100);
        setMaxHeight(100);
        setPadding(new Insets(30));

        cardIcon.setScaleX(ICON_SCALE_VALUE);
        cardIcon.setScaleY(ICON_SCALE_VALUE);

        labelsBox.setAlignment(Pos.CENTER_LEFT);
    }

    private void setIds() {
        setId("result-card");
        amount.setId("amount-result-card");
        info.setId("info-result-card");
        cardIcon.setId("icon-result-card");
    }

    private void registerElements() {
        getChildren().addAll(cardIcon, labelsBox);
    }
}