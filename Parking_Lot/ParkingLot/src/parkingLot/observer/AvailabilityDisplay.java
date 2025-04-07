package parkingLot.observer;

public class AvailabilityDisplay implements ParkingLotObserver{
    @Override
    public void update(String message) {
        System.out.println("[Observer] " + message);
    }
}
