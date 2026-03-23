package com.wave.waveutils.layouts;

import com.wave.waveutils.cards.Card;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane {

    public RootPane() {
        var center = new CenterLayout();
        var scrollPane = new ScrollPane(center);
        scrollPane.setId("background");
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setCenter(scrollPane);

//        Card c = new Card();
//        this.setTop(c);
    }

}
