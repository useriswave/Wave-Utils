package com.wave.waveutils.apputils.fileorganizer.logic;

import com.wave.waveutils.apputils.fileorganizer.enums.FolderAction;
import com.wave.waveutils.apputils.fileorganizer.records.FileInfo;
import com.wave.waveutils.apputils.fileorganizer.records.FolderDecision;
import com.wave.waveutils.apputils.fileorganizer.utils.FileHandler;
import com.wave.waveutils.apputils.fileorganizer.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;

public class FileOrganizer {

    private final String pathName;
    private final File directory;
    private ArrayList<File> fileList;
    private ArrayList<File> folderList;
    private ArrayList<File> validFiles;
    private ArrayList<File> fileWithNoExtension;
    private ArrayList<File> complicatedFiles;
    private ArrayList<String> extensions;
    private final ArrayList<File> selectedFolders;
    private final FileHandler fileHandler;
    private final File[] allFiles;
    private ArrayList<String> uniqueExtensions;
    private File foldersDirectory;
    private File extensionlessFilesFolder;
    private final String directoryFolderName;
    private String filesWithNoExtensionFolderName;
    private int totalFoldersCreated;

    public FileOrganizer(String pathName) {
        this.pathName = pathName;
        this.directory = new File(this.pathName);
        this.allFiles = directory.listFiles();
        this.selectedFolders = new ArrayList<>();
        this.fileHandler = new FileHandler(selectedFolders);
        this.directoryFolderName = "Folders";
        this.filesWithNoExtensionFolderName = "Extensionless Files";
        this.totalFoldersCreated = 0;
    }

    public void scanDirectory() {
        separateFilesAndFolders();
        sortValidAndComplicatedFiles();
        getAllExtensions();
        removeDuplicateExtensions();
        defineSelectedFolders();
    }

    private void defineSelectedFolders() {
        for(String extension : uniqueExtensions) {
            selectedFolders.add(new File(pathName + "\\" + extension.substring(1) + " Folder"));
        }

        if(!folderList.isEmpty()) {
            selectedFolders.add(this.foldersDirectory = new File(pathName + "\\" + "Folders"));
        }

        if(!fileWithNoExtension.isEmpty()) {
            selectedFolders.add(this.extensionlessFilesFolder = new File(pathName + "\\" + "Extensionless Files"));
        }
    }

    private void separateFilesAndFolders() {
        fileList = new ArrayList<File>();
        folderList = new ArrayList<File>();

        for (File file : allFiles) {
            if (file.isFile()) {
                fileList.add(file);
            } else if (file.isDirectory()) {
                folderList.add(file);
            }
            else {
                throw new IllegalStateException("Unexpected file entry: " + file.getPath());
            }
        }
    }

    private void sortValidAndComplicatedFiles() {
        validFiles = new ArrayList<File>();
        complicatedFiles = new ArrayList<File>();
        fileWithNoExtension = new ArrayList<File>();

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
    }

    private void getAllExtensions() {
        extensions = new ArrayList<String>();

        for (File file : validFiles) {
            String fileName = file.getName();

            extensions.add(fileName.substring(fileName.lastIndexOf('.')));
        }

        for (File file : complicatedFiles) {
            String fileName = file.getName();

            extensions.add(fileName.substring(fileName.lastIndexOf('.')));
        }
    }

    private void removeDuplicateExtensions() {
        var temp = new HashSet<String>(extensions);
        uniqueExtensions = new ArrayList<>(temp);
    }

    public ArrayList<File> findConflictingFolders() {
        var conflictingFolders = new ArrayList<File>();
        for (File selectedFolder : selectedFolders) {
            if (selectedFolder.exists()) {
                conflictingFolders.add(selectedFolder);
            } else {
                System.out.println(selectedFolder.getName() + " does not create conflicts.");
            }

        }
        return conflictingFolders;
    }

    public void applyConflictDecisions(ArrayList<FolderDecision> conflictDecisions) {
        for(FolderDecision decision : conflictDecisions) {
            if(decision.action() == FolderAction.CREATE_COPY) {
                if(decision.folder().equals(foldersDirectory)) {
                    foldersDirectory = fileHandler.createCopy(decision.folder(), directory);
                    createCopyHandler(decision.folder(), foldersDirectory);
                }
                else if(decision.folder().equals(extensionlessFilesFolder)) {
                    extensionlessFilesFolder = fileHandler.createCopy(decision.folder(), directory);
                    createCopyHandler(decision.folder(), extensionlessFilesFolder);
                }
                else {
                    createCopyHandler(decision.folder(), fileHandler.createCopy(decision.folder(), directory));
                }
            }

            if (decision.action() == FolderAction.USE_EXISTING) {
                removeFolderFromSource(decision.folder(), folderList);
            }
        }
    }

