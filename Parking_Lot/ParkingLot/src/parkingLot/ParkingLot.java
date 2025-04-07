package parkingLot;

import parkingLot.VehicleType.Vehicle;
import parkingLot.observer.ParkingLotObserver;
import parkingLot.observer.ParkingLotSubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot implements ParkingLotSubject {
    private static ParkingLot instance;
    private final List<Level> levels;
    private final Map<String, ParkingSpot> parkedVehicles;
    private final List<ParkingLotObserver> observers;

    private ParkingLot() {
        levels = new ArrayList<>();
        parkedVehicles = new HashMap<>();
        observers = new ArrayList<>();

    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new ParkingLot();
                }
            }
        }
        return instance;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (parkedVehicles.containsKey(vehicle.getLicensePlate())) {
            System.out.println("Vehicle is already parked.");
            return false;
        }

        for (Level level : levels) {
            ParkingSpot spot = level.parkVehicle(vehicle);
            if (spot != null) {
                parkedVehicles.put(vehicle.getLicensePlate(), spot);
                notifyObservers("Vehicle " + vehicle.getLicensePlate() + " parked at spot " + spot.getSpotNumber());
                return true;
            }
        }
        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        ParkingSpot spot = parkedVehicles.remove(vehicle.getLicensePlate());
        if (spot != null) {
            spot.leaveSpot();
            notifyObservers("Vehicle " + vehicle.getLicensePlate() + " unparked from spot " + spot.getSpotNumber());
            return true;
        }
        return false;
    }

    public void displayAvailability() {
        for (Level level : levels) {
            level.displayAvailability();

        }
    }

    @Override
    public void registerObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ParkingLotObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (ParkingLotObserver observer : observers) {
            observer.update(message);
        }
    }
}


