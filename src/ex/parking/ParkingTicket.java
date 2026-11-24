package ex.parking;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingTicket { //It represents a parking session for a specific vehicle. //It stores the entry and exit times and calculates the parking duration.
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    private TicketStatus ticketStatus;
    private VehicleType vehicleType;

    private final ParkingSpot parkingSpot; //Why do we keep a reference to an object inside another object?

    public ParkingTicket(LocalDateTime entryTime, ParkingSpot parkingSpot, VehicleType vehicleType) {
        this.parkingSpot = parkingSpot;
        //noooooo LocalDateTime exitTime,
        this.entryTime = entryTime; //const with initial value
        this.ticketStatus = TicketStatus.ISSUED;
        this.vehicleType = vehicleType;
    }

    public void recordExit() { //It records the exit time and updates the ticket status.
        LocalDateTime exitTime = LocalDateTime.now();
        if (exitTime.isBefore(entryTime)) {
            throw new IllegalArgumentException("Exit time cannot be null or  before entry time");
        }
        if (ticketStatus != TicketStatus.ISSUED) {
            throw new IllegalStateException("Ticket is already completed !!");
        }
        this.exitTime = exitTime;
        this.ticketStatus = TicketStatus.COMPLETED;
    }


    public Duration getParkingDuration() { //Guard Clauses.
        //Before the vehicle exits, exitTime is null.
        if (exitTime == null) {
            throw new IllegalStateException("Vehicle has not exited yet");
        }
        if (exitTime.isBefore(entryTime)) {
            throw new IllegalStateException("Exit time cannot be before entry time");
        }
        return Duration.between(entryTime, exitTime);
    }

    // The constructor initializes the ticket when the vehicle enters.
// At that moment, the exit time is unknown.
// recordExit() records the exit time and marks the ticket as COMPLETED when the vehicle leaves.

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
