package org.example.timer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Timer {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        Runnable timeTask = () -> {
            LocalTime now = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            System.out.println(LocalTime.now());
        };

        Runnable messageTask = () -> {
            System.out.println("5 seconds have passed" + LocalTime.now());
        };

        scheduler.scheduleAtFixedRate(timeTask, 0, 1, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(messageTask, 5, 5, TimeUnit.SECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown initiated...");
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("Forcing shutdown...");
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
            }
            System.out.println("Scheduler terminated.");
        }));
    }
}
