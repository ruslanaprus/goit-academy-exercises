package org.example.deadlock;

import java.util.Objects;

public class Deadlock {
    private static final Object MONITOR_1 = new Object();
    private static final Object MONITOR_2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (MONITOR_1) {
                System.out.println("thread 1 blocked MONITOR_1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    //NOP
                }

                synchronized (MONITOR_2) {
                    System.out.println("thread 1 blocked MONITOR_2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (MONITOR_2) {
                System.out.println("thread 2 blocked MONITOR_2");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    //NOP
                }

                synchronized (MONITOR_1) {
                    System.out.println("thread 2 blocked MONITOR_1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
