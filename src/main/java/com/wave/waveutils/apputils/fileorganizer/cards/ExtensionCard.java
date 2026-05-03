package com.wave.waveutils.apputils.fileorganizer.cards;

import com.wave.waveutils.apputils.fileorganizer.records.FileInfo;
import com.wave.waveutils.apputils.fileorganizer.icons.Icon;
import com.wave.waveutils.apputils.fileorganizer.utils.StringUtils;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ExtensionCard extends VBox {

    private Label extensionLabel;
    private Label fileAmountLabel;
    private final String extensionText;
    private final String fileAmountText;
    private Group folderIcon;
    private GridPane innerCardGrid;
    private Button openButton;
    private final FileInfo fileInfo;


    public ExtensionCard(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
        extensionText = StringUtils.getExtension(fileInfo.file().getName());
        fileAmountText = fileInfo.fileAmountText();
        initUI();
        openButtonEventHandler();
        initStyles();
        setIds();
        registerElements();
    }

    private void initUI() {
        extensionLabel = new Label(extensionText);
        fileAmountLabel = new Label(fileAmountText + " Files");
        folderIcon = Icon.loadFolderIcon();

        openButton = new Button("Open Folder");

        innerCardGrid = new GridPane();
        innerCardGrid.add(folderIcon, 0, 0);
        innerCardGrid.add(extensionLabel, 1, 0);
        innerCardGrid.add(fileAmountLabel, 2, 0);
        innerCardGrid.add(openButton, 3, 0);

    }

    public void openButtonEventHandler() {
        openButton.setOnAction(event -> {
            //var folder = new File(fileInfo.file().getAbsolutePath());
            String path = fileInfo.file().getAbsolutePath();
            var os = System.getProperty("os.name").toLowerCase();

                try {
                    if(os.contains("win")) {
                        new ProcessBuilder("explorer.exe", path).start();
                    }
                    else if(os.contains("mac")) {
                        new ProcessBuilder("open", path).start();
                    }
                    else {
                        new ProcessBuilder("xdg-open", path).start();
                    }

                } catch(IOException e) {
                    e.printStackTrace();
                }
        });
    }

    private void initStyles() {
        this.setSpacing(10);

        extensionLabel.setPrefWidth(200);
        fileAmountLabel.setPrefWidth(200);
        innerCardGrid.setHgap(75);
        innerCardGrid.setPadding(new Insets(15, 100, 15, 30));
    }

    private void setIds() {
        extensionLabel.setId("extension-card-label");
        fileAmountLabel.setId("fileamount-extension-card-label");
        folderIcon.setId("foldericon-extension-card");
        openButton.setId("openbtn");
        this.setId("extension-card-wrapper");
    }

    private void registerElements() {
        this.getChildren().addAll(innerCardGrid);
    }
}
