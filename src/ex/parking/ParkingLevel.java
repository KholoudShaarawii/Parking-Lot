package ex.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel { //20 spot for each level (2)
    private int levelNumber;
    private List<ParkingSpot> spots = new ArrayList<>();

    public ParkingLevel(int levelNumber, List<ParkingSpot> spots) {
        this.levelNumber = levelNumber;
        this.spots = spots;
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        ParkingSpot bestSpot = null; //"أنا لسه مختارتش أي Spot، ولما ألاقي واحد مناسب هحتفظ بيه هنا."
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && spot.canFit(vehicle)) {//not using park method because Checking ≠ Performing an Action,in this method we already paked the car
                if (bestSpot == null) {  //I haven't looped through the remaining spots yet. //Best So Far
                    // after selecting the best spot , it will Repeat The for each again
                    bestSpot = spot;
                } else if (spot.getSize().ordinal() < bestSpot.getSize().ordinal()) {
                    bestSpot = spot;
                }
            }
        }
        return bestSpot; //after for each

    }

    public List<ParkingSpot> getAvailableSpots() {
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable()) {
                availableSpots.add(spot);
            }
        }
        return availableSpots;

    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }
}

