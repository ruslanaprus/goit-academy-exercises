package org.example.sync;

public class RaceCondition {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread thread0 = new Thread(counter);
        Thread thread1 = new Thread(counter);

        thread0.start();
        thread1.start();
    }
}
