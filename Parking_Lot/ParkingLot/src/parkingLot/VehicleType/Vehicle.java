package parkingLot.VehicleType;

public abstract class  Vehicle {
    protected String licensePlate;
    protected VehicleType vehicleType;

    public Vehicle(String licensePlate, VehicleType vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public boolean canFitIn(VehicleType spotType) {
        return switch (this.vehicleType) {
            case MOTORCYCLE -> true;
            case CAR -> spotType != VehicleType.MOTORCYCLE;
            case TRUCK -> spotType == VehicleType.TRUCK;
        };
    }

}
