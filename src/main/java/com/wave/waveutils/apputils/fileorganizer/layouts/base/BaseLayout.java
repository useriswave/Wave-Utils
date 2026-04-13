package com.wave.waveutils.apputils.fileorganizer.layouts.base;

import javafx.scene.Node;
import javafx.scene.Parent;

public abstract class BaseLayout {

    private boolean built = false;

    protected final void build() {
        if(built) {
            throw new IllegalStateException("Layout is already built!");
        }
        initRoot();
        initUI();
        handleEvents();
        initStyles();
        setIds();
        registerElements();

        built = true;
    }

    protected abstract void initRoot();

    protected abstract void initUI();

    protected void handleEvents() {}

    protected abstract void initStyles();

    protected abstract void setIds();

    protected abstract void registerElements();

    protected abstract Parent getRoot();
}
