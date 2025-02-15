package devices.equipment;

import home.OutsideRoom;

/**
 * A factory for creating Bike objects with specific names.
 */
public class BikeFactory extends EquipmentFactory{
    public BikeFactory(OutsideRoom location) {
        super(location);
    }

    @Override
    public Bike createEquipment(String name) {
        return new Bike(name, location);
    }

    /**
     * Creates a new Bike with the name "FatherBike".
     * @return A new Bike object with the name "FatherBike"
     */
    public Bike createFatherBike() {
        return createEquipment("FatherBike");
    }

    /**
     * Creates a new Bike with the name "MotherBike".
     * @return A new Bike object with the name "MotherBike"
     */
    public Bike createMotherBike() {
        return createEquipment("MotherBike");
    }
}
