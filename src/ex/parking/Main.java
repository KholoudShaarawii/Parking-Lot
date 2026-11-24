
package ex.parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Spots -> levels -> ParkingLot -> issue a Ticket -> End the Parking Session
        // levels here ,System Configuration  //VehicleType type = new VehicleType(); // Compile Error

        //System Setup / System Configuration،
        System.out.println("Parking System Started");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle type: ");
        String input = scanner.nextLine();
        System.out.println("You entered: " + input);

        List<ParkingLevel> parkingLevels = new ArrayList<>(); // for all levels

        for (int level = 1; level <= 5; level++) {

            List<ParkingSpot> spots = new ArrayList<>();

            for (int spot = 1; spot <= 20; spot++) { // 5 small , 10 medium , 5 large
                SpotSize size;
                if (spot <= 5) {
                    size = SpotSize.SMALL;
                } else if (spot <= 15) {
                    size = SpotSize.MEDIUM;
                } else {
                    size = SpotSize.LARGE;
                }
                ParkingSpot parkingSpot = new ParkingSpot(size);
                spots.add(parkingSpot);
            }
            ParkingLevel parkingLevel = new ParkingLevel(spots);
            parkingLevels.add(parkingLevel);
        }

//Object Wiring
        FeeCalculator feeCalc = new FeeCalculator();

        ParkingLot parkingLot = new ParkingLot(parkingLevels, feeCalc); //ParkingLot parkingLot = new ParkingLot(feeCalc,parkingLevels);
        // FeeCalculator feeCalc = new FeeCalculator(); Xxx
        VehicleType vehicleType = VehicleType.valueOf(input.toUpperCase());

        Vehicle vehicle = new Vehicle("ABC123", vehicleType);

        ParkingSpot spot = parkingLot.findBestAvailableSpot(vehicle);
        System.out.println("Best available spot found" + " " + spot.getSize()); //why getsize

        LocalDateTime entryTime = LocalDateTime.of(2026, 11, 23 , 10, 0);

      //  ParkingTicket ticket = new ParkingTicket(entryTime, spot, vehicleType);


        ParkingTicket parkingTicket = parkingLot.parkVehicle(vehicle, entryTime);
        System.out.println("Vehicle parked successfully");
        System.out.println("Parking ticket issued");

        System.out.println("After a period of time");

       // parkingTicket.recordExit(); executed one

        ParkingSpot parkedSpot = parkingTicket.getParkingSpot();
        // System.out.println("parked spot is" + parkedSpot);xxxx
        System.out.println("parked spot is" + " " + parkedSpot.getSize());

        double fee = parkingLot.unparkVehicle(parkingTicket);

        System.out.println("Parking Fee: " + fee);

        Duration duration = parkingTicket.getParkingDuration();
        System.out.println("Parking Duration: " + duration);

        //double fee2 = parkingLot.unparkVehicle(parkingTicket);

    }
}