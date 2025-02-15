package devices.equipment;

import home.OutsideRoom;

/**
 * An abstract class representing a factory for creating equipment.
 */

public abstract class EquipmentFactory {
    protected OutsideRoom location;

    protected EquipmentFactory(OutsideRoom location) {
        this.location = location;
    }

    /**
     * Creates an instance of equipment with the given name.
     * @param name The name of the equipment to be created.
     * @return An instance of the created equipment.
     */
    public abstract Equipment createEquipment(String name);
}
