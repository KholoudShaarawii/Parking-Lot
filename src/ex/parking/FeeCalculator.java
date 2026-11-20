package ex.parking;

import java.time.Duration;

public class FeeCalculator {
    /*Small: $2/hour.
    Medium: $4/hour.
    Large: $6/hour.*/

    //    Parking Fee = Billable Hours × Hourly Rate

    // switch vs if
    public double calculateFee(ParkingTicket ticket) {
        Duration duration = ticket.getParkingDuration();
        //long hours = duration.toHours(); //It ignores the fractional part and takes only the completed hours.
        double hours = duration.toSeconds() / 3600.0;
        double hourlyRate = 0;
        //double fee = 0;
//We are accessing the object that holds the information we need.

        switch (ticket.getSpot().getSize()) {
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
        //double fee = hours * hourlyRate;

      //  return fee;
        return hours * hourlyRate;
    }

}