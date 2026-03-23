package com.wave.waveutils.layouts;

import com.wave.waveutils.cards.Card;
import com.wave.waveutils.elementlayouts.HorizontalCards;
import com.wave.waveutils.icons.Icon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class CenterLayout extends VBox {

    private Label tagline;
    private Label instantlyLabel;
    private Label description;
    private TextField directoryField;
    private Button scanButton;
    private HorizontalCards heroCards;
    private VBox heroBox;
    private VBox scannerBox;
    private Group folderOpenIcon;
    private VBox textBox;
    private StackPane textFieldBox;
    private Result result;

    public CenterLayout() {
        initUI();
        result = new Result("C:\\Users\\user\\Folder", "Incomplete");
        result.setVisible(false);
        registerElements();
    }

    private void initUI() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(50);

        tagline = new Label("Organize your files\n");

        instantlyLabel = new Label("instantly");

        textBox = new VBox(tagline, instantlyLabel);

        description = new Label("Choose a file path and have your files get automatically sorted into organized files by their extensions");

        directoryField = new TextField();

        folderOpenIcon = Icon.loadFolderOpenIcon();

        textFieldBox = new StackPane(directoryField, folderOpenIcon);

        scanButton = new Button("Scan");
        scanButton.setOnAction(e -> {
            this.getChildren().remove(result);
            result = new Result(getEnteredDirectory(), "Complete");
            result.setVisible(true);
            this.getChildren().add(result);
        });

        heroBox = new VBox(textBox, description);

        scannerBox = new VBox(textFieldBox, scanButton);

        heroCards = new HorizontalCards(
            new Card("Scans Directory", "Analyzes all file extensions", Icon.loadSearchIcon()),
            new Card("Creates Folders", "One folder per extension", Icon.loadFolderPlusIcon()),
            new Card("Organizes Files", "Moves files automatically", Icon.loadFileStackIcon())
        );

        applyStyles();
        setIds();
    }

    private void applyStyles() {
        tagline.setTextAlignment(TextAlignment.CENTER);
        tagline.setPadding(new Insets(150, 0, 0, 0));

        textBox.setAlignment(Pos.CENTER);

        directoryField.setMaxWidth(600);
        directoryField.setMinHeight(50);
        directoryField.setPromptText("C:\\Users\\user\\Folder");
        directoryField.setFocusTraversable(false);
        directoryField.setPadding(new Insets(0, 0, 0, 50));

        folderOpenIcon.setTranslateX(15);

        textFieldBox.setMaxWidth(directoryField.getMaxWidth());
        textFieldBox.setPadding(new Insets(10));

        StackPane.setAlignment(folderOpenIcon, Pos.CENTER_LEFT);

        scanButton.setPrefSize(150, 50);

        heroBox.setSpacing(15);
        heroBox.setAlignment(Pos.CENTER);

        scannerBox.setAlignment(Pos.CENTER);
        scannerBox.setSpacing(15);
    }

    private void setIds() {
        tagline.setId("tagline");
        instantlyLabel.setId("instantly-label");
        description.setId("description");
        directoryField.setId("directory-field");
        scanButton.setId("scan-button");
    }

    private void registerElements() {
        this.getChildren().addAll(heroBox, scannerBox, heroCards, result);
    }

    public String getEnteredDirectory() {
        return directoryField.getText();
    }
}
