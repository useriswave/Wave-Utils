package com.wave.waveutils.apputils.fileorganizer.layouts.children;

import com.wave.waveutils.apputils.fileorganizer.records.FileInfo;
import com.wave.waveutils.apputils.fileorganizer.cards.ExtensionCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ExtensionsScroller extends VBox {

    private ScrollPane scrollPane;
    private final ArrayList<FileInfo> fileInfoList;

    private HBox scrollerBox;
    private VBox elementBox;
    private Label folderLabel;
    private Label filesFoundLabel;
    private Label openFolderLabel;

    public ExtensionsScroller(ArrayList<FileInfo> fileInfoList) {
        this.fileInfoList = fileInfoList;
        initUI();
        initStyles();
        setIds();
        registerElements();
    }

    private void initUI() {
        elementBox = new VBox();
        scrollPane = new ScrollPane(elementBox);

        folderLabel = new Label("FOLDER");
        filesFoundLabel = new Label("FILES FOUND");
        openFolderLabel = new Label("OPEN FOLDER");

        for(FileInfo fileInfo : fileInfoList) {
            scrollerBox = new HBox();
            scrollerBox.setAlignment(Pos.CENTER_LEFT);
            scrollerBox.setSpacing(30);
            scrollerBox.getChildren().addAll(new ExtensionCard(fileInfo));

            elementBox.getChildren().add(scrollerBox);
        }
    }

    private void initStyles() {
        this.setPrefWidth(400);
        this.setMaxHeight(300);
        scrollPane.setPadding(new Insets(15, 30, 15, 30));
        elementBox.setSpacing(15);
    }

    private void setIds() {
        folderLabel.setId("folder-label");
        filesFoundLabel.setId("filesfound-label");
        openFolderLabel.setId("openfolder-label");
        scrollPane.setId("scroller-bg");
        this.setId("scroller-wrapper");
    }

    private void registerElements() {
        this.getChildren().addAll(scrollPane);
    }
}
