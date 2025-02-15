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
 * Represents a dishwasher device in a home.
 */
public class Dishwasher extends Device{
    private int detergentLevel;
    private static final int MAX_CAPACITY = 100;
    private static final int MAX_WEAR = 100;
    private static final int WEAR_OF_ONE_USING = 4;
    private boolean isEmpty = false;


    public Dishwasher(String name, InsideRoom currentRoom, TypesOfConsumption typesOfConsumption, double[] consumption) {
        super(name, currentRoom, typesOfConsumption, consumption);
        detergentLevel = MAX_CAPACITY;
    }

    @Override
    public Optional<Usable> use(Human human) {
        switch (getDeviceState()) {
            case OFF, IDLE -> {
                turnON();
            }
            case ON -> {
                decreaseDurability();
                washDishes(human.getDetergentConsumption());
                turnOFF();
                return Optional.empty();
            }
        }
        return Optional.of(this);
    }

    /**
     * Washes dishes using the dishwasher.
     * @param detergentAmount The amount of detergent to be used.
     */
    public void washDishes(int detergentAmount) {
        if (detergentAmount < getDetergentLevel()) {
            detergentLevel -= detergentAmount;
            recordEvent(new ReportInfo(EventType.WASH_DISHES, this, this, getCurrentFloor(), getCurrentRoom()));
            if (detergentLevel<= 0) {
                Alert noDetergentAlert = new Alert(AlertType.NO_PROVISIONS, this, null, getCurrentFloor(), getCurrentRoom());
                eventPublisher.updateFromAlertGenerator(noDetergentAlert);
                detergentLevel= 0;
                isEmpty = true;
            }
        } else {
            Alert noDetergentAlert = new Alert(AlertType.NO_PROVISIONS, this, null, getCurrentFloor(), getCurrentRoom());
            eventPublisher.updateFromAlertGenerator(noDetergentAlert);
        }
    }

    public int getDetergentLevel() {
        return detergentLevel;
    }

    public void recoverDetergent(int detergentAmount) {
        if (detergentAmount != 0) {
            recordEvent(new ReportInfo(EventType.DISH_WASHER_FILL_UP, this, this, getCurrentFloor(), getCurrentRoom()));
            if (detergentAmount + detergentLevel > MAX_CAPACITY) {
                detergentLevel = MAX_CAPACITY;
            } else {
                detergentLevel += detergentAmount;
            }
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
    public String toString() {
        return "Dish washer{" +
                name +
                " in " + currentRoom +
                '}';
    }
}
