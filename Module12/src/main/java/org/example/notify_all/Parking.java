package org.example.notify_all;

import java.util.Objects;

public class Parking {
    private int amountOfParkingSpaces = 0;
    private int blockedPlaces = 0;
    private final Object monitor = new Object();

    public Parking(int amountOfCars) {
        this.amountOfParkingSpaces = amountOfCars;
    }

    public void joinParking() throws InterruptedException {
        synchronized (monitor) {
            while (blockedPlaces == amountOfParkingSpaces) {
                System.out.println("parking is full");
                monitor.wait();
            }
            blockedPlaces++;
            monitor.notifyAll();
        }
    }

    public void leaveParking() throws InterruptedException {
        synchronized (monitor){
            while (blockedPlaces == 0){
                monitor.wait();
            }
            blockedPlaces--;
            monitor.notifyAll();
        }
    }
}
