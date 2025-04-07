package parkingLot;

import parkingLot.VehicleType.Vehicle;
import parkingLot.VehicleType.VehicleType;

public class ParkingSpot {
    private final int spotNumber ;
    private final VehicleType vehicletype ;
    private Vehicle parkedVehicle  ;

    public ParkingSpot(int spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.vehicletype = vehicleType;
    }
    public synchronized boolean isAvailable() {
        return parkedVehicle == null;
    }
    public synchronized boolean parkVehicle(Vehicle vehicle) {
        if (isAvailable() && vehicle.canFitIn(this.vehicletype)) {
            this.parkedVehicle = vehicle;
            System.out.println("Vehicle with license plate " + vehicle.getLicensePlate() + " parked in spot " + spotNumber);
            return true;
        }
        return false;
    }
    public synchronized void leaveSpot() {
        if (parkedVehicle != null) {
            System.out.println("Vehicle with license plate " + parkedVehicle.getLicensePlate() + " left spot " + spotNumber);
            parkedVehicle = null;
        }
    }
    public VehicleType getVehicleType() {
        return vehicletype;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public int getSpotNumber() {
        return spotNumber;
    }



}
