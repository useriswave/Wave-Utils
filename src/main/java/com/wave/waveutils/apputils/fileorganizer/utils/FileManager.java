package com.wave.waveutils.apputils.fileorganizer.utils;

import java.io.File;
import java.util.ArrayList;

public class FileManager {

    private static final String COPY_IDENTIFIER = "_Copy";

    private FileManager() {}

    public static void deleteFolder(File folder) {
        folder.delete();
    }

    public static void overWriteFolder(File folder) {
        File[] files = folder.listFiles();

        for(File file : files) {
            file.delete();
        }

        folder.delete();
        folder.mkdir();
    }

    public static void createCopy(File folder, File directory) {
        int copyCount = 0;
        File[] files = directory.listFiles();
        var copiesList = new ArrayList<Integer>();
        for(File file : files) {
            if(file.getName().equals(folder.getName())) {
                copyCount++;
            }
            else if(file.getName().contains(folder.getName() + COPY_IDENTIFIER)) {
                String folderCopyCount = file.getName().substring(file.getName().length() - 1);
                int folderCount = Integer.parseInt(folderCopyCount);

                copiesList.add(folderCount);
            }
        }

        if(!copiesList.isEmpty()) {
            if(copiesList.size() == 1) {
                copyCount = copiesList.get(0) + 1;
            }
            else {
                int largest = copiesList.get(0);
                for(int copyNum : copiesList) {
                    if(copyNum > largest) {
                        largest = copyNum;
                    }
                }
                copyCount = largest+1;
            }

        }

        String currentDirectory = directory.getAbsolutePath();
        File copyFile = new File(currentDirectory + "\\" +folder.getName() + COPY_IDENTIFIER + " " + copyCount);

        copyFile.mkdir();
    }

}
