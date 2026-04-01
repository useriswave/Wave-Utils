package com.wave.waveutils.apputils.fileorganizer.records;

import com.wave.waveutils.apputils.fileorganizer.enums.FolderAction;

import java.io.File;

public record FolderDecision(File folder, FolderAction action) {}
