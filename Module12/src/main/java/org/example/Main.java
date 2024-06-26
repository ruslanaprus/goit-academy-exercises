package org.example;

public class Main {

    static {
        System.out.println("name of current thread static block " + Thread.currentThread().getName());
        System.out.println("priority: " + Thread.currentThread().getPriority());
        System.out.println("thread group: " + Thread.currentThread().getThreadGroup());
    }
    public static void main(String[] args) {
//        System.out.println("name of current thread " + Thread.currentThread().getName());
//        System.out.println("priority: " + Thread.currentThread().getPriority());
//        System.out.println("thread group: " + Thread.currentThread().getThreadGroup());


        Thread.Builder builder = Thread.ofVirtual()
                .name("virtual thread")
                .inheritInheritableThreadLocals(false)
                .uncaughtExceptionHandler((t, e) -> System.out.printf("thread %s failed with exception %s", t, e));
        System.out.println("expected:\njava.lang.ThreadBuilders$VirtualThreadBuilder\nresult: ");
        System.out.println(builder.getClass().getName());

        Thread thread = builder.unstarted(() -> System.out.println("run"));

        System.out.println("expected:\njava.lang.VirtualThread\nresult: ");
        System.out.println(thread.getClass().getName());

        System.out.println("expected:\nvirtual thread\nresult: ");
        System.out.println(thread.getName());

        System.out.println("thread.isDaemon() = " + thread.isDaemon());
        System.out.println("expected priority:\n5\nresult: ");
        System.out.println(thread.getPriority());

        System.out.println("Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors());

        System.out.println("main thread...");
        Thread interruptThread = new InterruptThread();
        interruptThread.start();
        try{
            Thread.currentThread().sleep(2);
            interruptThread.interrupt();
            Thread.currentThread().sleep(2);
        } catch (InterruptedException e){
            System.out.println("thread has been interrupted...");
        }
        System.out.println("main thread finished");
    }
}

