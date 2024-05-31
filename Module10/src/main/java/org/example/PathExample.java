package org.example;

import java.io.IOException;
import java.nio.file.*;

public class PathExample {
    public static void main(String[] args) {
        String fileName = "file.txt";
        Path filePath = Paths.get(fileName);

        if (Files.exists(filePath)) {
            System.out.println("File already exists: " + filePath.toAbsolutePath());
        } else {
            try {
                Files.createFile(filePath);
                System.out.println("File created successfully: " + filePath.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error creating the file: " + e.getMessage());
            }
        }
    }
}
