package ex.parking;

import java.time.Duration;

public class FeeCalculator {

    public double calculateFee(ParkingTicket parkingTicket) {
        Duration duration = parkingTicket.getParkingDuration();
        //   ParkingSpot parkingSpot =null;
        //long hours = duration.toHours(); //It ignores the fractional part and takes only the completed hours.
        double hours = duration.toSeconds() / 3600.0;
        double hourlyRate = 0;

        switch (parkingTicket.getParkingSpot().getSize()) {
            case SMALL:
                hourlyRate = 2;
                break;

            case MEDIUM:
                hourlyRate = 4;
                break;

            case LARGE:
                hourlyRate = 6;
                break;

            default:
                throw new IllegalArgumentException("Invalid spot size");
        }
        return hours * hourlyRate;
    }

}

/*Small: $2/hour.
Medium: $4/hour.
Large: $6/hour.*/