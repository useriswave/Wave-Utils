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
        var nums = new ArrayList<Integer>();
        for(File file : files) {
            if(file.getName().equals(folder.getName())) {
                copyCount++;
            }
            else if(file.getName().contains(folder.getName() + COPY_IDENTIFIER)) {
                String folderWithCopy = folder.getName() + COPY_IDENTIFIER;
                String folderCopyCount = file.getName().substring(file.getName().length() - 1);
                System.out.println("--FOLDER COPY COUNT--: " + folderCopyCount);
                int folderCount = Integer.parseInt(folderCopyCount);
                nums.add(folderCount);
            }
        }

        if(!nums.isEmpty()) {
            if(nums.size() == 1) {
                copyCount = nums.get(0) + 1;
            }
            else {
                int largest = nums.get(0);
                for(int i = 0; i < nums.size(); i++) {
                    if(nums.get(i) > largest) {
                        largest = nums.get(i);
                    }
                }
                copyCount = largest+1;
            }

        }

        String currentDirectory = directory.getAbsolutePath();
        File copyFile = new File(currentDirectory + "\\" +folder.getName() + COPY_IDENTIFIER + copyCount);

        copyFile.mkdir();
    }

}
