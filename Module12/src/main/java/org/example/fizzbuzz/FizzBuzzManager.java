package org.example.fizzbuzz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class FizzBuzzManager {
    private final int n;
    private final AtomicInteger current = new AtomicInteger(1);
    private final BlockingQueue<String> outputQueue = new LinkedBlockingQueue<>();
    private final Semaphore semaphore = new Semaphore(1);
    private final List<String> resultList = new ArrayList<>();

    public FizzBuzzManager(int n) {
        this.n = n;
    }

    public void fizz() {
        process(num -> num % 3 == 0 && num % 5 != 0, "fizz");
    }

    public void buzz() {
        process(num -> num % 5 == 0 && num % 3 != 0, "buzz");
    }

    public void fizzbuzz() {
        process(num -> num % 3 == 0 && num % 5 == 0, "fizzbuzz");
    }

    public void number() {
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

    public List<String> getResult() {
        return List.copyOf(resultList);
    }

    public BlockingQueue<String> getOutputQueue() {
        return outputQueue;
    }
}
