package com.wave.waveutils.apputils.fileorganizer.layouts;

import com.wave.waveutils.apputils.fileorganizer.cards.ResultCard;
import com.wave.waveutils.apputils.fileorganizer.enums.ResultCardType;
import com.wave.waveutils.apputils.fileorganizer.icons.Icon;
import com.wave.waveutils.apputils.fileorganizer.logic.FileOrganizer;
import com.wave.waveutils.apputils.fileorganizer.records.FileInfo;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Result extends VBox {

    private Label directory;
    private String enteredDirectory;
    private String statusText;
    private Group folderOpenIcon;
    private Separator separator;
    private Label status;
    private Label statusInfo;
    private HBox directoryBox;
    private HBox resultCardsBox;
    private ExtensionsScroller extensionsScroller;
    private VBox operationInfoWrapper;
    private ArrayList<FileInfo> fileInfoList;
    private FileOrganizer fileOrganizer;

    public Result() {}

    public Result(String enteredDirectory, String statusText, ArrayList<FileInfo> fileInfoList, FileOrganizer fileOrganizer) {
        this.enteredDirectory = enteredDirectory;
        this.statusText = "Complete";
        this.fileInfoList = fileInfoList;
        this.fileOrganizer = fileOrganizer;

        initUI();
        registerElements();
        initStyles();
        setIds();
    }

    private void initUI() {

        directory = new Label(enteredDirectory);
        folderOpenIcon = Icon.loadFolderOpenIcon();
        directoryBox = new HBox(folderOpenIcon, directory);
        separator = new Separator(Orientation.HORIZONTAL);
        status = new Label("Organizing " + statusText);
        statusInfo = new Label("Found " + fileOrganizer.getTotalFiles() + " files across " + fileOrganizer.getTotalFileTypes() + " file types");
        operationInfoWrapper = new VBox(status, statusInfo);

        double totalFileSize = fileOrganizer.getTotalFilesSizeGB();
        String formattedSize = String.format("%.2f", totalFileSize);

        var resultCard = new ResultCard(Integer.toString(fileOrganizer.getTotalFiles()), ResultCardType.TOTAL_FILES);
        var resultCard2 = new ResultCard(Integer.toString(fileOrganizer.getTotalFoldersCreated()), ResultCardType.FOLDERS_CREATED);
        var resultCard3 = new ResultCard(formattedSize, ResultCardType.TOTAL_FILE_SIZES);

        resultCardsBox = new HBox();

        resultCardsBox.getChildren().addAll(resultCard, resultCard2, resultCard3);
        resultCardsBox.setVisible(true);

        extensionsScroller = new ExtensionsScroller(fileInfoList);
    }

    private void initStyles() {
        this.setSpacing(50);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefWidth(900);
        this.setMaxWidth(950);

        directoryBox.setSpacing(10);
        directoryBox.setAlignment(Pos.CENTER_LEFT);

        status.setMaxWidth(Double.MAX_VALUE);
        status.setAlignment(Pos.CENTER_LEFT);

        statusInfo.setMaxWidth(Double.MAX_VALUE);
        statusInfo.setAlignment(Pos.CENTER_LEFT);

        operationInfoWrapper.setSpacing(10);


        resultCardsBox.setSpacing(15);
        resultCardsBox.setAlignment(Pos.CENTER);
        resultCardsBox.setMaxWidth(950);
    }

    private void setIds() {
        directory.setId("directory-result");
        separator.setId("separator");
        status.setId("status-result");
        statusInfo.setId("status-info-result");
    }

    private void registerElements() {
        this.getChildren().addAll(
                directoryBox, separator, operationInfoWrapper, resultCardsBox, extensionsScroller
        );
    }
}
