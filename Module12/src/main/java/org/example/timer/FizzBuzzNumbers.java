package org.example.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class FizzBuzzNumbers {
    private final int n;
    private final AtomicInteger current = new AtomicInteger(1);
    private final BlockingQueue<String> outputQueue = new LinkedBlockingQueue<>();
    private final Semaphore semaphore = new Semaphore(1);
    private final List<String> resultList = new ArrayList<>();

    public FizzBuzzNumbers(int n) {
        this.n = n;
    }

    private void fizz() {
        process(num -> num % 3 == 0 && num % 5 != 0, "fizz");
    }

    private void buzz() {
        process(num -> num % 5 == 0 && num % 3 != 0, "buzz");
    }

    private void fizzbuzz() {
        process(num -> num % 3 == 0 && num % 5 == 0, "fizzbuzz");
    }

    private void number() {
        process(num -> num % 3 != 0 && num % 5 != 0, String.valueOf(current.get()));
    }

    private void process(Predicate<Integer> condition, String output) {
        try {
            semaphore.acquire();
            int num = current.get();
            if (num <= n && condition.test(num)) {
                outputQueue.put(output.equals("num") ? String.valueOf(num) : output);
                resultList.add(output);
                current.incrementAndGet();
            }
            semaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public List<String> getResult(){
        return List.copyOf(resultList);
    }

    private void printOutput() {
        try {
            String output = outputQueue.poll(1, TimeUnit.SECONDS);
            if (output != null) {
                System.out.print(output + " ");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void printResults(List<String> resultList) {
        System.out.println("\nResults:");
        for (String result : resultList) {
            System.out.print(result + " ");
        }
        System.out.println();
    }

    public void start() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        executor.scheduleAtFixedRate(this::fizz, 0, 100, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(this::buzz, 0, 100, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(this::fizzbuzz, 0, 100, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(this::number, 0, 100, TimeUnit.MILLISECONDS);
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
            printResults(getResult());
        }, n * 100, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        FizzBuzzNumbers fizzBuzz = new FizzBuzzNumbers(100);
        fizzBuzz.start();
    }
}

