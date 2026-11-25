package ex.parking;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingTicket { //It represents a parking session for a specific vehicle. //It stores the entry and exit times and calculates the parking duration.
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    private TicketStatus ticketStatus;
    private VehicleType vehicleType;

    private final ParkingSpot parkingSpot;

    public ParkingTicket(LocalDateTime entryTime, ParkingSpot parkingSpot, VehicleType vehicleType) {
        this.parkingSpot = parkingSpot;
        // LocalDateTime exitTime xxx
        this.entryTime = entryTime;
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

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
