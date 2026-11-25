package ex.parking;

import java.util.ArrayList;
import java.util.List;

//Filtering + selection
public class ParkingLevel { //It represents a single floor containing a collection of parking spots.
    private final List<ParkingSpot> parkingSpots; //It stores all parking spots on the floor, whether they are available or occupied.


    public ParkingLevel(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        ParkingSpot bestSpot = null;

        List<ParkingSpot> availableSpots = new ArrayList<>(); //We create it inside the method to collect only the available parking spots that are suitable for the requested vehicle.

        int spotNumber = 0;

        System.out.println("----------------------------------------");

        System.out.printf("%-10s %-12s %-12s%n",
                "SPOT", "SIZE", "DECISION");

        System.out.println("----------------------------------------");

        //System.out.println("Starting best spot selection...");
        for (ParkingSpot spots : parkingSpots) {
            spotNumber++;
            //  System.out.println("Current spot " + spots);
            // System.out.println("Current spot " + spotNumber);
            // System.out.println("Current spot " + spots.getSize());
            System.out.println("Current Spot " + spotNumber
                    + " | Size: " + spots.getSize());

            if (spots.checkAvailability() && spots.canFit(vehicle)) {
                availableSpots.add(spots);
              //  System.out.println("ALLOWED");
                System.out.printf("%-10d %-12s %-12s%n",
                        spotNumber, spots.getSize(), "ALLOWED");
            } else {
                System.out.printf("%-10d %-12s %-12s%n",
                        spotNumber, spots.getSize(), "REJECTED");
            //    System.out.println("REJECTED");

            }
        }
        System.out.println("----------------------------------------");
        //return availableSpots;
        for (ParkingSpot spot : availableSpots) { //spots? //Best So Far
//When I need to compare the current item with the best previous item → I need to remember the best so far.
            // ParkingSpot bestSpot = null; //We need `bestSpot` to retain its value throughout the loop.

            if (bestSpot == null) {
                bestSpot = spot;

                System.out.println("Initial best spot: Spot "
                        + (parkingSpots.indexOf(bestSpot) + 1)
                        + " | Size: " + bestSpot.getSize());
            } else if (spot.getSize().ordinal() < bestSpot.getSize().ordinal()) { //ordinal
                bestSpot = spot;

                //   System.out.println("Best so far: "+ bestSpot);
            }
        }
        System.out.println("Best so far: " + bestSpot.getSize());

        return bestSpot;
        // System.out.println("Best so far: "+ bestSpot);
        //There is no data to work with yet until I initialize the object using the constructor.
    }

}

