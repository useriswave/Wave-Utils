package com.wave.waveutils.apputils.fileorganizer.dialogs;

import javafx.scene.control.Alert;

public class ErrorDialog extends Alert{

    private String headerText;
    private String contentText;
    private String title;

    public ErrorDialog(String headerText, String contentText, String title) {
        super(AlertType.ERROR);
        this.headerText = headerText;
        this.contentText = contentText;
        this.title = title;

        initText();
        this.setResizable(false);
    }

    private void initText() {
        this.setHeaderText(headerText);
        this.setContentText(contentText);
        this.setTitle(title);
    }
}
