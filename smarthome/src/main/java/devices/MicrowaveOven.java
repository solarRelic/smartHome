package devices;

import events.EventType;
import events.ReportInfo;
import home.InsideRoom;
import resident.human.Human;
import resident.human.Usable;

import java.util.Optional;

/**
 * Represents a Microwave Oven in a home.
 */

public class MicrowaveOven extends Device{
    private static final int TOTAL_USAGE_TIME = 9;
    private static final int WEAR_OF_ONE_USING = 4;
    private static final int MAX_WEAR = 100;

    private int curUsageTime = 0;


    public MicrowaveOven(String name, InsideRoom currentRoom, TypesOfConsumption typesOfConsumption, double[] consumption) {
        super(name, currentRoom, typesOfConsumption, consumption);
    }

    @Override
    public void decreaseDurability() {
        wearDegree += WEAR_OF_ONE_USING;
        if (wearDegree >= MAX_WEAR) {
            wearDegree = MAX_WEAR;
            breakDown();
        }
        broadcast();
    }

    public void bakeCake() {
        recordEvent(new ReportInfo(EventType.BAKE_CAKE, this, this, getCurrentFloor(), getCurrentRoom()));
    }

    /**
     * Uses the MicrowaveOven with different states, baking a cake.
     *
     * @param human The human using the device.
     * @return The MicrowaveOven if still in use, or null if done
     */
    @Override
    public Optional<Usable> use(Human human) {
        setAvailable(false);
        switch (getDeviceState()) {
            case OFF, IDLE -> {
                turnON();
                return Optional.of(this);
            }
            case ON -> {
                decreaseDurability();
                if (curUsageTime < TOTAL_USAGE_TIME) {
                    if (curUsageTime == 0)
                        bakeCake();
                    curUsageTime++;
                    return Optional.of(this);
                } else {
                    curUsageTime = 0;
                    setAvailable(true);
                    turnIdle();
                    return Optional.empty();
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "Microwave oven{" +
                name +
                " in " + currentRoom +
                '}';
    }
}
