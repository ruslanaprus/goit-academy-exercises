package org.example.fizzbuzz;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzzNumbers {
    private final int n;
    private final AtomicInteger current = new AtomicInteger(1);
    private final BlockingQueue<String> outputQueue = new LinkedBlockingQueue<>();

    public FizzBuzzNumbers(int n) {
        this.n = n;
    }

    private abstract class Task implements Runnable {
        protected final int divisor;
        protected final String output;
        protected final boolean isFizzBuzzTask;

        public Task(int divisor, String output, boolean isFizzBuzzTask) {
            this.divisor = divisor;
            this.output = output;
            this.isFizzBuzzTask = isFizzBuzzTask;
        }

        @Override
        public void run() {
            while (true) {
                int num = current.get();
                if (num > n) return;
                if ((isFizzBuzzTask && num % 3 == 0 && num % 5 == 0) ||
                        (!isFizzBuzzTask && num % divisor == 0 && num % (divisor == 3 ? 5 : 3) != 0)) {
                    try {
                        outputQueue.put(output.equals("num") ? String.valueOf(num) : output);
                        current.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public BlockingQueue<String> getOutputQueue() {
        return outputQueue;
    }

    private class FizzTask extends Task {
        public FizzTask() {
            super(3, "fizz", false);
        }
    }

    private class BuzzTask extends Task {
        public BuzzTask() {
            super(5, "buzz", false);
        }
    }

    private class FizzBuzzTask extends Task {
        public FizzBuzzTask() {
            super(0, "fizzbuzz", true);
        }
    }

    private class NumberTask extends Task {
        public NumberTask() {
            super(0, "num", false);
        }

        @Override
        public void run() {
            while (true) {
                int num = current.get();
                if (num > n) return;
                if (num % 3 != 0 && num % 5 != 0) {
                    try {
                        outputQueue.put(String.valueOf(num));
                        current.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    private void printOutput() {
        while (true) {
            try {
                String output = outputQueue.poll(1, TimeUnit.SECONDS);
                if (output != null) {
                    System.out.print(output + " ");
                }
                if (current.get() > n && outputQueue.isEmpty()) break;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void start() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.submit(new FizzTask());
        executor.submit(new BuzzTask());
        executor.submit(new FizzBuzzTask());
        executor.submit(new NumberTask());
        executor.submit(this::printOutput);

        executor.shutdown();
        try {
            if (!executor.awaitTermination(n * 100 + 1000, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    public static void main(String[] args) {
        FizzBuzzNumbers fizzBuzz = new FizzBuzzNumbers(100);
        fizzBuzz.start();
    }
}
