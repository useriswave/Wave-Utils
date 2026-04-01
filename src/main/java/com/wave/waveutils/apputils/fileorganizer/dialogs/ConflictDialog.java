package com.wave.waveutils.apputils.fileorganizer.dialogs;

import com.wave.waveutils.apputils.fileorganizer.enums.FolderAction;
import com.wave.waveutils.apputils.fileorganizer.records.FolderDecision;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;

public class ConflictDialog extends BorderPane {

    private final int WIDTH;
    private final int HEIGHT;
    private File conflictingFolder;
    private Stage stage;
    private HBox centerHBox;
    private StackPane iconPane;
    private Circle circleIcon;
    private VBox textVBox;
    private Label mainTextLabel;
    private Label subTextLabel;
    private HBox bottomHBox;
    private Button useExistingBtn;
    private HBox rightButtonsHBox;
    private Button createCopyBtn;
    private FolderAction folderAction;
    private Window ownerWin;

    public ConflictDialog(File conflictingFolder, Window ownerWin) {
        this.WIDTH = 500;
        this.HEIGHT = 200;
        this.conflictingFolder = conflictingFolder;
        this.ownerWin = ownerWin;

        initUI();
        setIds();
        initStyles();
        registerElements();
    }

    public void showDialog() {
        stage = new Stage();
        var scene = new Scene(this, WIDTH, HEIGHT);

        if (ownerWin != null) {
            stage.initOwner(ownerWin);
        }

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        if (ownerWin != null && ownerWin.getScene() != null) {
            scene.getStylesheets().addAll(ownerWin.getScene().getStylesheets());
        }
        stage.setScene(scene);
        stage.setTitle("Folder Conflict Found");
        stage.showAndWait();
    }

    private void closeDialog() {
        if (stage != null)
            stage.close();
    }

    private void initUI() {
        centerHBox = new HBox();
        iconPane = new StackPane();
        circleIcon = new Circle(21);

        textVBox = new VBox();
        mainTextLabel = new Label("This destination already contains a folder named \"" + conflictingFolder.getName() + "\".");
        subTextLabel = new Label("How would you like to resolve this conflict?");

        bottomHBox = new HBox();
        useExistingBtn = new Button("Use existing");

        rightButtonsHBox = new HBox();
        createCopyBtn = new Button("Create Copy");
    }

    private void initStyles() {
        this.setPrefSize(WIDTH, HEIGHT);
        centerHBox.setAlignment(Pos.TOP_LEFT);
        centerHBox.setSpacing(20);
        centerHBox.setPadding(new Insets(24));

        textVBox.setSpacing(6);
        mainTextLabel.setWrapText(true);
        subTextLabel.setWrapText(true);

        bottomHBox.setAlignment(Pos.CENTER_LEFT);
        bottomHBox.setPadding(new Insets(14, 20, 14, 20));

        rightButtonsHBox.setAlignment(Pos.CENTER_RIGHT);
        rightButtonsHBox.setSpacing(10);

        var buttonPadding = new Insets(6, 16, 6, 16);
        useExistingBtn.setPadding(buttonPadding);
        createCopyBtn.setPadding(buttonPadding);
    }

    private void useExistingHandler() {
        useExistingBtn.setOnAction(e -> {
            this.folderAction = FolderAction.USE_EXISTING;
            closeDialog();
        });

    }

    private void createCopyHandler() {
        createCopyBtn.setOnAction(e -> {
            this.folderAction = FolderAction.CREATE_COPY;
            closeDialog();
        });

    }

    public FolderDecision getUserConflictDecision() {
        useExistingHandler();
        createCopyHandler();

        this.showDialog();
        return new FolderDecision(conflictingFolder, folderAction);
    }

    private void setIds() {
        this.setId("conflict-dialog");
        centerHBox.setId("center-hbox");
        iconPane.setId("icon-pane");
        circleIcon.setId("circle-icon");
        textVBox.setId("text-vbox");
        mainTextLabel.setId("main-text-label");
        subTextLabel.setId("sub-text-label");
        bottomHBox.setId("bottom-hbox");
        useExistingBtn.setId("use-existing-btn");
        rightButtonsHBox.setId("right-buttons-hbox");
        createCopyBtn.setId("create-copy-btn");
    }

    private void registerElements() {
        this.setCenter(centerHBox);
        this.setBottom(bottomHBox);
        iconPane.getChildren().add(circleIcon);
        textVBox.getChildren().addAll(mainTextLabel, subTextLabel);
        centerHBox.getChildren().addAll(iconPane, textVBox);

        // here
        rightButtonsHBox.getChildren().addAll(createCopyBtn);

        var spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        bottomHBox.getChildren().addAll(useExistingBtn, spacer, rightButtonsHBox);
    }
}