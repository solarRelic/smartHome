package devices;

import events.EventType;
import events.ReportInfo;
import home.InsideRoom;
import resident.human.Human;
import resident.human.Usable;

import java.util.Optional;

/**
 * Represents a TV in a home.
 */
public class TV extends Device{
    private static final int OVERALL_USE_TIME = 5;
    private static final int WEAR_OF_ONE_USING = 4;
    private static final int MAX_WEAR = 100;

    private int curUseTime = 0;

    public TV(String name, InsideRoom currentRoom, TypesOfConsumption typesOfConsumption, double[] consumption) {
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

    public void watchTV(Human human) {
        super.recordEvent(new ReportInfo(EventType.WATCHING_TV, human, this, getCurrentFloor(), getCurrentRoom()));
    }
    @Override
    public Optional<Usable> use(Human human) {
        setAvailable(false);
        switch (getDeviceState()) {
            case OFF, IDLE -> {
                turnON();
                return Optional.of(this);
            }
            case ON -> {
                if (curUseTime < OVERALL_USE_TIME) {
                    if (curUseTime == 0) {
                        watchTV(human);
                    }
                    curUseTime++;
                    return Optional.of(this);
                } else {
                    curUseTime = 0;
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
        return "TV {" +
                name +
                " in " + currentRoom +
                '}';
    }
}
