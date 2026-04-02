package com.wave.waveutils.main;

import com.wave.waveutils.apputils.fileorganizer.layouts.RootPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        final int WIDTH = 1200;
        final int HEIGHT = 800;

        var root = new RootPane(stage);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        loadLightMode(scene);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Wave Utils");
        stage.show();
    }

    private void loadLightMode(Scene scene) {
        scene.getStylesheets().addAll(
                getClass().getResource("/com/wave/waveutils/styles/light-mode/mainpage/light-mode.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/light-mode/cardstyles/hero-cards/scans-directory-style.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/light-mode/cardstyles/hero-cards/organizes-files-style.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/light-mode/cardstyles/hero-cards/creates-folders-style.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/light-mode/cardstyles/hero-cards/all-cards.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/light-mode/icons/icons.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/light-mode/result/result.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/light-mode/cardstyles/result-cards/result-cards.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/light-mode/cardstyles/extension-cards/extension-card.css").toExternalForm(),
                getClass().getResource("/com/wave/waveutils/styles/dialogs/dialog-style.css").toExternalForm()
        );
    }
}