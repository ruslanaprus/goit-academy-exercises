package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FilesExample {
    public static void main(String[] args) {

        String file = "file.txt";
        Path filePath = Paths.get(file);

        try {
            byte[] content = "Text added to the file".getBytes();
            writeToFile(filePath.toString(), content);

            byte[] readContent = readFromFile(filePath.toString());
            System.out.println(new String(readContent));

            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            byte[] content = "Content added to the file".getBytes();
            writeToFileWithFilesClass(filePath, content);

            byte[] readContent = readFromFileWithFilesClass(filePath);
            System.out.println(new String(readContent));

            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(String fileName, byte[] content) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            bufferedOutputStream.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] readFromFile(String fileName) {
        byte[] buffer = new byte[8192];
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(Paths.get(fileName.toString())))) {
            int length = bufferedInputStream.read(buffer);
            if (length < buffer.length) {
                return Arrays.copyOf(buffer, length);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
            while (length != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
                length = bufferedInputStream.read(buffer);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFileWithFilesClass(Path filePath, byte[] content) throws IOException {
        Files.write(filePath, content);
    }

    public static byte[] readFromFileWithFilesClass(Path filePath) throws IOException {
        return Files.readAllBytes(filePath);
    }
}
