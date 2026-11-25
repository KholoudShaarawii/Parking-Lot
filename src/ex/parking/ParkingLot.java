package ex.parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    //private final List<ParkingLevel> levels = new ArrayList<>(); //empty list , levels ref refers to levels, no sonst it is initialized already
    private final List<ParkingLevel> levels; //ref only without an obj and list
    private final FeeCalculator feeCalculator;

    public ParkingLot(List<ParkingLevel> levels, FeeCalculator feeCalculator) {
        this.levels = levels;
        this.feeCalculator = feeCalculator;
    }

    public ParkingSpot findBestAvailableSpot(Vehicle vehicle) {

        ParkingSpot bestSpot = null;
        int levelNumber = 0; //

        for (ParkingLevel level : levels) {

            levelNumber++; //
            System.out.println(
                    "Searching Level " + levelNumber
            );


            ParkingSpot availableSpot = level.findAvailableSpot(vehicle);
            if (availableSpot == null) {
                continue;
            }

            if (bestSpot == null) { //the first value
                bestSpot = availableSpot;
            } else if (availableSpot.getSize().ordinal() < bestSpot.getSize().ordinal()) {
                bestSpot = availableSpot;
            }
        }
        if (bestSpot == null) { //The exception will only be thrown if no suitable parking spot is found after searching all levels.
            throw new IllegalStateException("No available parking spot");
        }
        return bestSpot;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
        ParkingSpot bestSpot = findBestAvailableSpot(vehicle);
        if (bestSpot.park(vehicle)) {
            ParkingTicket ticket = new ParkingTicket(entryTime, bestSpot, vehicle.getType());
            return ticket;
        }
        throw new IllegalStateException("Parking failed");
    }

    public double unparkVehicle(ParkingTicket ticket) {
        if (ticket == null) { // secure the input
            throw new IllegalArgumentException("Ticket cannot be null");
        }
        ticket.recordExit();
        double fee = feeCalculator.calculateFee(ticket);
        // ParkingSpot spot = ticket.getParkingSpot();

        if (!ticket.getParkingSpot().vacate()) {
            throw new IllegalStateException("Failed to vacate parking spot");
        }

        return fee;

    }
}