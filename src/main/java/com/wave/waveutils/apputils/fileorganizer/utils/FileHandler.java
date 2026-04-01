package com.wave.waveutils.apputils.fileorganizer.utils;

import java.io.File;
import java.util.ArrayList;

public class FileHandler {

    private final String COPY_IDENTIFIER = "_Copy";
    private ArrayList<File> selectedFolders;

    public FileHandler(ArrayList<File> selectedFolders) {
        this.selectedFolders = selectedFolders;
    }

//    public void deleteFolder(File folder) {
//        folder.delete();
//    }

    public void useExistingFolder(File folder) {
        selectedFolders.add(folder);
    }

    // a better overwrite feature will be implemented later. I will prioritize finishing the project.

    public void overWriteFolder(File folder) {

        File[] files = folder.listFiles();

        for(File file : files) {
            file.delete();
        }

        folder.delete();
        folder.mkdir();
        selectedFolders.add(folder);
    }

    public File createCopy(File folder, File directory) {
        int copyCount = 0;
        File[] files = directory.listFiles();
        var copiesList = new ArrayList<Integer>();
        for(File file : files) {
            if(file.getName().equals(folder.getName())) {
                copyCount++;
            }

            else if (file.getName().contains(folder.getName() + COPY_IDENTIFIER)) {
                String folderCopyCount = file.getName().substring(file.getName().length() - 1);
                int folderCount = Integer.parseInt(folderCopyCount);

                copiesList.add(folderCount);
            }
        }

        if(!copiesList.isEmpty()) {
            if(copiesList.size() == 1) {
                copyCount = copiesList.getFirst() + 1;
            }
            else {
                int largest = copiesList.getFirst();
                for(int copyNum : copiesList) {
                    if(copyNum > largest) {
                        largest = copyNum;
                    }
                }
                copyCount = largest+1;
            }

        }

        String currentDirectory = directory.getAbsolutePath();

        File copyFile = new File(currentDirectory + "\\" + folder.getName() + COPY_IDENTIFIER + " " + copyCount);

        if(!copyFile.mkdir()) {
            System.out.printf("File '%s' has not been created.\n", copyFile.getName());
        }

//        selectedFolders.add(copyFile);

        return copyFile;
    }
}
