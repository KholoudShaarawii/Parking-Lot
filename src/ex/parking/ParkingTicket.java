package ex.parking;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingTicket { //It stores the entry and exit times and calculates the parking duration.
    private String ticketId;
    Vehicle vehicle;
    ParkingSpot spot;

    LocalDateTime entryTime;
    LocalDateTime exitTime;
    /* - LocalDate → Stores a date without a time.
- LocalTime → Stores a time without a date.
- LocalDateTime → Stores both date and time, without a time zone.*/

    TicketStatus status;

    public void complete(LocalDateTime exitTime) { //Record the vehicle's exit time and mark the parking session as completed.
//If the vehicle is still parked, exitTime is null.
        /*- `this.exitTime`: The instance variable stored inside the `Ticket` object.
- `exitTime`: The new exit time passed to the method as a parameter.*/
        //Guard Clauses.
        if (this.exitTime != null) {
            throw new IllegalStateException("Ticket already completed");

        }
        if (exitTime == null || exitTime.isBefore(entryTime)) {
            throw new IllegalArgumentException("Invalid exit time");
        }

        this.exitTime = exitTime; //State-Changing Method Assignment to an instance field → Change the object's state.
        this.status = TicketStatus.COMPLETED;
    }

    public Duration getParkingDuration() {
        //Declare Outside, Assign Inside
        // Duration duration;
//Duration = Exit Time − Entry Time
        if (exitTime == null) {
            //   return; -> error with void only , here we should use exceptions not return
            throw new IllegalStateException("Vehicle has not exited yet");

        } else if (exitTime.isBefore(entryTime)) {
            throw new IllegalStateException("Exit time cannot be before entry time.");
        } else {
            return Duration.between(entryTime, exitTime);
        }
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}
