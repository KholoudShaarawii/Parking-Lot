package ex.parking;

public class ParkingSpot {
    private int spotId;

    private SpotSize size;

    private Vehicle parkedVehicle; //A reference to the vehicle currently occupying this parking spot.
    private VehicleType vehicleType;

    public ParkingSpot(int spotId, SpotSize size) {
        this.spotId = spotId;
        this.size = size;
        this.parkedVehicle = parkedVehicle;
        this.vehicleType = vehicleType;
    }

    public boolean isAvailable() { //This is a shorter way to write an if-else statement and retur false / true
        return parkedVehicle == null;
    }

    public boolean canFit(Vehicle vehicle) { //Suitability
        if (vehicle.getType() == VehicleType.TRUCK) {
            return size == SpotSize.LARGE;
        }
        if (vehicle.getType() == VehicleType.CAR) {
            return size == SpotSize.MEDIUM
                    || size == SpotSize.LARGE;
        }
        if (vehicle.getType() == VehicleType.MOTORCYCLE) {
            return size == SpotSize.SMALL
                    || size == SpotSize.MEDIUM
                    || size == SpotSize.LARGE;
        }
        return false;
    }

    public boolean park(Vehicle vehicle) {
        if (isAvailable() && canFit(vehicle)) {
            parkedVehicle = vehicle;
            return true;
        }

        return false;
    }

    public boolean vacate() {
        if (!isAvailable()) {
            parkedVehicle = null;
            return true;
        }
        return false;
    }

    public int getSpotId() {
        return spotId;
    }

    public SpotSize getSize() {
        return size;
    }
}