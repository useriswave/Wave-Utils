package com.wave.waveutils.elementlayouts;

import com.wave.waveutils.cards.Card;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class HorizontalCards extends HBox {

    private Card[] cardList;

    public HorizontalCards(Card... cards) {
        this.cardList = cards;
        applyStyles();
        registerElements();

    }

    private void applyStyles() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
    }

    private void registerElements() {
        for(Card c : cardList) {
            this.getChildren().add(c);
        }
    }

}
