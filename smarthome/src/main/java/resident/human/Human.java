package resident.human;

import devices.Device;
import devices.equipment.Equipment;
import events.EventType;
import events.ReportInfo;
import resident.Resident;

import java.util.List;
import java.util.Random;

/**
 * Represents a human resident in a home.
 */
public abstract class Human extends Resident {
    private static final int FOOD_CONSUMPTION = 12;
    private static final int WASHING_POWDER_CONSUMPTION = 8;
    private static final int DETERGENT_CONSUMPTION = 5;
    private static final int COFFEE_CONSUMPTION = 3;



    protected Human(String name) {
        super(name);
    }

    public void nextIteration() {
        if (usedBy == null) {
            act();
        } else {
            usedBy = usedBy.map(device -> device.use(this)).orElse(null);
        }
        isAvailable = true;
    }

    /**
     * Performs an action based on availability and randomness.
     */
    public void act() {
        if (isAvailable) {
            if (getRandomNumberBetween(0, 100) <=  50) {
                List<Device> devices = home.getDeviceList()
                        .stream()
                        .filter(device -> !device.isBroken() && device.isAvailable())
                        .toList();
                int availableDevicesAmount = devices.size();
                if (availableDevicesAmount > 0) {
                    Device randDevice = devices.get(new Random().nextInt(availableDevicesAmount));
                    useDevice(randDevice);
                }
            } else {
                List<Equipment> equipments = home.getEquipment()
                        .stream()
                        .filter(Equipment::isAvailable)
                        .toList();
                int availableEqpsAmount = equipments.size();
                if (availableEqpsAmount > 0) {
                    Equipment eqp  = equipments.get(new Random().nextInt(availableEqpsAmount));
                    useEquipment(eqp);
                }
            }
            isAvailable = false;
        }
    }

    public int getRandomNumberBetween(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void useDevice(Device device) {
        if (device != null) {
            super.recordEvent(new ReportInfo(EventType.DEVICE_USAGE, this, device, getFloor(),currentLocation));
            usedBy = device.use(this);
        }
    }

    public void useEquipment(Equipment equipment) {
        if (equipment != null && isAvailable) {
            recordEvent(new ReportInfo(EventType.EQUIPMENT_USAGE, this, equipment, getFloor(), currentLocation));
            usedBy = equipment.use(this);
        }
    }

    public int getFoodConsumption() {
        return FOOD_CONSUMPTION;
    }

    public int getWashingPowderConsumption() {
        return WASHING_POWDER_CONSUMPTION;
    }

    public int getDetergentConsumption() {
        return DETERGENT_CONSUMPTION;
    }

    public int getCoffeeConsumption() {
        return COFFEE_CONSUMPTION;
    }
}
