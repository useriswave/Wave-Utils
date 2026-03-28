package com.wave.waveutils.apputils.fileorganizer.logic;

import com.wave.waveutils.apputils.fileorganizer.utils.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOrganizer {

    private String pathName;
    private File directory;
    private ArrayList<File> fileList;
    private ArrayList<File> folderList;
    private ArrayList<File> unknownFileList;
    private ArrayList<File> validFiles;
    private ArrayList<File> fileWithNoExtension;
    private ArrayList<File> complicatedFiles;
    private ArrayList<String> extensions;
    private ArrayList<File> selectedFolders;
    private FileHandler fileHandler;
    private File[] files;
    private ArrayList<String> uniqueExtensions;

    public FileOrganizer() {
        this.pathName = "C:\\Users\\user\\Downloads\\Testing Folder";
        this.directory = new File(pathName);
        this.files = directory.listFiles();
        this.selectedFolders = new ArrayList<>();
        this.fileHandler = new FileHandler(selectedFolders);
    }

    public void organizeFolder() {
        separateFilesAndFolders();
        sortValidAndComplicatedFiles();
        getAllExtensions();
        removeDuplicateExtensions();
        createFolders();
        moveFiles();
    }

    private void separateFilesAndFolders() {
        fileList = new ArrayList<File>();
        folderList = new ArrayList<File>();
        unknownFileList = new ArrayList<File>();

        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file);
            } else if (file.isDirectory()) {
                folderList.add(file);
            } else {
                unknownFileList.add(file);
            }
        }
        /*
         ==================================================================================
                                                DEBUG
         ==================================================================================
         */
        System.out.println("----------FILES----------");
        for (File f : fileList) {
            System.out.println(f.toString());
        }

        System.out.println("----------FOLDERS----------");
        for (File f : folderList) {
            System.out.println(f.toString());
        }

        System.out.println("----------UNKNOWN FILES----------");
        if (unknownFileList.isEmpty()) {
            System.out.println("NONE");
            return;
        }
        for (File f : unknownFileList) {
            System.out.println(f.toString());
        }
    }

    private void sortValidAndComplicatedFiles() {
        validFiles = new ArrayList<>();
        complicatedFiles = new ArrayList<>();
        fileWithNoExtension = new ArrayList<>();

        for (File file : fileList) {
            String fileName = file.getName();
            int dotCount = 0;

            for (int i = 0; i < fileName.length(); i++) {
                if (fileName.charAt(i) == '.') {
                    dotCount++;
                }
            }

            int lastDot = fileName.lastIndexOf('.');
            String extension = fileName.substring(lastDot + 1);

            if (lastDot == -1 || lastDot == 0 || extension.contains(" ")) {
                fileWithNoExtension.add(file);
            } else if (dotCount == 1) {
                validFiles.add(file);
            } else {
                complicatedFiles.add(file);
            }
        }
                /*
         ==================================================================================
                                                DEBUG
         ==================================================================================
         */

        System.out.println("Valid Files:");
        for (File file : validFiles) {
            System.out.println(file.toString());
        }

        System.out.println("Complicated Files:");
        for (File file : complicatedFiles) {
            System.out.println(file.toString());
        }

        System.out.println("Files With No Extension:");
        for (File file : fileWithNoExtension) {
            System.out.println(file.toString());
        }

    }

    private void getAllExtensions() {
        extensions = new ArrayList<>();

        for (File file : validFiles) {
            String fileName = file.getName();

            extensions.add(fileName.substring(fileName.lastIndexOf('.')));

        }

        for (File file : complicatedFiles) {
            String fileName = file.getName();

            extensions.add(fileName.substring(fileName.lastIndexOf('.')));
        }

        for (String e : extensions) {
            System.out.println("ALL EXTENSOINS ---> " + e);
        }
    }

    // this is a slower solution I should use a hashset
    private void removeDuplicateExtensions() {

        uniqueExtensions = new ArrayList<>();

        for (String extension : extensions) {
            if (!uniqueExtensions.contains(extension)) {
                uniqueExtensions.add(extension);
            }
        }
        /*
         ==================================================================================
                                                DEBUG
         ==================================================================================
         */
        System.out.println("UNIQUE EXTENSIONS MANUALLY CHECKED: ");
        for (String ue : uniqueExtensions) {
            System.out.println(ue);
        }
    }

    private void createFolders() {
        for (String extension : uniqueExtensions) {
            File extensionFolder = new File(pathName + "\\" + extension + " Folder");
            try {
                if (!extensionFolder.mkdir()) {
                    // there can be many causes, we can check if there is a duplicate folder that exists.
                    if (extensionFolder.exists()) {
                        /*
                            TODO: Make a list of foldersCreated to then add the files accordingly.
                         */
                        System.out.printf("Folder '%s' already exists!\nWould you like to:\n1- Delete it\n2- Overwrite it\n3- Use it.\n4- Create copy\n\nInput: \n", extension);
                        Scanner sc = new Scanner(System.in);
                        int input = sc.nextInt();
                        sc.nextLine();

                        switch (input) {
                            case 1:
                                fileHandler.deleteFolder(extensionFolder);
                                break;
                            case 2:
                                fileHandler.overWriteFolder(extensionFolder);
                                break;
                            case 3:
                                fileHandler.useExistingFolder(extensionFolder);
                                System.out.println("Will be used.");
                                break;
                            case 4:
                                extensionFolder = fileHandler.createCopy(extensionFolder, directory);
                                break;
                            default:
                                System.out.println("Invalid entry");
                                break;
                        }
                    } else {
                        System.out.printf("Folder '%s' cannot be created.\n", extension);
                    }
                } else {
                    selectedFolders.add(extensionFolder);
                    System.out.printf("Folder '%s' has been created!\n", extension);
                }
            } catch (SecurityException e) {
                System.out.println(e.getCause() + "\nPermission error, please check your directory's permission");
            }

        }
        printSelectedSourceFolders();
    }

    private void moveFiles() {
        moveValidFiles();
        moveComplicatedFiles();
    }

    private boolean moveValidFiles() {
        for (String extension : uniqueExtensions) {
            for (File file : validFiles) {
                String fileName = file.getName();
                String fileExtension = fileName.substring(fileName.lastIndexOf('.'));

                if (fileExtension.equalsIgnoreCase(extension)) {
                    Path source = file.toPath();
                    Path target = getProperSourceFolder(extension, fileName).toPath();

                    try {
                        Files.move(source, target);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        return false;
                    }
                }

            }
        }
        return true;
    }

    private boolean moveComplicatedFiles() {
        for (String extension : uniqueExtensions) {
            for (File file : complicatedFiles) {
                String fileName = file.getName();
                String fileExtension = fileName.substring(fileName.lastIndexOf('.'));

                if (fileExtension.equalsIgnoreCase(extension)) {
                    Path source = file.toPath();
                    Path target = getProperSourceFolder(extension, fileName).toPath();

                    try {
                        Files.move(source, target);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        return false;
                    }
                }

            }
        }
        return true;
    }

    private File getProperSourceFolder(String extension, String fileName) {
        for (File folder : selectedFolders) {
            if (folder.getName().contains(extension)) {
                File targetRepresentation = new File(folder.getAbsolutePath() + "\\" + fileName);
                return targetRepresentation;
            }
        }
        return null;
    }

    // temporary
    public void printSelectedSourceFolders() {
        int count = 0;
        for (File folder : selectedFolders) {
            System.out.println("Path " + (++count) + ": " + folder.getName());
        }
    }
}
