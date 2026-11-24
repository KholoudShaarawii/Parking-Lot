package ex.parking;

import java.util.ArrayList;
import java.util.List;

//Filtering + selection
public class ParkingLevel { //It represents a single floor containing a collection of parking spots.
    private final List<ParkingSpot> parkingSpots ; //It stores all parking spots on the floor, whether they are available or occupied.

    public ParkingLevel(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        ParkingSpot bestSpot = null;

        List<ParkingSpot> availableSpots = new ArrayList<>(); //We create it inside the method to collect only the available parking spots that are suitable for the requested vehicle.
        for (ParkingSpot spots : parkingSpots) {
            if (spots.checkAvailability() && spots.canFit(vehicle)) {
                availableSpots.add(spots);
            }
        }
        //return availableSpots;
        for (ParkingSpot spot : availableSpots) { //spots? //Best So Far
//When I need to compare the current item with the best previous item → I need to remember the best so far.
            // ParkingSpot bestSpot = null; //We need `bestSpot` to retain its value throughout the loop.

            if (bestSpot == null) {
                bestSpot = spot;
            } else if (spot.getSize().ordinal() < bestSpot.getSize().ordinal()) { //ordinal
                bestSpot = spot;
            }
        }
        return bestSpot;
        //There is no data to work with yet until I initialize the object using the constructor.
    }

}

