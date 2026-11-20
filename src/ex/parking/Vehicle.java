package ex.parking;

public class Vehicle {
    private String licensePlate;
    private VehicleType type;

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public Vehicle(String licensePlate, VehicleType type) {
        if (licensePlate==null || licensePlate.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.licensePlate = licensePlate;
        if (type == null ){
            throw new IllegalArgumentException();
        }

        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

}

// it's because licensePlate is a String, while type is an enum. Therefore, I can't pass an empty String ("") as a vehicle type so i cant use isblank method