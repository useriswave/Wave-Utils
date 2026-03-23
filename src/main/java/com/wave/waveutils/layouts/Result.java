package com.wave.waveutils.layouts;

import com.wave.waveutils.icons.Icon;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Result extends VBox {

    private Label directory;
    private String enteredDirectory;
    private String statusText;
    private Group folderOpenIcon;
    private Separator separator;
    private Label status;
    private Label statusInfo;
    private HBox directoryBox;

    public Result() {}

    public Result(String enteredDirectory, String statusText) {
        this.enteredDirectory = enteredDirectory;
        this.statusText = "Complete";

        initUI();
        registerElements();
    }

    private void initUI() {
        directory = new Label(enteredDirectory);

        folderOpenIcon = Icon.loadFolderOpenIcon();

        directoryBox = new HBox(directory, folderOpenIcon);

        separator = new Separator(Orientation.HORIZONTAL);

        status = new Label("Organizing " + statusText);

        statusInfo = new Label("Found x files across y file types");

    }

    private void registerElements() {
        this.getChildren().addAll(directoryBox, separator, status, statusInfo);
    }
}
