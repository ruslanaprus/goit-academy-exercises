package org.example;

import java.io.*;

public class FileExample {
    public static void main(String[] args) {

        String path = "/Users/ruslanaprus/IdeaProjects/goit-academy-exercises/Module10/src/main/resources/text.txt";

        File file = new File(path);

        if(!file.exists()){
            file.getParentFile().mkdirs();
            try{
                file.createNewFile();
            }
            catch(IOException e){
                System.err.println(e.getMessage());
            }
        }

        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))){
            String content = "This text added to the file\nCode works\n";
            bufferedOutputStream.write(content.getBytes());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path))){
            int character = bufferedInputStream.read();
            while (character != -1) {
                System.out.print((char) character);
                character = bufferedInputStream.read();
            }
        } catch (IOException e){
            System.err.print(e.getMessage());
        }

        File newFile = new File("newFile.txt");

        try{
            boolean created = newFile.createNewFile();
            if (!created) {
                throw new RuntimeException("Failed to create the file: " + newFile.getName());
            }
        }
        catch(Exception e){
            e.getStackTrace();
        }

        System.out.println(newFile.getAbsolutePath());

        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(newFile))){
            String content = "Content of the newFile\n";
            bufferedOutputStream.write(content.getBytes());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }

    }
}
