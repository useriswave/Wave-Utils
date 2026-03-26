package com.wave.waveutils.apputils.fileorganizer.cards;

import com.wave.waveutils.apputils.fileorganizer.records.FileInfo;
import com.wave.waveutils.apputils.fileorganizer.icons.Icon;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ExtensionCard extends VBox {

    private Label extensionLabel;
    private Label fileAmountLabel;
    private final String extensionText;
    private final String fileAmountText;
    private Group folderIcon;

    private GridPane innerCardGrid;
    private Button openFolderButton;


    public ExtensionCard(FileInfo fileInfo) {
        extensionText = fileInfo.extensionText();
        fileAmountText = fileInfo.fileAmountText();

        initUI();
        initStyles();
        setIds();
        registerElements();
    }

    private void initUI() {
        extensionLabel = new Label(extensionText);
        fileAmountLabel = new Label(fileAmountText + " Files");
        folderIcon = Icon.loadFolderIcon();

        openFolderButton = new Button("Open Folder");

        innerCardGrid = new GridPane();
        innerCardGrid.add(folderIcon, 0, 0);
        innerCardGrid.add(extensionLabel, 1, 0);
        innerCardGrid.add(fileAmountLabel, 2, 0);
        innerCardGrid.add(openFolderButton, 3, 0);


        viewFolderButtonEventHandler();
    }

    private void initStyles() {

        this.setSpacing(10);

        extensionLabel.setPrefWidth(200);
        fileAmountLabel.setPrefWidth(200);

        innerCardGrid.setHgap(75);
        innerCardGrid.setPadding(new Insets(15, 100, 15, 30));
    }

    private void viewFolderButtonEventHandler() {
        openFolderButton.setOnAction(e -> {
            System.out.println("View Folder Button Clicked");
        });
    }

    private void setIds() {

        extensionLabel.setId("extension-card-label");
        fileAmountLabel.setId("fileamount-extension-card-label");
        folderIcon.setId("foldericon-extension-card");
        openFolderButton.setId("openfolder-button");
        this.setId("extension-card-wrapper");

    }

    private void registerElements() {
        this.getChildren().addAll(innerCardGrid);
    }
}
