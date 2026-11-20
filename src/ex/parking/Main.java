
package ex.parking;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1. Create Parking Levels
        List<ParkingLevel> levels = new ArrayList<>();

        int spotId = 1;

        for (int levelNumber = 1; levelNumber <= 5; levelNumber++) {

            List<ParkingSpot> spots = new ArrayList<>();

            // 8 Small Spots
            for (int i = 0; i < 8; i++) {
                spots.add(new ParkingSpot(spotId++, SpotSize.SMALL));
            }

            // 1 Medium and 1 Large on Level 1
            if (levelNumber == 1) {
                spots.add(new ParkingSpot(spotId++, SpotSize.MEDIUM));
                spots.add(new ParkingSpot(spotId++, SpotSize.LARGE));
            } else {
                spots.add(new ParkingSpot(spotId++, SpotSize.SMALL));
                spots.add(new ParkingSpot(spotId++, SpotSize.SMALL));
            }

            levels.add(new ParkingLevel(levelNumber, spots));
        }

        // 2. Create Parking Lot
        ParkingLot parkingLot = new ParkingLot(levels);

        // 3. Create Vehicles
        Vehicle motorcycle = new Vehicle("M-001", VehicleType.MOTORCYCLE);
        Vehicle car = new Vehicle("C-001", VehicleType.CAR);
        Vehicle truck = new Vehicle("T-001", VehicleType.TRUCK);
        Vehicle secondTruck = new Vehicle("T-002", VehicleType.TRUCK);

        // 4. Check Initial Available Spots
        System.out.println("Available spots before parking: "
                + parkingLot.getAvailableSpots());

        // 5. Park Vehicles
        parkingLot.parkVehicle(motorcycle);
        System.out.println("Motorcycle parked successfully");

        parkingLot.parkVehicle(car);
        System.out.println("Car parked successfully");

        parkingLot.parkVehicle(truck);
        System.out.println("Truck parked successfully");

        // 6. Check Available Spots After Parking
        System.out.println("Available spots after parking: "
                + parkingLot.getAvailableSpots());

        // 7. Test No Suitable Spot
        try {
            parkingLot.parkVehicle(secondTruck);
            System.out.println("Second truck parked successfully");
        } catch (IllegalStateException e) {
            System.out.println("Parking failed: " + e.getMessage());
        }

        // 8. Final Available Spots
        System.out.println("Final available spots: "
                + parkingLot.getAvailableSpots());
    }
}
