package devices;

import events.EventType;
import events.ReportInfo;
import home.InsideRoom;
import resident.human.Human;
import resident.human.Usable;

import java.util.Optional;

/**
 * Represents a PlayStation device.
 */
public class PlayStation extends Device{
    private static final int TOTAL_USAGE_TIME = 3;
    private static final int WEAR_OF_ONE_USING = 3;
    private static final int MAX_WEAR = 100;

    private int curUsageTime = 0;


    public PlayStation(String name, InsideRoom currentRoom, TypesOfConsumption typesOfConsumption, double[] consumption) {
        super(name, currentRoom, typesOfConsumption, consumption);
    }

    public void playGame() {
        recordEvent(new ReportInfo(EventType.PLAY_GAME, this, this, getCurrentFloor(), getCurrentRoom()));
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
                        playGame();
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
        return "Playstation{" +
                name +
                " in " + currentRoom +
                '}';
    }
}
