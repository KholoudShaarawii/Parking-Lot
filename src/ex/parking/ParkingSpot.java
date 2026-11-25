package ex.parking;

public class ParkingSpot {
    private Vehicle parkedVehicle;
    private VehicleType vehicleType;
    private SpotSize size;

    public ParkingSpot( SpotSize size) {
        this.size = size;
    }
    public ParkingSpot(Vehicle parkedVehicle, VehicleType vehicleType, SpotSize size) {
        this.parkedVehicle = parkedVehicle;
        this.vehicleType = vehicleType;
        this.size = size;
    }

    public boolean checkAvailability() {
        return parkedVehicle == null;
    }

    public boolean canFit(Vehicle vehicle) {

        if (vehicle.getType() == VehicleType.TRUCK) {
            return size == SpotSize.LARGE;
        }
        if (vehicle.getType() == VehicleType.CAR) {
            return size == SpotSize.LARGE || size == SpotSize.MEDIUM;
        }
        if (vehicle.getType() == VehicleType.MOTORCYCLE) {
            return size == SpotSize.LARGE || size == SpotSize.MEDIUM || size == SpotSize.SMALL;
        }
        return false;
    }

    public boolean park(Vehicle vehicle) {
        if (checkAvailability() && canFit(vehicle)) {

            parkedVehicle = vehicle;
            return true;
        }
        return false;
    }

    public boolean vacate() {
        if (!checkAvailability()) {
            parkedVehicle = null;
            return true;
        }
        return false;
    }

    public SpotSize getSize() {
        return size;
    }

}
