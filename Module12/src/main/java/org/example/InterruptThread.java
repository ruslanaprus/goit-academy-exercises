package org.example;

public class InterruptThread extends Thread {
    @Override
    public void run(){
        final Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "started...");
        int count = 1;
        while (!thread.isInterrupted()){
            System.out.println("count = " + count);
            count++;
       //     threadSleep(thread);
        }
    }

    private void threadSleep(Thread thread){
        try{
            thread.sleep(100);
        } catch (InterruptedException e){
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName() + " interrupted");
            System.out.println("isInterrupted flag: " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
        }
    }
}
