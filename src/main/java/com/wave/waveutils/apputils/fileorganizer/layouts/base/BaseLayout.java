package com.wave.waveutils.apputils.fileorganizer.layouts.base;

import javafx.scene.Node;

public abstract class BaseLayout {

    protected abstract void initUI();

    protected abstract void initStyles();

    protected abstract void setIds();

    protected abstract void registerElements();

    protected abstract Node getRoot();
}
