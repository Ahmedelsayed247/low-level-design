package parkingLot;

import parkingLot.VehicleType.Vehicle;
import parkingLot.VehicleType.VehicleType;

import java.util.*;

public class Level {
    private final int levelNumber;
    private final List<ParkingSpot> parkingSpots;
    private final Map<VehicleType, Queue<ParkingSpot>> availableSpots;

    public Level(int levelNumber, int numberOfSpots) {
        this.levelNumber = levelNumber;
        this.parkingSpots = new ArrayList<>(numberOfSpots);
        this.availableSpots = new HashMap<>();

        availableSpots.put(VehicleType.MOTORCYCLE, new LinkedList<>());
        availableSpots.put(VehicleType.CAR, new LinkedList<>());
        availableSpots.put(VehicleType.TRUCK, new LinkedList<>());

        int mcSpots = (int) (numberOfSpots * 0.5);
        int carSpots = (int) (numberOfSpots * 0.3);
        int truckSpots = numberOfSpots - mcSpots - carSpots;

        for (int i = 0; i < mcSpots; i++) addSpot(i + 1, VehicleType.MOTORCYCLE);
        for (int i = mcSpots; i < mcSpots + carSpots; i++) addSpot(i + 1, VehicleType.CAR);
        for (int i = mcSpots + carSpots; i < numberOfSpots; i++) addSpot(i + 1, VehicleType.TRUCK);
    }

    private void addSpot(int spotNumber, VehicleType type) {
        ParkingSpot spot = new ParkingSpot(spotNumber, type);
        parkingSpots.add(spot);
        availableSpots.get(type).offer(spot);
    }

    public synchronized ParkingSpot parkVehicle(Vehicle vehicle) {
        Queue<ParkingSpot> queue = availableSpots.get(vehicle.getVehicleType());
        if (queue == null || queue.isEmpty()) return null;

        Iterator<ParkingSpot> it = queue.iterator();
        while (it.hasNext()) {
            ParkingSpot spot = it.next();
            if (spot.parkVehicle(vehicle)) {
                it.remove();
                return spot;
            }
        }
        return null;
    }

    public synchronized boolean unparkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isAvailable() && spot.getParkedVehicle().getLicensePlate().equals(vehicle.getLicensePlate())) {
                spot.leaveSpot();
                availableSpots.get(spot.getVehicleType()).offer(spot);
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        System.out.println("Level " + levelNumber + " Availability:");
        for (ParkingSpot spot : parkingSpots) {
            System.out.println("Spot " + spot.getSpotNumber() + ": " +
                    (spot.isAvailable() ? "Available for " + spot.getVehicleType() :
                            "Occupied by " + spot.getParkedVehicle().getLicensePlate()));
        }
    }
}