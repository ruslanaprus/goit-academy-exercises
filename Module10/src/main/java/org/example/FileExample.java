package org.example;

import java.io.*;

public class FileExample {
    public static void main(String[] args) {

        String path = "/Users/peach/IdeaProjects/goit-academy-exercises/Module10/src/main/resources/text.txt";

        File file = new File(path);

        if(!file.exists()){
            throw new RuntimeException("File with name " + file.getName() + " does not exist");
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
//        File newFile = new File(path);

        if(!newFile.exists()){
            newFile.getParentFile().mkdirs();
            try{
                newFile.createNewFile();
            }
            catch(IOException e){
                System.err.println(e.getMessage());
            }
        }

        System.out.println(newFile.getAbsolutePath());

        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))){
            String content = "This text added to the file\nCode works";
            bufferedOutputStream.write(content.getBytes());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
