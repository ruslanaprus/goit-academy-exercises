package org.example.livelock;

public class Car {
    private boolean honking;
    private String name;

    public Car(String name) {
        this.name = name;
        this.honking = true;
    }

    public String getName() {
        return name;
    }

    public void passBridge(Car car) {
        while (car.isHonking()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //NOP
            }
            System.out.println(car.getName() + " waiting to pass the bridge");
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            //NOP
        }

        System.out.println("passed the bridge");
        this.honking = false;
    }

    public boolean isHonking() {
        return honking;
    }
}
