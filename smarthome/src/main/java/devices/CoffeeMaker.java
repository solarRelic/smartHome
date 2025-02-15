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
 * Represents a coffee maker device in a home.
 */
public class CoffeeMaker extends Device{
    private int coffeeLevel;
    private static final int MAX_CAPACITY = 100;
    private static final int WEAR_OF_ONE_USING = 5;
    private static final int MAX_WEAR = 100;
    private boolean isEmpty = false;

    public CoffeeMaker(String name, InsideRoom currentRoom, TypesOfConsumption typesOfConsumption, double[] consumption) {
        super(name, currentRoom, typesOfConsumption, consumption);
        coffeeLevel = MAX_CAPACITY;
    }

    /**
     * Drains a certain amount of coffee from the coffee maker.
     * @param coffeeAmount The amount of coffee to be drained.
     */
    public  void drinkCoffee(int coffeeAmount) {
        if (coffeeAmount <=  getCoffeeLevel()) {
            coffeeLevel -= coffeeAmount;
            recordEvent(new ReportInfo(EventType.DRINK_COFFEE, this, this, getCurrentFloor(), getCurrentRoom()));
            if (coffeeLevel <= 0) {
                Alert noCoffeeAlert = new Alert(AlertType.NO_PROVISIONS, this, null, getCurrentFloor(), getCurrentRoom());
                eventPublisher.updateFromAlertGenerator(noCoffeeAlert);
                coffeeLevel = 0;
                isEmpty = true;
            }
        } else {
            Alert noCoffeeAlert = new Alert(AlertType.NO_PROVISIONS, this, null, getCurrentFloor(), getCurrentRoom());
            eventPublisher.updateFromAlertGenerator(noCoffeeAlert);
            coffeeLevel = 0;
            isEmpty = true;
        }
    }

    /**
     * Refills the coffee maker with a certain amount of coffee.
     * @param coffeeAmount The amount of coffee to be added.
     */
    public void recoverCoffee(int coffeeAmount) {
        if (coffeeAmount != 0) {
            recordEvent(new ReportInfo(EventType.COFFEE_MAKER_FILL_UP, this, this, getCurrentFloor(), getCurrentRoom()));
            if (coffeeAmount + coffeeLevel > MAX_CAPACITY) {
                coffeeLevel = MAX_CAPACITY;
            } else {
                coffeeLevel += coffeeAmount;
            }
        }
    }

    public int getCoffeeLevel() {
        return coffeeLevel;
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
                drinkCoffee(human.getCoffeeConsumption());
                turnOFF();
                return Optional.empty();
            }
        }
        return Optional.of(this);
    }

    @Override
    public String toString() {
        return "Coffeemaker{ " +
                name +
                " in " + currentRoom +
                '}';
    }
}
