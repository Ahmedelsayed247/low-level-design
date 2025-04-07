package parkingLot.observer;

public interface ParkingLotSubject {
    void registerObserver(ParkingLotObserver observer);
    void removeObserver(ParkingLotObserver observer);
    void notifyObservers(String message);
}
