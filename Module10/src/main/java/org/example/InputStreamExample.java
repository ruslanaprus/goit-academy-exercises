package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class InputStreamExample {
    public static void main(String[] args) throws IOException {
        byte[] input = new byte[]{1, 127, 34, 42, 0};

        InputStream stream = new InputStream() {
            private int index = 0;

            @Override
            public int read() throws IOException {
                if (index == input.length){
                    return -1;
                }
                return input[index++];
            }
        };

//        int data = stream.read();
//        while (data != -1){
//            System.out.println(data);
//            data = stream.read();
//        }

        byte[] out = new byte[input.length];
        int read = stream.read(out);
        System.out.println("read = " + read);
        System.out.println(Arrays.toString(out));
    }
}
