# Optimized Parking Lot System

## Overview

The **Optimized Parking Lot System** is a robust, object-oriented application designed to manage parking lots. It efficiently handles the parking and unparking of various vehicle types (Motorcycles, Cars, and Trucks) while tracking the availability of parking spots in real time. The system is optimized to ensure seamless operations, using object-oriented principles along with the **Observer Design Pattern** to provide notifications about parking spot availability.

## Features

- **Vehicle Types Support**: The system supports three types of vehicles: Motorcycles, Cars, and Trucks.
- **Real-time Availability**: The parking lot updates and displays available parking spots in real-time.
- **Observer Pattern**: Integrated Observer Pattern to notify subscribers (such as display screens) whenever there is a change in parking availability.
- **Dynamic Spot Assignment**: Parking spots are dynamically assigned based on vehicle type, with optimized allocation strategies:
  - 50% of spots reserved for motorcycles.
  - 30% of spots reserved for cars.
  - Remaining 20% for trucks.
- **Parking and Unparking**: Vehicles can be parked in appropriate spots based on their type and can be easily removed from the system.

## Technologies Used

- **Java**: The system is developed in Java using object-oriented programming principles.
- **Design Patterns**: The Observer Design Pattern is used for real-time updates and notifications regarding parking lot status.

## Classes

- **Vehicle**: An abstract class representing a vehicle with common properties like license plate and vehicle type.
- **Car, Motorcycle, Truck**: These classes extend `Vehicle` and represent specific vehicle types.
- **ParkingSpot**: Represents a parking spot in the parking lot. Each spot is dedicated to a specific type of vehicle (Motorcycle, Car, or Truck).
- **Level**: Represents a level of the parking lot, containing a list of parking spots and logic for parking and unparking vehicles.
- **ParkingLot**: The central class responsible for managing the entire parking lot, including levels and vehicles. Implements the **Observer Pattern** to notify changes to registered observers.
- **ParkingLotObserver**: Interface for observers that wish to be notified of changes in parking lot status.
- **AvailabilityDisplay**: Concrete observer that displays parking availability updates.

## How It Works

1. **Initialization**: The parking lot is initialized with multiple levels, each having a predefined number of parking spots. The system divides the spots based on vehicle type (Motorcycle, Car, and Truck).
2. **Park a Vehicle**: A vehicle is assigned to a parking spot based on its type. The system first checks for available spots and parks the vehicle accordingly.
3. **Unpark a Vehicle**: A parked vehicle can leave the parking spot, and the spot is marked as available again.
4. **Real-time Updates**: Whenever a vehicle parks or leaves, observers are notified, and the parking availability is updated.
5. **Observer Notifications**: An observer (such as a display screen) subscribes to the system and gets notified whenever there is a change in parking availability.

## Example Usage

```java
ParkingLot parkingLot = ParkingLot.getInstance();
parkingLot.addLevel(new Level(1, 100));
parkingLot.addLevel(new Level(2, 80));

Vehicle car = new Car("ABC123");
Vehicle truck = new Truck("XYZ789");
Vehicle motorcycle = new Motorcycle("M1234");

// Register Observer for parking availability updates
AvailabilityDisplay display = new AvailabilityDisplay();
parkingLot.registerObserver(display);

// Park vehicles
parkingLot.parkVehicle(car);
parkingLot.parkVehicle(truck);
parkingLot.parkVehicle(motorcycle);

// Display parking availability
parkingLot.displayAvailability();

// Unpark a vehicle
parkingLot.unparkVehicle(motorcycle);
