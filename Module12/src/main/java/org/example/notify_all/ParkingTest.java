package org.example.notify_all;

public class ParkingTest {
    Parking parking = new Parking(5);

    public static void main(String[] args) {
        ParkingTest parkingTest = new ParkingTest();

        for (int i = 0; i < 10; i++){
            Thread car = new Thread(() -> {
                try {
                    parkingTest.parking();
                } catch (InterruptedException e){
                    //NOP
                }
            });

            car.start();
        }
    }

    public void parking() throws InterruptedException{
        parking.joinParking();
        System.out.println("The car " + Thread.currentThread().getName() + " entered the parking");
        Thread.sleep(3000);
        parking.leaveParking();
        System.out.println("Tha car " + Thread.currentThread().getName() + " left the parking");
    }
}
