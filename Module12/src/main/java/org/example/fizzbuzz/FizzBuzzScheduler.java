package org.example.fizzbuzz;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FizzBuzzScheduler {
    private final FizzBuzzManager fizzBuzzManager;

    public FizzBuzzScheduler(FizzBuzzManager fizzBuzzManager) {
        this.fizzBuzzManager = fizzBuzzManager;
    }

    public void startFizzBuzz(int n) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        executor.scheduleAtFixedRate(fizzBuzzManager::fizz, 0, 100, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(fizzBuzzManager::buzz, 0, 100, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(fizzBuzzManager::fizzbuzz, 0, 100, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(fizzBuzzManager::number, 0, 100, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(this::printOutput, 0, 100, TimeUnit.MILLISECONDS);

        executor.schedule(() -> {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
            printResults(fizzBuzzManager.getResult());
        }, n * 100, TimeUnit.MILLISECONDS);
    }

    private void printOutput() {
        try {
            String output = fizzBuzzManager.getOutputQueue().poll(1, TimeUnit.SECONDS);
            if (output != null) {
                System.out.print(output + " ");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void printResults(List<String> resultList) {
        System.out.println("\nResults:");
        for (String result : resultList) {
            System.out.print(result + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FizzBuzzManager fizzBuzzManager = new FizzBuzzManager(100);
        FizzBuzzScheduler fizzBuzzScheduler = new FizzBuzzScheduler(fizzBuzzManager);
        fizzBuzzScheduler.startFizzBuzz(100);
    }
}
