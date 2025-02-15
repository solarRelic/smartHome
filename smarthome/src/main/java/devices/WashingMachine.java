package devices;

import events.Alert;
import events.AlertType;
import events.EventType;
import events.ReportInfo;
import home.InsideRoom;
import resident.human.Human;
import resident.human.Usable;

import java.util.Optional;

/**
 * Represents a washing machine in a home.
 */
public class WashingMachine extends Device{
    private int washingPowderLevel;
    private static final int MAX_CAPACITY = 100;
    private static final int WEAR_OF_ONE_USING = 6;
    private static final int MAX_WEAR = 100;

    private boolean isEmpty = false;


    public WashingMachine(String name, InsideRoom currentRoom, TypesOfConsumption typesOfConsumption, double[] consumption) {
        super(name, currentRoom, typesOfConsumption, consumption);
        washingPowderLevel = MAX_CAPACITY;
    }


    public int getWashingPowderLevel() {
        return washingPowderLevel;
    }

    /**
     * Washes clothes with the specified amount of washing powder.
     * @param powderAmount The amount of washing powder to use.
     */
    public void washClothes(int powderAmount) {
        if (powderAmount < getWashingPowderLevel()) {
            washingPowderLevel -= powderAmount;
            recordEvent(new ReportInfo(EventType.WASH_CLOTHES, this, this, getCurrentFloor(), getCurrentRoom()));
            if (washingPowderLevel <= 0) {
                Alert noPowderAlert = new Alert(AlertType.NO_PROVISIONS, this, null, getCurrentFloor(), getCurrentRoom());
                eventPublisher.updateFromAlertGenerator(noPowderAlert);
                washingPowderLevel = 0;
                isEmpty = true;
            }
        } else {
            Alert noPowderAlert = new Alert(AlertType.NO_PROVISIONS, this, null, getCurrentFloor(), getCurrentRoom());
            eventPublisher.updateFromAlertGenerator(noPowderAlert);
        }
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
        switch (getDeviceState()) {
            case OFF, IDLE -> {
                turnON();
            }
            case ON -> {
                decreaseDurability();
                washClothes(human.getWashingPowderConsumption());
                turnOFF();
                return Optional.empty();
            }
        }
        return Optional.of(this);
    }

    public void recoverPowder(int powderAmount) {
        if (powderAmount != 0) {
            recordEvent(new ReportInfo(EventType.WASHING_MACHINE_FILL_UP, this, this, getCurrentFloor(), getCurrentRoom()));
            if (washingPowderLevel + powderAmount > MAX_CAPACITY) {
                washingPowderLevel = MAX_CAPACITY;
            } else {
                washingPowderLevel += powderAmount;
            }
        }
    }

    @Override
    public String toString() {
        return "Washing machine{" +
                name +
                " in " + currentRoom +
                '}';
    }
}
