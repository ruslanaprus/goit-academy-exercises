package org.example.sync;

import java.util.Objects;

public class Counter implements Runnable {
    private int counter = 0;
    //   private final Object MONITOR = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
//          synchronized (this) {
//          synchronized (MONITOR) {
            synchronized (Counter.class) {  // the same as private final static Object MONITOR = new Object()
                increment();
            }
            System.out.println(Thread.currentThread().getName() + " - " + counter);
        }
    }

    private void increment() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            //NOP
        }
        counter++;
    }
}
