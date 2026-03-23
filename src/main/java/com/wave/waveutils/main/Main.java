package com.wave.waveutils.main;

import com.wave.waveutils.layouts.RootPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final int WIDTH = 1200;
        final int HEIGHT = 800;

        var root = new RootPane();

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        loadStyleSheets(scene);

        getFontInfo();  // for debugging

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Wave Utils");
        stage.show();
    }

    private void loadStyleSheets(Scene scene) {
        scene.getStylesheets().addAll(
                getClass().getResource("/com/wave/waveutils/styles/mainpage/dark-mode.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/cardstyles/scans-directory-style.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/cardstyles/organizes-files-style.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/cardstyles/creates-folders-style.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/cardstyles/all-cards.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/icons/icons.css").toExternalForm()
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
    }
}