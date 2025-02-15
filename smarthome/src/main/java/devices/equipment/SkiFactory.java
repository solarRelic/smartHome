package devices.equipment;

import home.OutsideRoom;

/**
 * A factory for creating Ski objects with concrete names.
 */

public class SkiFactory extends EquipmentFactory{
    public SkiFactory(OutsideRoom location) {
        super(location);
    }

    @Override
    public Ski createEquipment(String name) {
        return new Ski(name, location);
    }

    /**
     * Creates a new Ski with the name "FatherSki".
     * @return A new Ski object with the name "FatherSki".
     */
    public Ski createFatherSki() {
        return createEquipment("FatherSki");
    }

    /**
     * Creates a new Ski with the name "MotherSki".
     * @return A new Ski object with the name "MotherSki".
     */
    public Ski createMotherSki() {
        return createEquipment("MotherSki");
    }
}
