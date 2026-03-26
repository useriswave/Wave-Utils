package com.wave.waveutils.main;

import com.wave.waveutils.apputils.fileorganizer.layouts.RootPane;
import com.wave.waveutils.apputils.fileorganizer.logic.FileOrganizer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        new FileOrganizer().organizeFolder();

        final int WIDTH = 1200;
        final int HEIGHT = 800;

        var root = new RootPane(stage);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        loadStyleSheets(scene);

        getFontInfo();  // for debugging

        stage.setScene(scene);
        // set to true
        stage.setMaximized(false);
        stage.setTitle("Wave Utils");
        stage.show();
    }

    private void loadStyleSheets(Scene scene) {
        scene.getStylesheets().addAll(
                getClass().getResource("/com/wave/waveutils/styles/mainpage/dark-mode.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/cardstyles/hero-cards/scans-directory-style.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/cardstyles/hero-cards/organizes-files-style.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/cardstyles/hero-cards/creates-folders-style.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/cardstyles/hero-cards/all-cards.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/icons/icons.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/result/result.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/cardstyles/result-cards/result-cards.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/cardstyles/extension-cards/extension-card.css").toExternalForm()
        );
    }

    // TEMPORARY METHOD FOR DEBUGGING!!!!

    private void getFontInfo() {
        Font grotesk = Font.loadFont(
                getClass().getResourceAsStream("/com/wave/waveutils/fonts/SpaceGrotesk-Bold.ttf"), 20

        );
        System.out.println(grotesk.getName());
        System.out.println(grotesk.getFamily());
        Font inter = Font.loadFont(
                getClass().getResourceAsStream("/com/wave/waveutils/fonts/Inter_18pt-SemiBold.ttf"), 20

        );
        System.out.println(inter.getName());
        System.out.println(inter.getFamily());

        Font jetbrains = Font.loadFont(
                getClass().getResourceAsStream("/com/wave/waveutils/fonts/JetBrainsMono-Regular.ttf"), 20

        );
        System.out.println(jetbrains.getName());
        System.out.println(jetbrains.getFamily());
    }
}