    private void createCopyHandler(File folderToDelete, File copyFolder) {
        removeFolderFromSource(folderToDelete, selectedFolders);
        selectedFolders.add(copyFolder);
    }

    private void removeFolderFromSource(File targetFolder, ArrayList<File> listOfFolders) {
        for(int i = 0; i < listOfFolders.size(); i++) {
            if(listOfFolders.get(i).equals(targetFolder)) {
                listOfFolders.remove(listOfFolders.get(i));
            }
        }
    }

    public void createAllFolders() {
        var successfullyCreatedFolders = new ArrayList<File>();

        for (File folder : selectedFolders) {
            if (folder.exists() || folder.mkdir()) {
                System.out.println("Folder: " + folder.getName() + " created successfully");
                successfullyCreatedFolders.add(folder);
            } else {
                System.out.println("Cannot create folder: " + folder.getName());
            }
        }

        selectedFolders.clear();
        selectedFolders.addAll(successfullyCreatedFolders);
    }

    public void moveAllFiles() {
        moveValidFiles();
        moveComplicatedFiles();
        moveExtensionlessFiles();
        moveFoldersToFoldersDirectory();
    }

    private void moveFileToMatchingExtensionFolder(File file) {
        String extension = StringUtils.getExtension(file.getName());

        if (extension.startsWith(".")) {
            extension = extension.substring(1);
        }

        for (File selectedFolder : selectedFolders) {
            if (selectedFolder.getName().contains(extension)) {
                Path source = file.toPath();
                Path target = getProperSourceFolder(selectedFolder.getName(), file.getName()).toPath();

                try {
                    Files.move(source, target);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                return;
            }
        }
    }

    private void moveValidFiles() {
        for(File file : validFiles) {
            moveFileToMatchingExtensionFolder(file);
        }
    }

    private void moveComplicatedFiles() {
        for(File file : complicatedFiles) {
            moveFileToMatchingExtensionFolder(file);
        }
    }

    public void moveExtensionlessFiles() {
        for(File file : fileWithNoExtension) {
            Path source = file.toPath();
            Path target = getProperSourceFolder(extensionlessFilesFolder.getName(), file.getName()).toPath();

            try {
                Files.move(source, target);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void moveFoldersToFoldersDirectory() {

        for(File folder : folderList) {
            Path source = folder.toPath();
            Path target = getProperSourceFolder(foldersDirectory.getName(), folder.getName()).toPath();

            try {
                Files.move(source, target);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private File getProperSourceFolder(String folderName, String fileName) {
        for (File folder : selectedFolders) {
            if (folder.getName().equals(folderName)) {
                return new File(folder.getAbsolutePath() + "\\" + fileName);
            }
        }
        return null;
    }

    public int getTotalFiles() {
        return allFiles.length;
    }

    public int getTotalFileTypes() {
        return selectedFolders.size();
    }

    public int getTotalFoldersCreated() {
        return selectedFolders.size();
    }

    public double getTotalFilesSizeGB() {
        long totalBytes = getDirectorySizeBytes(directory);
        return totalBytes / 1_000_000_000.0;
    }

    private long getDirectorySizeBytes(File folder) {
        long total = 0;
        File[] files = folder.listFiles();

        if (files == null) {
            return -1;
        }

        for (File file : files) {
            if (file.isFile()) {
                total += file.length();
            } else if (file.isDirectory()) {
                total += getDirectorySizeBytes(file);
            }
        }

        return total;
    }

    public ArrayList<FileInfo> getFileInfoList() {
        var fileInfoList = new ArrayList<FileInfo>();

        for (File folder : selectedFolders) {
            File[] files = folder.listFiles();
            int fileCount = 0;

            if (files != null) {
                fileCount = files.length;
            }

            fileInfoList.add(new FileInfo(folder, Integer.toString(fileCount)));
        }
        return fileInfoList;
    }
}