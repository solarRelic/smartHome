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
 * Represents a fridge device in a home.
 */
public class Fridge extends Device{
    private int foodLevel;
    private static final int MAX_CAPACITY = 100;
    private static final int MAX_WEAR = 100;
    private static final int WEAR_OF_ONE_USING = 4;
    private static final int MAX_APPETITE = 10;
    private static final int MIN_APPETITE = 0;
    private boolean isEmpty = false;


    public Fridge(String name, InsideRoom currentRoom, TypesOfConsumption typesOfConsumption, double[] consumption) {
        super(name, currentRoom, typesOfConsumption, consumption);
        foodLevel = MAX_CAPACITY;
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
                consumeFood(human.getFoodConsumption());
                return Optional.empty();
            }

        }
        return Optional.of(this);
    }

    public void consumeFood(int appetite) {
        if (appetite < getFoodLevel() && appetite > MIN_APPETITE && appetite < MAX_APPETITE) {
            foodLevel -= appetite;
            recordEvent(new ReportInfo(EventType.CONSUME_FOOD, this, null, getCurrentFloor(), getCurrentRoom()));
            if (foodLevel <= 0) {
                Alert noFoodAlert = new Alert(AlertType.NO_PROVISIONS, this, null, getCurrentFloor(), getCurrentRoom());
                eventPublisher.updateFromAlertGenerator(noFoodAlert);
                foodLevel = 0;
                isEmpty = true;
            }
        } else {
            Alert noFoodAlert = new Alert(AlertType.NO_PROVISIONS, this, null, getCurrentFloor(), getCurrentRoom());
            eventPublisher.updateFromAlertGenerator(noFoodAlert);
        }
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public void recoverFood(int foodAmount) {
        if (foodAmount != 0) {
            recordEvent(new ReportInfo(EventType.FRIDGE_FILL_UP, this, this, getCurrentFloor(), getCurrentRoom()));
            if (foodLevel + foodAmount > MAX_CAPACITY) {
                foodLevel = MAX_CAPACITY;
            } else {
                foodLevel += foodAmount;
            }
        }
    }

    @Override
    public String toString() {
        return "Fridge{" +
                name +
                " in " + currentRoom +
                '}';
    }

}
