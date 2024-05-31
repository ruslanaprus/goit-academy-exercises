package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFileExample {
    public static void main(String[] args) {
        String zipFilePath = "/Users/ruslanaprus/IdeaProjects/goit-academy-exercises/Module10/src/main/resources/words.zip";

        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.isDirectory()) {
                    try (InputStream inputStream = zipFile.getInputStream(entry);
                         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // format metadata
        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                System.out.println("Entry: " + entry.getName());
                System.out.println("Size: " + entry.getSize() + " bytes");
                System.out.println("Last Modified: " + entry.getLastModifiedTime());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
