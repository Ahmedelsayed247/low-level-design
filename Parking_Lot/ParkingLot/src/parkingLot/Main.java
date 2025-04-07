package parkingLot;

import parkingLot.VehicleType.*;
import parkingLot.observer.AvailabilityDisplay;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addLevel(new Level(1, 100));
        parkingLot.addLevel(new Level(2, 80));

        // Create an observer to track parking lot availability
        AvailabilityDisplay display = new AvailabilityDisplay();

        // Register the observer to get updates on parking changes
        parkingLot.registerObserver(display);

        Vehicle car = new Car("ABC123");
        Vehicle truck = new Truck("XYZ789");
        Vehicle motorcycle = new Motorcycle("M1234");

        // Park vehicles and notify observers
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(truck);
        parkingLot.parkVehicle(motorcycle);

        // Display the availability after parking the vehicles
        parkingLot.displayAvailability();

        // Unpark the motorcycle and notify observers
        parkingLot.unparkVehicle(motorcycle);

        // Display the availability after unparking the motorcycle
        parkingLot.displayAvailability();

        // Removing the observer after some time, so it won't track further updates
        parkingLot.removeObserver(display);

        // Park a new vehicle after removing the observer
        Vehicle newCar = new Car("XYZ123");
        parkingLot.parkVehicle(newCar);

        // Since observer has been removed, no further notifications will be received
        parkingLot.displayAvailability();
    }
}
