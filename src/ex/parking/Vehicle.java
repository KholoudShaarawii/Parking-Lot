package ex.parking;

public class Vehicle {
    private final String licensePlate; //final is suitable because the field's value will not change throughout the object's lifetime in the system. It does not guarantee non-null or uniqueness.
    private VehicleType type;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }
}

