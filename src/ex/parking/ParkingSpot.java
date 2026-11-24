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
/*Difference Between a Boolean Field and a Boolean Method:

 first method: boolean method + boolean field
 Second method: boolean method without boolean field

 | Question to Ask Yourself                           | Decision                                        |
| -------------------------------------------------- | ----------------------------------------------- |
| Do I need to store a separate state?               | Use a `boolean field`.                          |
| Can I determine the answer from an existing field? | Use a `boolean method` to calculate the result. |
| Do I have a field and want to return its value?    | Use a method to read the field.                 |
  */

    public boolean checkAvailability() {
        return parkedVehicle == null;
    }

    public boolean canFit(Vehicle vehicle) { //The enum defines the three possible vehicle types in the system, but it doesn't specify the type of each individual vehicle.
        //if vs else if vs no return
        if (vehicle.getType() == VehicleType.TRUCK) { //The data types must be compatible when using the == operator.
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
            //return parkedVehicle != null; //Here, we check the field's state without changing it.
            // return parkedVehicle = vehicle;
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
