package org.example.livelock;

import javax.xml.catalog.Catalog;

public class Livelock {
    private static Car toyota = new Car("Toyota");
    private static Car bmw = new Car("BMW");

    public static void main(String[] args) {
        Thread thread0 = new Thread(() -> toyota.passBridge(bmw));
        Thread thread1 = new Thread(() -> bmw.passBridge(toyota));

        thread0.start();
        thread1.start();
    }
}
