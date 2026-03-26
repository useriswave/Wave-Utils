package com.wave.waveutils.apputils.fileorganizer.layouts;

import com.wave.waveutils.apputils.fileorganizer.cards.Card;
import com.wave.waveutils.apputils.fileorganizer.cards.HorizontalCards;
import com.wave.waveutils.apputils.fileorganizer.icons.Icon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;

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
    private HBox textFieldWrapper;
    private Group addDirectoryButton;
    private final Stage stage;
    private File folder;
    private boolean fieldClicked = false;

    public CenterLayout(Stage stage) {
        this.stage = stage;
        initUI();
        result = new Result("C:\\Users\\user\\  Folder", "Incomplete");
        result.setVisible(false);
        result.setManaged(false);
        setIds();
        initStyles();
        registerElements();
    }

    /*
    =============================
              UI STUFF
    =============================
     */

    private void initUI() {
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(50);

        tagline = new Label("Organize your files\n");
        instantlyLabel = new Label("instantly");

        textBox = new VBox(tagline, instantlyLabel);

        description = new Label("Choose a file path and have your files get automatically sorted into organized files by their extensions");
        directoryField = new TextField();

        folderOpenIcon = Icon.loadFolderOpenIcon();

        // **************************************************************************************

        textFieldBox = new StackPane(directoryField, folderOpenIcon);

        addDirectoryButton = Icon.loadFolderPlusIcon();
        textFieldWrapper = new HBox(textFieldBox, addDirectoryButton);

        // **************************************************************************************

        scanButton = new Button("Scan");
        System.out.println("ENTERED DIRECTORY: " + getEnteredDirectory());


        scanButtonEventHandler();
        addDirectoryEventHandler();
        onFieldClick();


        heroBox = new VBox(textBox, description);

        //here too
        scannerBox = new VBox(textFieldWrapper, scanButton);

        heroCards = new HorizontalCards(
            new Card("Scans Directory", "Analyzes all file extensions", Icon.loadSearchIcon()),
            new Card("Creates Folders", "One folder per extension", Icon.loadFolderPlusIcon()),
            new Card("Organizes Files", "Moves files automatically", Icon.loadFileStackIcon())
        );
    }

    private void initStyles() {
        tagline.setTextAlignment(TextAlignment.CENTER);
        tagline.setPadding(new Insets(150, 0, 0, 0));

        textBox.setAlignment(Pos.CENTER);

        directoryField.setMaxWidth(600);
        directoryField.setMinHeight(50);
        directoryField.setPromptText("C:\\Users\\user\\Folder");
        directoryField.setFocusTraversable(false);
        directoryField.setPadding(new Insets(0, 0, 0, 50));

        folderOpenIcon.setTranslateX(15);

        textFieldBox.setPrefWidth(600);
        textFieldBox.setPadding(new Insets(10));

        StackPane.setAlignment(folderOpenIcon, Pos.CENTER_LEFT);

        directoryField.setMinHeight(50);

        addDirectoryButton.setCursor(Cursor.HAND);

        textFieldWrapper.setAlignment(Pos.CENTER);
        textFieldWrapper.setSpacing(15);

        scanButton.setPrefSize(150, 50);

        heroBox.setSpacing(15);
        heroBox.setAlignment(Pos.CENTER);

        scannerBox.setAlignment(Pos.CENTER);
        scannerBox.setSpacing(15);
    }

    /*
    =================================
            EVENT HANDLERS
    =================================
     */

    private void scanButtonEventHandler() {
        scanButton.setOnAction(e -> {
            // if valid directory is entered:
            String trimmedDirectory = getEnteredDirectory().trim();
            if(fieldClicked && !trimmedDirectory.isEmpty()) {
                folder = new File(trimmedDirectory);
            }

            if(folder == null) {
                System.out.println("Dir cant be null!");
                return;
            }

            result.setVisible(false);
            result.setManaged(false);

            System.out.println("FOLDER PATH: " + folder.getAbsolutePath());
            System.out.println("ENTERED DIR: " + getEnteredDirectory());

            if(!folder.exists() || !folder.isDirectory()) {
                System.out.printf("'%s' is not valid.\nPlease enter a valid directory!\n", getEnteredDirectory());
                return;
            }

            result = new Result(trimmedDirectory, "Complete");
            result.setVisible(true);
            result.setManaged(true);
            result.setAlignment(Pos.CENTER);

            this.getChildren().addAll(result);
        });
    }

    private void addDirectoryEventHandler() {
        addDirectoryButton.setOnMouseClicked(e -> {
            fieldClicked = false;

            var directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose a directory");

            folder = directoryChooser.showDialog(stage);

            if(folder != null) {
                System.out.println("The Folder Path: " + folder.getAbsolutePath());
                directoryField.setText(folder.getAbsolutePath());
                System.out.println("clicked");
            }

            else {
                System.out.println("DEBUG: the file u chose is nothing!");
            }

        });
    }

    private void onFieldClick() {
        directoryField.setOnMouseClicked(e ->{
            directoryField.setEditable(true);
            System.out.println("FIELD EVENT TRIGGERED");
            fieldClicked = true;
        });
        // might need to make a more precise check
    }

    /*
    =================================
              OTHER STUFF
    =================================
     */

    private void setIds() {
        tagline.setId("tagline");
        instantlyLabel.setId("instantly-label");
        description.setId("description");
        directoryField.setId("directory-field");
        scanButton.setId("scan-button");
        folderOpenIcon.setId("text-field-stacked-icon");
    }

    private void registerElements() {
        this.getChildren().addAll(heroBox, scannerBox, heroCards, result);
    }

    public String getEnteredDirectory() {
        if(folder != null && fieldClicked) {
            return directoryField.getText();
        }
        else if(folder != null) {
            return folder.getAbsolutePath();
        }
        return directoryField.getText();
    }
}
