package ex.parking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingLevel> levels = new ArrayList<>();
    private FeeCalculator feeCalculator = new FeeCalculator();

    public ParkingLot(List<ParkingLevel> levels) {
        this.levels = levels;
    }

    public void parkVehicle(Vehicle vehicle) { //Parks a vehicle in a suitable available spot
        ParkingSpot spot;
        //levels.findAvailableSpot(vehicle); // Error
        for (ParkingLevel level : levels) {
            ParkingSpot availableSpot = level.findAvailableSpot(vehicle);
            if (availableSpot != null) {
                availableSpot.park(vehicle);
                return;
            }
        }
        throw new IllegalStateException("No available parking spot");

    }

    //try to write happy and unhappy path
    public double unparkVehicle(ParkingTicket ticket) {
     //   ParkingSpot spot;
        LocalDateTime exitTime = LocalDateTime.now();
        ticket.complete(exitTime); //= ticket.complete(LocalDateTime.now());
        if (ticket == null) { //to secure the input
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        ParkingSpot spot = ticket.getSpot();

        if (spot == null || spot.isAvailable()) {
            throw new IllegalStateException("Vehicle is not parked");
        }


        //  ticket.complete(ticket.exitTime); = ticket.complete(null);

        ticket.complete(LocalDateTime.now());

        // Calculate parking fee
        double fee = feeCalculator.calculateFee(ticket);

        // Free the parking spot
        if (!spot.vacate()) {
            throw new IllegalStateException("Failed to vacate spot");
        }

        return fee;
    }

    public int getAvailableSpots() {
        int count = 0;

        for (ParkingLevel level : levels) {
            for (ParkingSpot spot : level.getSpots()) {

                if (spot.isAvailable()) {
                    count++;
                }
            }
        }

        return count;
    }
}

/* ParkingLot
   Responsibilities:
  1. Manage the parking levels.
  2. Coordinate vehicle parking and unparking.
  3. Find a suitable available spot across the levels.
  4. Issue a parking ticket after successful parking.
  5. Coordinate fee calculation when a vehicle exits.
  6. Release the occupied spot when a vehicle exits.
  7. Provide the total number of available spots.

  Note:
  ParkingLot coordinates operations and delegates
  specific tasks to the appropriate classes.
 */




