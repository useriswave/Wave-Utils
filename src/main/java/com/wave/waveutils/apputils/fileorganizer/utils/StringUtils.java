package com.wave.waveutils.apputils.fileorganizer.utils;

public class StringUtils {
    private StringUtils() {}

    public static String getExtension(String fileName) {
        if(fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf('.'));
        }
        return fileName;
    }
}
